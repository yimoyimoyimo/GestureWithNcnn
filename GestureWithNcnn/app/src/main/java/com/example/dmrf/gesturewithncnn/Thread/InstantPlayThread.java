package com.example.dmrf.gesturewithncnn.Thread;


import com.example.dmrf.gesturewithncnn.JavaBean.GlobalBean;
import com.example.dmrf.gesturewithncnn.Utils.FrequencyPlayerUtils;

/**
 *  即时播放线程
 * Created by dmrf on 18-3-15.
 */

public class InstantPlayThread extends Thread {

    private GlobalBean globalBean;

    public InstantPlayThread(GlobalBean globalBean) {
        this.globalBean = globalBean;
    }

    @Override
    public void run() {
        globalBean.FPlay = new FrequencyPlayerUtils( globalBean.numfre,  globalBean.Freqarrary,globalBean.mHandler);
        globalBean.FPlay.palyWaveZ();
        while ( globalBean.flag) {
        }
        globalBean.FPlay.colseWaveZ();
    }
}
