package com.example.dmrf.gesturewithncnn.TestProcess;

import android.util.Log;

import com.example.dmrf.gesturewithncnn.JniClass.SignalProcess;

public class SignalTest {
    private SignalProcess signalProcess = new SignalProcess();

    public void SignalProcessTest_DemoNew() {

        signalProcess.DemoNew();
        Log.i("signalprocess", "demo_new");
    }

    public void SignalProcessTest_DemoL() {
        short[] bsRecord = new short[4400];
        double[] di = new double[110];
        double[] tempIIL = new double[880];
        double[] tempQQL = new double[880];
        signalProcess.DemoL(bsRecord, di, tempIIL, tempQQL);
        Log.i("signalprocess", "demo_L");
    }


    public void SignalProcessTest_DemoR() {

            short[] bsRecord = new short[4400];
            double[] di = new double[110];
            double[] tempIIL = new double[880];
            double[] tempQQL = new double[880];
            signalProcess.DemoR(bsRecord, di, tempIIL, tempQQL);
            Log.i("signalprocess", "demo_R");



    }

}
