@file:Suppress("DEPRECATION")

package dk.offlines.tfwsvendborg.ui.register

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dk.offlines.tfwsvendborg.R
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment: Fragment() {
    private lateinit var navController: NavController

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)

        val view =  inflater.inflate(R.layout.register_fragment, container, false)
        val registerButton = view.findViewById<Button>(R.id.buttonRegisterCreateNewUser)

        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        viewModel.userData.observe(viewLifecycleOwner, Observer {
            if(it == null) {
                toastMessage("Email er allerede oprettet.")
            } else {
//                toastMessage("Din bruger er blevet oprettet.\n Brugernavn: ${it.username} - Kodeord: ${it.password}")
            }
        })

        registerButton.setOnClickListener {
            val firstname = registerFirstname.text.toString()
            val lastname = registerLastname.text.toString()
            val address = registerAddress.text.toString()
            val birthdate = registerBirthdate.text.toString()
            val email = registerEmail.text.toString()
            val phoneNr = if(registerPhoneNumber.text.toString().isEmpty()) { 0 } else { registerPhoneNumber.text.toString().toInt() }
            var username = StringBuilder()
            val password = "1234"

            if(firstname.length < 3) {
                username.append(firstname.substring(firstname.indices))
            } else {
                username.append(firstname.substring(0..3))
            }
            if(lastname.length < 3) {
                username.append(lastname.substring(lastname.indices))
            } else {
                username.append(lastname.substring(0..3))
            }

            val arrayOfFields = arrayListOf<String>(firstname, lastname, address, birthdate, email, phoneNr.toString())
            val arrayOfMessages = arrayListOf<String>("Fornavn", "Efternavn", "Adresse", "Fødselsdag", "Email", "Telefon nummer")
            var count = 0
            var register = false

            for (field in arrayOfFields) {
                if(field.isEmpty() || field == "0") {
                    toastMessage("${arrayOfMessages[count]} skal udfyldes")
                    break
                }

                if(count == 5) {
                    register = true
                }

                count++
            }

            if(register) {
//                viewModel.register(username.toString(), password, firstname, lastname, address, birthdate, email, phoneNr)
            }
        }

        return view
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

    fun toastMessage(message: String) {
        val toastError = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toastError.setGravity(Gravity.TOP, 0, 250)
        toastError.show()
    }
}