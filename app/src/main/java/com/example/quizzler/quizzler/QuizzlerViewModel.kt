package com.example.quizzler.quizzler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizzlerViewModel: ViewModel() {

//    companion object{
//        private val questionBank = listOf(
//
//        )
//    }

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _currentDataQuestion = MutableLiveData<String>()
    val currentDataQuestion: LiveData<String>
        get() = _currentDataQuestion

    private val _currentProgress = MutableLiveData<Int>()
    val currentProgress: LiveData<Int>
        get() = _currentProgress
}