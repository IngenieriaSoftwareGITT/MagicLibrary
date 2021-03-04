/**
 * Clase correspondiente la vista de un título
 */
package edu.gitt.is.magiclibrary.view;

import javax.swing.JTextField;

import edu.gitt.is.magiclibrary.model.entities.Title;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class TitleView extends EntityView<Title> {
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
	protected DateFormat format;

	/**
	 * Crea el panel
	 */
	public TitleView() {
		createPanel();

	}
	public TitleView(Title title) {
		createPanel();		
		setTitle(title);
	}
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
		

		format = new SimpleDateFormat("dd-MM-yyyy");
		publishedAtField = new JFormattedTextField(format);
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
	public void setTitle(Title title) {
		this.entity=title;
		nameField.setText(title.getName());
		publishedAtField.setText(title.getPublishedAt().toString());
		authorField.setText(title.getAuthor());
	}
	

}
