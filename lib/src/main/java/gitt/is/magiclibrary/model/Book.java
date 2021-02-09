/**
 * 
 */
package gitt.is.magiclibrary.model;

/**
 * @author Isabel Román
 * Entidad para los datos de un libro, un tipo concreto de título.
 * Hereda de title pero se guarda en una tabla diferente en la base de datos
 *
 */
import javax.persistence.Entity;

import java.sql.Date;

//import javax.persistence.DiscriminatorValue;


@Entity
//@DiscriminatorValue( value="BK" )

public class Book extends Title 
{
   /**
	 * Aceptado el número de serie por defecto
	 */
   private static final long serialVersionUID = 1L;
   private String isbn;
   private int pages;

   public Book( String id, String name, String author, Date publishedAt, String isbn,int pages ) 
   {
      super( id, name, author, publishedAt );
      this.isbn = isbn;
      this.pages = pages;
   }
   
   public Book( String name, String author, Date publishedAt, String isbn,int pages ) 
   {
      super( name, author, publishedAt );
      this.isbn = isbn;
      this.pages = pages;
   }

   public Book( ) 
   {
      super( );
   }

   public String getIsbn( )
   {
      return isbn;
   }

   public void setIsbn( String isbn )
   {
      this.isbn = isbn;
   }

   public int getPages( ) 
   {
      return pages;
   }

   public void setPages( int pages )
   {
      this.pages = pages;
   }
	@Override
	public String toString() {
		return super.toString()+" Book[isbn= "+isbn+" pages= "+pages+"]\n";
	}
}