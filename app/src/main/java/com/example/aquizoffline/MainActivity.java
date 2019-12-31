package com.example.aquizoffline;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int numberOfAnswers = 2;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button play = findViewById(R.id.ButtonPlay);
        final Button score = findViewById(R.id.ButtonScore);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Easy:
                        numberOfAnswers = 2;
                        break;
                    case R.id.Medium:
                        numberOfAnswers = 3;
                        break;
                    case R.id.Hard:
                        numberOfAnswers = 4;
                        break;

                    default:
                        break;
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askUsernameAndRun();
            }
        });

        score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentScore = new Intent(MainActivity.this, Score.class);
//                startActivity(intentScore);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Scores unavailable in offline version. Please use A.Quiz App", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void startQuiz() {
        Intent intentQuiz = new Intent(MainActivity.this, Quiz.class);
        intentQuiz.putExtra(KEYS.NUMBER_OF_ANSWERS, numberOfAnswers);
        intentQuiz.putExtra(KEYS.USERNAME, username);
        startActivity(intentQuiz);
    }

    private void askUsernameAndRun() {
        try {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            final EditText editText = new EditText(this);
//            editText.setText("");
//            builder.setTitle("Username")
//                    .setCancelable(false)
//                    .setPositiveButton("START QUIZ", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            username = editText.getText().toString();
//                            Log.d(KEYS.LOGS_MAIN, "username: " + username);
//                            if(username.matches(""))
                                username = "user";
//                            Log.d(KEYS.LOGS_MAIN, "username: " + username);
                            startQuiz();
//                        }
//                    });
//            builder.setView(editText);
//            AlertDialog alert = builder.create();
//            alert.show();
        } catch (Exception ex) {
            Log.d(KEYS.LOGS_MAIN, ex.getMessage());
        }
    }


}
