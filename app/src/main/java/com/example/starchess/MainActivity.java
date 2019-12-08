package com.example.starchess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private ChessPanel chessPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chessPanel = (ChessPanel) findViewById(R.id.playPanel);
        //开始游戏
        Button createGame = findViewById(R.id.creategame);
        //createGame.setOnClickListener(unused -> createGameClicked());
        choose();
    }

    protected void choose() {
        //选择角色
        Button ben = findViewById(R.id.ben);
        Button geoff = findViewById(R.id.geoff);
        ben.setOnClickListener(unused -> clickben());
        geoff.setOnClickListener(unused -> clickgeoff());
    }

    private void clickben() {
        ChessPanel.turnofa = false;
    }
    private void clickgeoff() {
        ChessPanel.turnofa = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.id_restart) {
            chessPanel.restart();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
