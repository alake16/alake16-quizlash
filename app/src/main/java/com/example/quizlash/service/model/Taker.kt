package com.example.quizlash.service.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Taker(
    @PrimaryKey val user_id: String,
    val nickname: String
) : Parcelable