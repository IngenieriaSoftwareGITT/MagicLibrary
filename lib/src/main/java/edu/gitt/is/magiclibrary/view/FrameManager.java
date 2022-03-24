/**
 * 
 */
package edu.gitt.is.magiclibrary.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.gitt.is.magiclibrary.controller.CrudListener;

/**
 * <p>Clase que se respnsabiizará de gestionar una vista, encarnada en un {@link javax.swing.JFrame}</p>
 * @author Isabel Román
 *
 */
public class FrameManager implements FrameManagerI {
	private static Logger log=Logger.getLogger(FrameManager.class.getName());
	/**
	 * JFrame gestionado por este gestor
	 */
	private JFrame myFrame;
	private DateFormat dateFormat;

	      
			
	/**
	 * <p>Constructor</p>		
	 * @param frame Recibe el marco {@link javax.swing.JFrame} del que se va a responsabilizar
	 */
	public FrameManager(JFrame frame) {
		log.fine("Constructor");
		this.myFrame=frame;
		myFrame.setBounds(320, 320, 600,600);
		dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	@Override
	 public void addNorth(JFrame frame) {   
		log.fine("Añadir arriba");
        myFrame.getContentPane().add(BorderLayout.NORTH, frame);             
        myFrame.setVisible(true);   
    }

	@Override
	  public void addSouth(JFrame frame) {
		log.fine("Añadir abajo");
    	myFrame.getContentPane().add(BorderLayout.PAGE_END, frame);  
    	myFrame.setVisible(true);   
    }
	@Override
	  public void addSouth(JPanel panel) {
		log.fine("Añadir abajo");
  	myFrame.getContentPane().add(panel, BorderLayout.PAGE_END);

  	myFrame.setVisible(true);   
  }

	@Override
	  public void addCenter(JFrame frame) {
		log.fine("Añadir en el centro");
    	myFrame.getContentPane().add(BorderLayout.CENTER, frame);  
    	myFrame.pack();
    	myFrame.setVisible(true);   
	}
	@Override
	  public void addCenter(JPanel panel) {
		log.fine("Añadir en el centro");
		myFrame.getContentPane().add(panel,BorderLayout.CENTER);
		
		myFrame.setVisible(true);
		
	}
	@Override
	public void discard(JPanel panel) {
		log.fine("Limpiar la vista principal");	
		if (panel!=null) {
			panel.setVisible(false);
			panel=null;
		}
		reset();	
		}
	@Override
	public void reset() {
		log.fine("Resetear");
		Component[] components = myFrame.getComponents();
		for (Component c : components){
			if((c.getClass()==JPanel.class)) {
				log.fine("Encuentro un componente y lo oculto");
				myFrame.getContentPane().remove(c);
				c.setVisible(false);
				c=null;
			}
		}
	}
	@Override
	public void setEntityMenu(String entityName, CrudListener listener) {
		log.fine("Creo el Menu para una entidad llamada "+entityName);
		
		JMenu menu = new JMenu(entityName);
		menu.setName(entityName);
		JMenuItem create = new JMenuItem("Create");
		create.addActionListener(listener);
		menu.add(create);
		
		JMenuItem read = new JMenuItem("Read");
		read.addActionListener(listener);
		menu.add(read);
		
		JMenuItem update = new JMenuItem("Update");
		update.addActionListener(listener);
		menu.add(update);
		
		JMenuItem delete = new JMenuItem("Delete");
		delete.addActionListener(listener);
		menu.add(delete);
		
		myFrame.getJMenuBar().add(menu);

		myFrame.setVisible(true);
	}
	@Override
	public Component getComponentByName(String componentName) {
		log.fine("Buscando componente por el nombre "+componentName);
		Component component=null;
		Component[] components = myFrame.getComponents();
		for (Component c : components){
			log.info("Encontrado componente con nombre "+c.getName());
			if(c.getName()==componentName) {
				component=c;
			}
		}
		return component;
	}
	@Override
	public DateFormat getDateFormat() {
		log.fine("Recuperar el formato para fecha");
		return this.dateFormat;
	}
	
	
}
