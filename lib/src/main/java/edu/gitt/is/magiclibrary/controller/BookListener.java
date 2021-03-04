/**
 * 
 */
package edu.gitt.is.magiclibrary.controller;

import java.util.Optional;
import java.util.logging.Logger;

import edu.gitt.is.magiclibrary.model.Dao;
import edu.gitt.is.magiclibrary.model.JpaBookDao;
import edu.gitt.is.magiclibrary.model.entities.Book;
import edu.gitt.is.magiclibrary.view.BookView;
import edu.gitt.is.magiclibrary.view.MagicLibraryView;

/**
 * @author Isabel Román
 *
 */
public class BookListener extends CrudListener<Book> {
	private static Logger log=Logger.getLogger(BookListener.class.getName());
	

	@Override
	void search() {
		Optional<Book> recuperado = ((JpaBookDao) entityDao).findBookByIsbn(view.getAttributeAsString("isbn"));	
		if(recuperado.isPresent()) {
			MagicLibraryView.getFrameManager().discard(view);
			setView(recuperado.get());
			
		}else {
			log.info("Libro no encontrado");
			MagicLibraryView.getFrameManager().discard(view);
		}
		
	}

	@Override
	BookView newView() {
		return new BookView();
	}

	@Override
	BookView newView(Book entity) {
		return new BookView(entity);
	}

	@Override
	JpaBookDao newDao() {
		
		return new JpaBookDao();
	}

	@Override
	void save() {
		entity=((BookView) view).getBook();
		log.info("Voy a guardar la entidad "+entity);
		((JpaBookDao) entityDao).save((Book) entity);
		
	}
	/**
	 * Establece la vista de libro vacía para buscar un libro, sólo habilita la introducción del autor (en esta versión sólo busca por autor)
	 */
	protected void setSearchView() {	
		log.info("Estableciendo vista de libro vacía para buscar por isbn");
		setSearchView("isbn");
	
	}

}
