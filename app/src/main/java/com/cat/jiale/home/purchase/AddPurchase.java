package com.cat.jiale.home.purchase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.cat.jiale.R;

import java.io.File;

public class AddPurchase extends AppCompatActivity {
    private Button button;
    private ImageView imageView;
    private Uri imageUri;
    public static File tempFile;
    public static final int TAKE_PHOTO = 1;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        imageView=findViewById(R.id.imgnow);
        button=findViewById(R.id.img);
        button.setOnClickListener(v -> {
            Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(it,Activity.DEFAULT_KEYS_DIALER);

        });
        imageView.setOnClickListener(l->{
           Intent intent=new Intent(this,ImgLook.class);
           intent.putExtra("img",bitmap);
           startActivity(intent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==TAKE_PHOTO){
            Bundle bundle = data.getExtras();
            bitmap = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
