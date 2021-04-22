/**
 * 
 */
package edu.gitt.is.magiclibrary.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.gitt.is.magiclibrary.model.*;
import edu.gitt.is.magiclibrary.model.entities.*;
import edu.gitt.is.magiclibrary.model.entities.Item.ItemState;



/**
 * 
 * <p>Test para probar la clase JpaItemDao, Dao para manejar los ejemplares de la biblioteca</p>
 * <p>Las clases Item y Book ya están implementadas y se manejan durante este test, al igual que JpaBookDao</p>
 * @author Isabel Román
 *
 */
class JpaItemDaoTest {
	/**
	 * Para trazar el código {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaItemDaoTest.class.getName());
	static Item item1;
	static Item item2;
	static Item item3;
	static Book book1;
	static Book book2;
	static JpaItemDao undertest;
	static JpaBookDao bookdao;

	/**
	 * <p>Para las pruebas usaré dos libros, tres ejemplares y un manejador de Libros</p>
	 * @throws java.lang.Exception
	 * @see org.junit.jupiter.api.BeforeAll
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info("Entro en setUpBefore");
		bookdao =new JpaBookDao();
		
		log.info("Creo y persisto dos libros");
		
		book1 = new Book("Ingeniería del Software","Ian Sommerville", new Date(111,0,1), "miisbn", 500);
		bookdao.save(book1);
		log.info("Libro "+book1+" persistido");
		
		
		book2 = new Book("Ingeniería del Software: un enfoque práctico","Ian Roger S. Pressman", new Date(110,0,1), "otroisbn", 200);
		bookdao.save(book2);
		log.info("Libro "+book2+" persistido");
		
		log.info("Ejemplares con los títulos añadidos");
	
		item1 = new Item(book1);
		item1.setInventoryNr("item1");				
		log.info("Item 1 creado "+item1);
		
		item2 = new Item(book2);
		item2.setInventoryNr("item2");
		log.info("Item 2 creado "+item2);
		
		item3=new Item(book2);
		item3.setInventoryNr("item3");
		log.info("Item 3 creado "+item3);
		
		undertest = new JpaItemDao();
		log.info("JpaItemDao bajo test creada");
	}
	/**
	 * <p>Cada test parte de la misma situación en la BBDD, dos libros y ningún ejemplar</p>
	 * <p>Como los test pueden añadir ejemplares para comenzar otro hay que eliminar los ejemplares previamente</p>
	 * @throws Exception
	 */
    @AfterEach
    void setUpAfterEach() throws Exception{
    	log.info('\n'+"------Antes de ejecutar un nuevo test elimino todos los ejemplares de la BBDD-----"+'\n');
    	List<Item> items = undertest.findAll();
    	items.forEach(item->undertest.delete(item));
    	items = undertest.findAll();
    	assertTrue(items.size()==0,"Debería haber borrado todos los ejemplares, pero hay "+items.size());
    }

		/**
	 * Test method for {@link edu.gitt.is.magiclibrary.model.JpaItemDao#findById(String)}.
	 * @see org.junit.jupiter.api.Test
	 */
	@Test
	void testFindById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.gitt.is.magiclibrary.model.JpaItemDao#findAll()}.
	 * @see org.junit.jupiter.api.Test
	 */
	@Test

	void testFindAll() {
		log.info('\n'+"------Entro en el método para probar el método findAll-----"+'\n');
	
		undertest.save(item1);
		log.info("Persisto el primer ejemplar "+item1+'\n');
		List<Item> items = undertest.findAll();
		log.info("encontrados: "+items);
		assertTrue(items.size()==1,"He metido un ejemplar pero hay "+items.size());
		assertEquals(items.get(0).getItemInfo(),item1.getItemInfo(),"La información del título no coincide con la almacenada");
		
		
		undertest.save(item2);
		log.info("Persisto el segundo ejemplar "+item2+'\n');
		log.info("Busco todos los ejemplares");
		items = undertest.findAll();
		assertTrue(items.size()==2,"He metido dos ejemplares pero hay "+items.size());
		
		undertest.save(item3);
		log.info("Persisto el tercer ejemplar "+item3+'\n');
		log.info("Busco todos los ejemplares");
		items = undertest.findAll();
		assertTrue(items.size()==3,"He metido tres ejemplares pero hay "+items.size());
	}

	/**
	 * Test method for {@link edu.gitt.is.magiclibrary.model.JpaItemDao#save(gitt.is.magiclibrary.model.Item)}.
	 * @see org.junit.jupiter.api.Test
	 */
	@Test
	void testSave() {
		log.info('\n'+"------Entro en el método para probar el método save-----"+'\n');
		undertest.save(item1);
		log.info("Persisto "+item1);
		Optional<Item> recuperado=undertest.findById(item1.getInventoryNr());		
		if(recuperado.isPresent()){		
			log.info("Recupero "+recuperado.get());
			
			assertEquals(recuperado.get().getStatus(),item1.getStatus(),"El estado del ejemplar recuperado no es el esperado");
			assertEquals(recuperado.get().getItemInfo(),item1.getItemInfo(), "La información del ejemplar recuperado no es la esperada");
			
		}else {
			fail("No estaba el ejemplar buscado");
		}
		//Verifico que si lo vuelvo a guardar no se duplica
		undertest.save(item1);
		
		List<Item> items= undertest.findAll();
		assertTrue(items.size()==1,"He metido un ejemplar pero hay "+items.size());
	}

	/**
	 * Test method for {@link edu.gitt.is.magiclibrary.model.JpaItemDao#update(gitt.is.magiclibrary.model.Item)}.
	 * @see org.junit.jupiter.api.Test
	 */
	@Test
	void testUpdate() {
		log.info('\n'+"------Entro en el método para probar el método update-----"+'\n');
		undertest.save(item1);
		log.info("Persisto "+item1);
		item1.setStatus(ItemState.LOANED);
		undertest.update(item1);
		Optional<Item> recuperado=undertest.findById(item1.getInventoryNr());
		assertEquals(item1.getStatus(),recuperado.get().getStatus(),"El estado no ha cambiado en la BBDD");
	}

	/**
	 * Test method for {@link edu.gitt.is.magiclibrary.model.JpaItemDao#delete(gitt.is.magiclibrary.model.Item)}.
	 * @see org.junit.jupiter.api.Test
	 */
	@Test
	void testDeleteItem() {
		log.info("Entro en el método para probar el método delete (Item)");
		
		undertest.save(item1);
		log.info("Persisto "+item1);
		Optional<Item> recuperado = undertest.findById(item1.getInventoryNr());	
		if(recuperado.isPresent()){
			log.info("El ejemplar está, lo voy a eliminar");		
			
			undertest.delete(recuperado.get());
		}else {
			fail("No se ha recuperado bien el ejemplar");
		}
		
		log.info("Lo vuelvo a buscar y ahora no debería estar");
		
		recuperado = undertest.findById(item1.getInventoryNr());	
	
		assertFalse(recuperado.isPresent(),"El ejemplar lo había borrado, no puedo recuperarlo");
	}

	/**
	 * Test method for {@link edu.gitt.is.magiclibrary.model.JpaItemDao#delete(String)}.
	 * @see org.junit.jupiter.api.Test
	 */
	@Test
	void testDeleteString() {
		log.info('\n'+"Entro en el método para probar el delete(String)"+'\n');
		
		undertest.save(item1);
		log.info("Persisto "+item1);
		Optional<Item> recuperado = undertest.findById(item1.getInventoryNr());	
		if(recuperado.isPresent()){
			log.info("El ejemplar está, lo voy a eliminar");		
			
			undertest.delete(recuperado.get().getInventoryNr());
		}else {
			fail("No se ha recuperado bien el ejemplar");
		}
		log.info("Ahora lo vuelvo a buscar y no debería aparecer");
		recuperado = undertest.findById(item1.getInventoryNr());	
	
		assertFalse(recuperado.isPresent(),"El ejemplar lo había borrado, no puedo recuperarlo");
	}

}
