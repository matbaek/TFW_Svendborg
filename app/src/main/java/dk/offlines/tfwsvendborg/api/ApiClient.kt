package dk.offlines.tfwsvendborg.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    lateinit var retrofit: Retrofit
    lateinit var httpClient: OkHttpClient.Builder

    fun getClient(): Retrofit {

        httpClient = OkHttpClient.Builder()

        retrofit = Retrofit.Builder()
            .baseUrl("https://offlines.dk/testAPI/")
            .client(httpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}