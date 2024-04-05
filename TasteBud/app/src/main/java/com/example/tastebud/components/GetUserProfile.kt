package com.example.tastebud.components

import android.util.Log
import androidx.compose.runtime.Composable
import com.example.tastebud.data.User
import com.example.tastebud.screens.SharedViewModel
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun GetUserProfile(sharedViewModel: SharedViewModel) {
    val documentId = sharedViewModel.userId?.uid
    Log.d("USERID", "${documentId}")
    var currUser: User

    val db = FirebaseFirestore.getInstance()
    val docRef = documentId?.let { db.collection("Users").document(it) }
    Log.d("DOCREF", "${docRef}")

    if (docRef != null) {
        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    currUser = User(
                        documentId,
                        (document.data?.get("email")).toString(),
                        (document.data?.get("fullName")).toString(),
                        (document.data?.get("dietaryRestrictions")) as List<String>,
                        (document.data?.get("minsCooked")) as Long,
                        (document.data?.get("completedCount")) as Long,
                        (document.data?.get("startedCount")) as Long
                    )
                    Log.d("CURRUSERID", "currUser: ${currUser}")
                    sharedViewModel.addUser(currUser)
                } else {
                    Log.d("ERROR", "No such document")
                    println("No such document")
                }
            }
            .addOnFailureListener { exception ->
                println("Error getting documents: $exception")
            }
    }
}
