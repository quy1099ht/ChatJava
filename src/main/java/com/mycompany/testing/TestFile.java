/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testing;

import Menu.Friend;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author ACER
 */
public class TestFile
{

    public static void initFriends(String IP, String content)
    {
        try
        {

            ArrayList<Friend> friends = new ArrayList<>();

            Socket socket = new Socket(IP, 443);

            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            //outToServer.writeBytes("Length" + "\n");

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //Đọc tin từ Server thông qua InputSteam đã nối với socket
            //int a = Integer.parseInt(inFromServer.readLine());

            
                outToServer.writeBytes(content + "\n");
                String b = inFromServer.readLine();
                System.out.println(b);
            

            socket.close();

        } catch (IOException ex)
        {
            //Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[])
    {
        initFriends("113.176.35.186","Name_OK");
    }
}
