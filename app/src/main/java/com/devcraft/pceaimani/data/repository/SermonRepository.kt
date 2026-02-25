package com.devcraft.pceaimani.data.repository


import com.devcraft.pceaimani.data.model.Sermon
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.firebase.firestore.Query

class SermonRepository {

    private val firestore = FirebaseFirestore.getInstance()

    fun getSermons(): Flow<List<Sermon>> = callbackFlow {
        val listener = firestore.collection("Sermons")
            .orderBy("datePreached", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->

                if (error != null) {
                    close(error)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val sermons = snapshot.documents.mapNotNull { document ->
                        val sermon = document.toObject(Sermon::class.java)
                        sermon?.copy(id = document.id)
                    }

                    trySend(sermons)
                }
            }

        awaitClose { listener.remove() }
    }

    fun getSermonById(id: String): Flow<Sermon?> = callbackFlow {
        val docRef = firestore.collection("Sermons").document(id)
        val listener = docRef.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val sermon = snapshot?.toObject(Sermon::class.java)?.copy(id = snapshot.id)
            trySend(sermon)
        }
        awaitClose { listener.remove() }
    }
}