package com.cat.jiale.store;


import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

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
        if (bitmap!=null) {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            //100代表不压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
//        fileOutputStream.write(bitmap.getNinePatchChunk());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        }
        return false;
    }

    public String getSdpath(){
        File sdpath=null;
        //判断sd卡是否存在
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            //存在就获取内存卡根目录
            sdpath=Environment.getExternalStorageDirectory();
        }
        return sdpath.toString();
    }
    public boolean ready(){
        String sdpath = getSdpath();
        if(sdpath!=null){
            File file=getHome();
            Log.d("path",file.toString());
            if(!file.exists()){
                boolean mkdir = file.mkdir();
                Log.d("文件夹",mkdir+"");
                File file1Help=new File(file,"Log.txt");
                if(!file1Help.exists()){
                    try {
                        boolean newFile = file1Help.createNewFile();
                        Log.d("创建文件",newFile+"");
                        FileOutputStream fileOutputStream=new FileOutputStream(file1Help,true);
                        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream);
                        BufferedWriter bufferedWriter=new BufferedWriter(outputStreamWriter);
                        bufferedWriter.write("本文件夹请勿删除!!!");
                        bufferedWriter.write("\r\n");
                        bufferedWriter.write("文件夹"+mkdir+"  创建文件"+newFile);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        Log.d("succes","YES");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.d("jiale","创建");
                }
            }
            return true;
        }
        return false;
    }
    public File getHome(){
        return new File(new File(getSdpath()),"jiale");
    }
    public void addLog(String msg,String log) {
    if (ready()){
        File logFile = new File(getHome(), "Log.txt");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(logFile, true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(msg+":  "+log);
            bufferedWriter.write("\r\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("jiale", "创建");
    }
    }

}
