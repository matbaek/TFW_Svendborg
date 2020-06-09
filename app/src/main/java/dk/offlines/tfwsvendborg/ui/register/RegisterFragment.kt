package dk.offlines.tfwsvendborg.ui.register

import android.content.Context.INPUT_METHOD_SERVICE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonParser
import dk.offlines.tfwsvendborg.MainActivity
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.api.ApiClient
import dk.offlines.tfwsvendborg.api.ApiInterface
import dk.offlines.tfwsvendborg.api.ApiResponseUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


@Suppress("DEPRECATION")
class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.admin_register_fragment, container, false)
        val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        val register_button: Button = view.findViewById(R.id.button_register)

        register_button.setOnClickListener {
            val username: EditText = view.findViewById(R.id.register_username)
            val first_name: EditText = view.findViewById(R.id.register_firstname)
            val last_name: EditText = view.findViewById(R.id.register_lastname)
            val birthday: EditText = view.findViewById(R.id.register_birthday)
            val address: EditText = view.findViewById(R.id.register_address)
            val zip_code: EditText = view.findViewById(R.id.register_zip_code)
            val city: EditText = view.findViewById(R.id.register_city)
            val phone_number: EditText = view.findViewById(R.id.register_phone_number)
            val email: EditText = view.findViewById(R.id.register_email)

            // Hide keyboard - https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
            val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

            val call: Call<ApiResponseUser> = apiInterface.register(
                username.text.toString(), first_name.text.toString(), last_name.text.toString(),
                birthday.text.toString(), address.text.toString(), Integer.parseInt(zip_code.text.toString()),
                city.text.toString(), Integer.parseInt(phone_number.text.toString()), email.text.toString()
            )
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

                        val intent = Intent (activity, MainActivity::class.java)
                        this@RegisterFragment.startActivity(intent)

                        activity?.finish()
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