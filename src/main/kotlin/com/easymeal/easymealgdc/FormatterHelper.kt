package com.easymeal.easymealgdc

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.regex.Pattern

class FormatterHelper {
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val regex = """^\+[1-9]\d{1,14}$""".toRegex()
        return regex.matches(phoneNumber)
    }
    fun getResponseEntity(addedResults: Results): ResponseEntity<*> {
        val statusCode = addedResults.statusCode
        val results = addedResults.details
        return when (statusCode) {
            201 -> { ResponseEntity(results, HttpStatus.CREATED) }
            200 -> { ResponseEntity(results, HttpStatus.OK) }
            else -> { ResponseEntity.badRequest().body(DbResults(results.toString())) }
        }
    }
    fun isEmailValid(emailAddress: String):Boolean{

        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"
        val pat = Pattern.compile(emailRegex)
        return pat.matcher(emailAddress).matches()
    }
}