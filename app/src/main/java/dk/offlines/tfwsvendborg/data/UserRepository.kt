package dk.offlines.tfwsvendborg.data

import android.app.Application
import android.content.Context
import android.service.autofill.UserData
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository(val context: Context): AppCompatActivity() {
    val userData = MutableLiveData<User>()
    val userDao = UserDatabase.getDatabase(context).userDao()

    fun login(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val data = userDao.getUser(username, password)
            if (data.equals(null)){
                withContext(Dispatchers.Main){
                    val toast = Toast.makeText(applicationContext, "Error wrong username or password", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.TOP,0,200)
                    toast.show()
                }
            } else{
                userData.postValue(data)
            }

        }
    }
    fun register(username: String, password: String, firstname: String, lastname: String,
                 address: String, birthdate: String, email: String, phoneNr: Int){
        CoroutineScope(Dispatchers.IO).launch {
            userData.value = User(1, username, password, firstname, lastname, address, birthdate, phoneNr, email)
            userDao.insertUser(userData.value!!)
        }
    }
}