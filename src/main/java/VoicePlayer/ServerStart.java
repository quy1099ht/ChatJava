/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VoicePlayer;

import java.net.DatagramSocket;
import java.net.SocketException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author ACER
 */
public class ServerStart extends Thread
{
    public static boolean calling = false;
    public static int port = 8000;
    
    public static AudioFormat getAudioFormat(){
        float sampleRate = 8000.0F;
        int sampleSizeInBit = 16;
        int channel = 2;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate,sampleSizeInBit,channel,signed,bigEndian);
    }

    public ServerStart()
    {
    }
    public static SourceDataLine audio_in;
    
    public static void initAudio(){
        try
        {
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            if(!AudioSystem.isLineSupported(info))
            {
                System.out.println("Not supported");
                System.exit(0);
            }
            audio_in = (SourceDataLine)AudioSystem.getLine(info);
            audio_in.open(format);
            audio_in.start();
            PlayerThread player = new PlayerThread();
            try
            {
                player.socket = new DatagramSocket(port);
            } catch (SocketException ex)
            {
                
            }
            player.audio_in = audio_in;
            ServerStart.calling = true;
            player.start();
        } catch (LineUnavailableException ex)
        {
            
        }
    }
    
    @Override
    public void run()
    {
        initAudio();
    }

}
