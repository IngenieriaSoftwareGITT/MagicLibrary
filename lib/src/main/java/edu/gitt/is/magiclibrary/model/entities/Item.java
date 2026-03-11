package edu.gitt.is.magiclibrary.model.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;




import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;

import java.io.Serializable;
/**
 * <p>Entidad para almacenar los datos de un ejemplar concreto de la biblioteca</p>
 *
 * @author Isabel Rom�n
 * @version 0.0
 */

@Entity
public class Item implements Serializable{
	/**
	 * A�adido el n�mero de serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * Estados posibles de un ejemplar:
	 * Disponible, reservado, prestado, perdido
	 *
	 */

	public enum ItemState{
		/**
		 * Disponible
		 */	
		AVAILABLE,
		/**
		 * Reservado
		 */
		ASSIGNED,
		/**
		 * Prestado
		 */
		LOANED,
		/**
		 * Perdido
		 */
		LOST
	}
	/**
	 * Id un�voco
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String inventoryNr;
	
	/**
	 * <p>T�tulo asociado al ejemplar, s�lo uno, pero puede haber varios ejemplares con el mismo t�tulo</p>
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TITLE")
	private Title itemInfo;
	
	/**
	 * Estado del ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Item.ItemState}
	 */
	@Enumerated(EnumType.STRING)
	private ItemState status;
	/**
	 * Constructor de ejemplar simple, se construye siempre en estado disponible {@link edu.gitt.is.magiclibrary.model.entities.Item.ItemState}
	 */
	
	public Item() {
		super();
		this.status=ItemState.AVAILABLE;
	}
	/**
	 * 
	 * @param title El t�tulo concreto de este ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Title}
	 * @param status Estado en el que se construye el ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Item.ItemState}
	 */
	public Item(Title title, ItemState status) {
		super();
		this.itemInfo = title;
		this.status = status;
	}
	/**
	 * Se construye en estado disponible {@link edu.gitt.is.magiclibrary.model.entities.Item.ItemState}
	 * @param title El t�tulo concreto del ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Title}
	 */
	public Item(Title title) {
		super();
		this.itemInfo = title;
		this.status = ItemState.AVAILABLE;
	}
	/**
	 * 
	 * @return El t�tulo concreto del ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Title}
	 */
	public Title getItemInfo() {
		return itemInfo;
	}
	/**
	 * 
	 * @param title El t�tulo concreto del ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Title}
	 */
	public void setTitle(Title title) {
		this.itemInfo = title;
	}
	/**
	 * 
	 * @return Estado del ejemplar {@link edu.gitt.is.magiclibrary.model.entities.Item.ItemState}
	 */
	public ItemState getStatus() {
		return status;
	}
	/**
	 * 
	 * @return Identificador un�voco del ejemplar
	 */
	public String getInventoryNr() {
		return this.inventoryNr;
	}
	/**
	 * 
	 * @param number Identificador un�voco del ejemplar
	 */
	public void setInventoryNr(String number) {
		this.inventoryNr = number;
	}
	/**
	 * 
	 * @param status Estado {@link edu.gitt.is.magiclibrary.model.entities.Item.ItemState}
	 */
	public void setStatus(ItemState status) {
		this.status = status;
	}
	 /**
	    * Sobreescribo el m�todo toString para tener una representaci�n m�s clara del objeto al depurar
	    */
	@Override
	public String toString() {
		return "\n Item["+ inventoryNr + " status= "+ status+ " Title= "+ itemInfo.toString() +"]\n";
	}
	/**
	 * @see java.io.Serializable
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item )) return false;
        return inventoryNr != null && inventoryNr.equals(((Item) o).getInventoryNr());
    }
	
	/**
	 * @see java.io.Serializable
	 */
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
