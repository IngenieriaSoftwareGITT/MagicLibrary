/**
 * 
 */
package gitt.is.magiclibrary.model;

import java.util.List;
import java.util.Optional;

import java.util.logging.*;


import javax.persistence.Query;
import javax.persistence.TypedQuery;


import javax.persistence.PersistenceContext;
/**
 * @author Isabel Román
 * @see https://www.baeldung.com/java-dao-pattern
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class JpaBookDao implements Dao<Book> {
	
	private static final Logger log = Logger.getLogger(JpaBookDao.class.toString());
	private JpaDaoFeatures jpa;
	

	public JpaBookDao( ) 
	{
	      log.info("Entro en el constructor obtengo el objeto JpaDaoFeatures");
	      jpa = JpaDaoFeatures.getInstance();
	}
	
	 @Override
	 public Optional<Book>  findById(String primaryKey) {
	       log.info("----\nBusco un Book usando el id "+primaryKey);
	       Optional<Book> book = Optional.ofNullable(jpa.getEntityManager().find(Book.class, primaryKey));
	       return book;
	       
	   }
	 @Override
	 public List<Book> findAll(){
	       log.info("----\nBusco todos los libros de la biblioteca");
	       TypedQuery<Book> q = jpa.getEntityManager().createQuery("select b from Book b", Book.class);
	       List<Book> resultList =q.getResultList();
	       log.info(q.getResultList().toString());
	     
	       return resultList;
	 }
	 	
	@Override
	public void save(Book book) {
		log.info("Voy a persistir el libro\n"+book);
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().persist(book));
	}

	@Override
	public void update(Book book) {
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().merge(book));
	}

	@Override
	public void delete(Book book) {
		jpa.executeInsideTransaction(myEntityManager->jpa.getEntityManager().remove(book));	
	}

	@Override
	public void delete(String id) {
		findById(id).ifPresent((value)->this.delete(value));		
	}
	
	public Optional<Book> findBookByAuthor(String author) {
		   log.info("Consulto Book con author= "+author);
		   Query query = jpa.getEntityManager()
		         .createQuery("Select a from Book a where a.author = :author");
		   query.setParameter("author", author);		   
	       Book book= (Book) query.getSingleResult();
	      
		   return Optional.ofNullable((Book)book);
		}
	
	}
