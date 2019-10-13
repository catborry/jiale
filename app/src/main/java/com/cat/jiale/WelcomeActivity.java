package com.cat.jiale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.cat.jiale.home.HomePage;
import com.cat.jiale.store.SDCard;

import java.io.File;


public class WelcomeActivity extends AppCompatActivity {
    SDCard sdCard=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file=getStorageDir();
        handler.sendEmptyMessageDelayed(0,3000);
    }
//设置沉浸式
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            getHome();
        }
    };
    public void getHome(){
        startActivity(new Intent(WelcomeActivity.this, HomePage.class));
        finish();
    }
    //获取图片目录
    public File getStorageDir(){
//        File file=new File(Environment.getExternalStorageDirectory(),"jiale");
//        if(!file.mkdir()) {
//            Toast.makeText(this,",目录已存在",Toast.LENGTH_LONG).show();
//        }else{
//            Toast.makeText(this,"目录已创建",Toast.LENGTH_LONG).show();
//        }
//        Log.d("目录信息",file.toString());
//        return file;
        sdCard=new SDCard();
        boolean ready = sdCard.ready();
        if(!ready){
         Toast.makeText(this,"未检测到内存卡,请联系我",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"初始化完成,文件夹已创建",Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
