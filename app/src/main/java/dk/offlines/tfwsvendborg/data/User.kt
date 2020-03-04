package dk.offlines.tfwsvendborg.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class User (
    @PrimaryKey (autoGenerate = true)
    @Json(name = "id")val id: Int,
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "firstname") val firstname: String,
    @Json(name = "lastname") val lastname: String,
    @Json(name = "address") val address: String,
    @Json(name = "birthDate" )val birthDate: String,
    @Json(name = "phone_Nr") val phone_Nr: Int,
    @Json(name = "email") val email: String

)