/**
 * Clase para los aspectos comunes a todos los listeners para operaciones CRUD
 * 
 */
package edu.gitt.is.magiclibrary.controller;


import java.util.logging.*;

import edu.gitt.is.magiclibrary.model.Dao;

import edu.gitt.is.magiclibrary.view.*;

import java.awt.event.*;        //for action events

/**
 * @author Isabel Román
 *
 */
public abstract class CrudListener<T> implements ActionListener{
	private static Logger log=Logger.getLogger(CrudListener.class.getName());
	protected EntityView view;
	private CrudOperation operation;
	protected T entity;
	
	protected Dao entityDao;
   
   
	public void actionPerformed(ActionEvent e) {
		    log.info("Recibido evento "+e+" en el CrudListener\n");
		    entityDao = newDao();
		
		    String cmd=e.getActionCommand();
		    log.info("Action command= "+cmd);
		    switch(cmd) {
		    	case "Create":
		    		log.info("Implementar la creación");
		    		operation = CrudOperation.CREATE;
		    
		    		setCreateView();
		    	  
		    		break;
		    	case "Save":
		    		log.info("Implementar guardar");
		    	
		    		save();
		    		
		    		MagicLibraryView.getFrameManager().discard(view);
		    		break;
		    	case "Read":
		    		log.info("Implementar la lectura");
		    	
		    		operation=CrudOperation.READ;
		    		setSearchView();
		    		break;
		    	case "Update":
		    		log.info("Implementar la actualización");
		    	
		    		operation=CrudOperation.UPDATE;
		    		setSearchView();
		    		break;
		    	case "Delete":
		    		log.info("Implementar el borrado");
		    	
		    		operation=CrudOperation.DELETE;
		    		setSearchView();
		    		break;
		    	case "Search":
		    		
		    		log.info("Implementar la búsqueda");
		    	
		    		search();
		    			
		    		break;
		    	case "Remove":		 
		    		log.info("Eliminar definitivamente");		    	
		    		entityDao.delete(view.getEntity());
		    		MagicLibraryView.getFrameManager().discard(view);
		    		break;
		    	case "Discard":
		    		log.info("Implementar Discard");
		    		MagicLibraryView.getFrameManager().discard(view);  		
		    		
		    }
	        
	    }

	
	protected void setView(T entity) {
		/**Datos del título*/
		view=newView(entity);
		switch(operation) {
		case READ:
			view.disableAllAttributes();
			view.addDiscardButton(this);
			break;
		case UPDATE:
			view.addCreateButtons(this);
			break;
		case DELETE:
			view.addDeleteButtons(this);
		}	
		MagicLibraryView.getFrameManager().addCenter(view);
		
	}
	/**
	 * Establece la vista vacía para crear una entidad nueva
	 */
	private void setCreateView() {	
		log.info("Estableciendo vista vacía para crear");
		MagicLibraryView.getFrameManager().discard(view);
		MagicLibraryView.getFrameManager().reset();
		view=newView();
		view.addCreateButtons(this);
		view.setVisible(true);
		MagicLibraryView.getFrameManager().addCenter(view);
	}
	/**
	 * Establece la vista vacía para buscar una entidad nueva, todos los campos habilitados para buscar
	 */
	protected void setSearchView() {	
		log.info("Estableciendo vista vacía para buscar");
		MagicLibraryView.getFrameManager().discard(view);
		view=newView();
		view.addSearchButtons(this);		
	
		MagicLibraryView.getFrameManager().addCenter(view);
	
	}
	/**
	 * Establece la vista vacía para buscar una entidad, sólo habilita el campo elegido para buscar
	 */
	protected void setSearchView(String query) {	
		log.info("Estableciendo vista vacía para buscar");
		MagicLibraryView.getFrameManager().discard(view);
		view=newView();
		view.addSearchButtons(this);		
		view.disableAllAttributes();
		view.enableAttribute(query);
		MagicLibraryView.getFrameManager().addCenter(view);
	
	}
	abstract void search();
	abstract EntityView newView();
	abstract EntityView newView(T entity);
	abstract Dao newDao();
	abstract void save();
	
}

