/**
 * 
 */
package edu.gitt.is.magiclibrary.controller;


import edu.gitt.is.magiclibrary.model.JpaBookDao;
import edu.gitt.is.magiclibrary.model.JpaItemDao;
import edu.gitt.is.magiclibrary.model.entities.Book;
import edu.gitt.is.magiclibrary.model.entities.Item;
import edu.gitt.is.magiclibrary.view.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.*;

import javax.swing.event.ListSelectionEvent;


/**
 * <p>Controlador de la vista de Ejemplares. Está sin terminar y tiene problemas graves, como por ejemplo al crear uno no se especifica tipo de título y no permite
 * asociarlo con uno ya existente</p>
 * @author Isabel Román
 *
 */
public class ItemListener extends CrudListener<Item>{

	private static Logger log=Logger.getLogger(ItemListener.class.getName());


	@Override
	protected void search() {
		String inventoryNr=view.getAttributeAsString("inventoryNr");
		
		if((!inventoryNr.isEmpty()) && (inventoryNr!="*")) {
			log.info("Buscando un ejemplar con Id ="+inventoryNr);
			Optional<Item> recuperado = ((JpaItemDao) entityDao).findById(inventoryNr);
						
			if(recuperado.isPresent()) {
				MLView.getFrameManager().discard(view);
				setView(recuperado.get());
			
			}else {
				log.info("Ejemplar no encontrado");
				MLView.getFrameManager().discard(view);
			}
		}else {
			log.info("Buscando todos los ejemplares");
			List<Item> recuperados=((JpaItemDao) entityDao).findAll();
			MLView.getFrameManager().discard(view);
			setView(recuperados);
		}		
	}
	/**
	 * Responde a los cambios en la lista de múltiples ejemplares
	 */
	public void valueChanged(ListSelectionEvent e) {
		log.info("Cambia la selección en la lista");		
		((ItemDetails) view).setEntity((Item)view.getSelectedValue());
    } 
	@Override
	protected ItemDetails newView() {
		return new ItemDetails();
	}

	@Override
	protected ItemDetails newView(Item entity) {
		return new ItemDetails(entity);
	}

	@Override
	protected JpaItemDao newDao() {
		
		return new JpaItemDao();
	}

	@Override
	protected void save() {
		log.finest("Entrando en método save");
		entity=((ItemDetails) view).getItem();
		log.info("Voy a guardar la entidad "+entity);
		
		((JpaItemDao) entityDao).save((Item) entity);
	}
	/**
	 * Establece la vista de ejemplar vacía para buscar un libro, sólo habilita la introducción del número de inventario
	 */
	protected void setSearchView() {	
		log.info("Estableciendo vista de ejemplar vacía para buscar por número de inventario");
		setSearchView("inventoryNr");	
	}

	
}