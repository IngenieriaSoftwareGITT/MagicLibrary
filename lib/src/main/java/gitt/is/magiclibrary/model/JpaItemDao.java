package gitt.is.magiclibrary.model;

import java.util.List;
import java.util.Optional;

import java.util.logging.*;

import javax.persistence.TypedQuery;

import javax.persistence.PersistenceContext;

/**
 * @author Isabel Román
 * @version 0.0
 * <p> Esta es la clase para manejar las entidades de tipo Item (ejemplar) {@link gitt.is.magiclibrary.model.Item} y está codificada usando la API JPA {@link javax.persistence}</p>
 * <p> Se sigue el patrón DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patrón DAO</a></p> 
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class JpaItemDao implements Dao<Item> {
	/**
	 * Para trazar el código {@link java.util.logging}
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
		log.info("Voy a persistir el ejemplar\n"+item);
		jpa.executeInsideTransaction(myEntityManager -> jpa.getEntityManager().merge(item));
	}

	@Override
	public void update(Item t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Item item) {
		jpa.executeInsideTransaction(myEntityManager->jpa.getEntityManager().remove(item));	
		
	}

	@Override
	public void delete(String id) {
		findById(id).ifPresent((value)->this.delete(value));	
		
	}
	
}
