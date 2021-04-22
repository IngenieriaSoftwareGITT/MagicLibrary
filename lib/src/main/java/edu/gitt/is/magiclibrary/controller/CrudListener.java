/**
 * Clase para los aspectos comunes a todos los listeners para operaciones CRUD
 * 
 */
package edu.gitt.is.magiclibrary.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.*;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import edu.gitt.is.magiclibrary.model.Dao;

import edu.gitt.is.magiclibrary.view.*;



/**
 * <p>Clase genérica para los controladores que atienden a la vista de cualquier tipo de entidad</p>
 * <p>Implementará las interfaces {@link java.awt.event.ActionListener} y {@link javax.swing.event.ListSelectionListener}</p>
 * <p>Participa en el patrón observador como observador</p>
 * @author Isabel Román
 *
 */
public abstract class CrudListener<T> implements ActionListener, ListSelectionListener{
	private static Logger log=Logger.getLogger(CrudListener.class.getName());
	/**
	 * <p>Vista concreta con la que se asociará este controlador. Participa en el patrón observador como observado</p>
	 */
	protected EntityDetails view;
	private CrudOperation operation;
	/**
	 * <p>Entidad manejada por el controlador</p>
	 */
	protected T entity;
	/**
	 * <p>Objeto de acceso a datos, que permitirá realizar las acciones CRUD para la entidad manejada</p>
	 */
	protected Dao entityDao;
   
/**
 * <p>Método correspondiente a la interfaz ActionListener, responde a los eventos lanzados por la vista de entidad</p>
 * {@link java.awt.event.ActionListener}
 * @param e {@link java.awt.event.ActionEvent}   
 */
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
		    		
		    		MLView.getFrameManager().discard(view);
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
		    		MLView.getFrameManager().discard(view);
		    		break;
		    	case "Discard":
		    		log.info("Implementar Discard");
		    		MLView.getFrameManager().discard(view);  		
		    		
		    }
	        
	    }
	/**
	 * <p>Método correspondiente a la interfaz ListSelectionListener responde a los cambios en la selección en la lista de entidades múltiples de la vista correspondiente</p>
	 * {@link javax.swing.event.ListSelectionListener}
	 * @param e {@link javax.swing.event.ListSelectionEvent}
	 */
	
	public void valueChanged(ListSelectionEvent e) {
		log.info("Cambia la selección en la lista");
		
		view.setEntity(view.getSelectedValue());
    }

	/**
	 * <p>Crea la vista con una única entidad</p>
	 * <p>La dependencia del estado hace ver que debería utilizarse el patrón "Estado como objeto"</p>
	 * @param entity Entidad que se quiere presentar por pantalla
	 */
	protected void setView(T entity) {
		log.info("Creando vista con una entidad");
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
		MLView.getFrameManager().addCenter(view);
		
	}
	/**
	 * <p>Crea la vista con un conjunto de entidades/p>
	 * <p>La dependencia del estado hace ver que debería utilizarse el patrón "Estado como objeto"</p>
	 * @param entities Lista de entidades que se quieren presentar por pantalla
	 */
	protected void setView(List<T> entities) {
		log.info("Creando vista con múltiples entidades");
		/**Datos del título*/
		view=newView(entities.get(0));
		view.setEntity(entities);
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
		view.addList(this);
		MLView.getFrameManager().addCenter(view);
		
	}
	/**
	 * Establece la vista vacía para crear una entidad nueva
	 */
	private void setCreateView() {	
		log.info("Estableciendo vista vacía para crear");
		MLView.getFrameManager().discard(view);
		MLView.getFrameManager().reset();
		view=newView();
		view.addCreateButtons(this);
		view.setVisible(true);
		MLView.getFrameManager().addCenter(view);
	}
	/**
	 * Establece la vista vacía para buscar una entidad nueva, todos los campos habilitados para buscar
	 */
	protected void setSearchView() {	
		log.info("Estableciendo vista vacía para buscar");
		MLView.getFrameManager().discard(view);
		view=newView();
		view.addSearchButtons(this);		
	
		MLView.getFrameManager().addCenter(view);
	
	}
	/**
	 * <p>Establece la vista vacía para buscar una entidad, sólo habilita el campo elegido para buscar, indicado en el parámetro</p>
	 * @param query Nombre del atributo que se usará para la búsqueda
	 */
	protected void setSearchView(String query) {	
		log.info("Estableciendo vista vacía para buscar");
		MLView.getFrameManager().discard(view);
		view=newView();
		view.addSearchButtons(this);		
		view.disableAllAttributes();
		view.enableAttribute(query);
		MLView.getFrameManager().addCenter(view);
	
	}
	/**
	 * <p>Método de búsqueda, específico de las clases hijas</p>
	 */
	protected abstract void search();
	/**
	 * <p>Método para guardar, específico de las clases hijas</p>
	 */
	protected abstract void save();
	/**
	 * <p>Método factoría, serán los hijos los que decidan la clase concreta del objeto vista creado, en este caso vacía (sin relacionar con una instancia de entidad concreta)</p> 
	 * @return una nueva vista para un tipo de entidad concreto
	 */
	protected abstract EntityDetails newView();
	/**
	 * <p>Método factoría, serán los hijos los que decidan la clase concreta del objeto vista creado, en este caso rellena (con los datos de la entidad pasada como parámetro</p> 
	 * @return una nueva vista para un tipo de entidad concreto
	 * @param entity entidad asociada a la vista
	 */
	protected abstract EntityDetails newView(T entity);
	/**
	 * <p>Método factoría, serán los hijos los que decidan la clase concreta del Dao asociado</p> 
	 * @return un nuevo objeto dao para manejar un tipo de entidad concreto
	 *
	 */
	protected abstract Dao newDao();
	
	
}

