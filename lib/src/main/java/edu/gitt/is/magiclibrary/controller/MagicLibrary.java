/**
 * Controlador principal para arrancar la aplicación Magic Library, construye la vista principal y los controladores para cada tipo de entidad
 */
package edu.gitt.is.magiclibrary.controller;

import java.util.logging.Logger;

import edu.gitt.is.magiclibrary.view.MLView;

/**
 * @author Isabel Román
 *
 */
public class MagicLibrary {
	private static Logger log=Logger.getLogger(MagicLibrary.class.getName());

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final BookListener bookListener;
		final ItemListener itemListener;

		log.info("Arranco la aplicación y creo el controlador para manejar entidades desde la interfaz");

		bookListener=new BookListener();

		MLView.getFrameManager().setEntityMenu("Book", bookListener);
		itemListener=new ItemListener();
		MLView.getFrameManager().setEntityMenu("Item", itemListener);
	
	}

}
