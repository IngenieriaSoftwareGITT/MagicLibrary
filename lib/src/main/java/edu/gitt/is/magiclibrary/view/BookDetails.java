package edu.gitt.is.magiclibrary.view;


import javax.swing.JTextField;
import edu.gitt.is.magiclibrary.model.entities.Book;


import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.JLabel;

/**
 * <p>Esta clase es la vista para los datos que son exclusivos de los libros (número de páginas e ISBN)</p>
 * @author Isabel Román
 *
 */
public class BookDetails extends TitleDetails {
	/**
	 * Id por defecto, por ser serializable
	 */
	private static final long serialVersionUID = 1L;



	private static Logger log=Logger.getLogger(BookDetails.class.getName());
	

	
	private JTextField isbnField;
	private JTextField pagesField;
	private JLabel isbnLabel;
	private JLabel pagesLabel;
	



	


	/**
	 * <p>Creación de la vista vacía. Se usará para introducir búsquedas o los datos de un libro nuevo</p>
	 */
	public BookDetails() {
		super();
		createPanel();
	}
	/**
	 * <p>Creación de la vista con los datos de un libro. Se usará para presentar los datos de un libro ya existente para su edición o eliminación</p>
	 * @param book El libro a presentar en la vista
	 */
	public BookDetails(Book book) {
		
		createPanel();
		this.setEntity(book);
	}
	/**
	 * <p>Crea el panel añadiendo los campos específicos de un título de tipo libro, los campos genéricos de título están heredados de la vista de entidades de tipo título</p>
	 */
	private void createPanel() {
		log.info("Creando el panel de libro");
	
		
		isbnField = new JTextField();
		isbnField.setName("isbn");
		isbnField.setBounds(140, 166, 300, 20);
		add(isbnField);
		isbnField.setColumns(10);
	
		
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(33, 169, 46, 14);
		add(isbnLabel);
		
		pagesField = new JTextField();
		pagesField.setName("pages");
		pagesField.setBounds(140, 203, 300, 20);
		add(pagesField);
		pagesField.setColumns(10);
		
		pagesLabel = new JLabel("Pages");
		pagesLabel.setBounds(33, 206, 46, 14);
		add(pagesLabel);
	
		
		}


	/**
	 * <p>Método par establecer el libro en la vista, se mostrarán sus datos por pantalla</p>
	 * @param book Libro que queremos mostrar por pantalla
	 */
	public void setEntity(Book book) {
		log.info("Mostrando atributos del libro");
		super.setEntity(book);
		isbnField.setText(book.getIsbn());		
		pagesField.setText(Integer.toString(book.getPages()));
	}
	/**
	 * 
	 * @return Devuelve un Book con los datos correspondientes a las casillas de texto de la vista
	 */
	
	public Book getBook() {
		log.info("Creando libro a partir de los datos de entrada");
		Book book=null;
		if (this.entity==null){
			try {
				book = new Book(nameField.getText(),authorField.getText(),MLView.getFrameManager().getDateFormat().parse(publishedAtField.getText()),isbnField.getText(),Integer.parseUnsignedInt(pagesField.getText()));
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
	
			
			setEntity(book);
		}else {
			entity.setAuthor(authorField.getText());
			entity.setName(nameField.getText());
			
			try {
				entity.setPublishedAt(MLView.getFrameManager().getDateFormat().parse(publishedAtField.getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			book=(Book) entity;
		}
		book.setIsbn(isbnField.getText());
		book.setPages(Integer.parseUnsignedInt(pagesField.getText()));
		
		return book;
	}
	
}
