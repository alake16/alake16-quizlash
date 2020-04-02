package com.example.quizlash.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.quizlash.service.model.MultipleChoiceQuestion
import com.example.quizlash.service.repository.RepositoryImpl


class BrowseQuestionsViewModel : ViewModel {
    private var questionListObservable: LiveData<List<MultipleChoiceQuestion>>? = null

    constructor(application: Application) : super() {
        questionListObservable = RepositoryImpl.getInstance(application).getAllQuestions()
    }

    fun getQuestionListObservable() : LiveData<List<MultipleChoiceQuestion>>? {
        return questionListObservable
    }
}
