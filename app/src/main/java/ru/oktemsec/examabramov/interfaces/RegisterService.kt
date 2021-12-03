package ru.oktemsec.examabramov.interfaces

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import ru.oktemsec.examabramov.models.RegisterRequest
import ru.oktemsec.examabramov.models.RegisterResponse

interface RegisterService {
    @POST("/auth/register")
    fun registerUser(@Body registerRequest: RegisterRequest): Call<RegisterResponse>
}