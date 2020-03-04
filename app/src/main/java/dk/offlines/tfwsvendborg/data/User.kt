package dk.offlines.tfwsvendborg.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class User (
    @Json(name = "username") var username: String,
    @Json(name = "password") var password: String,
    @Json(name = "firstname") var firstname: String,
    @Json(name = "lastname") var lastname: String,
    @Json(name = "address") var address: String,
    @Json(name = "birthDate" )var birthDate: String,
    @Json(name = "phone_Nr") var phone_Nr: Int,
    @Json(name = "email") var email: String
) {
    @PrimaryKey (autoGenerate = true)
    @Json(name = "id") var id: Int = 0
}