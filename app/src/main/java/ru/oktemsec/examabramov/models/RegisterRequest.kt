package ru.oktemsec.examabramov.models

data class RegisterRequest(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)
