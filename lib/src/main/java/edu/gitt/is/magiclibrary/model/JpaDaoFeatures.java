
package edu.gitt.is.magiclibrary.model;

import java.util.function.Consumer;
import java.util.logging.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * <p> Esta es la clase incluye funcionalidades comunes a los DAO que utilizan la API JPA {@link javax.persistence}</p>
 * <p> Se sigue el patr�n DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patr�n DAO</a></p>
 * @author Isabel Rom�n
 * @version 0.0 
 */
public class JpaDaoFeatures {
	/**
	 * Para trazar el c�digo {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaDaoFeatures.class.toString());
	/**
	 * Referencia a la f�brica de gestores de entidad
	 */
	private static EntityManagerFactory emf=null;
	/**
	 * Referencia al gestor de entidad
	 */
	private static EntityManager myEntityManager=null;
	/**
	 * Referencia a la instancia �nica
	 */
	private static JpaDaoFeatures myInstance=null;
	
	/**
	 * Constructor simple
	 */
	private JpaDaoFeatures() {
		
	}
	/**
	 * Esta clase sigue el patr�n singleton <a href="https://www.javacodegeeks.com/2015/09/singleton-design-pattern.html">Patr�n Singleton</a>
	 * @return referencia al ejemplar �nico
	 */
	public static JpaDaoFeatures getInstance() {
		if (myInstance==null){
			myInstance = new JpaDaoFeatures();
			log.info("Creando un nuevo EntityManager");
			try {
				if(emf==null) {
					log.info("Comienzo creando la factor�a de EntityManager\n");
					emf =
						Persistence.createEntityManagerFactory("h2-eclipselink");
						log.info("A continuaci�n creo un entityManager, usando la factor�a\n");
				}
				myEntityManager = emf.createEntityManager();
			}
			catch(Throwable ex) {
				log.severe("Ha fallado la creaci�n de SessionFactory "+ex+"\n");
				throw new ExceptionInInitializerError(ex);
			}	
		}
		return myInstance;
	}
/**
 * Ejecuta la acci�n dentro de una transacci�n JPA
 * @param action Acci�n a ejecutar
 */
	public void executeInsideTransaction(Consumer<EntityManager> action) {
		
		
        EntityTransaction tx = myEntityManager.getTransaction();
        try {
            tx.begin();
            action.accept(myEntityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
	/**
	 * 
	 * @return referencia al gestor de entidad {@link jakarta.persistence.EntityManager}
	 */
	public EntityManager getEntityManager() {
		return myEntityManager;
		
	}
}
