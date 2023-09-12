package com.easymeal.easymealgdc

data class Results(
        val statusCode: Int,
        val details: Any
)
data class LoginResponse(

        val accessToken:String,
        val userId:String,
        val name:String,
        val emailAddress:String,
        val phoneNumber:String,
        val roles:List<String>,
)
data class DbResults(
        val details: String
)
data class DbRegister(
        val name: String,
        val emailAddress: String,
        val password: String,
        val confirmPassword:String,
        val phoneNumber: String
)
data class DbLogin(
        val username:String,
        val password: String
)
data class DbRequestPasswordChange(
        val emailAddress: String
)
data class DbPasswordChange(
        val currentPassword:String,
        val newPassword:String,
        val confirmPassword:String,
        val otpPassword:String
)