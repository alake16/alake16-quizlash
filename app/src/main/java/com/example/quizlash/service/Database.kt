package com.example.quizlash.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.quizlash.service.DAOs.QuestionDao
import com.example.quizlash.service.DAOs.QuizDao
import com.example.quizlash.service.DAOs.ResponseDao
import com.example.quizlash.service.DAOs.UserDao
import com.example.quizlash.service.model.MultipleChoiceQuestion
import com.example.quizlash.service.model.MultipleChoiceResponse
import com.example.quizlash.service.model.Quiz
import com.example.quizlash.service.model.Taker

// Followed the tutorial on https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#6

@Database(entities = arrayOf(MultipleChoiceQuestion::class, MultipleChoiceResponse::class, Quiz::class, Taker::class), version = 1)
@TypeConverters(DataTypeConverters::class)
abstract class QuizDatabase: RoomDatabase(){

    abstract fun questionDao(): QuestionDao
    abstract fun responseDao(): ResponseDao
    abstract fun userDao(): UserDao
    abstract fun quizDao(): QuizDao

    companion object {

        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getDatabase(context: Context): QuizDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "Brian").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}