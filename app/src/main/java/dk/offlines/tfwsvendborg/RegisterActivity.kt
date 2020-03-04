package dk.offlines.tfwsvendborg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dk.offlines.tfwsvendborg.data.UserRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        buttonRegisterCreateNewUser.setOnClickListener {
            var username = "test"
            var password = "test"
            var firstname = registerFirstname.text.toString()
            var lastname = registerLastname.text.toString()
            var address = registerAddress.text.toString()
            var birthdate = registerBirthdate.text.toString()
            var email = registerEmail.text.toString()
            var phoneNr = registerPhoneNumber.text.toString().toInt()



            Log.i("TestUser", "1")
            val dataRepository = UserRepository(applicationContext)
            dataRepository.register(username, password, firstname, lastname, address, birthdate,
                email, phoneNr)
            val userdata = dataRepository.userData
            Log.i("TestUser", "2")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
