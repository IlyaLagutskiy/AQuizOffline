package com.example.aquizoffline;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class Quiz extends FragmentActivity implements onAnswerSelectedListener {

    private final static int PAGE_COUNT = 10;
    ViewPager pager;
    PagerAdapter adapter;
    ArrayList<Question> questions;
    int answersCount;
    int respondedQuestions;
    int score;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        initiate();
    }

    @Override
    public void onBackPressed() {
        finishQuiz();
    }

    private void initiate() {
        Intent intent = getIntent();
        answersCount = intent.getIntExtra(KEYS.NUMBER_OF_ANSWERS, 4);
        username = intent.getStringExtra(KEYS.USERNAME);
        questions = new ArrayList<>();
        questions = new AQuizApiProvider().getQuestions(this);
        pager = findViewById(R.id.viewPager);
        adapter = new QuizPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        respondedQuestions = 0;
        score = 0;
    }

    @Override
    public void changeRespondedQuestions(boolean isCorrectAnswer) {
        Log.d(KEYS.LOGS_QUIZ, "callback");
        respondedQuestions++;
        if (isCorrectAnswer) {
            score += 100 / answersCount;
        }
        if (respondedQuestions == PAGE_COUNT) {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result")
                .setMessage("Результат: " + score)
                .setCancelable(false)
                .setPositiveButton("Я молодец", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        new AQuizApiProvider().sendQuizScore(username, score);
                        finish();
                    }

                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    protected class QuizPagerAdapter extends FragmentPagerAdapter {

        public QuizPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Question q = questions.get(position);
            q.setAnswersCount(answersCount);
            return FragmentQuiz.newInstance(q);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }
}
