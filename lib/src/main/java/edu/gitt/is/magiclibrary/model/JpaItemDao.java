package edu.gitt.is.magiclibrary.model;

import java.util.List;
import java.util.Optional;

import java.util.logging.*;

import jakarta.persistence.TypedQuery;

import edu.gitt.is.magiclibrary.model.entities.Book;
import edu.gitt.is.magiclibrary.model.entities.Item;
import edu.gitt.is.magiclibrary.model.entities.Title;

import jakarta.persistence.PersistenceContext;

/**
 * <p> Esta es la clase para manejar las entidades de tipo Item (ejemplar) {@link edu.gitt.is.magiclibrary.model.entities.Item} y est� codificada usando la API JPA {@link javax.persistence}</p>
 * <p> Se sigue el patr�n DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patr�n DAO</a></p>
 * @author Isabel Rom�n
 * @version 0.0 
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class JpaItemDao implements Dao<Item> {
	/**
	 * Para trazar el c�digo {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaItemDao.class.toString());
	/**
	 * Funcionalidades para manejar la persistencia de entidades usando JPA
	 * 
	 */
	private JpaDaoFeatures jpa;
	
	/**
	 * Constructor simple
	 */
	public JpaItemDao( ) 
	{
	      log.info("Entro en el constructor y obtengo el objeto JpaDaoFeatures");
	      jpa = JpaDaoFeatures.getInstance();
	}

	@Override
	public Optional<Item> findById(String primaryKey) {
		  log.info("----\nBusco un Item usando el id "+primaryKey);
	      return Optional.ofNullable(jpa.getEntityManager().find(Item.class, primaryKey));
	}

	@Override
	public List<Item> findAll() {
	       log.info("----\nBusco todos los ejemplares de la biblioteca");
	       TypedQuery<Item> q = jpa.getEntityManager().createQuery("select i from Item i", Item.class);
	       List<Item> resultList =q.getResultList();
	       log.info(q.getResultList().toString());	     
	       return resultList;
	}

	@Override
	public void save(Item item) {
		log.info("\nVoy a persistir el ejemplar\n"+item);
	
		/**
		 * Antes de persistir la entidad deber�a verifico si el T�tulo asociado existe ya 
		 * Para eso debo buscar el t�tulo usando los par�metros disponibles, el id no est� a�n, pero si se localiza se asigna
		 * Si no se crea un t�tulo nuevo
		 */
		log.info("\nBusco el t�tulo: "+item.getItemInfo());
		try {
			Optional<Title> title = Optional.ofNullable((Title) jpa.getEntityManager()
					.createNamedQuery("Title.findTitleByNameAndAuthor")
					.setParameter("name", item.getItemInfo().getName())
					.setParameter("author", item.getItemInfo().getAuthor())
					.getSingleResult());
			if(title.isPresent()) {
				log.info("\nLocalizado el t�tulo, con id: "+title.get().getId());
				item.setTitle(title.get());
			}
		}catch(jakarta.persistence.NoResultException e) {
			log.info("\nT�tulo no localizado se crear� uno nuevo");
		}
				
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().persist(item));
	}

	@Override
	public void update(Item item) {
		log.info("Voy a actualizar el ejemplar\n"+item);
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().merge(item));
		
	}

	@Override
	public void delete(Item item) {
		log.info("Voy a eliminar el ejemplar\n"+item);
		jpa.executeInsideTransaction(myEntityManager->jpa.getEntityManager().remove(item));	
		
	}

	@Override
	public void delete(String id) {
		findById(id).ifPresent((value)->this.delete(value));	
		
	}
	
}
