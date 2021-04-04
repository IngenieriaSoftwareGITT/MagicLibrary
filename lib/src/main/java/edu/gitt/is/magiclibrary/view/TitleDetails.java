/**
 * Clase correspondiente la vista de un título
 */
package edu.gitt.is.magiclibrary.view;

import javax.swing.JTextField;

import edu.gitt.is.magiclibrary.model.entities.Title;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import java.text.DateFormat;

/**
 * Esta clase es la vista de los datos comunes a todos los títulos
 * @author Isabel Román
 *
 */
public class TitleDetails extends EntityDetails<Title> {
	private static Logger log=Logger.getLogger(TitleDetails.class.getName());
	/**
	 * Número de serie por defecto
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JTextField nameField;
	protected JFormattedTextField publishedAtField;
	protected JTextField authorField;
	private JLabel publishedAtLabel;
	private JLabel authorLabel;
	private JLabel nameLabel;


	/**
	 * Constructor simple, invoca el método para crear el panel principal de la vista de títulos
	 */
	public TitleDetails() {
		createPanel();

	}
	/**
	 * <p>Constructor que invoca el método para crear el panel principal de la vista de títulos y la asocia a un título determinado</p>
	 * @param title entidad de tipo título que se va presentar por pantalla
	 */
	public TitleDetails(Title title) {
		createPanel();		
		setEntity(title);
	}
	/**
	 * <p>Crea el panel principal con los campos genéricos de todos los títulos de la biblioteca</p>
	 */
	private void createPanel(){
		setLayout(null);
		
		nameField = new JTextField();
		nameField.setName("name");
		nameField.setBounds(139, 25, 300, 20);
		add(nameField);
		nameField.setColumns(10);
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(32, 25, 46, 14);
		add(nameLabel);
		

		publishedAtField = new JFormattedTextField(MLView.getFrameManager().getDateFormat());
		publishedAtField.setName("publishedAt");
		publishedAtField.setBounds(139, 66, 300, 20);
		add(publishedAtField);
		publishedAtField.setColumns(10);		
		publishedAtLabel = new JLabel("Published At");
		publishedAtLabel.setBounds(32, 64, 97, 14);
		add(publishedAtLabel);
		
		authorField = new JTextField();
		authorField.setName("author");
		authorField.setBounds(139, 107, 300, 20);
		add(authorField);
		authorField.setColumns(10);			
		authorLabel = new JLabel("Author");
		authorLabel.setBounds(32, 103, 46, 14);
		add(authorLabel);	
		
	}
	/**
	 * Asocia la vista a un título específico y muestra sus valores
	 * @param title el título que quiero asociar a la vista
	 */
	public void setEntity(Title title) {
		log.info("Mostrando atributos del título");
		this.entity=title;
		nameField.setText(title.getName());
		publishedAtField.setText(title.getPublishedAt().toString());
		authorField.setText(title.getAuthor());
	}
	

}
