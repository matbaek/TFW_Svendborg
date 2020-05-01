package dk.offlines.tfwsvendborg.ui.login

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
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonParser
import dk.offlines.tfwsvendborg.MainActivity
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.api.ApiClient
import dk.offlines.tfwsvendborg.api.ApiInterface
import dk.offlines.tfwsvendborg.api.ApiResponseUser
import dk.offlines.tfwsvendborg.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.login_fragment, container, false)
        val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        val viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val main_button: Button = view.findViewById(R.id.mainButtonLogin)

        main_button.setOnClickListener {
            val username: EditText = view.findViewById(R.id.username)
            val password: EditText = view.findViewById(R.id.password)

            // Hide keyboard - https://stackoverflow.com/questions/3400028/close-virtual-keyboard-on-button-press
            val imm = activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)

            val call: Call<ApiResponseUser> = apiInterface.login(username.text.toString(), password.text.toString())
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
                        var user: User = apiResponseUser!!.user

                        viewModel.saveUser(user)

                        val intent = Intent (activity, MainActivity::class.java)
                        this@LoginFragment.startActivity(intent)

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