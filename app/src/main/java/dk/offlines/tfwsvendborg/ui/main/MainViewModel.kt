package dk.offlines.tfwsvendborg.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.data.UserRepository

class MainViewModel(app: Application): AndroidViewModel(app) {
    private val dataRepository = UserRepository(app)
    val userData = dataRepository.userData

    fun getUser(): User {
        return dataRepository.getUser()
    }

    fun removeUser() {
        dataRepository.removeUser()
    }
}