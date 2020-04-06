package com.example.quizlash.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

import com.example.quizlash.R
import com.example.quizlash.service.model.MultipleChoiceQuestion
import com.example.quizlash.viewmodel.ActiveQuestionViewModel
import kotlinx.android.synthetic.main.active_question_fragment.*

class ActiveQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = ActiveQuestionFragment()
    }

    private lateinit var viewModel: ActiveQuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.active_question_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ActiveQuestionViewModel::class.java)
        // Other code to setup the activity...

        // Create the observer which updates the UI.
        val activeQuestionObserver = Observer<MultipleChoiceQuestion> { newQuestion ->
            // Update the UI, in this case, a TextView.
            activeQuestionTextView.text = newQuestion.prompt
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.activeQuestion.observe(this, activeQuestionObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_active_question).setOnClickListener {
            findNavController().navigate(R.id.action_activeQuestionFragment_to_FirstFragment)
        }
    }
}