package dk.offlines.tfwsvendborg.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val app: Application) {
    private val userDao = UserDatabase.getDatabase(app).userDao()

    fun saveUser(user: User) {
        userDao.saveUser(user)
    }

    fun getUser(): User {
        return userDao.getUser()
    }

    fun removeUser() {
        userDao.removeUser()
    }

    fun updateRecords(username: String, pull_ups: Int, knee_graps: Int) {
        userDao.updateRecords(username, pull_ups, knee_graps)
    }
}