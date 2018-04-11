package com.example.dmrf.gesturewithncnn.Utils;

import android.content.Context;
import android.os.Environment;

import com.example.dmrf.gesturewithncnn.JniClass.GestureNcnn;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class InitGestureNcnnUtils {
    private Context context;

    public InitGestureNcnnUtils(Context context) {
        this.context = context;
    }


    public void InitSqueezeNcnn(GestureNcnn gestureNcnn) throws IOException {


        try {
            copyBigDataToSD("gesture.bin");

            copyBigDataToSD("gesture.param");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //模型初始化
        File sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        String sdPath = sdDir.toString() + "/gesturencnn/";
        boolean b = gestureNcnn.InitNcnn(sdPath);

    }


    private void copyBigDataToSD(String strOutFileName) throws IOException {

        File sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        File file = new File(sdDir.toString() + "/gesturencnn/");
        if (!file.exists()) {
            file.mkdir();
        }

        String tmpFile = sdDir.toString() + "/gesturencnn/" + strOutFileName;
        File f = new File(tmpFile);
        if (f.exists()) {
            return;
        }
        InputStream myInput;
        java.io.OutputStream myOutput = new FileOutputStream(sdDir.toString() + "/gesturencnn/" + strOutFileName);
        myInput = context.getAssets().open(strOutFileName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();

    }


}
