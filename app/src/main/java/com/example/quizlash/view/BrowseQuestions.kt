package com.example.quizlash.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.quizlash.R
import com.example.quizlash.viewmodel.BrowseQuestionsViewModel

class BrowseQuestions : Fragment() {

    companion object {
        fun newInstance() = BrowseQuestions()
    }

    private lateinit var viewModel: BrowseQuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.browse_questions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BrowseQuestionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
