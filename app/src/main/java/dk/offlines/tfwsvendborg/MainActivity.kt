package dk.offlines.tfwsvendborg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)


//        mainButtonLogin.setOnClickListener {
//            var username = username.text.toString()
//            var password = password.text.toString()
//            val dataRepository = UserRepository(applicationContext)
//            dataRepository.login(username, password)
//
//            val userdata = dataRepository.userData
//
//            if(userdata != null){
//                val intent = Intent(this, ProfileActivity::class.java)
//                startActivity(intent)
//            } else {
//
//            }
//
//
//        }
//        mainButtonRegisterNewUser.setOnClickListener {
//            val intent = Intent (this, RegisterActivity::class.java)
//            startActivity(intent)
//        }


    }


}
