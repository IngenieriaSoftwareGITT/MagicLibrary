package edu.gitt.is.magiclibrary.view;

import javax.swing.JTextField;

import edu.gitt.is.magiclibrary.model.entities.Book;
import edu.gitt.is.magiclibrary.model.entities.Item;
import edu.gitt.is.magiclibrary.model.entities.Title;

import java.text.ParseException;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JList;

/**
 * Esta clase es la vista para la información detallada de un ejemplar de la biblioteca
 * @author Isabel Román
 *
 */
public class ItemDetails extends EntityDetails<Item> {
	private static Logger log=Logger.getLogger(ItemDetails.class.getName());
	/**
	 * Número de serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inventoryNrField;
	private JTextField statusField;
	private JLabel inventoryNrLabel;
	private JLabel statusLabel;

	private TitleDetails titlePanel;
	/**
	 * Constructor simple, construye el objeto en blanco
	 */
	public ItemDetails() {
		createPanel();		
		
	}
	/**
	 * Construye el objeto y lo asocia con una entidad de tipo Item
	 * @param item Entidad a la que se asocia esta vista
	 */
	public ItemDetails(Item item) {
		createPanel();	
		setEntity(item);
	}
	/**
	 * Create the panel.
	 */
	public void createPanel() {
		setLayout(null);
		
		inventoryNrField = new JTextField();
		inventoryNrField.setName("inventoryNr");
		inventoryNrField.setBounds(155, 11, 295, 20);
		add(inventoryNrField);
		inventoryNrField.setColumns(10);
		
		inventoryNrLabel = new JLabel("Inventory Number");
		inventoryNrLabel.setBounds(35, 14, 110, 14);
		add(inventoryNrLabel);
		
		statusField = new JTextField();
		statusField.setName("status");
		statusField.setBounds(155, 57, 295, 20);
		add(statusField);
		statusField.setColumns(10);
		
		statusLabel = new JLabel("Status");
		statusLabel.setBounds(37, 60, 46, 14);
		add(statusLabel);
		
		titlePanel = new TitleDetails();
		titlePanel.setName("title");
		titlePanel.setBounds(10,85,552,158);
		add(titlePanel);
		
		
	}
	@Override
	public void disableAllAttributes() {
		super.disableAllAttributes();
		titlePanel.disableAllAttributes();
	}
	/**
	 * Asocia la entidad pasada como parámetro a esta vista
	 * @param item Ejemplar al que se asocia esta vista
	 */
	public void setEntity(Item item) {
		this.entity=item;
		inventoryNrField.setText(item.getInventoryNr());
		statusField.setText(item.getStatus().toString());
		titlePanel.setEntity(item.getItemInfo());
	}
	/**
	 * Devuelve el Ejemplar a partir de los datos de entrada. El título correspondiente queda sin especificar, no se sabe el tipo y no lo asocia con ningun existente
	 * A esto le queda muchísimo por hacer, el ejemplar debería estar asociado a un título previamente existente, de modo que la creación implicaría la búsqueda y selección de un título
	 * @return ejemplar a partir de los datos de entrada
	 */
	public Item getItem() {
		log.info("Creando Item a partir de los datos de entrada");
		Item item=null;
		Title title=null;
		if (this.entity==null){
			try {
				title = new Title(titlePanel.getAttributeAsString("name"),titlePanel.getAttributeAsString("author"),MLView.getFrameManager().getDateFormat().parse(titlePanel.getAttributeAsString("publishedAt")));
				item=new Item(title);
				item.setInventoryNr(inventoryNrField.getText());
				
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
			setEntity(item);
		}else {
			entity.getItemInfo().setAuthor(titlePanel.getAttributeAsString("author"));
			entity.getItemInfo().setName(titlePanel.getAttributeAsString("name"));
			entity.setInventoryNr(inventoryNrField.getText());
			try {
				entity.getItemInfo().setPublishedAt(MLView.getFrameManager().getDateFormat().parse(titlePanel.getAttributeAsString("publishedAt")));
			} catch (ParseException e) {
			
				e.printStackTrace();
			}
		
			item=(Item) entity;
		}
		
		return item;
	}
		
}
