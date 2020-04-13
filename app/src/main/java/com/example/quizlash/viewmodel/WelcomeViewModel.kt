package com.example.quizlash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizlash.service.model.Taker

class WelcomeViewModel : ViewModel() {
    val userInfo: MutableLiveData<Taker> by lazy {
        MutableLiveData<Taker>()
    }

    fun createUser(name: String) {
        var taker = Taker("random", name)
        // TODO: figure out where to pass this info in association with the Session object since the
        //  user has still not yet decided if they are creating a new session (if they are an
        //  instructor) or entering an existing session
    }
}