package com.example.starchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Random;

public class User1choose extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton radioButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1choose);
        radioGroup = findViewById(R.id.radioGroup);
        textView = findViewById(R.id.chooseOneText);
        Button confirm = findViewById(R.id.next_step);
        confirm.setOnClickListener(unused -> confirmClicked());
    }
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        //radioButton = findViewById(radioId);
        if (radioId == -1) {
            ChessPanel.aChoice = R.drawable.geoff1;
        }
        if (radioId == R.id.checkGeoff1_u1) {
            ChessPanel.aChoice = R.drawable.geoff1;
        }
        if (radioId == R.id.checkGeoff2_u1) {
            ChessPanel.aChoice = R.drawable.geoff2;
        }
        if (radioId == R.id.checkBen_u1) {
            ChessPanel.aChoice = R.drawable.ben;

        }
    }
    public void confirmClicked() {
        Intent intent = new Intent(this, User2choose.class);
        startActivity(intent);
    }
}
