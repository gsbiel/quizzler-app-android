package com.example.quizzler;

public class Question {
    private String question;
    private Boolean answer;

    Question(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public Boolean validateAnswer(Boolean answer){
        if(answer == this.answer){
            return true;
        }
        else{
            return false;
        }
    }

    public String getQuestion(){
        return this.question;
    }

}
