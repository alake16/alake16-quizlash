package com.example.quizlash.service.DAOs

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>?): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T?): Long

    @Delete
    fun delete(obj: T?)

    @Delete
    fun delete(obj: List<T>?)

    @Update
    fun update(obj: T?): Int
}