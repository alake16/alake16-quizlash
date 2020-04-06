package com.example.quizlash.service.DAOs

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.quizlash.service.model.MultipleChoiceQuestion

@Dao
interface QuestionDao :
    BaseDao<MultipleChoiceQuestion> {

    @Query("SELECT * from MultipleChoiceQuestion")
    fun getAllQuestions(): List<MultipleChoiceQuestion>

    @Query("DELETE FROM MultipleChoiceQuestion")
    fun deleteAllQuestions()

    @Query("SELECT * FROM MultipleChoiceQuestion WHERE question_id in (:id_list)")
    fun getQuestions(id_list: List<String>): List<MultipleChoiceQuestion>

    @Query("SELECT * FROM MultipleChoiceQuestion WHERE question_id = :id")
    fun getQuestion(id: String): MultipleChoiceQuestion?
}