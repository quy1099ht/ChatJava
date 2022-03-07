/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voicecall;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.TargetDataLine;

/**
 *
 * @author ACER
 */
public class RecorderThread extends Thread
{
    public TargetDataLine audio_in = null;
    public DatagramSocket socket;
    byte byteBuff[] = new byte[512];
    public InetAddress serverIp ;
    public int serverPort;
    @Override
    public void run(){
        int i = 0;
        while(VoiceCall.calling){
            try
            {
                audio_in.read(byteBuff, 0, byteBuff.length);
                DatagramPacket data = new DatagramPacket(byteBuff,byteBuff.length,serverIp,serverPort);
                System.out.println("Send : #"+i++);
                socket.send(data);
            } catch (IOException ex)
            {
                Logger.getLogger(RecorderThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public RecorderThread()
    {
    }

    public RecorderThread(DatagramSocket socket, InetAddress serverIp, int serverPort,TargetDataLine audioIn)
    {
        this.socket = socket;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.audio_in = audioIn;
    }
}
