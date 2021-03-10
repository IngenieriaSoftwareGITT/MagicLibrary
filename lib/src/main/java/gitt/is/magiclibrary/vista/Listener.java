/**
 * 
 */
package gitt.is.magiclibrary.vista;





import javax.swing.JTextField;
import java.util.logging.*;


import java.awt.event.*;        //for action events


/**
 * @author Isabel Román
 *
 */
public class Listener implements ActionListener{
	private static Logger log=Logger.getLogger(Listener.class.getName());
	private TitleView panel;
	 public void actionPerformed(ActionEvent e) {
		    log.info("Recibido evento "+e+" en el Listener\n");
	        String prefix = "Recibido nombre ";
	     
	        log.info("Imprimo el texto en la ventana");
	        panel.printMessage(prefix+panel.getName());
	        
	    }
	 public void setJFrame(TitleView panel) {
		 this.panel = panel;
		 
	 }
}

