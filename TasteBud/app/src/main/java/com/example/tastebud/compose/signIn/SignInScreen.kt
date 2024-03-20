package com.example.tastebud.compose.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.compose.SharedViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignInScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    NavBarScaffold(navController) { SignInContent(navController, it, sharedViewModel) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInContent(navController: NavController, innerPadding: PaddingValues, sharedViewModel: SharedViewModel) {
    val auth = Firebase.auth
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val emailValue = remember { mutableStateOf(TextFieldValue()) }
        val passwordValue = remember { mutableStateOf(TextFieldValue()) }

        OutlinedTextField(
            label = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = emailValue.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                emailValue.value = it
            }
        )
        OutlinedTextField(
            label = {
                Text("Password")
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = passwordValue.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                passwordValue.value = it
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                auth.createUserWithEmailAndPassword(
                    emailValue.value.text.trim(),
                    passwordValue.value.text.trim()
                )
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d("AUTH", "Success!")
                        } else {
                            Log.d("AUTH", "Failed: ${task.exception}")
                        }
                    }
            }) {
            Text(text = "Register")
        }
    }
}
