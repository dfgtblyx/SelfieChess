package com.example.starchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import static android.view.View.GONE;

public class User2choose extends AppCompatActivity {

    RadioGroup radioGroup2;
    RadioButton radioButton2;
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2choose);
        radioGroup2 = findViewById(R.id.radioGroup2);
        textView2 = findViewById(R.id.chooseTwoText);
        Button letsGo = findViewById(R.id.next_step2);
        RadioButton geoff1 = findViewById(R.id.checkGeoff1_u2);
        RadioButton geoff2 = findViewById(R.id.checkGeoff2_u2);
        RadioButton ben = findViewById(R.id.checkBen_u1);
        if (ChessPanel.aChoice == R.drawable.geoff1) {
            geoff1.setVisibility(GONE);
        }
        if (ChessPanel.aChoice == R.drawable.ben) {
            ben.setVisibility(GONE);
        }
        if (ChessPanel.aChoice == R.drawable.geoff2) {
            geoff2.setVisibility(GONE);
        }
        letsGo.setOnClickListener(unused -> letsgoClicked());
    }
    public void checkButton(View v) {
        int radioId = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = findViewById(radioId);
        if (radioId == -1) {
            ChessPanel.aChoice = R.drawable.geoff1;
        }
        if (radioId == R.id.checkGeoff1_u2) {
            ChessPanel.bChoice = R.drawable.geoff1;
        }
        if (radioId == R.id.checkGeoff2_u2) {
            ChessPanel.bChoice = R.drawable.geoff2;
        }
        if (radioId == R.id.checkBen_u2) {
            ChessPanel.bChoice = R.drawable.ben;

        }
    }
    private void letsgoClicked() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
