package com.example.quizzler;

public class QuizzlerBrain {

    private Question[] questions = {
            new Question("A slug's blood is green.", true),
            new Question("Approximately one quarter of human bones are in the feet.", true),
            new Question("The total surface area of two human lungs is approximately 70 square metres.", true),
            new Question("In West Virginia, USA, if you accidentally hit an animal with your car, you are free to take it home to eat.", true),
            new Question("In London, UK, if you happen to die in the House of Parliament, you are technically entitled to a state funeral, because the building is considered too sacred a place.", false),
            new Question("It is illegal to pee in the Ocean in Portugal.", true),
            new Question("You can lead a cow down stairs but not up stairs.", false),
            new Question("Google was originally called 'Backrub'.", true),
            new Question("Buzz Aldrin's mother's maiden name was 'Moon'.", true),
            new Question("The loudest sound produced by any animal is 188 decibels. That animal is the African Elephant.", false),
            new Question("No piece of square dry paper can be folded in half more than 7 times.", false),
            new Question("Chocolate affects a dog's heart and nervous system; a few ounces are enough to kill a small dog.", true)
    };

    private int currentQuestion;

    private int progress;

    private int score;

    QuizzlerBrain(){
        this.currentQuestion = 0;
        this.score = 0;
        this.progress = (int) ((((float) (this.currentQuestion + 1)) / (float) this.questions.length)*100);
    }

    public String nextQuestion(){
        if(this.currentQuestion < this.questions.length-1){
            this.currentQuestion += 1;
            this.progress = (int) ((((float) (this.currentQuestion + 1))  / (float) this.questions.length)*100);
        }else{
            this.currentQuestion = 0;
            this.progress = (int) ((((float) (this.currentQuestion + 1))  / (float) this.questions.length)*100);
            this.score = 0;
        }
        return this.getCurrentQuestion();
    }

    public String getCurrentQuestion(){
        return this.questions[this.currentQuestion].getQuestion();
    }

    public int getProgress(){
        return this.progress;
    }

    public int getScore(){
        return this.score;
    }

    public Boolean checkAnswer(Boolean answer){
        Boolean isCorrect = this.questions[this.currentQuestion].validateAnswer(answer);
        if(isCorrect){
            this.score += 10;
        }
        return isCorrect;
    }

}
