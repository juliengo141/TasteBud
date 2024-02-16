package org.example.model

class UserModel : IPresenter() {
    var searchInput: String = ""
        set(value) {
            field = value
            notifySubscribers()
        }
}