package gitt.is.magiclibrary.model;

import java.util.List;
import java.util.Optional;

import java.util.logging.*;


import javax.persistence.Query;
import javax.persistence.TypedQuery;


import javax.persistence.PersistenceContext;
/**
 * @author Isabel Román
 * @version 0.0
 * <p> Esta es la clase para manejar las entidades de tipo Book (libro) {@link gitt.is.magiclibrary.model.Book} y está codificada usando la API JPA {@link javax.persistence}</p>
 * <p> Se sigue el patrón DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patrón DAO</a></p> 
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class JpaBookDao implements Dao<Book> {
	/**
	 * Para trazar el código {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaBookDao.class.toString());
	/**
	 * Funcionalidades para manejar la persistencia de entidades usando JPA
	 * 
	 */
	private JpaDaoFeatures jpa;
	
/**
 * Constructor simple
 */
	public JpaBookDao( ) 
	{
	      log.info("Entro en el constructor obtengo el objeto JpaDaoFeatures");
	      jpa = JpaDaoFeatures.getInstance();
	}
	
	/**
	 *
	 * {@link gitt.is.magiclibrary.model.Dao}
	 */
	 @Override
	 public Optional<Book>  findById(String primaryKey) {
	       log.info("----\nBusco un Book usando el id "+primaryKey);
	       Optional<Book> book = Optional.ofNullable(jpa.getEntityManager().find(Book.class, primaryKey));
	       return book;
	       
	   }
	 /**
	 *
	 * {@link gitt.is.magiclibrary.model.Dao}
	 */
	 @Override
	 public List<Book> findAll(){
	       log.info("----\nBusco todos los libros de la biblioteca");
	       TypedQuery<Book> q = jpa.getEntityManager().createQuery("select b from Book b", Book.class);
	       List<Book> resultList =q.getResultList();
	       log.info(q.getResultList().toString());
	     
	       return resultList;
	 }
	 /**
	 *
	 * {@link gitt.is.magiclibrary.model.Dao}
	 */ 	
	@Override
	public void save(Book book) {
		log.info("Voy a persistir el libro\n"+book);
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().persist(book));
	}
	/**
	 *
	 * {@link gitt.is.magiclibrary.model.Dao}
	 */
	@Override
	public void update(Book book) {
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().merge(book));
	}
	/**
	 *
	 * {@link gitt.is.magiclibrary.model.Dao}
	 */
	@Override
	public void delete(Book book) {
		jpa.executeInsideTransaction(myEntityManager->jpa.getEntityManager().remove(book));	
	}
	/**
	 *
	 * {@link gitt.is.magiclibrary.model.Dao}
	 */
	@Override
	public void delete(String id) {
		findById(id).ifPresent((value)->this.delete(value));		
	}
/**
 * Busca un libro por su autor
 * @param author Autor a buscar
 * @return Libro {@link java.util.Optional} Relleno con el libro si se encontraba, o vacío si no estaba
 */
	public Optional<Book> findBookByAuthor(String author) {
		   log.info("Consulto Book con author= "+author);
		   Query query = jpa.getEntityManager()
		         .createQuery("Select a from Book a where a.author = :author");
		   query.setParameter("author", author);		   
	       Book book= (Book) query.getSingleResult();
	      
		   return Optional.ofNullable((Book)book);
		}
	
	}
