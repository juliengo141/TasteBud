package org.example.userinterface

import androidx.compose.runtime.mutableStateOf
import org.example.model.UserModel

class UserViewModel(val model: UserModel) : ISubscriber {
    var firstname = mutableStateOf("")

    init {
        model.subscribe(this)
    }

    override fun update() {
        firstname.value = model.searchInput
    }
}