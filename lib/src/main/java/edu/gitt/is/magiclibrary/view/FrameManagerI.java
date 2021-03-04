/**
 * 
 */
package edu.gitt.is.magiclibrary.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.gitt.is.magiclibrary.controller.CrudListener;

/**
 * @author Isabel  Román
 * <p>Interfaz para la simplificación de la gestión y composición de un JFrame</p>
 *
 */
public interface FrameManagerI {
	/**
	 * Añade a la vista principal un JFrame arriba
	 * @param frame JFrame a añadir
	 */
	  public void addNorth(JFrame frame);
	  /**
	   * Añade a la vista principal un JFrame abajo
	   * @param frame JFrame a añadir
	   */
	  public void addSouth(JFrame frame);
	  /**
	   * Añade a la vista principal un JPanel abajo
	   * @param panel JPanel a añadir
	   */
	  public void addSouth(JPanel panel);
	  /**
		 * Añade a la vista principal un JFrame en el centro
		 * @param frame JFrame a añadir
		 */
	  public void addCenter(JFrame frame);
	  /**
	   * Añade a la vista principal un JPanel en el centro
	   * @param panel JPanel a añadir
	   */
	  public void addCenter(JPanel panel);
	  /**
	   * Limpia la vista principal y elimina un panel específico
	   * @param panel
	   */
	  public void discard(JPanel panel);
	  /**
	   * Limpia la vista principal de todos los paneles que tenga para añadir uno nuevo
	   * @param panel
	   */
	 
	  public void reset();
	  /**
	   * Añade un menú tipo CRUD para una entidad concreta y le asocia un controlador CRUD particular
	   * @param entityName Nombre de la entidad a manejar
	   * @param listener Controlador que se le va a asociar al menú
	   */
	  public void setEntityMenu(String entityName, CrudListener listener);
	  /**
	   * Busca un componente por su nombre
	   * @param componentName El nombre del componente buscado
	   * @return
	   */
	  public Component getComponentByName(String componentName);
	
}
