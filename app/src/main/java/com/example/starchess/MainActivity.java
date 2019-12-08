package com.example.starchess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
//        choose();
    }
    private void createGameClicked() {
        Intent intent = new Intent(this, User1choose.class);
        startActivity(intent);
    }



}
