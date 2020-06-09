package dk.offlines.tfwsvendborg.api

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName
import dk.offlines.tfwsvendborg.data.User

class ApiResponseGetAllUsers {
    @SerializedName("message")
    lateinit var message: String
    @SerializedName("item")
    @Nullable
    lateinit var users: List<User>
}