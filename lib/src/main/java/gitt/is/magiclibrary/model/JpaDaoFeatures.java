
package gitt.is.magiclibrary.model;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Isabel Román
 * @version 0.0
 * <p> Esta es la clase incluye funcionalidades comunes a los DAO que utilizan la API JPA {@link javax.persistence}</p>
 * <p> Se sigue el patrón DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patrón DAO</a></p> 
 */
public class JpaDaoFeatures {
	/**
	 * Para trazar el código {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaDaoFeatures.class.toString());
	/**
	 * Referencia a la fábrica de gestores de entidad
	 */
	private static EntityManagerFactory emf=null;
	/**
	 * Referencia al gestor de entidad
	 */
	private static EntityManager myEntityManager=null;
	/**
	 * Referencia a la instancia única
	 */
	private static JpaDaoFeatures myInstance=null;
	
	/**
	 * Constructor simple
	 */
	private JpaDaoFeatures() {
		
	}
	/**
	 * Esta clase sigue el patrón singleton <a href="https://www.javacodegeeks.com/2015/09/singleton-design-pattern.html">Patrón Singleton</a>
	 * @return referencia al ejemplar único
	 */
	public static JpaDaoFeatures getInstance() {
		if (myInstance==null){
			myInstance = new JpaDaoFeatures();
			log.info("Creando un nuevo EntityManager");
			try {
				if(emf==null) {
					log.info("Comienzo creando la factoría de EntityManager\n");
					emf =
						Persistence.createEntityManagerFactory("h2-eclipselink");
						log.info("A continuación creo un entityManager, usando la factoría\n");
				}
				myEntityManager = emf.createEntityManager();
			}
			catch(Throwable ex) {
				log.severe("Ha fallado la creación de SessionFactory "+ex+"\n");
				throw new ExceptionInInitializerError(ex);
			}	
		}
		return myInstance;
	}
/**
 * Ejecuta la acción dentro de una transacción JPA
 * @param action Acción a ejecutar
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
	 * @return referencia al gestor de entidad {@link javax.persistence.EntityManager}
	 */
	public EntityManager getEntityManager() {
		return myEntityManager;
		
	}
}
