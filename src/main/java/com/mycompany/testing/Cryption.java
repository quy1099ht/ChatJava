/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testing;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author ACER
 */
interface Encrypt{
    String encrypt(String data,int key1);
}
interface Decrypt{
    String decrypt(String encrypted,int key);
}
public class Cryption implements Encrypt,Decrypt{
    @Override
    public String encrypt(String data,int key1)
    {
        String chars = data;
        int key = key1;
        char[] message = chars.toCharArray();
        String encrypted = "";
        for(char c : message)
        {
            c += key;
            encrypted += c; 
        }
        char giber = 'q';
        Random generator = new Random();
        int value = generator.nextInt(20) + 1;
        giber += value;
        encrypted += giber;
        return encrypted;
    }

    @Override
    public String decrypt(String encrypted,int key)
    {
        char[] a = encrypted.toCharArray();
        String decrypted = "";
        for(char c : a)
        {
            c -= key;
            decrypted += c; 
        }
        ArrayList<String> lis = new ArrayList();
        char[] b = decrypted.toCharArray();
        for(char c : b)
        {
            lis.add(String.valueOf(c));
        }
        lis.remove(lis.size()-1);
        decrypted = "";
        for(String s : lis)
        {
            decrypted += s;
        }
        System.out.println(""+ decrypted);
        return "";
    }

    
}
