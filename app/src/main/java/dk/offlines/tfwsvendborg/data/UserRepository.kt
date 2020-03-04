package dk.offlines.tfwsvendborg.data

import android.app.Application
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository(val app: Application) {
    val userData = MutableLiveData<User>()
    private val userDao = UserDatabase.getDatabase(app).userDao()

    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = userDao.getUser(username, password)
            userData.postValue(data)
        }
    }

    fun register(username: String, password: String, firstname: String, lastname: String,
                 address: String, birthdate: String, email: String, phoneNr: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val exist = userDao.checkUser(username, email)
            if(!exist) {
                val user = User(username, password, firstname, lastname, address, birthdate, phoneNr, email)
                userDao.insertUser(user)
                userData.postValue(user)
            } else {
                userData.postValue(null)
            }
        }
    }
}