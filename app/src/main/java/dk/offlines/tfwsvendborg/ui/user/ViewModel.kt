package dk.offlines.tfwsvendborg.ui.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.data.UserRepository

class ViewModel(app: Application): AndroidViewModel(app) {
    private val dataRepository = UserRepository(app)

    fun getUser(): User {
        return dataRepository.getUser()
    }

    fun removeUser() {
        dataRepository.removeUser()
    }

    fun saveUser(user: User) {
        return dataRepository.saveUser(user)
    }

    fun updateRecords(username: String, pull_ups: Int, knee_graps: Int) {
        dataRepository.updateRecords(username, pull_ups, knee_graps)
    }
}