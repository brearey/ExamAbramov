package ru.oktemsec.examabramov.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.oktemsec.examabramov.models.LoginRequest
import ru.oktemsec.examabramov.models.LoginResponse

interface LoginService {
    @POST("/auth/login")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>
}