package gitt.is.magiclibrary.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.sql.Date;
import java.io.Serializable;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;
/**
 * 
 * @author Isabel Rom�n
 * <p>Entidad para almacenar los datos de un t�tulo de la biblioteca, informaci�n com�n a t�tulos de distinta naturaleza</p>
 * <p>Se elige que en la base de datos se use una tabla para cada tipo concreto de t�tulo y una para la informaci�n com�n</p>
 *
 */
@Entity
@Table
// @Inheritance( strategy = InheritanceType.SINGLE_TABLE )
// @DiscriminatorColumn( name="type" )
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
	@OneToMany(mappedBy = "itemInfo", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Item> items = new ArrayList<Item>();

	 /**
	    * Asignaci�n manual del id
	    * @param id identificador un�voco de la grabaci�n audio {@link gitt.is.magiclibrary.model.Title}
	    * @param name Nombre	{@link gitt.is.magiclibrary.model.Title}
	    * @param author Autor {@link gitt.is.magiclibrary.model.Title}
	    * @param publishedAt Fecha de publicaci�n {@link gitt.is.magiclibrary.model.Title}
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
	    * @param name Nombre	{@link gitt.is.magiclibrary.model.Title}
	    * @param author Autor {@link gitt.is.magiclibrary.model.Title}
	    * @param publishedAt Fecha de publicaci�n {@link gitt.is.magiclibrary.model.Title}
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
	public void SetId(String id) {
		this.id=id;
	}
/**
 * 
 * @param name Nombre del t�tulo
 */
	public void getName(String name) {
		this.name = name;
	}
/**
 * 
 * @param author Autor
 */
	public void getAuthor(String author) {
		this.author = author;
	}
	/**
	 * 
	 * @param date Fecha de publicaci�n 
	 */
	public void getPublishedAt(Date date) {
		this.publishedAt = date;
	}
	/**
	 * 
	 * @param items Lista de ejemplares de este t�utlo
	 */
	public void getItems(List<Item> items) {
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
