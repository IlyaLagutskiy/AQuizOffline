package com.example.aquizoffline;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question implements Parcelable {

    public static Creator<Question> CREATOR = new Creator<Question>() {

        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }

    };

    private String question;
    private ArrayList<String> answers;
    private String correctAnswer;
    private int answersCount;
    private int answersCountInJson;

    public Question(int answersCount) {
        this.answersCountInJson = answersCount;
        answers = new ArrayList<>();
    }

    public Question(Parcel parcel) {
        this.question = parcel.readString();
        this.correctAnswer = parcel.readString();
        this.answersCount = parcel.readInt();
        this.answers = parcel.readArrayList(null);
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }

    public void parseJSON(JSONObject json) {
        try {
            question = json.getString("question");
            correctAnswer = json.getString("correct");
            JSONObject answersObj = json.getJSONObject("answers");
            for (int i = 1; i <= answersCountInJson; i++) {
                answers.add(answersObj.getString("ans" + i));
            }
        } catch (Exception ex) {
            Log.d(KEYS.LOGS_PARSER, ex.getMessage());
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(correctAnswer);
        dest.writeInt(answersCount);
        dest.writeList(answers);
    }

    public ArrayList<String> getShuffleAnswers() {
        ArrayList<String> ans = new ArrayList<>();
        ans.add(answers.get(0));
        List<String> wrongAns = new ArrayList<>();
        for (int i = 1; i < answersCountInJson; i++) {
            wrongAns.add(answers.get(i));
        }
        Collections.shuffle(wrongAns);
        ans.addAll(wrongAns.subList(0, answersCount - 1));
        Collections.shuffle(ans);
        return ans;
    }

}
