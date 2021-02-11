/**
 * 
 */
package gitt.is.magiclibrary.model;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author Isabel Román
 * @see https://www.baeldung.com/java-dao-pattern
 * Clase que incluye funcionalidades comunes a todos los DAO basados en la api JPA
 *
 */
public class JpaDaoFeatures {
	private static final Logger log = Logger.getLogger(JpaDaoFeatures.class.toString());
	private static EntityManagerFactory emf=null;
	private static EntityManager myEntityManager=null;
	private static JpaDaoFeatures myInstance=null;
	
	
	private JpaDaoFeatures() {
		
	}
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
	
	public EntityManager getEntityManager() {
		return myEntityManager;
		
	}
}
