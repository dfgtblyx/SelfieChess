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

public class TakePhoto extends AppCompatActivity {

    private Button confirm;
    private ImageView mimageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        mimageView = findViewById(R.id.imageview);
        ChessPanel.userA = BitmapFactory.decodeResource(getResources(), R.drawable.geoff1);
        mimageView.setImageBitmap(ChessPanel.userA);
        confirm = findViewById(R.id.user1confirm);
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
            ChessPanel.userA = (Bitmap) extras.get("data");
            mimageView.setImageBitmap(ChessPanel.userA);
        }

    }
    public void confirmClicked() {
        Intent intent = new Intent(this, TakeAnotherPhoto.class);
        startActivity(intent);
    }
}
