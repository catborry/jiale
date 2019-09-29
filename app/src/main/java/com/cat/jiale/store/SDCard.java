package com.cat.jiale.store;


import android.graphics.Bitmap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
    //保存文件
    public boolean saveFile(File file, Bitmap bitmap) throws IOException {
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        //100代表不压缩
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,fileOutputStream);
//        fileOutputStream.write(bitmap.getNinePatchChunk());
        fileOutputStream.flush();
        fileOutputStream.close();
        return true;
    }

}
