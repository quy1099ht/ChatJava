/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ACER
 */
public class FileServer extends Thread
{

    public void receiveFile(ServerSocket sv)
    {
        try
        {

            Socket s = sv.accept();
            DataInputStream dataInputStream = new DataInputStream(s.getInputStream());

            // Read the size of the file name so know when to stop reading.
            int fileNameLength = dataInputStream.readInt();
            // If the file exists
            if (fileNameLength > 0)
            {
                // Byte array to hold name of file.
                byte[] fileNameBytes = new byte[fileNameLength];

                // Read from the input stream into the byte array.
                dataInputStream.readFully(fileNameBytes, 0, fileNameBytes.length);

                // Create the file name from the byte array.
                String fileName = new String(fileNameBytes);
                // Read how much data to expect for the actual content of the file.
                int fileContentLength = dataInputStream.readInt();
                System.out.println("File length = " + fileContentLength);
                // If the file exists.
                if (fileContentLength > 0)
                {
                    // Array to hold the file data.
                    byte[] fileContentBytes = new byte[fileContentLength];
                    // Read from the input stream into the fileContentBytes array.
                    dataInputStream.readFully(fileContentBytes, 0, fileContentBytes.length);

                    int output = JOptionPane.showConfirmDialog(null, "Do you want to save the file " + fileName + " ?", "Waiting...", JOptionPane.YES_NO_OPTION);
                    if (output == JOptionPane.YES_OPTION)
                    {

                        File fileToDownload = new File(fileName);
                        try
                        {
                            System.out.println(fileToDownload.getAbsolutePath());
                            // Create a stream to write data to the file.
                            FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);
                            // Write the actual file data to the file.
                            fileOutputStream.write(fileContentBytes);
                            // Close the stream.
                            fileOutputStream.close();

                        } catch (IOException ex)
                        {
                            ex.printStackTrace();
                        }
                        
                        String fileType = fileName.substring(fileName.length() - 3, fileName.length());
                        if (fileType.endsWith("png") || fileType.endsWith("gif"))
                        {
                            int a = JOptionPane.showConfirmDialog(null, "Do you want to open " + fileName + " ?", "Waiting...", JOptionPane.YES_NO_OPTION);
                            if(a == JOptionPane.YES_OPTION)
                            {
                                Thread1 t1 = new Thread1(fileToDownload.getAbsolutePath());
                                t1.start();
                            }
                        }
                    }
                }
                s.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        ServerSocket sv;
        try
        {
            sv = new ServerSocket(6000);
            while (true)
            {
                receiveFile(sv);
            }
        } catch (IOException ex)
        {
            Logger.getLogger(FileServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

class MyCanvas extends JComponent
{
    String url;
    public MyCanvas(String ul)
    {
        url = ul;
    }

    public void paint(Graphics g)
    {
        String url = this.url;
        //Plz change the url on other computer

        Graphics2D g2d = (Graphics2D) g;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(url);
        g2d.drawImage(image, 0, 0, this);
    }
}

class Thread1 extends Thread
{
    String url;
    public Thread1(String ul)
    {
        url = ul;
    }

    @Override
    public void run()
    {
        JFrame window = new JFrame(url);
        MyCanvas canvas = new MyCanvas(url);
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setLayout(new BoxLayout(window.getContentPane(), BoxLayout.Y_AXIS));

        window.getContentPane().add(canvas);
        window.pack();
        window.setVisible(true);
    }

}
