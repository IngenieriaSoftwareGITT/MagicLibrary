package gitt.is.magiclibrary.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.sql.Date;
import java.io.Serializable;

@Entity
@Table
// @Inheritance( strategy = InheritanceType.SINGLE_TABLE )
// @DiscriminatorColumn( name="type" )
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )
public class Title implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String author;
	private Date publishedAt;

	
	public Title(Long id, String name, String author, Date publishedAt) {
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
				"\nTitle[id=%d, Nombre='%s', Autor='%s', fecha de publicacion='%s']",
				id, name, author, publishedAt.toString());
	}

	public Long getId() {
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
}
