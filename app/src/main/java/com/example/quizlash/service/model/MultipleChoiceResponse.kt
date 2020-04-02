package com.example.quizlash.service.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(foreignKeys = [ForeignKey(entity = MultipleChoiceQuestion::class, parentColumns = arrayOf("question_id"), childColumns = arrayOf("parent_question_id"), onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Taker::class, parentColumns = arrayOf("user_id"), childColumns = arrayOf("user_id"), onDelete = ForeignKey.CASCADE),
    ForeignKey(entity = Quiz::class, parentColumns = arrayOf("quiz_id"), childColumns = arrayOf("quiz_id"), onDelete = ForeignKey.CASCADE)],
    indices = [Index(value=["parent_question_id"]), Index(value=["user_id"]), Index(value=["quiz_id"])])
data class MultipleChoiceResponse(
    @PrimaryKey val response_id: String,
    val parent_question_id: String,
    val answer: String,
    val user_id: String,
    val quiz_id: String
) : Parcelable