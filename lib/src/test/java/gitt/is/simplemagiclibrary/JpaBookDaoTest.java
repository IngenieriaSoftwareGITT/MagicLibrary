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

import gitt.is.magiclibrary.model.Book;
import gitt.is.magiclibrary.model.JpaBookDao;


/**
 * @author Isabel Román
 * Test para probar JpaBookDao, clase para manejar los libros de la biblioteca
 */
class JpaBookDaoTest {
	/**
	 * Para trazar el código {@link java.util.logging}
	 */
	private static final Logger log = Logger.getLogger(JpaBookDaoTest.class.getName());
	
	static Book book1;
	static Book book2;
	static JpaBookDao undertest;
	

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
		  undertest = new JpaBookDao();
		  log.info("JpaBookDao bajo test creada");
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
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#findById(String)}.
	 */
	@Test
	final void testFindById() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#findAll()}.
	 */
	@Test
	final void testFindAll() {
		log.info("Entro en el método para probar el método findAll");
		log.info("Persisto el libro 1 "+book1);
		undertest.save(book1);
		
		log.info("Persisto el libro 2 "+book2);
		undertest.save(book2);
		List<Book> books = undertest.findAll();
		assertTrue(books.size()==2,"He metido dos libros pero hay "+books.size());

	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#save(gitt.is.magiclibrary.model.Book)}.
	 */
	@Test
	final void testSave() {
		log.info("Entro en el método para probar el método save");
		undertest.save(book1);
		log.info("Persisto "+book1);
		
	
		Optional<Book> recuperado=undertest.findById(book1.getId());
		
		if(recuperado.isPresent()){
			
			log.info("Recupero "+recuperado);
		
			assertEquals(recuperado.get().getAuthor(),"Ian Sommerville");
			assertEquals(recuperado.get().getName(),"Ingeniería del Software");
			assertEquals(recuperado.get().getPages(),500);
			assertEquals(recuperado.get().getPublishedAt(),new Date(111,0,1));
			assertEquals(recuperado.get().getIsbn(),"miisbn");	
		}else {
			fail("No estaba el libro");
		}
		
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#update(gitt.is.magiclibrary.model.Book)}.
	 */
	@Test
	final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#delete(gitt.is.magiclibrary.model.Book)}.
	 */
	@Test
	final void testDeleteBook() {
		log.info("Entro en el método para probar el método delete");
		
		undertest.save(book1);
		log.info("Persisto "+book1);
		Optional<Book> recuperado = undertest.findById(book1.getId());	
		if(recuperado.isPresent()){
			log.info("El libro está, lo voy a eliminar");		
			
			undertest.delete(recuperado.get());
		}else {
			fail("No se ha recuperado bien el libro");
		}
		recuperado = undertest.findById(book1.getId());	
		assertFalse(recuperado.isPresent(),"El libro lo había borrado, no puedo recuperarlo");
			
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#delete(java.lang.String)}.
	 */
	@Test
	final void testDeleteLong() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link gitt.is.magiclibrary.model.JpaBookDao#findBookByAuthor(java.lang.String)}.
	 */
	
	@Test
	final void testFindBookByAuthor() {
		log.info("Entro en el método para probar el método findBookByAuthor");
		
		undertest.save(book1);
		log.info("Persisto "+book1);
		Optional<Book> recuperado = undertest.findBookByAuthor(book1.getAuthor());	
		assertTrue(recuperado.isPresent());
		assertEquals(recuperado.get().getAuthor(),book1.getAuthor(),"No tienen el mismo autor");
	}
	

}
