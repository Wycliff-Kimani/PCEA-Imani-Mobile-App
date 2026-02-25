package com.devcraft.pceaimani.data.repository

import com.devcraft.pceaimani.data.model.Sermon
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map

object SermonRepository {

    // In-memory cached state of sermons, updated by a single snapshot listener
    private val _sermonsState = MutableStateFlow<List<Sermon>>(emptyList())
    val sermonsState = _sermonsState.asStateFlow()

    init {
        // Instantiate firestore locally to avoid holding a static Context reference
        val firestore = com.google.firebase.firestore.FirebaseFirestore.getInstance()

        // Attach a single snapshot listener to keep the cache updated
        firestore.collection("Sermons")
            .orderBy("datePreached", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    // keep the previous cached value
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

    // Return the cached sermons as a Flow (immediate emission)
    fun getSermons(): Flow<List<Sermon>> = sermonsState

    // Return matching sermon from the cache; updates when cache updates
    fun getSermonById(id: String): Flow<Sermon?> = sermonsState.map { list ->
        list.find { it.id == id }
    }
}