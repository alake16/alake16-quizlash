package com.example.quizlash.service.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizlash.service.model.MultipleChoiceResponse

@Dao
interface ResponseDao :
    BaseDao<MultipleChoiceResponse> {

    @Query("SELECT * from MultipleChoiceResponse")
    fun getAllResponses(): List<MultipleChoiceResponse>

    @Query("DELETE FROM MultipleChoiceResponse")
    fun deleteAllResponses()

    @Query("SELECT * FROM MultipleChoiceResponse WHERE response_id in (:id_list)")
    fun getResponses(id_list: List<String>): List<MultipleChoiceResponse>

    @Query("SELECT * FROM MultipleChoiceResponse WHERE response_id = :id")
    fun getResponse(id: String): MultipleChoiceResponse

    @Query("SELECT * FROM MultipleChoiceResponse WHERE parent_question_id = :question_id")
    fun getResponsesByQuestionID(question_id: String): List<MultipleChoiceResponse>

    @Query("SELECT * FROM MultipleChoiceResponse WHERE parent_question_id = :question_id AND quiz_id = :quiz_id")
    fun getResponsesByQuestionIDAndQuizID(question_id: String, quiz_id: String) : List<MultipleChoiceResponse>

    @Query("SELECT * FROM MultipleChoiceResponse WHERE parent_question_id = :question_id AND quiz_id = :quiz_id AND user_id = :user_id")
    fun getResponsesByQuestionIDAndQuizIDAndUserID(question_id: String, quiz_id: String, user_id: String): List<MultipleChoiceResponse>
}