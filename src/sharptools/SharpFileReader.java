package sharptools;

/**
 * Title:        Sharp Tools Spreadsheet Package
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Oleg Lebedev
 * @version 1.0
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

 /**
  * This class provides functionality for reading files.
  */

public class SharpFileReader {

    /**
     * Default constructor.
     */
    public SharpFileReader()
    {
    }

    /* Found this routine on JavaWorld by Todd Webb
    http://www.javaworld.com/javaworld/jw-07-1998/jw-07-jar_p.html */
    public ImageIcon getImageIconFromJAR(String fileName)
    {
        if (fileName == null) return null;

        Image image = null;
        byte[] thanksToNetscape = null;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        InputStream in = null;

        try{
            URL imgURL = this.getClass().getResource("/" + fileName);
        Debug.println("got resource url: " + imgURL);

        Debug.println("open stream to file " + "sharptools.jar!/" + fileName);
            in = getClass().getResourceAsStream("/" + fileName);
            //in = ClassLoader.getSystemClassLoader().getResourceAsStream("sharptools.jar!/" + fileName);

        Debug.println("null?? " + (in == null));
            int length = in.available();
            thanksToNetscape = new byte[length];
            in.read( thanksToNetscape );
            image = toolkit.createImage( thanksToNetscape );
            //image = toolkit.getImage(imgURL);
        }
        catch(Exception exc){
            System.out.println( exc +" getting resource " +fileName );
            return null;
        }

        return new ImageIcon(image);
    }


    /**
     * This method opens a BufferedReader to the file with specified file name.
     */
    public BufferedReader getFileReader(String fileName)
    {
        if (fileName == null) return null;
        BufferedReader in = null;
        File file = new File(fileName);

        try {
              if (file.exists())
                {
                    Debug.println("config file exists");
                    in = new BufferedReader(new FileReader(file));
                }
                else // read it from jar
                {
                    Debug.println("try to open a jar");
                    java.util.jar.JarFile jar = new java.util.jar.JarFile("sharptools.jar");
                    Debug.println("found config file: " + jar.getEntry(file.getName()));
                    in = new BufferedReader(new InputStreamReader(jar.getInputStream(jar.getEntry(file.getName()))));
                }
            } catch (Exception e1)
            {
                System.out.println("Can not open configuration file: " + e1.getMessage());
            }

            // if not succeeded yet, open it from the applet jar
            if (in == null)
            {
                in = new BufferedReader(new InputStreamReader(
                            getClass().getResourceAsStream("/" + fileName)));
            }

            return in;
    }


}