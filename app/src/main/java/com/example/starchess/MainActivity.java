package com.example.starchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createGame;
    private ChessPanel chessPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chessPanel = findViewById(R.id.playPanel);
        //开始游戏
        createGame = findViewById(R.id.createGame);
        createGame.setOnClickListener(unused -> createGameClicked());
    }
    private void createGameClicked() {
        Intent intent = new Intent(this, TakePhoto.class);
        startActivity(intent);
    }
}
