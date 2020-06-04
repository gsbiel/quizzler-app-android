package com.example.quizzler.quizzler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuizzlerViewModel: ViewModel() {

    private data class Question(val question: String, val answer: Boolean)

    companion object{
        private val questionBank = listOf(
                Question("A slug's blood is green.", true),
                Question("Approximately one quarter of human bones are in the feet.", true),
                Question("The total surface area of two human lungs is approximately 70 square metres.", true),
                Question("In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.", true),
                Question("In London, UK, if you happen to die in the House of Parliament, you are technically entitled to a state funeral, because the building is considered too sacred a place.", false),
                Question("It is illegal to pee in the Ocean in Portugal.", true),
                Question("You can lead a cow down stairs but not up stairs.", false),
                Question("Google was originally called 'Backrub'.", true),
                Question("Buzz Aldrin's mother's maiden name was 'Moon'.", true),
                Question("The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.", false),
                Question("No piece of square dry paper can be folded in half more than 7 times.", false),
                Question("Chocolate affects a dog's heart and nervous system; a few ounces are enough to kill a small dog.", true)
        )
    }

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