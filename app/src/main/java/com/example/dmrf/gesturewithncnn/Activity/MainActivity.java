package com.example.dmrf.gesturewithncnn.Activity;


import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dmrf.gesturewithncnn.JavaBean.GlobalBean;
import com.example.dmrf.gesturewithncnn.R;
import com.example.dmrf.gesturewithncnn.Utils.NetWorkUtils;
import com.example.dmrf.gesturewithncnn.Utils.VerifyPermission;

import java.io.IOException;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {

    private GlobalBean globalBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        if (Build.VERSION.SDK_INT >= 23) {
            VerifyPermission verifyPermission = new VerifyPermission(MainActivity.this);
            verifyPermission.RequestPermission();
        }

        Bmob.initialize(this, "9dbc988651cd8b0403a4d8e2566459e9");
        globalBean = new GlobalBean(MainActivity.this);

        try {
            Init();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private void Init() throws IOException {
        globalBean.btnPlayRecord = (Button) findViewById(R.id.btnplayrecord);
        globalBean.btnStopRecord = (Button) findViewById(R.id.btnstoprecord);
        globalBean.tvDist = (TextView) findViewById(R.id.textView1);
        globalBean.tvDist2 = (TextView) findViewById(R.id.textView2);
        globalBean.flag_small = (ImageView) findViewById(R.id.flag_small);
        globalBean.Init();

        if (globalBean.senddataflag){
            if (NetWorkUtils.getAPNType(MainActivity.this) == 0) {
                findViewById(R.id.no_network_worning).setVisibility(View.VISIBLE);
                return;
            }
        }


    }


}

