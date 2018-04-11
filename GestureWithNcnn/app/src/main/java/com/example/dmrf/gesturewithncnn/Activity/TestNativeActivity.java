package com.example.dmrf.gesturewithncnn.Activity;


import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;

import com.example.dmrf.gesturewithncnn.R;
import com.example.dmrf.gesturewithncnn.TestProcess.GestureTest;
import com.example.dmrf.gesturewithncnn.TestProcess.SignalTest;
import com.example.dmrf.gesturewithncnn.Utils.VerifyPermission;

public class TestNativeActivity extends Activity {
    private Button ncnn;
    private Button signal;
    GestureTest gestureTest=new GestureTest(TestNativeActivity.this);
    SignalTest signalTest=new SignalTest();





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_native_layout);
        //VerifyPermission.R(TestNativeActivity.this);
        ncnn=findViewById(R.id.test_ncnn);
        signal=findViewById(R.id.test_signal);

        ncnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gestureTest.InitTest();
                gestureTest.DetectTest();
            }
        });

        signal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signalTest.SignalProcessTest_DemoNew();
                signalTest.SignalProcessTest_DemoL();
                signalTest.SignalProcessTest_DemoR();
            }
        });


    }
}
