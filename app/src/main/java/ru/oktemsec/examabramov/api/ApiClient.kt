package ru.oktemsec.examabramov.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import ru.oktemsec.examabramov.interfaces.LoginService
import ru.oktemsec.examabramov.interfaces.RegisterService

class ApiClient {
    private fun getRetrofit(): Retrofit {
        val httpLoggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient: OkHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://cinema.areas.su")
            .build()
    }

    fun getLogin(): LoginService {
        return getRetrofit().create(LoginService::class.java)
    }

    fun getRegister(): RegisterService {
        return getRetrofit().create(RegisterService::class.java)
    }
}