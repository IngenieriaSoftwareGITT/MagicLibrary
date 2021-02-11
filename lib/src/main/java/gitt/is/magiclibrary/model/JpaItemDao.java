/**
 * 
 */
package gitt.is.magiclibrary.model;

import java.util.List;
import java.util.Optional;

import java.util.logging.*;

import javax.persistence.TypedQuery;

import javax.persistence.PersistenceContext;

/**
 * @author isa
 *
 */
@PersistenceContext(unitName = "h2-eclipselink")
public class JpaItemDao implements Dao<Item> {
	
	private static final Logger log = Logger.getLogger(JpaItemDao.class.toString());
	private JpaDaoFeatures jpa;
	
	
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
