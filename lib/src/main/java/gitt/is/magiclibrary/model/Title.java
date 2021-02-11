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
 * @author Isabel Román
 * Entidad para almacenar los datos de un título de la biblioteca, información común a títulos de distinta naturaleza
 * Se elige que en la base de datos se use una tabla para cada tipo concreto de título y una para la información común
 *
 */
@Entity
@Table
// @Inheritance( strategy = InheritanceType.SINGLE_TABLE )
// @DiscriminatorColumn( name="type" )
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class Title implements Serializable{

	/**
	 * Añadido el número de serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String name;
	private String author;
	private Date publishedAt;
	@OneToMany(mappedBy = "itemInfo", cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<Item> items = new ArrayList<Item>();

	
	public Title(String id, String name, String author, Date publishedAt) {
		super();
		this.id= id;
		this.name = name;
		this.author = author;
		this.publishedAt = publishedAt;
	}

	public Title(String name, String author, Date publishedAt) {
		super();
		this.name = name;
		this.author = author;
		this.publishedAt = publishedAt;
	}
	
	protected Title() {
		super();
	}

	@Override
	public String toString() {
		return String.format(
				"\n Title[id=%s, Nombre='%s', Autor='%s', fecha de publicacion='%s']",
				id, name, author, publishedAt.toString());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAuthor() {
		return author;
	}
	public Date getPublishedAt() {
		return publishedAt;
	}
	public List<Item> getItems() {
		return items;
	}
	public void SetId(String id) {
		this.id=id;
	}

	public void getName(String name) {
		this.name = name;
	}

	public void getAuthor(String author) {
		this.author = author;
	}
	public void getPublishedAt(Date date) {
		this.publishedAt = date;
	}
	public void getItems(List<Item> items) {
		this.items = items;
	}
	public void addItem(Item item) {
		this.items.add(item);
		item.setTitle(this);
	}
	public void deleteItem(Item item) {
		this.items.remove(item);
		item.setTitle(null);
	}
}
