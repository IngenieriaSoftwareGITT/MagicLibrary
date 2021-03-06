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
import edu.gitt.is.magiclibrary.view.BookView;
import edu.gitt.is.magiclibrary.view.MLView;

/**
 * <p>Controlador específico para las vistas de entidades de tipo Libro</p>
 * @author Isabel Román
 *
 */
public class BookListener extends CrudListener<Book> {
	private static Logger log=Logger.getLogger(BookListener.class.getName());
	

	@Override
	void search() {
		String isbn=view.getAttributeAsString("isbn");
		
		if((!isbn.isEmpty()) && (isbn!="*")) {
			log.info("Buscando un libro con isbn ="+isbn);
			Optional<Book> recuperado = ((JpaBookDao) entityDao).findBookByIsbn(view.getAttributeAsString("isbn"));	
			if(recuperado.isPresent()) {
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
		
		((BookView) view).setEntity((Book)view.getSelectedValue());
    } 
	
	@Override
	BookView newView() {
		log.info("Creo una vista de libro nueva");
		return new BookView();
	}

	@Override
	BookView newView(Book entity) {
		return new BookView(entity);
	}

	@Override
	JpaBookDao newDao() {
		log.info("Creo un DAO de libro nuevo");
		return new JpaBookDao();
	}

	@Override
	void save() {
		entity=((BookView) view).getBook();
		log.info("Voy a guardar la entidad "+entity);
		((JpaBookDao) entityDao).save((Book) entity);
		
	}
	/**
	 * Establece la vista de libro vacía para buscar un libro, sólo habilita la introducción del isbn, en esa versión sólo busca por isbn o todos los libros
	 */
	protected void setSearchView() {	
		log.info("Estableciendo vista de libro vacía para buscar por isbn");
		setSearchView("isbn");
	
	}

}
