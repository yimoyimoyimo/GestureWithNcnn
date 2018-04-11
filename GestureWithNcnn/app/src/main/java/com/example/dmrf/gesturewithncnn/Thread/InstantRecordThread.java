package com.example.dmrf.gesturewithncnn.Thread;

import android.content.Context;
import android.os.Message;
import android.widget.Toast;

import com.example.dmrf.gesturewithncnn.JavaBean.DataBean;
import com.example.dmrf.gesturewithncnn.JavaBean.GlobalBean;
import com.example.dmrf.gesturewithncnn.JniClass.SignalProcess;
import com.example.dmrf.gesturewithncnn.Utils.SendDataUtils;


/**
 * 即时录音线程
 * Created by dmrf on 18-3-15.
 */

public class InstantRecordThread extends Thread {
    private GlobalBean globalBean;
    private Context context;

    public InstantRecordThread(GlobalBean globalBean, Context context) {
        this.globalBean = globalBean;
        this.context = context;
    }

    @Override
    public void run() {
        short[] bsRecord = new short[globalBean.recBufSize];//recBufSize=4400

        double totPhase = 0;
        double NowPhase = 0;
        //--------------jni------------------------
        globalBean.signalProcess=new SignalProcess();
        globalBean.signalProcess.DemoNew();

        while (globalBean.flag == false) {
        }
        try {
            globalBean.audioRecord.startRecording();

        } catch (IllegalStateException e) {
            // 录音开始失败
            //Toast.makeText(context, "录音开始失败！", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            return;
        }
        int while_count = 0;
        int begin_while = 0;

        while (globalBean.flag)//大循环
        {


            //读取录音
            // short[] bsRecord = new short[recBufSize];//recBufSize=4400*2

            int Len = globalBean.audioRecord.read(bsRecord, 0, globalBean.recBufSize);


            double[] di = new double[110];
            double[] tempIIL = new double[880];
            double[] tempQQL = new double[880];


            globalBean.signalProcess.DemoL(bsRecord, di, tempIIL, tempQQL);


            while_count++;

            if (begin_while > 0) {
                globalBean.AddDataToList(globalBean.L_I, tempIIL);
                globalBean.AddDataToList(globalBean.L_Q, tempQQL);

            }


            if (while_count == 5 && begin_while == 0) {
                Message msg3 = new Message();
                msg3.what = 0;

                msg3.obj = ("start");
                globalBean.mHandler.sendMessage(msg3);
                while_count = 0;
                begin_while++;
            } else if (while_count == 10 && begin_while > 0)
            {

            //    SaveData();
                while_count = 0;
                begin_while++;
                Message msg2 = new Message();
                msg2.what = 0;

                msg2.obj = ("wait");
                globalBean.mHandler.sendMessage(msg2);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg3 = new Message();
                msg3.what = 0;

                msg3.obj = ("start");
                globalBean.mHandler.sendMessage(msg3);
            }
           if (globalBean.flag1==true) {

                Message msg2 = new Message();
                msg2.what = 0;

                msg2.obj = "stop";
                globalBean.mHandler.sendMessage(msg2);

                break;
            }


            double[] tempIIR = new double[880];
            double[] tempQQR = new double[880];
            globalBean.signalProcess.DemoR(bsRecord, di, tempIIR, tempQQR);



            NowPhase += totPhase / 2;
            while (NowPhase < 0) NowPhase += Math.PI * 2;
            while (NowPhase > Math.PI * 2) NowPhase -= Math.PI * 2;

        }//while end


    }

    private void SaveData() {

        DataBean dataBean = new DataBean();
        dataBean.setI0(globalBean.L_I[0].toString());
        dataBean.setI1(globalBean.L_I[1].toString());
        dataBean.setI2(globalBean.L_I[2].toString());
        dataBean.setI3(globalBean.L_I[3].toString());
        dataBean.setI4(globalBean.L_I[4].toString());
        dataBean.setI5(globalBean.L_I[5].toString());
        dataBean.setI6(globalBean.L_I[6].toString());
        dataBean.setI7(globalBean.L_I[7].toString());


        dataBean.setQ0(globalBean.L_Q[0].toString());
        dataBean.setQ1(globalBean.L_Q[1].toString());
        dataBean.setQ2(globalBean.L_Q[2].toString());
        dataBean.setQ3(globalBean.L_Q[3].toString());
        dataBean.setQ4(globalBean.L_Q[4].toString());
        dataBean.setQ5(globalBean.L_Q[5].toString());
        dataBean.setQ6(globalBean.L_Q[6].toString());
        dataBean.setQ7(globalBean.L_Q[7].toString());
        dataBean.setWhoandwhich(globalBean.whoandwhich);

        long time = System.currentTimeMillis();
        dataBean.setFilename(String.valueOf(time));

        SendDataUtils sendDataUtils = new SendDataUtils(dataBean, context, globalBean);
        sendDataUtils.execute("");
    }



}
