package dk.offlines.tfwsvendborg.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dk.offlines.tfwsvendborg.data.UserRepository

class LoginViewModel(app: Application): AndroidViewModel(app) {
    private val dataRepository = UserRepository(app)
    val userData = dataRepository.userData

    fun login(username: String, password: String) {
        dataRepository.login(username, password)
    }
}