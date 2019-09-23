package com.example.metronome;

import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.List;

public class MetronomeFunction {
    //Common variables
    private static long lastTime = 0;
    private static long currentTime = 0;
    public static double bpm = 0;
    private static List<Long> times = new ArrayList<>();

    public static double metronomeFunction(){
        currentTime = System.currentTimeMillis();
        if (lastTime == 0){ //First time the button is pressed
            lastTime = currentTime;
        }else{
            long time = currentTime - lastTime;
            if(time > 3000){ //Been too long between taps,
                times = new ArrayList<>();
                lastTime = currentTime;
            }else{
                times.add(time);
                lastTime = currentTime;
                calculateBPM();
                return bpm;
            }
        }
        return 0;
    }

    //Takes a list of times between taps in milliseconds and returns an average of them in seconds
    public static double averageTime(){
        long total = 0;
        double average = 0;
        for (int i = 0; i < times.size(); i++) {
            total += times.get(i);
        }
        if (times.size()>0) {
            average = total / times.size();
        }
        return average/1000;
    }

    public static void calculateBPM(){
        bpm = Math.round((1/averageTime())*60);
    }

    public static long BPMToDelay(){return (long)(60/bpm*1000);}

}
