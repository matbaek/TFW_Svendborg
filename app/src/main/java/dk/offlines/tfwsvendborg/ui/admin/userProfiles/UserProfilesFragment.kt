package dk.offlines.tfwsvendborg.ui.admin.userProfiles

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.JsonParser
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.api.ApiClient
import dk.offlines.tfwsvendborg.api.ApiInterface
import dk.offlines.tfwsvendborg.api.ApiResponseGetAllUsers
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.ui.admin.user.UserFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Suppress("DEPRECATION")
class UserProfilesFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.admin_show_all_users_fragment, container, false)
        val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
        val listView = view.findViewById<ListView>(R.id.listView)

        val call: Call<ApiResponseGetAllUsers> = apiInterface.get_all_users(1)
        call.enqueue(object: Callback<ApiResponseGetAllUsers> {
            @SuppressLint("RestrictedApi", "CommitPrefEdits")
            @Override
            override fun onResponse(
                call: Call<ApiResponseGetAllUsers>,
                response: Response<ApiResponseGetAllUsers>
            ) {
                if(response.isSuccessful) {
                    val apiResponseUser: ApiResponseGetAllUsers? = response.body()
                    val users: List<User> = apiResponseUser!!.users
                    val usernames = mutableListOf<String>()

                    for(i in 0 until users.size) {
                        usernames.add(users[i].username)
                    }

                    val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, usernames)
                    listView.adapter = adapter

                    listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                        // This is your listview's selected item
                        val item = parent.getItemAtPosition(position) as String
                        Log.d("LOGCATTEST", item)

                        val settings = context?.getSharedPreferences("Preferences", 0)
                        val editor = settings?.edit()
                        editor?.putString("username", item)
                        editor?.commit()

                        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, UserFragment())?.commit()
                    }
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

            override fun onFailure(call: Call<ApiResponseGetAllUsers>, t: Throwable) {
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