package sharptools;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.lang.reflect.*;

/**
 * Title:        Sharp Tools Spreadsheet Package
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author Oleg Lebedev
 * @version 1.0
 */

public class SharpApplet extends JApplet {

  boolean isStandalone = false;
  String codebase;
  String archive;
  String classname;
  JButton viewButton = new JButton();


  /**Get a parameter value*/
  public String getParameter(String key, String def) {
    return isStandalone ? System.getProperty(key, def) :
      (getParameter(key) != null ? getParameter(key) : def);
  }

  /**Construct the applet*/
  public SharpApplet()
  {}


  /**Initialize the applet*/
  public void init() {
Debug.println("init called");

    try {
      codebase = this.getParameter("codebase", ".");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    try {
      archive = this.getParameter("archive", "sharptools.jar");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    try {
      classname = this.getParameter("classname", "sharptools.SharpTools");
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }


  /**
   * Component initialization
   */
  private void jbInit() throws Exception {
    viewButton.setBackground(Color.pink);
    viewButton.setFont(new java.awt.Font("Dialog", 1, 12));
    viewButton.setBorder(BorderFactory.createRaisedBevelBorder());
    viewButton.setText("View Reports");
    viewButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        viewButton_actionPerformed(e);
      }
    });
    this.setSize(new Dimension(120, 20));
    this.getContentPane().add(viewButton, BorderLayout.CENTER);
  }

  /**Start the applet*/
  public void start() {
      Debug.println("start called");
  }

  /**Stop the applet*/
  public void stop() {
  }

  /**Destroy the applet*/
  public void destroy() {
  }

  /**Get Applet information*/
  public String getAppletInfo() {
    return "Applet Information";
  }

  /**Get parameter info*/
  public String[][] getParameterInfo() {
    String[][] pinfo =
      {
      {"codebase", "String", "Code base of the application to display"},
      {"archive", "String", "Archive name of the application."},
      {"classname", "String", "Name of the class to run."},
      };
    return pinfo;
  }

  //static initializer for setting look & feel
  static {
    try {
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch(Exception e) {
    }
  }

  /**
   * Called when View Reports button is clicked on the applet.
   */
  void viewButton_actionPerformed(ActionEvent e)
  {
      showSpreadsheet();
  }

  /**
   * Shows Sharp Tools spreadsheet.
   */
  void showSpreadsheet() {
      // find main application class
      Class theClass = null;
      try {
          theClass = Class.forName(classname);
      } catch (java.lang.Exception s)
      {
          System.out.println("Exiting: " + classname + " does not refer to a class");
          return;
      }

      // display application class
      Frame theWindow = null;
      if (theWindow == null) {
          try {
              // instantiate class object
              Class partypes[] = new Class[1];
              partypes[0] = Boolean.TYPE;
              Object arglist[] = new Object[1];
              arglist[0] = Boolean.TRUE;

//              Constructor ct = theClass.getConstructor(partypes);
//              Object inst = ct.newInstance(arglist);

              Object inst = theClass.newInstance();
              Method mth = theClass.getMethod("setIsApplet", partypes);
              mth.invoke(inst, arglist);
              theWindow = (Frame) (inst);
          } catch (java.lang.InstantiationException s) {
              System.out.println(s);
          } catch (java.lang.IllegalAccessException s) {
                System.out.println(s);
          } catch (java.lang.ClassCastException s) {
                System.out.println(s);
          } catch (Exception e) {
                System.out.println("Exception during a reflection call: " + e.getMessage());
          }
      }

      theWindow.pack();
      theWindow.setVisible(true);
      theWindow.toFront();
  }
}
