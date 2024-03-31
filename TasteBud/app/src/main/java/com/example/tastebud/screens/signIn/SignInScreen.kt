package com.example.tastebud.screens.home

import NavBarScaffold
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tastebud.R
import com.example.tastebud.components.HeadingTextComponent
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudDarkGreen
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignInScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    SignInContent(navController, sharedViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInContent(navController: NavController, sharedViewModel: SharedViewModel) {
    val auth = Firebase.auth
    Column(
        modifier = Modifier.background(TasteBudBackground).fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            painterResource(R.drawable.tastebud),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        val emailValue = remember { mutableStateOf(TextFieldValue()) }
        val passwordValue = remember { mutableStateOf(TextFieldValue()) }

        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = Inter
        )

        OutlinedTextField(
            label = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            value = emailValue.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onValueChange = {
                emailValue.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp)
                )
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
                Text("Password")
            },
            textStyle = MaterialTheme.typography.bodyMedium,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            value = passwordValue.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onValueChange = {
                passwordValue.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Security,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                cursorColor = TasteBudDarkGreen,
                focusedBorderColor = TasteBudDarkGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = TasteBudDarkGreen
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth().padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TasteBudGreen,
                contentColor = Color.White
            ),
            onClick = {
                auth.signInWithEmailAndPassword(
                    emailValue.value.text.trim(),
                    passwordValue.value.text.trim()
                )
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            Log.d("AUTH", "Login Success!")
                            val user = auth.currentUser
                            if (user != null) {
                                sharedViewModel.addUser(user)
                                Log.d("AUTH", "${sharedViewModel.user?.email}")
                            }
                            navController.navigate("profileScreen")
                        } else {
                            Log.d("AUTH", "Login Failed: ${task.exception}")
                        }
                    }
            }) {
            Text(text = "Login")
        }
        ClickableText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = AnnotatedString("Don't have an account yet? ") + AnnotatedString(
                text = "Register",
                spanStyle = androidx.compose.ui.text.SpanStyle(
                    color = TasteBudGreen, // Set the color to green
                    fontWeight = FontWeight.Bold,
                ),
            ),
            onClick = {
                navController.navigate("signUpScreen")
            }
        )
    }
}
