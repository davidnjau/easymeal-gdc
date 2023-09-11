package com.easymeal.easymealgdc

data class Results(
        val statusCode: Int,
        val details: Any
)
data class LoginResponse(

        val accessToken:String,
        val userId:String,
        val firstName:String,
        val emailAddress:String,
        val lastName:String,
        val phoneNumber:String,
        val roles:List<String>,
)