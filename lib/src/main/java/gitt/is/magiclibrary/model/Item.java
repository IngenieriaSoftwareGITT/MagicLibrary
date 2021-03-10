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
 * @author Isabel Rom�n
 * @version 0.0
 * <p>Entidad para almacenar los datos de un ejemplar concreto de la biblioteca</p>
 *
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
	 * T�tulo asociado al ejemplar, s�lo uno, pero puede haber varios ejemplares con el mismo t�tulo
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "TITLE")
	private Title itemInfo;
	
	/**
	 * Estado del ejemplar {@link gitt.is.magiclibrary.model.Item.ItemState}
	 */
	@Enumerated(EnumType.STRING)
	private ItemState status;
	/**
	 * Constructor de ejemplar simple, se construye siempre en estado disponible {@link gitt.is.magiclibrary.model.Item.ItemState}
	 */
	
	public Item() {
		super();
		this.status=ItemState.AVAILABLE;
	}
	/**
	 * 
	 * @param title El t�tulo concreto de este ejemplar {@link gitt.is.magiclibrary.model.Title}
	 * @param status Estado en el que se construye el ejemplar {@link gitt.is.magiclibrary.model.Item.ItemState}
	 */
	public Item(Title title, ItemState status) {
		super();
		this.itemInfo = title;
		this.status = status;
	}
	/**
	 * Se construye en estado disponible {@link gitt.is.magiclibrary.model.Item.ItemState}
	 * @param title El t�tulo concreto del ejemplar {@link gitt.is.magiclibrary.model.Title}
	 */
	public Item(Title title) {
		super();
		this.itemInfo = title;
		this.status = ItemState.AVAILABLE;
	}
	/**
	 * 
	 * @return El t�tulo concreto del ejemplar {@link gitt.is.magiclibrary.model.Title}
	 */
	public Title getItemInfo() {
		return itemInfo;
	}
	/**
	 * 
	 * @param title El t�tulo concreto del ejemplar {@link gitt.is.magiclibrary.model.Title}
	 */
	public void setTitle(Title title) {
		this.itemInfo = title;
	}
	/**
	 * 
	 * @return Estado del ejemplar {@link gitt.is.magiclibrary.model.Item.ItemState}
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
	 * @param status Estado {@link gitt.is.magiclibrary.model.Item.ItemState}
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
