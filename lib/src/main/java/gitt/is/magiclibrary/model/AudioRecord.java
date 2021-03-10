package gitt.is.magiclibrary.model;


import javax.persistence.Entity;

import java.sql.Date;

//import javax.persistence.DiscriminatorValue;
/**
 * @author Isabel Román
 * @version 0.0
 * <p>Entidad para almacenar los datos de una grabación de audio.</p>
 * <p>Hereda de {@link gitt.is.magiclibrary.model.Title}, los datos particulares a la clase AudioRecord se guardan en una tabla, pero los comunes a Title se guardan en otra</p>
 *
 */

@Entity
//@DiscriminatorValue( value="AR" )

public class AudioRecord extends Title 
{
   /**
	 * Añadido número de serie por defecto
	 */
   private static final long serialVersionUID = 1L;
   /**
    * Etiqueta
    */
   private String label;
   /**
    * Duración de la grabación
    */
   private int duration;
   /**
    * Número de pistas
    */
   private String tracks;
   /**
    * 
    * @param id identificador unívoco de la grabación audio {@link gitt.is.magiclibrary.model.Title}
    * @param name Nombre	{@link gitt.is.magiclibrary.model.Title}
    * @param author Autor {@link gitt.is.magiclibrary.model.Title}
    * @param publishedAt Fecha de publicación {@link gitt.is.magiclibrary.model.Title}
    * @param label Etiqueta
    * @param duration Duración
    * @param tracks Número de Tracks
    */

   public AudioRecord(String id, String name, String author, Date publishedAt, String label,int duration, String tracks) 
   {
      super(id, name, author, publishedAt );
      this.label = label;
      this.duration = duration;
      this.tracks = tracks;
   }
   /**
    * 
    * @param name Nombre {@link gitt.is.magiclibrary.model.Title}
    * @param author Autor {@link gitt.is.magiclibrary.model.Title}
    * @param publishedAt Fecha de publicación {@link gitt.is.magiclibrary.model.Title}
    * @param label Etiqueta
    * @param duration Duración
    * @param tracks Número de Tracks
    */
   
   public AudioRecord( String name, String author, Date publishedAt, String label,int duration, String tracks) 
   {
      super( name, author, publishedAt );
      this.label = label;
      this.duration = duration;
      this.tracks = tracks;
   }
/**
 * Constructor simple
 */
   public AudioRecord( ) 
   {
      super( );
   }
/**
 * 
 * @return La etiqueta del AudioRecord
 */
   public String getLabel( )
   {
      return label;
   }
/**
 * 
 * @param label Etiqueta
 */
   public void setLabel( String label )
   {
      this.label = label;
   }
/**
 * 
 * @return Duración de la grabación
 */
   public int getDuration( ) 
   {
      return duration;
   }
/**
 * 
 * @param duration Duración de la grabación
 */
   public void setDuration( int duration )
   {
      this.duration = duration;
   }
   /**
    * Sobreescribo el método toString para tener una representación más clara del objeto al depurar
    */
	@Override
	public String toString() {
		return super.toString()+" AudioRecord[label= "+label+" duration= "+duration+" tracks= "+tracks+"]\n";
	
	}
}