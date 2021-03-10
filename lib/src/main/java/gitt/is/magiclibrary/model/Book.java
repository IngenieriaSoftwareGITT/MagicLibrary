package gitt.is.magiclibrary.model;

import javax.persistence.Entity;

import java.sql.Date;

//import javax.persistence.DiscriminatorValue;

/**
 * @author Isabel Rom�n
 * @version 0.0
 * <p>Entidad para los datos de un libro (Book), un tipo concreto de t�tulo (Title) </p>
 * <p>Hereda de Title {@link gitt.is.magiclibrary.model.Title} pero se configura para que los datos se guarden en una tabla diferente en la base de datos</p>
 * <p> Se sigue el patr�n DAO, puede ver un ejemplo en <a href="https://www.baeldung.com/java-dao-pattern">Ejemplo patr�n DAO</a></p> 
 * <p> La estructura de datos es conforme al proyecto ejemplo de MagiDraw (MagicLibrary) <img src="./doc-files/MLEntities.gif" alt="Modelo de MagicLibrary" height="50%" width="100%"/> </p>
 */
@Entity
//@DiscriminatorValue( value="BK" )

public class Book extends Title 
{
   /**
	 * Aceptado el n�mero de serie por defecto
	 */
   private static final long serialVersionUID = 1L;
   /**
    * ISBN del libro
    */
   private String isbn;
   /**
    * N�mero de p�ginas
    */
   private int pages;
   /**
    * Asignaci�n manual del id
    * @param id identificador un�voco de la grabaci�n audio {@link gitt.is.magiclibrary.model.Title}
    * @param name Nombre	{@link gitt.is.magiclibrary.model.Title}
    * @param author Autor {@link gitt.is.magiclibrary.model.Title}
    * @param publishedAt Fecha de publicaci�n {@link gitt.is.magiclibrary.model.Title}
    * @param isbn El ISBN del libro
    * @param pages N�mero de p�ginas
    */
   public Book( String id, String name, String author, Date publishedAt, String isbn,int pages ) 
   {
      super( id, name, author, publishedAt );
      this.isbn = isbn;
      this.pages = pages;
   }
   /**
    * Asigna autom�tica el id
    * @param name Nombre	{@link gitt.is.magiclibrary.model.Title}
    * @param author Autor {@link gitt.is.magiclibrary.model.Title}
    * @param publishedAt Fecha de publicaci�n {@link gitt.is.magiclibrary.model.Title}
    * @param isbn El ISBN del libro
    * @param pages N�mero de p�ginas
    */
   public Book( String name, String author, Date publishedAt, String isbn,int pages ) 
   {
      super( name, author, publishedAt );
      this.isbn = isbn;
      this.pages = pages;
   }
/**
 * Constructor simple
 */
   public Book( ) 
   {
      super( );
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
   * @return N�mero de p�ginas
   */

   public int getPages( ) 
   {
      return pages;
   }
/**
 * 
 * @param pages N�mero de p�ginas
 */
   public void setPages( int pages )
   {
      this.pages = pages;
   }
   /**
    * Sobreescribo el m�todo toString para tener una representaci�n m�s clara del objeto al depurar
    */
	@Override
	public String toString() {
		return super.toString()+" Book[isbn= "+isbn+" pages= "+pages+"]\n";
	}
}