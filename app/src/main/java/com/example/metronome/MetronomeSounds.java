package com.example.metronome;

import java.util.concurrent.atomic.AtomicBoolean;

public class MetronomeSounds implements Runnable {
    private Thread metronome;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public void start(){
        metronome = new Thread(this);
        metronome.start();
    }


    public void run(){
        running.set(true);
        while (running.get()) {
            MainActivity.clickNoise.start();
            try {
                Thread.sleep(MetronomeFunction.BPMToDelay());
            }catch (InterruptedException e){

            }
        }
    }

    public void stop(){
        running.set(false);
    }
}
