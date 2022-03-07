/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testing;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author ACER
 */
public class ReadChat extends Thread {
    public String read;
    BufferedReader inFromServer;

    public ReadChat()
    {
        
    }
    public ReadChat(Socket s) throws IOException
    {
        read = "";
        inFromServer = new BufferedReader(new InputStreamReader(s.getInputStream()));
    }
    @Override
    public void run()
    {
        while(true)
        {
            try{
                read = inFromServer.readLine();
            }
            catch(Exception e)
            {

            }
        }
        
    }
    /*
    for(String line = read(); line != null; line = read())
        masterList.add(line);
    */
}
