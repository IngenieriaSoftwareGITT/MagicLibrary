/**
 * 
 */
package edu.gitt.is.magiclibrary.controller;

import java.util.List;
import java.util.Optional;

import java.util.logging.Logger;

import javax.swing.event.ListSelectionEvent;


import edu.gitt.is.magiclibrary.model.JpaBookDao;
import edu.gitt.is.magiclibrary.model.entities.Book;
import edu.gitt.is.magiclibrary.view.BookDetails;
import edu.gitt.is.magiclibrary.view.MLView;

/**
 * <p>Controlador específico para las vistas de entidades de tipo Libro</p>
 * @author Isabel Román
 *
 */
public class BookListener extends CrudListener<Book> {
	private static Logger log=Logger.getLogger(BookListener.class.getName());
	

	@Override
	protected void search() {
		log.fine("Entrando en el método search");
		String isbn=view.getAttributeAsString("isbn");
		
		if((!isbn.isEmpty()) && (!isbn.contentEquals("*"))) {
			log.info("Buscando un libro con isbn ="+isbn);
			Optional<Book> recuperado = ((JpaBookDao) entityDao).findBookByIsbn(view.getAttributeAsString("isbn"));	
			if(recuperado.isPresent()) {
				log.finest("Libro encontrado");
				MLView.getFrameManager().discard(view);
				setView(recuperado.get());
			
			}else {
				log.info("Libro no encontrado");
				MLView.getFrameManager().discard(view);
			}
		}else {
			log.info("Buscando todos los libros");
			List<Book> recuperados=((JpaBookDao) entityDao).findAll();
			MLView.getFrameManager().discard(view);
			setView(recuperados);
			
		}
			
		
	}
	/**
	 * Responde a los cambios en la lista de múltiples libros 
	 */
	public void valueChanged(ListSelectionEvent e) {
		log.info("Cambia la selección en la lista");
		
		((BookDetails) view).setEntity((Book)view.getSelectedValue());
    } 
	
	@Override
	protected BookDetails newView() {
		log.info("Creo una vista de libro nueva");
		return new BookDetails();
	}

	@Override
	protected BookDetails newView(Book entity) {
		log.finest("Construyendo la vista con los valores "+ entity);
		return new BookDetails(entity);
	}

	@Override
	protected JpaBookDao newDao() {
		log.info("Creo un DAO de libro nuevo");
		return new JpaBookDao();
	}

	@Override
	protected void save() {
		log.finest("Entrando en método save");
		entity=((BookDetails) view).getBook();
		log.info("Voy a guardar la entidad "+entity);
		((JpaBookDao) entityDao).save((Book) entity);
		
	}
	/**
	 * Establece la vista de libro vacía para buscar un libro, sólo habilita la introducción del isbn, en esa versión sólo busca por isbn o todos los libros
	 */
	protected void setSearchView() {	
		log.finest("Entrando en setSearchView");
		log.info("Estableciendo vista de libro vacía para buscar por isbn");
		setSearchView("isbn");
	
	}

}
