package com.example.starchess;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Main2Activity extends AppCompatActivity {
    private ChessPanel chessPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        chessPanel = findViewById(R.id.playPanel);
        if (ChessPanel.gameOver) {
            AlertDialog.Builder alertcontent = new AlertDialog.Builder(Main2Activity.this);
            alertcontent.setMessage("GameOver").setCancelable(false)
                    .setPositiveButton("NewGame", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            newgame();
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = alertcontent.create();
            alert.setTitle("GameOver");
            alert.show();
        }
    }

    private void newgame() {
        chessPanel.restart();
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
