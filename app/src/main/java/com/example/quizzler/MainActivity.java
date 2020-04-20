package com.example.quizzler;

import androidx.appcompat.app.AppCompatActivity;

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

        this.quizzlerBrain = new QuizzlerBrain();

        this.catchReferencesFromLayout();
        this.registerForEvents();

        this.updateUI();
    }

    private void catchReferencesFromLayout() {
        this.scoreLabel = findViewById(R.id.scoreLabel);
        this.firstAnswerBtn = findViewById(R.id.firstAnswer);
        this.secondAnswerBtn = findViewById(R.id.secondAnswer);
        this.questionLabel = findViewById(R.id.questionLabel);
        this.progressBar = findViewById(R.id.progressBar);
    }

    private void registerForEvents() {

        this.firstAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.secondAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateUI(){
        this.questionLabel.setText(this.quizzlerBrain.getCurrentQuestion());
        this.progressBar.setProgress((int) this.quizzlerBrain.getProgress());
    }
}
