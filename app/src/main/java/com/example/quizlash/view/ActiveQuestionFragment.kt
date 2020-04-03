package com.example.quizlash.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

import com.example.quizlash.R
import com.example.quizlash.viewmodel.ActiveQuestionViewModel

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
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button_active_question).setOnClickListener {
            findNavController().navigate(R.id.action_activeQuestionFragment_to_FirstFragment)
        }
    }

}
