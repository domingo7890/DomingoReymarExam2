package com.domingo.practical_exam_domingo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    CheckBox[] checkBoxes = new CheckBox[8];
    EditText etComments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxes[0] = findViewById(R.id.checkBox);
        checkBoxes[1] = findViewById(R.id.checkBox2);
        checkBoxes[2] = findViewById(R.id.checkBox3);
        checkBoxes[3] = findViewById(R.id.checkBox4);
        checkBoxes[4] = findViewById(R.id.checkBox5);
        checkBoxes[5] = findViewById(R.id.checkBox6);
        checkBoxes[6] = findViewById(R.id.checkBox7);
        checkBoxes[7] = findViewById(R.id.checkBox8);
        etComments = findViewById(R.id.editText2);
    }
    public void save(View v){
        FileOutputStream writer = null;
        String comments;
        comments = etComments.getText().toString();
        int etCLength = etComments.length();
        try {
            writer = openFileOutput("Events.txt", MODE_PRIVATE);
            for(CheckBox checkbox : checkBoxes){
                if(checkbox.isChecked()){
                    writer.write(checkbox.getText().toString().getBytes());
                    writer.write("\n".getBytes());
                }
            }
        writer.write(("Comments: \n" + comments).getBytes());
        } catch (FileNotFoundException e) {
            Log.d("Error", "File not found");
        } catch (IOException e) {
            Log.d("Error", "IOException");
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                Log.d("Error", "File not found in writer.close()");
            }
        }
        Toast.makeText(this,"Data Saved... ", Toast.LENGTH_LONG).show();
    }
    public void next(View v){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
