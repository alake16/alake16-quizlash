package com.example.quizlash.service.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.quizlash.service.DAOs.QuestionDao
import com.example.quizlash.service.DAOs.QuizDao
import com.example.quizlash.service.DAOs.ResponseDao
import com.example.quizlash.service.DAOs.UserDao
import com.example.quizlash.service.QuizDatabase
import com.example.quizlash.service.model.MultipleChoiceQuestion
import com.example.quizlash.service.model.MultipleChoiceResponse
import com.example.quizlash.service.model.Quiz
import com.example.quizlash.service.model.Taker

interface Repository{
    fun getQuestion(id: String): MultipleChoiceQuestion?
    fun getQuestions(id_list: List<String>): List<MultipleChoiceQuestion?>
    fun insertAllQuestions(vararg multipleChoiceQuestions: MultipleChoiceQuestion)
    fun insertQuestion(multipleChoiceQuestion: MultipleChoiceQuestion)
    fun getResponses(id_list: List<String>): List<MultipleChoiceResponse?>
    fun getResponse(id: String): MultipleChoiceResponse?
    fun getResponsesByQuestionId(question_id: String): List<MultipleChoiceResponse?>
    fun insertResponse(multipleChoiceResponse: MultipleChoiceResponse)
    fun insertResponses(vararg respons: MultipleChoiceResponse)
    fun getResponsesByQuizIdAndQuestionID(quiz_id: String, question_id: String): List<MultipleChoiceResponse?>
    fun getResponsesByQuizIdQuestionIDAndUserId(quiz_id: String, question_id: String, user_id: String): List<MultipleChoiceResponse?>
    fun getUser(user_id: String): Taker?
    fun getQuiz(quiz_id: String): Quiz?
    fun insertQuiz(quiz: Quiz)
}

class RepositoryImpl (
    private val question_dao: QuestionDao,
    private val response_dao: ResponseDao,
    private val user_dao: UserDao,
    private val quiz_dao: QuizDao

): Repository {

    override  fun getQuestions(id_list: List<String>): List<MultipleChoiceQuestion?>{
        return question_dao.getQuestions(id_list)
    }

    override  fun insertAllQuestions(vararg multipleChoiceQuestions: MultipleChoiceQuestion) {
        val listOfQuestions = multipleChoiceQuestions.toList()
        question_dao.insert(listOfQuestions)
    }

    override  fun insertQuestion(multipleChoiceQuestion: MultipleChoiceQuestion) {
        question_dao.insert(multipleChoiceQuestion)
    }

    override  fun getQuestion(id: String): MultipleChoiceQuestion? {
        return question_dao.getQuestion(id)
    }

    override fun getResponse(id: String): MultipleChoiceResponse? {
        return response_dao.getResponse(id)
    }

    override  fun getResponses(id_list: List<String>): List<MultipleChoiceResponse?> {
        return response_dao.getResponses(id_list)
    }

    override  fun getResponsesByQuestionId(question_id: String): List<MultipleChoiceResponse?> {
        return response_dao.getResponsesByQuestionID(question_id)
    }

    override  fun insertResponse(multipleChoiceResponse: MultipleChoiceResponse) {
        response_dao.insert(multipleChoiceResponse)
    }

    override  fun insertResponses(vararg respons: MultipleChoiceResponse) {
        response_dao.insert(respons.toList())
    }

    override  fun getResponsesByQuizIdAndQuestionID(
        quiz_id: String,
        question_id: String
    ): List<MultipleChoiceResponse?> {
        return response_dao.getResponsesByQuestionIDAndQuizID(question_id, quiz_id)
    }

    override  fun getResponsesByQuizIdQuestionIDAndUserId(
        quiz_id: String,
        question_id: String,
        user_id: String
    ): List<MultipleChoiceResponse?> {
        return response_dao.getResponsesByQuestionIDAndQuizIDAndUserID(question_id, quiz_id, user_id)
    }

    override  fun getUser(user_id: String): Taker? {
        return user_dao.getUser(user_id)
    }

    override  fun getQuiz(quiz_id: String): Quiz? {
        return quiz_dao.getQuizWithQuestions(quiz_id)
    }

    override  fun insertQuiz(quiz: Quiz){
        return quiz_dao.insertQuizWithQuestions(quiz, quiz.questions)
    }

    fun getAllQuestions(): LiveData<List<MultipleChoiceQuestion>>{
        return question_dao.getAllQuestions()
    }

    fun insertUser(taker: Taker){
        user_dao.insert(taker)
    }
    companion object {

        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(context: Context): RepositoryImpl {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val dataaccess = QuizDatabase.getDatabase(context)
                val instance = RepositoryImpl(
                    dataaccess.questionDao(),
                    dataaccess.responseDao(),
                    dataaccess.userDao(),
                    dataaccess.quizDao()
                )
                INSTANCE = instance
                return instance
            }
        }
    }
}

class Cache: Repository {

    val questions = hashMapOf<String, MultipleChoiceQuestion>()
    val response_list = hashMapOf<String, MultipleChoiceResponse>()

    // User Submitted Questions
    val submitted_questions = hashMapOf<String, MultipleChoiceQuestion>()
    val users_list = hashMapOf<String, Taker>()

    override  fun getQuestion(id: String): MultipleChoiceQuestion? {
        return questions[id]
    }

    override  fun getQuestions(id_list: List<String>): List<MultipleChoiceQuestion?> {
        return questions.values.toList()
    }

    override  fun getQuiz(quiz_id: String): Quiz? {
        return Quiz(
            quiz_id = quiz_id,
            quiz_name = "Unnamed",
            questions = questions.values.toList()
        )
    }

    override fun getResponse(id: String): MultipleChoiceResponse? {
        return response_list.get(id)
    }

    override  fun getResponses(id_list: List<String>): List<MultipleChoiceResponse?> {
        return response_list.values.toList()
    }

    override  fun getResponsesByQuestionId(question_id: String): List<MultipleChoiceResponse?> {
        return response_list.values.toList().filter { it.parent_question_id == question_id }
    }

    override  fun getResponsesByQuizIdAndQuestionID(
        quiz_id: String,
        question_id: String
    ): List<MultipleChoiceResponse?> {
        return response_list.values.toList().filter{ (it.parent_question_id == question_id) and (it.quiz_id == quiz_id)}
    }

    override  fun getResponsesByQuizIdQuestionIDAndUserId(
        quiz_id: String,
        question_id: String,
        user_id: String
    ): List<MultipleChoiceResponse?> {
        return response_list.values.toList().filter{ (it.parent_question_id == question_id) and (it.quiz_id == quiz_id) and (it.user_id == user_id)}
    }

    override  fun getUser(user_id: String): Taker? {
        return users_list[user_id]
    }

    override  fun insertAllQuestions(vararg multipleChoiceQuestions: MultipleChoiceQuestion) {
        for (question in multipleChoiceQuestions){
            questions[question.question_id] = question
        }
    }

    override  fun insertQuestion(multipleChoiceQuestion: MultipleChoiceQuestion) {
        questions[multipleChoiceQuestion.question_id] = multipleChoiceQuestion
    }

    override  fun insertQuiz(quiz: Quiz) {
        return
    }

    override  fun insertResponse(multipleChoiceResponse: MultipleChoiceResponse) {
        response_list[multipleChoiceResponse.response_id] = multipleChoiceResponse
    }

    override  fun insertResponses(vararg respons: MultipleChoiceResponse) {
        for (response in respons){
            insertResponse(response)
        }
    }
}