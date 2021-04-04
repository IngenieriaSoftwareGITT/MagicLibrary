package edu.gitt.is.magiclibrary.model.entities;

import javax.persistence.Entity;

import edu.gitt.is.magiclibrary.controller.MagicLibrary;

//import java.sql.Date;
import java.util.Date;
import java.util.logging.Logger;

//import javax.persistence.DiscriminatorValue;

/**
 * <p>Entidad para los datos de un libro (Book), un tipo concreto de título (Title) </p>
 * <p>Hereda de Title {@link edu.gitt.is.magiclibrary.model.entities.Title} pero se configura para que los datos se guarden en una tabla diferente en la base de datos</p>
 * <p> Se sigue el patrón DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patrón DAO</a></p> 
 * <p> La estructura de datos es conforme al proyecto ejemplo de MagiDraw (MagicLibrary) <img src="./doc-files/MLEntities.gif" alt="Modelo de MagicLibrary" height="50%" width="100%"/> </p>
 * @author Isabel Román
 * @version 0.0
 */
@Entity
//@DiscriminatorValue( value="BK" )

public class Book extends Title 
{
   /**
	 * Aceptado el número de serie por defecto
	 */
   private static final long serialVersionUID = 1L;
   /**
    * ISBN del libro
    */
   private String isbn;
   /**
    * Número de páginas
    */
   private int pages;
   /**
    * Trazador
    */
   private static Logger log=Logger.getLogger(MagicLibrary.class.getName());
   /**
    * Asignación manual del id
    * @param id identificador unívoco de la grabación audio {@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param name Nombre	{@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param author Autor {@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param publishedAt Fecha de publicación {@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param isbn El ISBN del libro
    * @param pages Número de páginas
    */
   public Book( String id, String name, String author, Date publishedAt, String isbn,int pages ) 
   {
	  super( id, name, author, publishedAt );
      this.isbn = isbn;
      this.pages = pages;
      log.info("Construyo un Book pasando todos los datos del libro, incluido el id");
   }
   /**
    * Asigna automática el id
    * @param name Nombre	{@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param author Autor {@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param date Fecha de publicación {@link edu.gitt.is.magiclibrary.model.entities.Title}
    * @param isbn El ISBN del libro
    * @param pages Número de páginas
    */
   public Book( String name, String author, Date date, String isbn,int pages ) 
   {
      super( name, author, date );
      this.isbn = isbn;
      this.pages = pages;
      log.info("Construyo un Book pasando todos los datos salvo el id, que se asigna automáticamente");
   }
/**
 * Constructor simple
 */
   public Book( ) 
   {
      super( );
      log.info("Construyo un Book vacío");
   }
   /**
    * 
    * @return isbn ISBN del libro
    */

   public String getIsbn( )
   {
      return isbn;
   }
/**
 * 
 * @param isbn ISBN del libro
 */
   public void setIsbn( String isbn )
   {
      this.isbn = isbn;
   }
  /**
   * 
   * @return Número de páginas
   */

   public int getPages( ) 
   {
      return pages;
   }
/**
 * 
 * @param pages Número de páginas
 */
   public void setPages( int pages )
   {
      this.pages = pages;
   }
   /**
    * Sobreescribo el método toString para tener una representación más clara del objeto al depurar
    */
	@Override
	public String toString() {
		return super.toString()+" Book[isbn= "+isbn+" pages= "+pages+"]\n";
	}
}