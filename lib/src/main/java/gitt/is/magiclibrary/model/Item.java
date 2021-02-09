/**
 * 
 */
package gitt.is.magiclibrary.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;




import javax.persistence.Enumerated;

import javax.persistence.JoinColumn;

import java.io.Serializable;

/**
 * @author Isabel Román
 * Entidad para almacenar los datos de un ejemplar concreto de la biblioteca
 *
 */
@Entity
public class Item implements Serializable{
	/**
	 * Añadido el número de serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	public enum ItemState{
		AVAILABLE,
		ASSIGNED,
		LOANED,
		LOST
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String inventoryNr;
	
	@ManyToOne
	@JoinColumn(name = "TITLE")
	private Title itemInfo;
	
	@Enumerated(EnumType.STRING)
	private ItemState status;
	
	public Item() {
		super();
		this.status=ItemState.AVAILABLE;
	}
	public Item(Title title, ItemState status) {
		super();
		this.itemInfo = title;
		this.status = status;
	}
	public Item(Title title) {
		super();
		this.itemInfo = title;
		this.status = ItemState.AVAILABLE;
	}
	
	public Title getItemInfo() {
		return itemInfo;
	}
	public void setTitle(Title title) {
		this.itemInfo = title;
	}
	public ItemState getStatus() {
		return status;
	}
	public String getInventoryNr() {
		return this.inventoryNr;
	}
	public void setInventoryNr(String number) {
		this.inventoryNr = number;
	}
	public void setStatus(ItemState status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "\n Item["+ inventoryNr + " status= "+ status+ " Title= "+ itemInfo.toString() +"]\n";
	}
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item )) return false;
        return inventoryNr != null && inventoryNr.equals(((Item) o).getInventoryNr());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
