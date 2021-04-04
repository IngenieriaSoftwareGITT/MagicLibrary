package edu.gitt.is.magiclibrary.view;


import javax.swing.JFrame;

import javax.swing.JMenuBar;

import java.util.logging.Level;
import java.util.logging.Logger;





/**
 * <p>Esta clase permite crear un objeto (único) contenedor de la GUI de la aplicación MagicLibrary, de tipo {@link javax.swing.JFrame}</p>
 * <p>Cada vista se configura incluyendo y eliminando los componentes de este contenedor principal</p>
 * @author Isabel Román
 *
 */
public class MLView {
	private static Logger log=Logger.getLogger(MLView.class.getName());
	
	private static JFrame frame;
	private static FrameManagerI myFrameManager=null;
	private static MLView mainWindow=null;



	

	/**
	 * Constructor privado, crea la Vista principal
	 */
	private MLView() {
		log.log(Level.INFO,"Constructor de la vista principal de ML");
	}
    /**
     * <p>Devuelve la instancia única de gestor de vistas</p>
     * <p>La primera vez que se invoca crea la vista principal de la aplicación</p>
     * @return la instancia única de gestor de vistas
     */
	public static FrameManagerI getFrameManager() {
		if (myFrameManager==null){
			log.info("Creo la vista principal de Magic Library");
			mainWindow=new MLView();
			mainWindow.initialize();
			frame.setVisible(true);
			
		}
		log.finest("Devuelvo la referencia al FrameManager");
		return myFrameManager;
	}

	/**
	 * <p> Inicia los contenidos de la vista principal de Magic Library, simplemente una barra de menú</p>
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
