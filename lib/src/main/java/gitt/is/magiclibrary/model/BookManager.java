/**
 * 
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
public class BookManager {
	private static final Logger log = Logger.getLogger(BookManager.class.toString());
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

        Book myBook = new Book("Ingeniería del Software","Ian Sommerville", new Date(111,0,1), "miisbn", 500);
 
        log.info("He creado el Book "+myBook+" y procedo a persistirlo");
     
        myEntityManager.persist(myBook);

        myBook = new Book("Ingeniería del Software: un enfoque práctico","Ian Roger S. Pressman", new Date(110,0,1), "otroisbn", 200);
        log.info("He creado el Book "+myBook+" y procedo a persistirlo");
      
        myEntityManager.persist(myBook);
        
        AudioRecord myAudioRecord = new AudioRecord("SerHistoria","Cadenaser", new Date(115,11,1), "podcast", 200, "varias");
        myEntityManager.persist(myAudioRecord);
      

        log.info("Finalizo la transacción con la base de datos\n");
        myEntityManager.getTransaction().commit();

        log.info("Invoco findById con primaryKey = '2L'");
        Book found= findById(2L);
        log.info("Recupero "+found);
        log.info("Invoco findAll");
        List<Book> encontrados=findAll();
        log.info("Recupero "+encontrados);
            
        log.info("Invoco findBookByAuthor\n");
        Title found2=findBookByAuthor("Ian Roger S. Pressman");
        log.info("Obtengo "+found2);
        
        List<Title> encontrados2=TitleManager.findAll();
        log.info("Todos los titulos "+encontrados2.toString());
     
	}
	
	 public static Book findById(java.lang.Object primaryKey) {
	        log.info("----\nBusco un Book usando el id "+primaryKey);
	        Book o = getEntityManager().find(Book.class, primaryKey);
	        log.info(o.toString());
	        return o;
	    }
	
	 public static Title findBookByAuthor(String author) {
		   log.info("Consulto Book con author= "+author);
		   Query query = getEntityManager()
		         .createQuery("Select a from Book a where a.author = :author");
		   query.setParameter("author", author);
		   Book book = (Book) query.getSingleResult();
		   log.info("Resultado : " + book.toString());
		   return book;
		}

	  public static List<Book> findAll(){
	        log.info("----\nBusco todos los libros");
	        TypedQuery<Book> q = getEntityManager().createQuery("select b from Book b", Book.class);
	        List<Book> resultList =q.getResultList();
	        log.info(q.getResultList().toString());
	        return resultList;
	    }
	  	
}
