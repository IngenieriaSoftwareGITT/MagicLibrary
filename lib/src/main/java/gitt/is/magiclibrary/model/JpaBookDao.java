/**
 * 
 */
package gitt.is.magiclibrary.model;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.logging.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.sql.Date;
import javax.persistence.PersistenceContext;
/**
 * @author Isabel Román
 * @see https://www.baeldung.com/java-dao-pattern
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class JpaBookDao implements Dao<Book> {
	private static final Logger log = Logger.getLogger(JpaBookDao.class.toString());
	private static EntityManager myEntityManager;
	private static JpaBookDao instance;
	private JpaBookDao( ) 
	{
	      log.info("Entro en el constructor");
	}
	public static JpaBookDao getInstance() {
		if (instance==null){
			log.info("Crea el entity Manager");
			getEntityManager();
			log.info("Crea el JpaBookDao");
			instance=new JpaBookDao();
		}
		return instance;
	}
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
	 @Override
	 public Optional<Book>  findById(long primaryKey) {
	       log.info("----\nBusco un Book usando el id "+primaryKey);
	       return Optional.ofNullable(getEntityManager().find(Book.class, primaryKey));
	   }
	 @Override
	 public List<Book> findAll(){
	       log.info("----\nBusco todos los libros");
	       TypedQuery<Book> q = getEntityManager().createQuery("select b from Book b", Book.class);
	       List<Book> resultList =q.getResultList();
	       log.info(q.getResultList().toString());
	       return resultList;
	 }
	 	
	@Override
	public void save(Book book) {
		executeInsideTransaction(myEntityManager->getEntityManager().persist(book));
	}

	@Override
	public void update(Book book) {
		executeInsideTransaction(myEntityManager -> getEntityManager().merge(book));
	}

	@Override
	public void delete(Book book) {
		executeInsideTransaction(myEntityManager->getEntityManager().remove(book));	
	}

	@Override
	public void delete(long id) {
		findById(id).ifPresent((value)->this.delete(value));		
	}
	
	public Optional<Book> findBookByAuthor(String author) {
		   log.info("Consulto Book con author= "+author);
		   Query query = getEntityManager()
		         .createQuery("Select a from Book a where a.author = :author");
		   query.setParameter("author", author);		   
	       Book book= (Book) query.getSingleResult();
	       
		   return Optional.ofNullable((Book)book);
		}
	
	 private void executeInsideTransaction(Consumer<EntityManager> action) {
	        EntityTransaction tx = getEntityManager().getTransaction();
	        try {
	            tx.begin();
	            action.accept(getEntityManager());
	            tx.commit(); 
	        }
	        catch (RuntimeException e) {
	            tx.rollback();
	            throw e;
	        }
	    }
}
