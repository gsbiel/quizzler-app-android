package com.example.quizzler;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView questionLabel;
    private ProgressBar progressBar;
    private Button firstAnswerBtn;
    private Button secondAnswerBtn;
    private QuizzlerBrain quizzlerBrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        this.quizzlerBrain = new QuizzlerBrain();
//
//        this.registerForEvents();
//
//        this.updateUI();
    }



    private void registerForEvents() {

        this.firstAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizzlerBrain.checkAnswer(true)){
                    firstAnswerBtn.setBackgroundColor(Color.rgb(53,229,59));
                }else{
                    firstAnswerBtn.setBackgroundColor(Color.rgb(255,0 ,0));
                }
                new NextQuestionTimer(firstAnswerBtn).execute();
            }
        });

        this.secondAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizzlerBrain.checkAnswer(false)){
                    secondAnswerBtn.setBackgroundColor(Color.rgb(53,229,59));
                }else{
                    secondAnswerBtn.setBackgroundColor(Color.rgb(255,0 ,0));
                }
                new NextQuestionTimer(firstAnswerBtn).execute();
            }
        });
    }

    private void updateUI(){
        this.firstAnswerBtn.setBackgroundColor(Color.rgb(204,204,204));
        this.secondAnswerBtn.setBackgroundColor(Color.rgb(204,204,204));
        this.questionLabel.setText(this.quizzlerBrain.getCurrentQuestion());
        this.scoreLabel.setText("Score: " + Integer.toString(this.quizzlerBrain.getScore()));
        this.progressBar.setProgress((int) this.quizzlerBrain.getProgress());
    }


    private class NextQuestionTimer extends AsyncTask<Void, Void, Void> {
        Button button;
        NextQuestionTimer(Button button){
            this.button = button;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            quizzlerBrain.nextQuestion();
            updateUI();
        }
    }
}
