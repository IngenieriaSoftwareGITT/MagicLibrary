package edu.gitt.is.magiclibrary.view;


import javax.swing.JFrame;

import javax.swing.JMenuBar;

import java.util.logging.Logger;





/**
 * @author Isabel Román
 * <p>Esta clase permite crear un objeto (único) contenedor de la GUI de la aplicación MagicLibrary, de tipo {@link java.swing.JFrame}</p>
 * <p>Cada vista se configura incluyendo y eliminando los componentes de este contenedor principal</p>
 *
 */
public class MagicLibraryView {
	private static Logger log=Logger.getLogger(MagicLibraryView.class.getName());
	
	private static JFrame frame;
	private static FrameManagerI myFrameManager=null;
	private static MagicLibraryView mainWindow=null;


	

	/**
	 * Crea la Vista principal
	 */
	private MagicLibraryView() {
		
	}

	public static FrameManagerI getFrameManager() {
		
		if (myFrameManager==null){
			log.info("Creo el MagicLibraryView");
			mainWindow=new MagicLibraryView();
			mainWindow.initialize();
			frame.setVisible(true);
			
		}
		return myFrameManager;
	}

	/**
	 * Inicia los contenidos de la vista principal de Magic Library, simplemente una barra de menú
	 */
	private void initialize() {
		log.info("Creo el JFrame principal, el frameManager y la barra de Menú");
		frame = new JFrame();
		myFrameManager = new FrameManager(frame);
		
		JMenuBar menuBar = new JMenuBar();
	
		frame.setJMenuBar(menuBar);	
		menuBar.setVisible(true);
	}
	
	


}
