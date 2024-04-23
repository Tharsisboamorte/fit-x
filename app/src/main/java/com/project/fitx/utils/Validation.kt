package com.project.fitx.utils

class Validation {

    val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
    fun isEmail(email: String): Boolean{
        return  emailRegex.matches(email)
    }

    fun isPassword(password: String): Boolean{
        return password.length >= 6
    }

}