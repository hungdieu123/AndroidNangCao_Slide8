package com.hungviet.androidnangcao_slide8;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OpenCamera(View view) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.CAMERA
            },
                    999);
        }else {
            Intent intent
                    =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,999);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== 999 && resultCode== RESULT_OK){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            ImageView imageView =findViewById(R.id.ivview);
            imageView.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"chụp ảnh không thành công",Toast.LENGTH_SHORT).show();
        }

    }

    public void OpenMedia(View view) {

//        MediaPlayer mediaPlayer =MediaPlayer.create(this,R.raw.cothamkhongve);
//        mediaPlayer.start();




        String url = "https://data25.chiasenhac.com/downloads/2036/6/2035613-5a4faa89/128/Co%20Tham%20Khong%20Ve%20-%20Phat%20Ho_%20Jokes%20Bii_%20T.mp3";
        Uri uri = Uri.parse(url);
        MediaPlayer mediaPlayer =MediaPlayer.create(this,uri);
        mediaPlayer.start();





        if (mediaPlayer.isPlaying()){
            mediaPlayer.reset();
            mediaPlayer.stop();
        }else {
            try {
                mediaPlayer.prepare();
                mediaPlayer.start();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
