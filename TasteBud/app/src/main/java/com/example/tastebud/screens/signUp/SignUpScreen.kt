package com.example.tastebud.screens.signUp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tastebud.R
import com.example.tastebud.screens.SharedViewModel
import com.example.tastebud.ui.theme.Inter
import com.example.tastebud.ui.theme.TasteBudBackground
import com.example.tastebud.ui.theme.TasteBudDarkGreen
import com.example.tastebud.ui.theme.TasteBudGreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    SignUpContent(navController, sharedViewModel)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(navController: NavController, sharedViewModel: SharedViewModel) {
    val auth = Firebase.auth
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
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
        val fullName = remember { mutableStateOf(TextFieldValue()) }
        val emailValue = remember { mutableStateOf(TextFieldValue()) }
        val passwordValue = remember { mutableStateOf(TextFieldValue()) }

        // State variable to track whether all fields are filled
        var allFieldsFilled by remember { mutableStateOf(false) }

        // Update the allFieldsFilled variable whenever any field changes
        LaunchedEffect(fullName.value, emailValue.value, passwordValue.value) {
            allFieldsFilled =
                fullName.value.text.isNotBlank() && emailValue.value.text.isNotBlank() && passwordValue.value.text.isNotBlank()
        }

        Text(
            text = "Create New Account",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontFamily = Inter
        )
        OutlinedTextField(
            label = {
                Text(text = "Full Name", style = MaterialTheme.typography.bodyLarge)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            value = fullName.value,
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onValueChange = {
                fullName.value = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(30.dp),
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                cursorColor = TasteBudDarkGreen,
                focusedBorderColor = TasteBudDarkGreen,
                focusedLeadingIconColor = TasteBudDarkGreen,
                unfocusedBorderColor = Color.Gray,
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
                focusedLeadingIconColor = TasteBudDarkGreen,
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
                focusedLeadingIconColor = TasteBudDarkGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = TasteBudDarkGreen
            ),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), colors = ButtonDefaults.buttonColors(
            containerColor = TasteBudGreen,
            contentColor = Color.White,
        ), onClick = {
            if (allFieldsFilled) {
                if (!isValidEmail(emailValue.value.text)) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Please enter a valid email address.",
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short,
                        )
                    }
                } else if (passwordValue.value.text.length < 6) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Password must be at least 6 characters.",
                            actionLabel = "Dismiss",
                            duration = SnackbarDuration.Short,
                        )
                    }
                } else {
                    auth.createUserWithEmailAndPassword(
                        emailValue.value.text.trim(), passwordValue.value.text.trim()
                    ).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d("AUTH", "Sign Up Success!")
                            val user = auth.currentUser
                            if (user != null) {
                                sharedViewModel.addUserId(user)
                                Log.d("AUTH", "${sharedViewModel.userId?.email}")
                                val db = Firebase.firestore
                                val newUser = hashMapOf(
                                    "email" to user.email,
                                    "fullName" to fullName.value.text.trim(),
                                    "dietaryRestrictions" to emptyList<String>(),
                                    "minsCooked" to 0,
                                    "completedCount" to 0,
                                    "startedCount" to 0
                                )
                                db.collection("Users").document(user.uid).set(newUser).addOnSuccessListener {
                                        Log.d(
                                            "USERS_DB",
                                            "DocumentSnapshot successfully written!"
                                        )
                                    }.addOnFailureListener { e -> Log.w("USERS_DB", "Error writing document", e) }
                            }
                            navController.navigate("UserDietaryRestrictions")
                        } else {
                            Log.d("AUTH", "Sign Up Failed: ${task.exception}")
                            // Handle sign-up failure here
                            if (task.exception is FirebaseAuthUserCollisionException) {
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "User with this email already exist.",
                                        actionLabel = "Dismiss",
                                        duration = SnackbarDuration.Short,
                                    )
                                }
                            }
                        }
                    }
                }
            } else {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = "Please fill all fields.",
                        actionLabel = "Dismiss",
                        duration = SnackbarDuration.Short,
                    )
                }
            }
        }) {
            Text(text = "Sign Up", fontWeight = FontWeight.Black, fontFamily = Inter, fontSize = 20.sp)
        }
        SnackbarHost(hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            snackbar = {
                Snackbar(
                    snackbarData = it, containerColor = Color.hsl(0f, 1f, 0.64f)
                )
            })
        ClickableText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = AnnotatedString("Already have an account? ") + AnnotatedString(
                text = "Login",
                spanStyle = androidx.compose.ui.text.SpanStyle(
                    color = TasteBudGreen, // Set the color to green
                    fontWeight = FontWeight.Bold,
                ),
            ),
            onClick = {
                navController.navigate("signInScreen")
            },
        )
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    return email.matches(emailRegex.toRegex())
}
