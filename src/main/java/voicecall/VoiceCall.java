/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package voicecall;

import javax.swing.JFrame;

/**
 *
 * @author ACER
 */
public class VoiceCall extends Thread
{

    /**
     * @param args the command line arguments
     */
    public static boolean calling = false;
    private String name;
    private String IP;
    
    public VoiceCall(String name, String IP){
        this.name = name;
        this.IP = IP;
    }

    
    @Override
    public void run()
    {
        Client client = new Client(name,IP);
        System.out.println(IP);
        client.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        client.setVisible(true);
    }
    
}
