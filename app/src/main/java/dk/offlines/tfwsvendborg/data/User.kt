package dk.offlines.tfwsvendborg.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "users")
data class User (
    @Json(name = "admin") var admin: Int,
    @Json(name = "username") var username: String,
    @Json(name = "first_name") var first_name: String,
    @Json(name = "last_name") var last_name: String,
    @Json(name = "birthday") var birthday: String,
    @Json(name = "address") var address: String,
    @Json(name = "zip_code") var zip_code: Int,
    @Json(name = "city") var city: String,
    @Json(name = "phone_number") var phone_number: Int,
    @Json(name = "email") var email: String,
    @Json(name = "pull_ups") var pull_ups: Int,
    @Json(name = "knee_graps") var knee_graps: Int
) {
    @PrimaryKey (autoGenerate = true)
    @Json(name = "id") var id: Int = 0
}