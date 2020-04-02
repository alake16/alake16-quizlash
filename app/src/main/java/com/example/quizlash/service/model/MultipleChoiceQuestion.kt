package com.example.quizlash.service.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class MultipleChoiceQuestion(
    @PrimaryKey val question_id: String,
    val answer: String,
    val choices: List<String>,
    val prompt: String,
    val quiz_id: String
) : Parcelable