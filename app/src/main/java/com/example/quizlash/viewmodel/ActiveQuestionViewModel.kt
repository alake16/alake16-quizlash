package com.example.quizlash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizlash.service.model.MultipleChoiceQuestion

class ActiveQuestionViewModel : ViewModel() {
    // Create a LiveData with a String
    val activeQuestion: MutableLiveData<MultipleChoiceQuestion> by lazy {
        MutableLiveData<MultipleChoiceQuestion>()
    }
    // TODO: Implement the ViewModel
}
