package com.easymeal.easymealgdc

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import java.util.regex.Pattern
import kotlin.random.Random

class FormatterHelper {
    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        val regex = """^(\+[1-9]\d{1,14}|0\d{9})$""".toRegex()
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
    fun generateOtp(): String {
        val characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
        val randomString = StringBuilder(6)

        repeat(6) {
            val index = Random.nextInt(characters.length)
            randomString.append(characters[index])
        }

        return randomString.toString()
    }
    fun sendOtpMail(
            emailSender: JavaMailSender,
            emailAddress: String,
            message:String,
            subject:String){
        CoroutineScope(Dispatchers.IO).launch { sendOtpCode(emailSender, emailAddress, message, subject) }
    }
    private suspend fun sendOtpCode(
            emailSender: JavaMailSender,
            emailAddress: String,
            messageStr: String,
            subject: String){
        coroutineScope {
            launch(Dispatchers.IO) {

                val message = SimpleMailMessage()
                message.from = "geoke2021@gmail.com"
                message.setTo(emailAddress)
                message.subject = subject
                message.text = messageStr

                // Send the email
                emailSender.send(message)
            }
        }
    }
}