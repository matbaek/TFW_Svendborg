package dk.offlines.tfwsvendborg.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dk.offlines.tfwsvendborg.data.UserRepository

class RegisterViewModel(app: Application): AndroidViewModel(app) {
    private val dataRepository = UserRepository(app)
    val userData = dataRepository.userData

    fun register(username: String, password: String, firstname: String, lastname: String,
                 address: String, birthdate: String, email: String, phoneNr: Int) {
        dataRepository.register(username, password, firstname, lastname,
            address, birthdate, email, phoneNr)
    }
}