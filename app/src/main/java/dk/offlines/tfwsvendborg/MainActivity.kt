package dk.offlines.tfwsvendborg

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mainButtonLogin.setOnClickListener {
            var userName = username.text.toString()
            var password = password.text.toString()

            if(userName == "Test" && password == "Test") {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            } else {
                val toast = Toast.makeText(applicationContext, "Error wrong username or password", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.TOP,0,450)
                toast.show()

            }

        }
        mainButtonRegisterNewUser.setOnClickListener {
            val intent = Intent (this, RegisterActivity::class.java)
            startActivity(intent)
        }


    }


}
