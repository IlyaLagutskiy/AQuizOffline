package com.example.aquizoffline;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Score extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initiate();
    }

    private void initiate() {
        try {
            UserData[] userData = new UserData[0];
            ArrayList<UserData> data = new AQuizApiProvider().getScore();
            Collections.sort(data, new Comparator<UserData>() {
                @Override
                public int compare(UserData o1, UserData o2) {
                    return o2.getScore() - o1.getScore();
                }
            });
            userData = data.toArray(userData);
            ListView listView = findViewById(R.id.list);
            listView.setAdapter(new scoreListAdapter(this, userData));
        } catch (Exception ex) {
            Log.d(KEYS.LOGS_SCORE, ex.getMessage());
        }
    }

    private class scoreListAdapter extends ArrayAdapter<UserData> {

        private UserData[] userData;
        private Activity context;

        scoreListAdapter(Activity context, UserData[] userData) {
            super(context, R.layout.row_score);
            this.userData = userData;
            this.context = context;
        }

        @Override
        public int getCount() {
            return userData.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            try {
                if (view == null) {
                    LayoutInflater inflater = context.getLayoutInflater();
                    view = inflater.inflate(R.layout.row_score, null);
                    holder = new ViewHolder();
                    holder.textUsername = view.findViewById(R.id.textUsername);
                    holder.textScore = view.findViewById(R.id.textScore);
                    view.setTag(holder);
                } else {
                    holder = (ViewHolder) view.getTag();
                }
                holder.textUsername.setText(userData[position].getUsername());
                holder.textScore.setText(Integer.toString(userData[position].getScore()));
            } catch (Exception ex) {
                Log.d(KEYS.LOGS_SCORE, ex.getMessage());
            }
            return view;
        }

        class ViewHolder {
            TextView textUsername;
            TextView textScore;
        }
    }


}
