/**
 * Clase abstracta que contiene todo lo común a las vistas de entidades
 */
package edu.gitt.is.magiclibrary.view;

import java.awt.Component;
import java.awt.event.ActionListener;

import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import javax.swing.JPanel;
import javax.swing.JTextField;




/**
 * @author Isabel Román
 *
 */
public abstract class EntityView<T> extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static Logger log=Logger.getLogger(EntityView.class.getName());
	

	private JButton searchButton;
	private JButton saveButton;
	private JButton deleteButton;
	private JButton discardButton;
    protected T entity=null;
	


	/**
	 * Creación de la vista sin establecer la entidad
	 */
	public EntityView() {
		log.info("Creando Vista vacía");
		
	}
	/**
	 * Creación de la vista estableciendo la entidad
	 * @param entity La entidad correspondiente a esta vista
	 */
	public EntityView(T entity) {	
		log.info("Creando vista con entidad");
		this.entity=entity;
	}

	public void addCreateButtons(ActionListener listener) {
		log.info("Añadiendo los botones de Guardar y Descartar");
		saveButton = new JButton("Save");
		

		saveButton.setBounds(56, 275, 89, 23);
		saveButton.addActionListener(listener);
		add(saveButton);
		
		
		addDiscardButton(listener);
		setVisible(true);
		
	}
	public void addDeleteButtons(ActionListener listener) {
		log.info("Añadiendo los botones de eliminar y descartar");
		deleteButton = new JButton("Remove");
		

		deleteButton.setBounds(56, 275, 89, 23);
		deleteButton.addActionListener(listener);
		add(deleteButton);
		
		
		addDiscardButton(listener);
		
		setVisible(true);
		
	}
	public void addDiscardButton(ActionListener listener) {
		log.info("Añadiendo el botón de descartar");
			
		discardButton = new JButton("Discard");
		discardButton.addActionListener(listener);
		discardButton.setBounds(350, 275, 89, 23);
		add(discardButton);
		
		setVisible(true);
		
	}
	public void addSearchButtons(ActionListener listener) {
		
		searchButton = new JButton("Search");
		searchButton.addActionListener(listener);
		searchButton.setBounds(56, 275, 89, 23);
		add(searchButton);
		addDiscardButton(listener);
		setVisible(true);
		
	}

	/**
	 * Método par establecer la entidad tras la construcción
	 * @param entity entidad correspondiente a esta vista
	 */
	public void setEntity(T entity) {
		this.entity=entity;
		
	}
	/**
	 * 
	 * @return Devuelve un Book con los datos correspondientes a las casillas de texto
	 */
	
	public T getEntity() {
		
		return entity;
	}
	
	/**
	 * Deshabilita la entrada de todos los campos de texto
	 */
	public void disableAllAttributes() {
		Component[] components = this.getComponents();
		for (Component c : components){
			if((c.getClass()==JTextField.class)||(c.getClass()==JFormattedTextField.class)) {
				log.info("Encontrado campo de texto con nombre "+c.getName());
				((JTextField) c).setEditable(false);
			} 
		}
	}
	/**
	 * Habilita la entrada de todos los campos de texto
	 */
	public void enableAllAttributes() {
		Component[] components = this.getComponents();
		for (Component c : components){
			if((c.getClass()==JTextField.class)||(c.getClass()==JFormattedTextField.class)) {
				((JTextField) c).setEditable(true);
			}
		}
	}
	/**
	 * Deshabilita la entrada para uno de los atributos
	 * @param name Atributo para el que quiero deshabilitar la edición
	 */
	public void disableAttribute(String name) {

	
		Component[] components = this.getComponents();
		for (Component c : components){
			if(c.getName()==name) {
				((JTextField) c).setEditable(false);
			}
		}
	}
	/**
	 * Habilita la entrada de un campo de texto específico
	 * @param name Atributo para el que quiero habilitar la edición
	 */
	public void enableAttribute(String name) {
		
		Component[] components = this.getComponents();
		for (Component c : components){
			if(c.getName()==name) {
				((JTextField) c).setEditable(true);
			}
		}
	}
	/**
	 * Los campos de texto en la vista siempre tendrán el mismo nombre que el atributo correspondiente de la entidad
	 * @param name El nombre del atributo que queremos recuperar
	 * @return el valor del campo correspondiente como String
	 */
	public String getAttributeAsString(String name) {
		String value="";
		
		log.info("Buscando valor del campo "+name);
		Component[] components = this.getComponents();
		for (Component c : components){
			log.info("Encontrado componente con nombre "+c.getName());
			if(c.getName()==name) {
				value=((JTextField) c).getText();
			}
		}
		return value;
	}
	

}

