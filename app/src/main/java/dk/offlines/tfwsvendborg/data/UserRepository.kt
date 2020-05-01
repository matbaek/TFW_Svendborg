package dk.offlines.tfwsvendborg.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val app: Application) {
    val userData = MutableLiveData<User>()
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
}