/**
 * Lo que he hecho hasta aquí me ha servido para:
 * configurar el proyecto
 * Entender cómo funciona, de forma básica jpa
 * 
 * Deuda técnica
 * No está bien organizado, hay que pensar qué managers vamos a tener y cuál será el singleton, no tiene sentido que sean todos hay cosas repetidas, no hay arquitectura
 * también hay que organizar bien en paquetes DAO y CONTROL, hay que mirar alguna referencia para verlo bien
 */
package gitt.is.magiclibrary.model;

import java.util.List;
import java.util.logging.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.sql.Date;
import javax.persistence.PersistenceContext;
/**
 * @author Isabel Román
 *
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class TitleManager {
	private static final Logger log = Logger.getLogger(TitleManager.class.toString());
	private static EntityManager myEntityManager;
	private static EntityManager getEntityManager() {
		if (myEntityManager==null){
			try {
			log.info("Comienzo creando la factoría de EntityManager\n");
			EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("h2-eclipselink");
			log.info("A continuación creo un entityManager, usando la factoría\n");
			myEntityManager = emf.createEntityManager();
			}
			catch(Throwable ex) {
				log.severe("Ha fallado la creación de SessionFactory "+ex+"\n");
				throw new ExceptionInInitializerError(ex);
			}
		}
        log.info("Devuelvo el entityManager creado\n");
        return myEntityManager;
     }
	
	 public static void main(String[] args) {
		log.info("Comienzo una transacción con la BBDD\n");
        getEntityManager().getTransaction().begin();

        Title myBook = new Title("Ingeniería del Software","Ian Sommerville", new Date(111,0,1));
        log.info("He creado el Title "+myBook+" y procedo a persistirlo");
     
        myEntityManager.persist(myBook);

        myBook = new Title("Ingeniería del Software: un enfoque práctico","Ian Roger S. Pressman", new Date(110,0,1));
        log.info("He creado el Title "+myBook+" y procedo a persistirlo");
        
      
        myEntityManager.persist(myBook);

        log.info("Finalizo la transacción con la base de datos\n");
        myEntityManager.getTransaction().commit();

      //  log.info("Invoco findById con primaryKey = '2L'");
       // Title found= findById(2L);
       // log.info("Recupero "+found);
        log.info("Invoco findAll");
        List<Title> encontrados=findAll();
        log.info("Recupero "+encontrados);
            
     //   log.info("Invoco findTitleByAuthor\n");
      //  Title found=findTitleByAuthor("Ian Roger S. Pressman");
       // log.info("Obtengo "+found);
     
	}
	
	 public static Title findById(java.lang.Object primaryKey) {
	        log.info("----\nBusco un Title usando el id "+primaryKey);
	        Title o = getEntityManager().find(Title.class, primaryKey);
	        log.info(o.toString());
	        return o;
	    }
	
	 public static Title findTitleByAuthor(String author) {
		   log.info("Consulto Title con author= "+author);
		   Query query = getEntityManager()
		         .createQuery("Select a from Title a where a.author = :author");
		   query.setParameter("author", author);
		   Title title = (Title) query.getSingleResult();
		   log.info("Resultado : " + title.toString());
		   return title;
		}

	  public static List<Title> findAll(){
	        log.info("----\nConsulta tipada usando JPQL");
	        TypedQuery<Title> q = getEntityManager().createQuery("select t from Title t", Title.class);
	        List<Title> resultList =q.getResultList();
	        log.info(q.getResultList().toString());
	        return resultList;
	    }
	  	
}
