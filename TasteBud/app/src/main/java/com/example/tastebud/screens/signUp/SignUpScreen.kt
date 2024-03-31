package com.example.tastebud.screens.signUp

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.components.HeadingTextComponent
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudDarkGreen
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen(navController: NavController, sharedViewModel: SharedViewModel) {
     SignUpContent(navController, sharedViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(navController: NavController, sharedViewModel: SharedViewModel) {
    val auth = Firebase.auth
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        val firstName = remember { mutableStateOf(TextFieldValue()) }
        val lastName = remember { mutableStateOf(TextFieldValue()) }
        val emailValue = remember { mutableStateOf(TextFieldValue()) }
        val passwordValue = remember { mutableStateOf(TextFieldValue()) }

        HeadingTextComponent("Create an Account")
        OutlinedTextField(
            label = {
                Text(text="First Name", style = MaterialTheme.typography.bodyLarge)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = firstName.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                firstName.value = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.Black,
            cursorColor = TasteBudDarkGreen,
            focusedBorderColor = TasteBudDarkGreen,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = TasteBudDarkGreen
        ),
        )
        OutlinedTextField(
            label = {
                Text( text ="Last Name", style = MaterialTheme.typography.bodyLarge )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = lastName.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                lastName.value = it
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Change text color
                cursorColor = TasteBudDarkGreen, // Change cursor color
                focusedBorderColor = TasteBudDarkGreen, // Change border color when focused
                unfocusedBorderColor = Color.Gray, // Change border color when not focused
                focusedLabelColor = TasteBudDarkGreen
            ),
        )
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
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black, // Change text color
                cursorColor = TasteBudDarkGreen, // Change cursor color
                focusedBorderColor = TasteBudDarkGreen, // Change border color when focused
                unfocusedBorderColor = Color.Gray, // Change border color when not focused
                focusedLabelColor = TasteBudDarkGreen
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = TasteBudGreen,
                contentColor = Color.White
            ),

            onClick = {
                auth.createUserWithEmailAndPassword(
                    emailValue.value.text.trim(),
                    passwordValue.value.text.trim()
                )
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d("AUTH", "Sign Up Success!")
                            val user = auth.currentUser
                            if (user != null) {
                                sharedViewModel.addUser(user)
                                Log.d("AUTH", "${sharedViewModel.user?.email}")
                            }
                            navController.navigate("profileScreen")
                        } else {
                            Log.d("AUTH", "Sign Up Failed: ${task.exception}")
                        }
                    }
            }) {
            Text(text = "Register")
        }
        ClickableText(
            text = AnnotatedString("Already have an account? Login"),
            onClick = {
                navController.navigate("signInScreen")
            }
        )
    }
}

