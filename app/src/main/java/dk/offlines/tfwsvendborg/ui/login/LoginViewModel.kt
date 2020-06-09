package dk.offlines.tfwsvendborg.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.data.UserRepository

class LoginViewModel(app: Application): AndroidViewModel(app) {
    private val dataRepository = UserRepository(app)

    fun getUser(): User {
        return dataRepository.getUser()
    }

    fun saveUser(user: User) {
        dataRepository.saveUser(user)
    }
}