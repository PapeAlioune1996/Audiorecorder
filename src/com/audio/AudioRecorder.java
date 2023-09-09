package com.audio;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;


public class AudioRecorder {
	  
	  private AudioFormat audioFormat;
	    private TargetDataLine targetDataLine;

	    //demarer l'enregistrement
	    public void startRecording(String outputFileName) {
	        try {
	            audioFormat = new AudioFormat(16000, 16, 2, true, true);
	            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
	            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
	            targetDataLine.open(audioFormat);
	            targetDataLine.start();

	            AudioInputStream audioInputStream = new AudioInputStream(targetDataLine);

	            File audioFile = new File(outputFileName);
	            AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    //arreter l'enregistrement
	    public void stopRecording() {
	        if (targetDataLine != null) {
	            targetDataLine.stop();
	            targetDataLine.close();
	        }
	    }

	    public static void main(String[] args) {
	        AudioRecorder recorder = new AudioRecorder();
	        recorder.startRecording("output.wav");
	        System.out.println("Recording...");
	        try {
	            Thread.sleep(5000); // Enregistrement pendant 5 secondes
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        recorder.stopRecording();
	        System.out.println("Recording stopped.");
	    }
	}

