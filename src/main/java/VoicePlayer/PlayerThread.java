/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VoicePlayer;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author ACER
 */
public class PlayerThread extends Thread
{
    public DatagramSocket socket;
    public SourceDataLine audio_in;
    byte[] buffer = new byte[512];
    @Override
    public void run(){
        DatagramPacket incoming = new DatagramPacket(buffer,buffer.length);
        while(ServerStart.calling){
            try
            {
                socket.receive(incoming);
                buffer = incoming.getData();
                audio_in.write(buffer, 0, buffer.length);
            } catch (IOException ex)
            {
                Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        audio_in.close();
        audio_in.drain();
        System.out.println("Stop");
    }
}
