package com.cat.jiale.store;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.cat.jiale.WelcomeActivity;

import java.io.File;

//存储处理类
public class SDCard {
    public String fileURL="store/sdCare";
    //判断路径是否可用
    public boolean isGood(){
        File file=new File(fileURL);
        if(!file.exists()){
            boolean mkdirs = file.mkdirs();
            if (mkdirs){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    //判断路径是否可读取存储
    public boolean isWork(File file){
        if(file.canRead()&&file.canWrite()){
            return true;
        }
        return false;
    }


}
