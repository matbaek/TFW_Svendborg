package dk.offlines.tfwsvendborg.ui.user.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.ui.user.ViewModel
import dk.offlines.tfwsvendborg.ui.user.update.UpdateFragment

class ProfileFragment: Fragment() {
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.user_profile_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        val user: User = viewModel.getUser()

        val button_update: TextView = view.findViewById(R.id.textView_updateUser)
        val username: TextView = view.findViewById(R.id.textView_username)
        val first_name: TextView = view.findViewById(R.id.textView_firstName)
        val last_name: TextView = view.findViewById(R.id.textView_lastName)
        val birthday: TextView = view.findViewById(R.id.textView_birthday)
        val adress: TextView = view.findViewById(R.id.textView_address)
        val zip_code: TextView = view.findViewById(R.id.textView_zipCode)
        val city: TextView = view.findViewById(R.id.textView_city)
        val phone_number: TextView = view.findViewById(R.id.textView_phoneNumber)
        val email: TextView = view.findViewById(R.id.textView_email)

        username.text = user.username
        first_name.text = user.first_name
        last_name.text = user.last_name
        birthday.text = user.birthday
        adress.text = user.address
        zip_code.text = user.zip_code.toString()
        city.text = user.city
        phone_number.text = user.phone_number.toString()
        email.text = user.email

        button_update.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, UpdateFragment())?.commit()
        }

        return view
    }
}