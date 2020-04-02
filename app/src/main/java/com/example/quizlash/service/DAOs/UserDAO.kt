package com.example.quizlash.service.DAOs

import androidx.room.Dao
import androidx.room.Query
import com.example.quizlash.service.model.Taker

@Dao
interface UserDao:
    BaseDao<Taker> {
    @Query("SELECT * FROM Taker where user_id =:user_id")
    fun getUser(user_id: String): Taker
}