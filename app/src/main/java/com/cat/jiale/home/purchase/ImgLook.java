package com.cat.jiale.home.purchase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.cat.jiale.R;

public class ImgLook extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_look);
        imageView=findViewById(R.id.imgBig);
            Intent intent=getIntent();
            Bitmap bitmap=intent.getParcelableExtra("img");
            imageView.setImageBitmap(bitmap);
    }
}
