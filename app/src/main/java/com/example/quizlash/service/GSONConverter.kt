package com.example.quizlash.service

import com.example.quizlash.service.model.MultipleChoiceQuestion
import com.example.quizlash.service.model.MultipleChoiceResponse
import com.example.quizlash.service.model.Taker
import com.example.quizlash.service.network.HeartBeat
import com.google.gson.Gson

class GSONConverter(val gson: Gson = Gson()){
    // Store the JSON directly is probably more efficient. We might want to do speed tests.
    fun convertToClass(type: String, json_string: String): Any?{
        val returnValue = null
        when(type){
            "multiple_choice_question" -> return gson.fromJson(json_string, MultipleChoiceQuestion::class.java)
            "multiple_choice_response" -> return gson.fromJson(json_string, MultipleChoiceResponse::class.java)
            "user" -> return gson.fromJson(json_string, Taker::class.java)
            "hb" -> return gson.fromJson(json_string, HeartBeat::class.java)
        }
        return returnValue
    }
}