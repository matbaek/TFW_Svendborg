@file:Suppress("DEPRECATION")

package dk.offlines.tfwsvendborg.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dk.offlines.tfwsvendborg.LoginActivity
import dk.offlines.tfwsvendborg.MainActivity
import dk.offlines.tfwsvendborg.R
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragmentOld: Fragment() {
//    private lateinit var navController: NavController
//
//    private lateinit var viewModel: LoginViewModel
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        (requireActivity() as AppCompatActivity).run {
//            supportActionBar?.setDisplayHomeAsUpEnabled(false)
//        }
//        setHasOptionsMenu(true)
//
//        val view =  inflater.inflate(R.layout.login_fragment, container, false)
//        val mainButtonLogin = view.findViewById<Button>(R.id.mainButtonLogin)
//        val mainButtonRegister = view.findViewById<TextView>(R.id.mainButtonRegisterNewUser)
//
//        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
//
//        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
//        viewModel.userData.observe(viewLifecycleOwner, Observer {
//            if (it == null) {
//                val toastError = Toast.makeText(context, "Error wrong username or password!", Toast.LENGTH_SHORT)
//                toastError.setGravity(Gravity.TOP, 0, 250)
//                toastError.show()
//            } else {
//                //navController.navigate(R.id.mainFragment)
//                val intent = Intent (activity, MainActivity::class.java)
//                this.startActivity(intent)
//
//                activity?.finish()
//            }
//        })
//
//        mainButtonLogin.setOnClickListener {
//            val username = username.text.toString()
//            val password = password.text.toString()
//
//            viewModel.login(username, password)
//        }
//
//        mainButtonRegister.setOnClickListener {
//            navController.navigate(R.id.registerFragment)
//        }
//
//        return view
//    }
}