package edu.chemeketa.zombieDice;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * File name: ZombieSounds.java
 *
 * Programmer: Alexander Molodyh Chemeketa Community College Class CIS234J
 * Created: May 22, 2015 10:38:03 PM Assignment: CIS234J Final Project
 */
/**
 * Class Name: ZombieSounds.java
 *
 * Description: This class is a Thread sound class. Its constructor takes in a
 * url for the sound that you want to play. You call the run method to play the
 * sound.
 *
 */
public class ZombieSounds extends Thread implements LineListener
{

    /*
     * A boolean to check if the audio is done or not.
     */
    private boolean audioDone;

    /*
     * A String to hold the URL for the audio file.
     */
    private String playSound = "";

    /**
     * Constructor passes in the String parameter which is the URL in to the
     * playSound String. Then audioDone is set to false.
     *
     * @param playSound takes in the URL of the sound file to play.
     */
    public ZombieSounds(String playSound)
    {
        this.playSound = playSound;
        audioDone = false;
    }

    /**
     * This is an overridden method from the Thread class. The method keeps
     * playing the sound until the audioDone boolean is set to true.
     */
    @Override
    public void run()
    {
        while (audioDone == false)
        {
            playSound();
        }
    }

    /**
     * This method creates a File from the URL given and crates an audioClip
     * that is playable. Then the audioClip starts and when it reaches the end,
     * the boolean audioDone is set to true.
     */
    private void playSound()
    {
        File audioFile = new File(playSound);

        /*
         * try to create the audio clip and play it.
         */
        try
        {
            /*
             * This lets us create an audio file from the URL.
             */
            AudioInputStream audioStream = AudioSystem
                    .getAudioInputStream(audioFile);

            /*
             * Create an audio format to get the format of the sound.
             */
            AudioFormat format = audioStream.getFormat();

            /*
             * Create an audio file data line so we can tell when it starts or
             * stops.
             */
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            /*
             * Create the clip and play it.
             */
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();

            while (audioDone == false)
            {
                // wait for the playback completes
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }

            /*
             * Close the audio file so its not running in the background.
             */
            audioClip.close();
            audioStream.close();

        } catch (UnsupportedAudioFileException ex)
        {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex)
        {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex)
        {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }

    /**
     * This method updates the lines of the audio file. With this we will know
     * when the sound file finishes and then the boolean audioDone will be set
     * to true to stop the thread.
     *
     * @param event takes in the update event.
     */
    @Override
    public void update(LineEvent event)
    {
        LineEvent.Type eventType = event.getType();

        if (eventType == LineEvent.Type.START)
        {

        }
        else if (eventType == LineEvent.Type.STOP)
        {
            audioDone = true;// Set the audioDone to true.
        }
    }
}
