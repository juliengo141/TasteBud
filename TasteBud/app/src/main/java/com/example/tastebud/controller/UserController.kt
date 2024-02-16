package com.example.tastebud.controller

import org.example.model.UserModel
import org.example.userinterface.ViewEvent

class UserController(val model: UserModel) {
    // we can cast `Any` later since each event has an associated type
    fun invoke(event: ViewEvent, value: Any?) {
        when(event) {
            ViewEvent.SearchInputEvent -> model.searchInput = value as String
            ViewEvent.GetRecipeEvent -> {
                // todo: call recipe api
            }
            ViewEvent.ResetEvent -> {
                model.searchInput = ""
                // todo: clear recipe displayed on screen
            }
        }
    }
}