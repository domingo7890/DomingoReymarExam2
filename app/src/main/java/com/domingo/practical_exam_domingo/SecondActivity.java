package com.domingo.practical_exam_domingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {
    TextView enlisted, comments;
    String events;
    String[] eventsArray;
    String eventsStr;
    String commentsStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        enlisted = findViewById(R.id.textView);
        comments = findViewById(R.id.commentId);
    }
    @Override
    public void onStart() {
        super.onStart();
        try {
            FileInputStream reader = openFileInput("Events.txt");
            int token;
            int i;
            eventsStr = "";
            commentsStr = "";
            while ((token = reader.read()) != -1) {
                char event = (char) token;
                events += event;
            }
            eventsArray = events.split("\n");
            for(i = 0; i < eventsArray.length; i++) {
                if (eventsArray[i].contains("Comments:")) {
                    break;

                }
                eventsStr += eventsArray[i].replace("null", "") + "\n";
            }
            i++;
            while(i < eventsArray.length){
                commentsStr += eventsArray[i].replace("null", "") + "\n";
                i++;
            }
            enlisted.setText(eventsStr);
            comments.setText(commentsStr);
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IOException");
        }

    }
    public void send(View v){
        setTitle("Summary");
        Toast.makeText(this, "Registration Sent", Toast.LENGTH_SHORT).show();
    }
    public void previous(View v){
        Intent intent = new Intent(SecondActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
