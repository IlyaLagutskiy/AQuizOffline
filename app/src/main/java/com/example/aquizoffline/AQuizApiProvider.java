package com.example.aquizoffline;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AQuizApiProvider {

    private static final String AQUIZ_API_QUESTIONS = "questions.json";
    private static final String AQUIZ_API_SCORES = "scores.json";

    public ArrayList<Question> getQuestions(Activity context) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            InputStream is = context.getAssets().open(AQUIZ_API_QUESTIONS);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");
            JSONObject jsonObject = new JSONObject(json);
            int questionsCount = jsonObject.getInt("questions_count");
            int answersCount = jsonObject.getInt("answers_count");
            JSONArray qsjson = jsonObject.getJSONArray("questions");
            JSONObject qjson;
            for (int i = 0; i < questionsCount; i++) {
                qjson = qsjson.getJSONObject(i);
                Question question = new Question(answersCount);
                question.parseJSON(qjson);
                questions.add(question);
            }
        } catch (Exception ex) {
            Log.d(KEYS.LOGS_ASYNC, ex.getMessage());
        }
        return questions;
    }

    public ArrayList<UserData> getScore(){
        ArrayList<UserData> userData = new ArrayList<>();
//        try {
//            JSONObject jsonObject = new ApiRequest().execute(AQUIZ_API_SCORES).get();
//            int usersCount = jsonObject.getInt("users_count");
//            JSONObject users = jsonObject.getJSONObject("users");
//            Map<String, Integer> usersMap = new Gson().fromJson(
//                    users.toString(), new TypeToken<HashMap<String, Integer>>() {}.getType()
//            );
//            for (Map.Entry<String, Integer> entry: usersMap.entrySet()){
//                UserData data = new UserData(entry.getKey(), entry.getValue());
//                userData.add(data);
//            }
//        } catch (Exception ex) {
//            Log.d(KEYS.LOGS_ASYNC, ex.getMessage());
//        }
        return userData;
    }

//    public void sendQuizScore(String username, int score) {
//        JSONObject json = new JSONObject();
//        try {
//            json.put("score", score);
//            json.put("username", username);
//        } catch (Exception ex) {
//            Log.d(KEYS.LOGS_ASYNC, ex.getMessage());
//        }
//        new ApiPost().execute(AQUIZ_API_UPDATE_USERDATA, json.toString());
//    }

//    private static class ApiRequest extends AsyncTask<String, String, JSONObject> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(JSONObject jsonObject) {
//            super.onPostExecute(jsonObject);
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected JSONObject doInBackground(String... strings) {
//            try {
//                String filename = strings[0];
//
//
//
//                StringBuffer json = new StringBuffer(2048);
//                String tmp = "";
//                while ((tmp = reader.readLine()) != null)
//                    json.append(tmp).append("\n");
//                reader.close();
//
//                JSONObject data = new JSONObject(json.toString());
//                Log.d(KEYS.LOGS_ASYNC, "Receiver JSON: " + json.toString());
//                return data;
//            } catch (Exception ex) {
//                Log.d(KEYS.LOGS_ASYNC, "Exception: " + ex.getMessage());
//                return null;
//            }
//        }
//    }

//    private static class ApiPost extends AsyncTask<String, String, Void> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Void voids) {
//            super.onPostExecute(voids);
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected Void doInBackground(String... strings) {
//            try {
//                URL url = new URL(strings[0]);
//                HttpURLConnection connection =
//                        (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setRequestProperty("Content-Type", "application/json; utf-8");
//                connection.setRequestProperty("Accept", "application/json");
//                connection.setDoOutput(true);
//                connection.connect();
//
//                String dataToSend = strings[1];
//
//                OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
//                os.write(dataToSend);
//
//                os.flush();
//                os.close();
//
//                Log.i(KEYS.LOGS_ASYNC, String.valueOf(connection.getResponseCode()));
//                Log.i(KEYS.LOGS_ASYNC, connection.getResponseMessage());
//
//                connection.disconnect();
//            } catch (Exception e) {
//                Log.d(KEYS.LOGS_ASYNC, "Exception: " + e.getMessage());
//            }
//            return null;
//        }
//    }

}
