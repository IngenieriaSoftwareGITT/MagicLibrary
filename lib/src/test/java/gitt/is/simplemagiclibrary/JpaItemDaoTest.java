/**
 * 
 */
package gitt.is.simplemagiclibrary;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;

import gitt.is.magiclibrary.model.*;



/**
 * @author Isabel Rom�n
 * Test para probar la clase JpaItemDao, Dao para manejar los ejemplares de la biblioteca
 *
 */
class JpaItemDaoTest {
	/**
	 * Para trazar el c�digo {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaItemDaoTest.class.getName());
	static Item item1;
	static Item item2;
	static Book book1;
	static Book book2;
	static JpaItemDao undertest;
	static JpaBookDao bookdao;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("Entro en setUpBefore");
		bookdao =new JpaBookDao();
		
		log.info("Creo y persisto dos libros");
		book1 = new Book("Ingenier�a del Software","Ian Sommerville", new Date(111,0,1), "miisbn", 500);
		

		bookdao.save(book1);
		log.info("Libro "+book1+" persistido");
		book2 = new Book("Ingenier�a del Software: un enfoque pr�ctico","Ian Roger S. Pressman", new Date(110,0,1), "otroisbn", 200);
		
		bookdao.save(book2);
		log.info("Libro "+book2+" persistido");
		
		log.info("Ejemplares con los t�tulos a�adidos");
	
		item1 = new Item(book1);
		item1.setInventoryNr("item1");
		
				
		log.info("Item 1 creado "+item1);
		item2 = new Item(book2);
		item2.setInventoryNr("item2");
		

		log.info("Item 2 creado "+item2);
		undertest = new JpaItemDao();
		log.info("JpaItemDao bajo test creada");
	}


	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#JpaItemDao()}.
	 */
	@Test
	void testJpaItemDao() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#findById(String)}.
	 */
	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#findAll()}.
	 */
	@Test
	void testFindAll() {
		log.info('\n'+"------Entro en el m�todo para probar el m�todo findAll-----"+'\n');
	
		undertest.save(item1);
		log.info("Persisto el primer ejemplar "+item1+'\n');
		List<Item> items = undertest.findAll();
		log.info("encontrados: "+items);
		assertTrue(items.size()==1,"He metido un ejemplar pero hay "+items.size());
		
		
		
		undertest.save(item2);
		log.info("Persisto el segundo ejemplar "+item2+'\n');
		log.info("Busco todos los ejemplares");
		items = undertest.findAll();
		assertTrue(items.size()==2,"He metido dos ejemplares pero hay "+items.size());
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#save(gitt.is.magiclibrary.model.Item)}.
	 */
	@Test
	void testSave() {
		log.info('\n'+"------Entro en el m�todo para probar el m�todo save-----"+'\n');
		undertest.save(item1);
		log.info("Persisto "+item1);
		Optional<Item> recuperado=undertest.findById(item1.getInventoryNr());		
		if(recuperado.isPresent()){		
			log.info("Recupero "+recuperado.get());
			
			assertEquals(recuperado.get().getStatus(),item1.getStatus());
			assertEquals(recuperado.get().getItemInfo(),item1.getItemInfo());
			
		}else {
			fail("No estaba el ejemplar buscado");
		}
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#update(gitt.is.magiclibrary.model.Item)}.
	 */
	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#delete(gitt.is.magiclibrary.model.Item)}.
	 */
	@Test
	void testDeleteItem() {
		log.info("Entro en el m�todo para probar el m�todo delete (Item)");
		
		undertest.save(item1);
		log.info("Persisto "+item1);
		Optional<Item> recuperado = undertest.findById(item1.getInventoryNr());	
		if(recuperado.isPresent()){
			log.info("El ejemplar est�, lo voy a eliminar");		
			
			undertest.delete(recuperado.get());
		}else {
			fail("No se ha recuperado bien el ejemplar");
		}
		
		log.info("Lo vuelvo a buscar y ahora no deber�a estar");
		
		recuperado = undertest.findById(item1.getInventoryNr());	
	
		assertFalse(recuperado.isPresent(),"El ejemplar lo hab�a borrado, no puedo recuperarlo");
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaItemDao#delete(String)}.
	 */
	@Test
	void testDeleteString() {
		log.info('\n'+"Entro en el m�todo para probar el delete(String)"+'\n');
		
		undertest.save(item1);
		log.info("Persisto "+item1);
		Optional<Item> recuperado = undertest.findById(item1.getInventoryNr());	
		if(recuperado.isPresent()){
			log.info("El ejemplar est�, lo voy a eliminar");		
			
			undertest.delete(recuperado.get().getInventoryNr());
		}else {
			fail("No se ha recuperado bien el ejemplar");
		}
		log.info("Ahora lo vuelvo a buscar y no deber�a aparecer");
		recuperado = undertest.findById(item1.getInventoryNr());	
	
		assertFalse(recuperado.isPresent(),"El ejemplar lo hab�a borrado, no puedo recuperarlo");
	}

}
