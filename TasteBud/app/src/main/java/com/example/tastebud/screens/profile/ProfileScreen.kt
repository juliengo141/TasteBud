package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.screens.SharedViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

//val db = Firebase.firestore
//db.collection("users")
//.get()
//.addOnSuccessListener { result ->
//    for (document in result) {
//        Log.d(TAG, "${document.id} => ${document.data}")
//    }
//}
//.addOnFailureListener { exception ->
//    Log.w(TAG, "Error getting documents.", exception)
//}
private const val TAG = "Firestore"

@Composable
fun FetchDocumentById(navController: NavController, documentId: String) {
    val db = remember { FirebaseFirestore.getInstance() }

    // This effect will execute once when the composable is first displayed
    LaunchedEffect(true) {
        try {
            val documentSnapshot = db.collection("users").document(documentId).get().await()
            if (documentSnapshot.exists()) {
                val title = documentSnapshot.getString("title")
                Log.d(TAG, "Title of document with ID $documentId: $title")
                // Now you can use 'title' to display it in your UI or perform any other action
            } else {
                Log.d(TAG, "Document with ID $documentId does not exist")
            }
        } catch (e: Exception) {
            Log.w(TAG, "Error getting document.", e)
        }
    }
}

@Composable
fun ProfileScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController) { ProfileContent(navController, it) }
}

@Composable
fun ProfileContent(navController: NavController, innerPadding: PaddingValues) {
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = """
                    This is the PROFILESCREEN
                """.trimIndent(),
        )
        //FetchDocumentById(navController, "0YIzcg6o4YIOWRYMukxA")


    }
}
