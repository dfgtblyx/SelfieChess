package com.example.starchess;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class TakeAnotherPhoto extends AppCompatActivity {
    private Button confirm;
    private ImageView mimageView;
    private static final int REQUEST_IMAGE_CAPTURE = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_another_photo);
        mimageView = findViewById(R.id.imageview2);
        ChessPanel.userB = BitmapFactory.decodeResource(getResources(), R.drawable.ben);
        mimageView.setImageBitmap(ChessPanel.userB);
        confirm = findViewById(R.id.user2confirm);
        confirm.setOnClickListener(unused -> confirmClicked());
    }
    public void takePicture(View view) {
        Intent photointent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (photointent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(photointent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            ChessPanel.userB = (Bitmap) extras.get("data");
            mimageView.setImageBitmap(ChessPanel.userB);
        }
    }
    public void confirmClicked() {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
