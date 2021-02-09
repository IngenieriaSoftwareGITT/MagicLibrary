/**
 * 
 */
package gitt.is.simplemagiclibrary;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import gitt.is.simplemagiclibrary.Book;


/**
 * @author Isabel Román
 *
 */
class JpaBookDaoTest {
	private static final Logger log = Logger.getLogger(Library.class.toString());
	static Book book1;
	static Book book2;
//	static JpaBookDao undertest;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		  log.info("Entro en setUpBefore");
		  book1 = new Book("Ingeniería del Software","Ian Sommerville", new Date(111,0,1), "miisbn", 500);
		  log.info("Libro 1 creado: "+book1);
		  book2 = new Book("Ingeniería del Software: un enfoque práctico","Ian Roger S. Pressman", new Date(110,0,1), "otroisbn", 200);
		  log.info("Libro 2 creado: "+book2);
		//  undertest = JpaBookDao.getInstance();
		//  log.info("JpaBookDao bajo test creada");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#findById(long)}.
	 */
	@Test
	final void testFindById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#findAll()}.
	 */
	@Test
	final void testFindAll() {
		log.info("Entro en el método para probar el método findAll");
		JpaBookDao.getInstance().save(book1);
		JpaBookDao.getInstance().save(book2);
		List<Book> books = JpaBookDao.getInstance().findAll();
		assertTrue(books.size()==2,"He metido dos libros pero hay "+books.size());

	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#save(gitt.is.simplemagiclibrary.Book)}.
	 */
	@Test
	final void testSave() {
		log.info("Entro en el método para probar el método save");
		JpaBookDao.getInstance().save(book1);
		log.info("Persisto "+book1);
		
	
		//recuperado=undertest.findBookByAuthor("Ian Sommerville");
		
		if(JpaBookDao.getInstance().findById(1L).isPresent()){
			Book recuperado=JpaBookDao.getInstance().findById(1L).get();
			
			log.info("Recupero "+recuperado);
			//	assertEquals(recuperado,book1);
			assertEquals(recuperado.getAuthor(),"Ian Sommerville");
			assertEquals(recuperado.getName(),"Ingeniería del Software");
			assertEquals(recuperado.getPages(),500);
			assertEquals(recuperado.getPublishedAt(),new Date(111,0,1));
			assertEquals(recuperado.getIsbn(),"miisbn");	
		}else {
			fail("No estaba el libro");
		}
		
	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#update(gitt.is.simplemagiclibrary.Book, java.lang.String[])}.
	 */
	@Test
	final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#delete(gitt.is.simplemagiclibrary.Book)}.
	 */
	@Test
	final void testDeleteBook() {
		log.info("Entro en el método para probar el método delete");
		
		JpaBookDao.getInstance().save(book1);
		log.info("Persisto "+book1);
		Optional<Book> recuperado = JpaBookDao.getInstance().findById(book1.getId());	
		if(recuperado.isPresent()){
			log.info("El libro está, lo voy a eliminar");		
			
			JpaBookDao.getInstance().delete(recuperado.get());
		}else {
			fail("No se ha recuperado bien el libro");
		}
		recuperado = JpaBookDao.getInstance().findById(book1.getId());	
		assertFalse(recuperado.isPresent(),"El libro lo había borrado, no puedo recuperarlo");
			
	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#delete(long)}.
	 */
	@Test
	final void testDeleteLong() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link gitt.is.simplemagiclibrary.JpaBookDao#findBookByAuthor(java.lang.String)}.
	 */
	
	@Test
	final void testFindBookByAuthor() {
		log.info("Entro en el método para probar el método findBookByAuthor");
		
		JpaBookDao.getInstance().save(book1);
		log.info("Persisto "+book1);
		Optional<Book> recuperado = JpaBookDao.getInstance().findBookByAuthor(book1.getAuthor());	
		assertTrue(recuperado.isPresent());
		assertEquals(recuperado.get().getAuthor(),book1.getAuthor(),"No tienen el mismo autor");
	}
	

}
