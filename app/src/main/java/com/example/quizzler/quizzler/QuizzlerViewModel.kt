package com.example.quizzler.quizzler

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.math.roundToInt

class QuizzlerViewModel: ViewModel() {

    private data class Question(val question: String, val answer: Boolean)

    // The fragment_quizzler layout accesses these variables to identify which question has been selected by the user.
    val firstAnswer: Boolean = true
    val secondAnswer: Boolean = false

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

        private val INDEX_WRONG_ANSWER = 0
        private val INDEX_RIGHT_ANSWER = 1
        private val INDEX_DEFAULT = 2
    }

    // List of questions ---------------------------------------------------
    private lateinit var questionList: List<Question>

    // Score ---------------------------------------------------------------
    private val score = MutableLiveData<Int>()
    val scoreString = Transformations.map(score) {
        it.toString()
    }

    // Current question -----------------------------------------------------
    private var currentQuestionIndex: Int = 0
    private val currentDataQuestion = MutableLiveData<Question>()
    val questionText = Transformations.map(currentDataQuestion){
        it.question
    }

    // Current progress -----------------------------------------------------
    private val _currentProgress = MutableLiveData<Int>()
    val currentProgress: LiveData<Int>
        get() = _currentProgress

    // Buttons background color
    private val _firstButtonHexColor = MutableLiveData<Int>()
    val firstButtonHexColor: LiveData<Int>
        get() = _firstButtonHexColor
    private val _secondButtonHexColor = MutableLiveData<Int>()
    val secondButtonHexColor: LiveData<Int>
        get() = _secondButtonHexColor

    // transition state property
    private var isInTransitionState: Boolean =  false

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init {
        resetGame()
        _firstButtonHexColor.value = INDEX_DEFAULT
        _secondButtonHexColor.value = INDEX_DEFAULT
    }

    fun onAnswerSelected(answer: Boolean){
        if(!isInTransitionState){
            isInTransitionState = true
            updateScoreAndUIFor(answer)
        }
    }

    private fun updateScoreAndUIFor(answer: Boolean){
        if(answer == currentDataQuestion.value!!.answer){
            score.value = score.value!! + 10
            when(answer){
                true -> changeButtonColorTo(0, INDEX_RIGHT_ANSWER)
                else -> changeButtonColorTo(1,  INDEX_RIGHT_ANSWER)
            }
        }else{
            when(answer){
                true -> changeButtonColorTo(0, INDEX_WRONG_ANSWER)
                else -> changeButtonColorTo(1,  INDEX_WRONG_ANSWER)
            }
        }
    }

    private fun nextQuestion(){
        if(currentQuestionIndex < (questionList.size-1)){
            currentQuestionIndex += 1;
            currentDataQuestion.value = questionList[currentQuestionIndex]
            _currentProgress.value = ((currentQuestionIndex.toDouble()/questionList.size.toDouble())*100).roundToInt()
        }else{
            resetGame()
        }
    }

    private fun resetGame(){
        questionList = QuizzlerViewModel.questionBank.shuffled()
        currentQuestionIndex = 0;
        currentDataQuestion.value = questionList[currentQuestionIndex]
        score.value = 0
        _currentProgress.value = 0
    }

    private fun changeButtonColorTo(buttonIndex: Int, color: Int){
        uiScope.launch{
            when(buttonIndex){
                0 -> _firstButtonHexColor.value = color
                1 -> _secondButtonHexColor.value = color
            }

            withContext(Dispatchers.Default){
                Thread.sleep(1000)
            }

            when(buttonIndex){
                0 -> _firstButtonHexColor.value = INDEX_DEFAULT
                1 -> _secondButtonHexColor.value = INDEX_DEFAULT
            }
            nextQuestion()
            isInTransitionState = false
        }
    }

}