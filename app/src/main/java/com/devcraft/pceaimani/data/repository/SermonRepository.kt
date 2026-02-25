package com.devcraft.pceaimani.data.repository

import com.devcraft.pceaimani.data.model.Sermon
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

object SermonRepository {

    // In-memory cached state of sermons, updated by a snapshot listener when active
    private val _sermonsState = MutableStateFlow<List<Sermon>>(emptyList())
    val sermonsState = _sermonsState.asStateFlow()

    // Listener management
    private var listenerRegistration: ListenerRegistration? = null
    private val listenerLock = Any()
    private var subscribers = 0

    // delayed shutdown
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    private var shutdownTask: ScheduledFuture<*>? = null

    private fun startListenerIfNeeded() {
        synchronized(listenerLock) {
            // cancel pending shutdown if any
            shutdownTask?.cancel(false)
            shutdownTask = null

            if (listenerRegistration != null) return
            val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            listenerRegistration = firestore.collection("Sermons")
                .orderBy("datePreached", Query.Direction.DESCENDING)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        // keep previous cache; optionally log
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val sermons = snapshot.documents.mapNotNull { document ->
                            val sermon = document.toObject(Sermon::class.java)
                            sermon?.copy(id = document.id)
                        }
                        _sermonsState.value = sermons
                    }
                }
        }
    }

    private fun scheduleStopListener() {
        synchronized(listenerLock) {
            shutdownTask?.cancel(false)
            shutdownTask = scheduler.schedule({
                stopListenerIfNeeded()
            }, 5, TimeUnit.SECONDS)
        }
    }

    private fun stopListenerIfNeeded() {
        synchronized(listenerLock) {
            listenerRegistration?.remove()
            listenerRegistration = null
        }
    }

    // Return a Flow that emits cached sermons immediately and ensures listener lifecycle
    fun getSermons(): Flow<List<Sermon>> = flow {
        // increment subscribers and start listener on first
        synchronized(listenerLock) {
            subscribers++
            if (subscribers == 1) startListenerIfNeeded()
        }

        try {
            emitAll(sermonsState)
        } finally {
            synchronized(listenerLock) {
                subscribers--
                if (subscribers <= 0) {
                    subscribers = 0
                    // schedule delayed stop to avoid rapid attach/detach
                    scheduleStopListener()
                }
            }
        }
    }

    // Return matching sermon from the live cached flow; ensures listener lifecycle via getSermons()
    fun getSermonById(id: String): Flow<Sermon?> = getSermons().map { list ->
        list.find { it.id == id }
    }
}