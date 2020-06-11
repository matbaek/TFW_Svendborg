package dk.offlines.tfwsvendborg.ui.user.records

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.gson.JsonParser
import dk.offlines.tfwsvendborg.R
import dk.offlines.tfwsvendborg.api.ApiClient
import dk.offlines.tfwsvendborg.api.ApiInterface
import dk.offlines.tfwsvendborg.api.ApiResponseUser
import dk.offlines.tfwsvendborg.data.User
import dk.offlines.tfwsvendborg.ui.user.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordsFragment: Fragment() {
    private lateinit var viewModel: ViewModel
    private lateinit var pull_ups: TextView
    private lateinit var knee_graps: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.user_records, container, false)
        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)
        val user: User = viewModel.getUser()

        val button_pullUps_add: Button = view.findViewById(R.id.button_pullUps_add)
        val button_pullUps_subtract: Button = view.findViewById(R.id.button_pullUps_subtract)
        val button_kneeGraps_add: Button = view.findViewById(R.id.button_kneeGraps_add)
        val button_kneeGraps_subtract: Button = view.findViewById(R.id.button_kneeGraps_subtract)
        val button_update: Button = view.findViewById(R.id.button_updateRecords)
        pull_ups = view.findViewById(R.id.editView_pullUps)
        knee_graps = view.findViewById(R.id.editView_kneeGraps)

        set_editView(user.pull_ups, user.knee_graps)
        if(user.pull_ups == 0) {
            enable_button(button_pullUps_subtract)
        }
        if(user.knee_graps == 0) {
            enable_button(button_kneeGraps_subtract)
        }

        button_pullUps_add.setOnClickListener {
            user.pull_ups++
            pull_ups.text = user.pull_ups.toString()
            if(user.pull_ups == 1) {
                enable_button(button_pullUps_subtract)
            }
        }
        button_pullUps_subtract.setOnClickListener {
            user.pull_ups--
            pull_ups.text = user.pull_ups.toString()
            if(user.pull_ups == 0) {
                enable_button(button_pullUps_subtract)
            }
        }
        button_kneeGraps_add.setOnClickListener {
            user.knee_graps++
            knee_graps.text = user.knee_graps.toString()
            if(user.knee_graps == 1) {
                enable_button(button_kneeGraps_subtract)
            }
        }
        button_kneeGraps_subtract.setOnClickListener {
            user.knee_graps--
            knee_graps.text = user.knee_graps.toString()
            if(user.knee_graps == 0) {
                enable_button(button_kneeGraps_subtract)
            }
        }

        button_update.setOnClickListener {
            val apiInterface = ApiClient().getClient().create(ApiInterface::class.java)

            val call: Call<ApiResponseUser> = apiInterface.update_records(user.username, pull_ups.text.toString().toInt(), knee_graps.text.toString().toInt())
            call.enqueue(object: Callback<ApiResponseUser> {
                override fun onResponse(
                    call: Call<ApiResponseUser>,
                    response: Response<ApiResponseUser>
                ) {
                    var message: String?
                    if(response.isSuccessful) {
                        val apiResponseUser: ApiResponseUser? = response.body()
                        message = apiResponseUser?.message

                        viewModel.updateRecords(user.username, user.pull_ups, user.knee_graps)
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

    fun set_editView(pull_ups: Int, knee_graps: Int) {
        this.pull_ups.text = pull_ups.toString()
        this.knee_graps.text = knee_graps.toString()
    }

    fun enable_button(button: Button) {
        button.isEnabled = !button.isEnabled
    }
}