/**
 * 
 */
package gitt.is.magiclibrary.vista;

/**
 * @author Isabel Román
 *
 */
import java.awt.*;
//import java.awt.event.*;
import javax.swing.*;

/* TopLevelDemo.java requires no other files. */
public class MagicLibraryView {
    /**
     * Crea el JFrame principal de la aplicación MagicLibrary
     * Muestra la GUI.
     * For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
	private static JFrame myFrame=null;
	private static MagicLibraryView myInstance= null;
	
	private MagicLibraryView() {
		
	}
	
	public static MagicLibraryView getInstance() {
		if (myInstance==null){
			myInstance=new MagicLibraryView();
			//Create and set up the window. JFrame
	        myFrame = new JFrame("MagicLibrary GUI");
	        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //Display the window.
	        myFrame.pack();
	        myFrame.setVisible(true);
		}
		return myInstance;
	}
	
    public void createAndShowGUI() {
        

        //Create the menu bar.  Make it have a green background.
        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(200, 20));

        //Create a yellow label to put in the content pane.
        JLabel yellowLabel = new JLabel();
        yellowLabel.setOpaque(true);
        yellowLabel.setBackground(new Color(248, 213, 131));
        yellowLabel.setPreferredSize(new Dimension(200, 180));

        //Set the menu bar and add the label to the content pane.
        myFrame.setJMenuBar(greenMenuBar);
        myFrame.getContentPane().add(yellowLabel, BorderLayout.CENTER);

       
    }
    public void addFrame(JFrame frame) {
    	myFrame.add(frame);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MagicLibraryView.getInstance().createAndShowGUI();
            }
        });
    }
}

