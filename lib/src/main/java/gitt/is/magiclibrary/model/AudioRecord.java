/**
 * 
 */
package gitt.is.magiclibrary.model;

/**
 * @author Isabel Román
 * Entidad para almacenar los datos de una grabación de audio.
 * Hereda de Title, los datos particulares a la clase AudioRecord se guardan en una tabla, pero los comunes (title) se guardan en otra
 *
 */
import javax.persistence.Entity;

import java.sql.Date;

//import javax.persistence.DiscriminatorValue;


@Entity
//@DiscriminatorValue( value="AR" )

public class AudioRecord extends Title 
{
   /**
	 * Añadido número de serie por defecto
	 */
   private static final long serialVersionUID = 1L;
   private String label;
   private int duration;
   private String tracks;

   public AudioRecord(String id, String name, String author, Date publishedAt, String label,int duration, String tracks) 
   {
      super(id, name, author, publishedAt );
      this.label = label;
      this.duration = duration;
      this.tracks = tracks;
   }
   
   public AudioRecord( String name, String author, Date publishedAt, String label,int duration, String tracks) 
   {
      super( name, author, publishedAt );
      this.label = label;
      this.duration = duration;
      this.tracks = tracks;
   }

   public AudioRecord( ) 
   {
      super( );
   }

   public String getLabel( )
   {
      return label;
   }

   public void setLabel( String label )
   {
      this.label = label;
   }

   public int getDuration( ) 
   {
      return duration;
   }

   public void setDuration( int duration )
   {
      this.duration = duration;
   }
	@Override
	public String toString() {
		return super.toString()+" AudioRecord[label= "+label+" duration= "+duration+" tracks= "+tracks+"]\n";
	
	}
}