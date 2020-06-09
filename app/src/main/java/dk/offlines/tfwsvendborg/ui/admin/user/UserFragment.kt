package dk.offlines.tfwsvendborg.ui.admin.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.JsonParser
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.api.ApiClient
import dk.offlines.tfwsvendborg.api.ApiInterface
import dk.offlines.tfwsvendborg.api.ApiResponseGetAllUsers
import dk.offlines.tfwsvendborg.api.ApiResponseUser
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.ui.admin.userProfiles.UserProfilesFragment
import kotlinx.android.synthetic.main.admin_show_user_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.admin_show_user_fragment, container, false)
        val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)

        val settings = context?.getSharedPreferences("Preferences", 0)
        val username = settings?.getString("username", "")

        val back_button = view.findViewById<TextView>(R.id.textView_back)
        back_button.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, UserProfilesFragment())?.commit()
        }

        val call: Call<ApiResponseUser> = apiInterface.get_user(username)
        call.enqueue(object: Callback<ApiResponseUser> {
            @Override
            override fun onResponse(
                call: Call<ApiResponseUser>,
                response: Response<ApiResponseUser>
            ) {
                if(response.isSuccessful) {
                    val apiResponseUser: ApiResponseUser? = response.body()
                    val user: User = apiResponseUser!!.user

                    view.findViewById<TextView>(R.id.textView_admin_username).text = user.username;
                    view.findViewById<TextView>(R.id.textView_admin_firstName).text = user.first_name;
                    view.findViewById<TextView>(R.id.textView_admin_lastName).text = user.last_name;
                    view.findViewById<TextView>(R.id.textView_admin_birthday).text = user.birthday;
                    view.findViewById<TextView>(R.id.textView_admin_address).text = user.address;
                    view.findViewById<TextView>(R.id.textView_admin_zipCode).text = user.zip_code.toString();
                    view.findViewById<TextView>(R.id.textView_admin_city).text = user.city;
                    view.findViewById<TextView>(R.id.textView_admin_phoneNumber).text = user.phone_number.toString();
                    view.findViewById<TextView>(R.id.textView_admin_email).text = user.email;

                } else {
                    val error = response.errorBody()?.string()
                    val jsonObject = JsonParser().parse(error).asJsonObject

                    Toast.makeText(
                        view.context,
                        jsonObject.get("message").asString,
                        Toast.LENGTH_LONG
                    ).show()
                }
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

        return view
    }
}