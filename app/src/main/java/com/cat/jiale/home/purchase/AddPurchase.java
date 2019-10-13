package com.cat.jiale.home.purchase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.os.EnvironmentCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.cat.jiale.R;
import com.cat.jiale.store.SDCard;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AddPurchase extends AppCompatActivity {
    private Button button;
    private  Button save;
    private ImageView imageView;
    private Uri imageUri;
    public static File tempFile;
    private  SDCard sdCard;
    Bitmap bitmap;
    private static final int REQUEST_PERMISSION_CODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);
        imageView=findViewById(R.id.imgnow);
        button=findViewById(R.id.img);
        sdCard=new SDCard();
        button.setOnClickListener(v -> {
            //大于android6.0
            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.N){
                Toast.makeText(this,"6.0及以上",Toast.LENGTH_LONG).show();
                //检查权限
               if(!checkPermission()){
                   //请求授权
                    requestPermissions();
                }else{
                   takePhote();
               }
            }else{
                Toast.makeText(this,"6.0及以下",Toast.LENGTH_LONG).show();
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //加载路径
                Uri uri=Uri.fromFile(new File(sdCard.getHome()+"/first.png"));
                it.putExtra(MediaStore.EXTRA_OUTPUT,uri);
                startActivityForResult(it,2);
            }

        });
        imageView.setOnClickListener(l->{
           Intent intent=new Intent(this,ImgLook.class);
//           intent.putExtra("img",bitmap);
           startActivity(intent);
        });
        save=findViewById(R.id.save);
        //保存文件
        save.setOnClickListener(l->{
            sdCard.addLog("保存文件","操作开始");

            try {
                boolean b = sdCard.saveFile(new File(sdCard.getHome(), "first.jpg"), bitmap);
                if(!b){
                    Toast.makeText(this,"请先拍摄照片",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
                }
                sdCard.addLog("保存文件","操作结束"+b);
            } catch (IOException e) {
                sdCard.addLog("出现错误",e.getMessage());
                e.printStackTrace();
            }
            boolean good = sdCard.isGood();

        });

    }

    //检查权限
    private boolean checkPermission(){
        boolean haveCameraPermission= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED;
        boolean haveWritePermission=ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED;
        Toast.makeText(this,haveCameraPermission+"---"+haveWritePermission,Toast.LENGTH_LONG).show();
        return haveCameraPermission && haveWritePermission;
    }
    //请求权限
    @RequiresApi(Build.VERSION_CODES.M)
    private void requestPermissions(){
        requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION_CODE);
    }

    //请求权限后回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(this,"请求权限后回调",Toast.LENGTH_LONG).show();
        switch (requestCode){
            case REQUEST_PERMISSION_CODE:
                boolean allowAllPermission=false;
                for(int i=0; i < grantResults.length;i++){
                    //被拒绝授权
                    allowAllPermission=true;
                    if(grantResults[i]!=PackageManager.PERMISSION_GRANTED){
                        allowAllPermission=false;
                        break;
                    }
                }
                if(allowAllPermission){
                    takePhote();
                }else{
                    Toast.makeText(this,"需要授权",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //拍照
    private void takePhote(){
        //创建存储照片文件
        File file=new File(sdCard.getHome(),"first.png");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Uri mUri=null;
        if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.N) {
             mUri = FileProvider.getUriForFile(this, "com.cat.jiale.home.purchase.AddPurchase", file);
        }else{
           mUri=Uri.fromFile(file);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, 101);
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
//            Bundle bundle = data.getExtras();
//            bitmap = (Bitmap) bundle.get("data");
//            imageView.setImageBitmap(bitmap);
            try {
                FileInputStream is=new FileInputStream(sdCard.getHome()+"/first.png");
                bitmap = BitmapFactory.decodeStream(is);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}
