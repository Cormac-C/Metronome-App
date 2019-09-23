package com.example.metronome;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity{

    public TextView textElement;
    public Button playB;
    public Button stopB;
    public static MediaPlayer clickNoise;
    public boolean metronomeOn = false;
    public MetronomeSounds m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textElement = (TextView) findViewById(R.id.bpmValue);
        playB = findViewById(R.id.playButton);
        stopB = findViewById(R.id.stopButton);
        clickNoise = MediaPlayer.create(this, R.raw.click);
        m = new MetronomeSounds();
        m.start();
    }

    //Called when the user taps the Tap tempo button
    public void tempoTapped(View view) {
        updateOutput(MetronomeFunction.metronomeFunction());
    }

    public void playTapped(View view) throws InterruptedException {
        if (!metronomeOn) {
            metronomeOn = true;
            deactivatePlayButton();
            activateStopButton();
            m.run();
        }else{
            deactivateStopButton();
            activatePlayButton();
            m.stop();
        }
    }

    //Updates the tempo output
    public void updateOutput(double bpm) {
        String newLabel = (int) bpm + " bpm";
        textElement.setText(newLabel);
    }

    public void activateStopButton() {
        stopB.setVisibility(View.VISIBLE);
        stopB.setClickable(true);
    }

    public void activatePlayButton() {
        playB.setVisibility(View.VISIBLE);
        stopB.setClickable(true);
    }

    public void deactivateStopButton() {
        stopB.setVisibility(View.INVISIBLE);
        stopB.setClickable(false);
    }

    public void deactivatePlayButton() {
        playB.setVisibility(View.INVISIBLE);
        playB.setClickable(false);
    }

//    public void metroNoise (){
//        MediaPlayer mediaPlayer = MediaPlayer.create(View view, R.raw.click);
//    }
}
