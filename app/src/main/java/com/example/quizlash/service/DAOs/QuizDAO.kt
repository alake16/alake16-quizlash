package com.example.quizlash.service.DAOs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.quizlash.service.model.MultipleChoiceQuestion
import com.example.quizlash.service.model.Quiz

// Thanks a ton to https://stackoverflow.com/questions/44667160/android-room-insert-relation-entities-using-room
// This really helped deal with some design issues we'd face and simplified things
@Dao
abstract class QuizDao:
    BaseDao<Quiz> {

    @Insert
    abstract fun insertQuestions(questions: List<MultipleChoiceQuestion>)

    @Query("SELECT * FROM QUIZ WHERE quiz_id = :quiz_id")
    abstract fun getQuiz(quiz_id: String): Quiz

    @Query("SELECT * FROM MultipleChoiceQuestion WHERE quiz_id = :quiz_id")
    abstract fun getQuestionList(quiz_id: String): List<MultipleChoiceQuestion>

    fun getQuizWithQuestions(quiz_id: String): Quiz {
        val quiz = getQuiz(quiz_id)
        quiz.questions = getQuestionList(quiz_id)
        return quiz
    }

    fun insertQuizWithQuestions(quiz: Quiz, questions: List<MultipleChoiceQuestion>){
        insert(quiz)
        insertQuestions(questions)
    }

}