@file:Suppress("DEPRECATION")

package dk.offlines.tfwsvendborg.ui.user.update

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonParser
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.api.ApiClient
import dk.offlines.tfwsvendborg.api.ApiInterface
import dk.offlines.tfwsvendborg.api.ApiResponseUser
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.ui.user.ViewModel
import dk.offlines.tfwsvendborg.ui.user.profile.ProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateFragment: Fragment() {
    private lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.user_update_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        val user: User = viewModel.getUser()

        val button_update: Button = view.findViewById(R.id.button_updateUser)
        val first_name: TextView = view.findViewById(R.id.editView_firstName)
        val last_name: TextView = view.findViewById(R.id.editView_lastName)
        val birthday: TextView = view.findViewById(R.id.editView_birthday)
        val adress: TextView = view.findViewById(R.id.editView_address)
        val zip_code: TextView = view.findViewById(R.id.editView_zipCode)
        val city: TextView = view.findViewById(R.id.editView_city)
        val phone_number: TextView = view.findViewById(R.id.editView_phoneNumber)
        val email: TextView = view.findViewById(R.id.editView_email)

        first_name.text = user.first_name
        last_name.text = user.last_name
        birthday.text = user.birthday
        adress.text = user.address
        zip_code.text = user.zip_code.toString()
        city.text = user.city
        phone_number.text = user.phone_number.toString()
        email.text = user.email

        button_update.setOnClickListener {
            user.first_name = first_name.text.toString()
            user.last_name = last_name.text.toString()
            user.birthday = birthday.text.toString()
            user.address = adress.text.toString()
            user.zip_code = zip_code.text.toString().toInt()
            user.city = city.text.toString()
            user.phone_number = phone_number.text.toString().toInt()
            user.email = email.text.toString()

            val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
            val call: Call<ApiResponseUser> = apiInterface.update_user(user.username, user.first_name, user.last_name, user.birthday, user.address, user.zip_code, user.city, user.phone_number, user.email)
            call.enqueue(object: Callback<ApiResponseUser> {
                @Override
                override fun onResponse(
                call: Call<ApiResponseUser>,
                response: Response<ApiResponseUser>
                ) {
                    var message: String?

                    if(response.isSuccessful) {
                        val apiResponseUser: ApiResponseUser? = response.body()
                        message = apiResponseUser?.message

                        viewModel.removeUser()
                        viewModel.saveUser(user)

                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, ProfileFragment())?.commit()
                    } else {
                        val error = response.errorBody()?.string()
                        val jsonObject = JsonParser().parse(error).asJsonObject
                        message = jsonObject.get("message").asString
                    }

                    Toast.makeText(
                        view.context,
                        message,
                        Toast.LENGTH_LONG
                    ).show()
                }
                override fun onFailure(call: Call<ApiResponseUser>, t: Throwable) {
                    Toast.makeText(
                        view.context,
                        "Noget gik galt. Kontakt support!",
                        Toast.LENGTH_LONG
                    ).show()
                    call.cancel()
                }
            })
        }

        return view
    }
}