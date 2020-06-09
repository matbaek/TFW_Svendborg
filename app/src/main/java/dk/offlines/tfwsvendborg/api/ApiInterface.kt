package dk.offlines.tfwsvendborg.api

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ApiResponseUser>

    @FormUrlEncoded
    @POST("register_user.php")
    fun register(
        @Field("username") username: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("birthday") birthday: String,
        @Field("address") address: String,
        @Field("zip_code") zip_code: Int,
        @Field("city") city: String,
        @Field("phone_number") phone_number: Int,
        @Field("email") email: String
    ): Call<ApiResponseUser>

    @FormUrlEncoded
    @POST("update_user.php")
    fun update_user(
        @Field("username") username: String,
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("birthday") birthday: String,
        @Field("address") address: String,
        @Field("zip_code") zip_code: Int,
        @Field("city") city: String,
        @Field("phone_number") phone_number: Int,
        @Field("email") email: String
    ): Call<ApiResponseUser>

    @FormUrlEncoded
    @POST("get_all_users.php")
    fun get_all_users(
        @Field("admin") admin: Int
    ): Call<ApiResponseGetAllUsers>

    @FormUrlEncoded
    @POST("get_user.php")
    fun get_user(
        @Field("username") username: String?
    ): Call<ApiResponseUser>

    @FormUrlEncoded
    @POST("update_records.php")
    fun update_records(
        @Field("username") username: String,
        @Field("pull_ups") pull_ups: Int,
        @Field("knee_graps") knee_graps: Int
    ): Call<ApiResponseUser>
}