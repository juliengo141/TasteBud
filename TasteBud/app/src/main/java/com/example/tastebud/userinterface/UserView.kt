package org.example.userinterface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.tastebud.controller.UserController

enum class ViewEvent {
    SearchInputEvent,
    GetRecipeEvent,
    ResetEvent
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserView(userViewModel: UserViewModel, userController: UserController) {
    val viewModel by remember { mutableStateOf(userViewModel) }
    val controller by remember { mutableStateOf(userController) }

    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        TextField(
            viewModel.firstname.value,
            label = {Text("Search: ")},
            onValueChange = { controller.invoke(ViewEvent.SearchInputEvent, it) })

        Row (horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                controller.invoke(ViewEvent.GetRecipeEvent, null)
            }) {
                Text("Search")
            }
            Button(onClick = {
                controller.invoke(ViewEvent.ResetEvent, null)
            }) {
                Text("Reset")
            }
        }
    }
}