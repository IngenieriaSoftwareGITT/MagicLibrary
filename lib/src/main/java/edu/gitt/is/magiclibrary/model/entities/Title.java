package edu.gitt.is.magiclibrary.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NamedQuery;

//import java.sql.Date;
import java.util.Date;
import java.io.Serializable;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
/**
 * 
 * <p>Entidad para almacenar los datos de un t�tulo de la biblioteca, informaci�n com�n a t�tulos de distinta naturaleza</p>
 * <p>Se elige que en la base de datos se use una tabla para cada tipo concreto de t�tulo y una para la informaci�n com�n</p>
 * {@link javax.persistence.Entity}
 * {@link javax.persistence.Table}
 * {@link javax.persistence.Inheritance}
 * {@link javax.persistence.InheritanceType}
 * @author Isabel Rom�n
 * @version 0.0
 */
@Entity
@Table
// @Inheritance( strategy = InheritanceType.SINGLE_TABLE )
// @DiscriminatorColumn( name="type" )
/**
 * <p>Query para localizar un t�tulo por nombre y autor</p>
 * @author irm
 *
 */
@NamedQuery(name="Title.findTitleByNameAndAuthor",
query="SELECT t " +
      "FROM Title t " +
      "WHERE t.name = :name AND t.author = :author")
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class Title implements Serializable{

	/**
	 * A�adido el n�mero de serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador un�voco
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
 	private String id;
	/**
	 * Nombre del T�tulo
	 */
	private String name;
	/**
	 * Autor
	 */
	private String author;
	/**
	 * Fecha de publicaci�n
	 */
	private Date publishedAt;
	/**
	 * Lista de ejemplares de este t�tulo, puede haber varias, pero un ejemplar s�lo puede contener un t�ulo
	 */

	@OneToMany(mappedBy = "itemInfo", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items = new ArrayList<Item>();

	 /**
	    * Asignaci�n manual del id
	    * @param id identificador un�voco de la grabaci�n audio {@link edu.gitt.is.magiclibrary.model.entities.Title}
	    * @param name Nombre	{@link edu.gitt.is.magiclibrary.model.entities.Title}
	    * @param author Autor {@link edu.gitt.is.magiclibrary.model.entities.Title}
	    * @param publishedAt Fecha de publicaci�n {@link edu.gitt.is.magiclibrary.model.entities.Title}
	    */
	public Title(String id, String name, String author, Date publishedAt) {
		super();
		this.id= id;
		this.name = name;
		this.author = author;
		this.publishedAt = publishedAt;
	}
	  /**
	    * Asigna autom�tica el id
	    * @param name Nombre	{@link edu.gitt.is.magiclibrary.model.entities.Title}
	    * @param author Autor {@link edu.gitt.is.magiclibrary.model.entities.Title}
	    * @param publishedAt Fecha de publicaci�n {@link edu.gitt.is.magiclibrary.model.entities.Title}
	    */
	public Title(String name, String author, Date publishedAt) {
		super();
		this.name = name;
		this.author = author;
		this.publishedAt = publishedAt;
	}
	/**
	 * Constructor simple
	 */
	protected Title() {
		super();
	}

	@Override
	public String toString() {
		return String.format(
				"\n Title[id=%s, Nombre='%s', Autor='%s', fecha de publicacion='%s']",
				id, name, author, publishedAt.toString());
	}
/**
 * 
 * @return El identificador un�voco
 */
	public String getId() {
		return id;
	}
/**
 * 
 * @return Nombre del t�tulo
 */
	public String getName() {
		return name;
	}
/**
 * 
 *  @return Autor
 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 
	 * @return Fecha de publicaci�n
	 */
	public Date getPublishedAt() {
		return publishedAt;
	}
	/**
	 * 
	 * @return Lista de ejemplares de este t�tulo
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * 
	 * @param id Establece el identificador un�voco
	 */
	public void setId(String id) {
		this.id=id;
	}

/**
 * 
 * @param name Nombre del t�tulo
 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 
	 * @param author Autor
	 */
		public void setAuthor(String author) {
			this.author = author;
		}


	/**
	 * 
	 * @param date Fecha de publicaci�n 
	 */
	public void setPublishedAt(Date date) {
		this.publishedAt = date;
	}
	/**
	 * @return Devuelve la fecha de publicaci�n
	 * @param date Fecha de publicaci�n 
	 */
	public Date getPublishedAt(Date date) {
		return this.publishedAt;
	}
	/**
	 * 
	 * @param items Lista de ejemplares de este t�tulo
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	/**
	 * 
	 * @param item Nuevo ejemplar del t�tulo para a�adir a la lista
	 */
	public void addItem(Item item) {
		this.items.add(item);
		item.setTitle(this);
	}
	/**
	 * Elimina un ejemplar concreto de este t�tulo
	 * @param item ejemplar a eliminar
	 */
	public void deleteItem(Item item) {
		this.items.remove(item);
		item.setTitle(null);
	}
}
