package dk.offlines.tfwsvendborg

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import dk.offlines.tfwsvendborg.data.UserRepository

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        mainButtonLogin.setOnClickListener {
            var username = username.text.toString()
            var password = password.text.toString()
            Log.i("TestUser", "1")
            val dataRepository = UserRepository(applicationContext)
            dataRepository.login(username, password)
            val userdata = dataRepository.userData
            Log.i("TestUser", "2")
            if(!userdata.equals(null)){
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }


        }
        mainButtonRegisterNewUser.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }


}
