package edu.gitt.is.magiclibrary.view;

import javax.swing.JTextField;

import edu.gitt.is.magiclibrary.model.entities.Item;

import javax.swing.JLabel;
import javax.swing.JList;

public class ItemView extends EntityView<Item> {
	/**
	 * Número de serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inventoryNrField;
	private JTextField statusField;
	private JLabel inventoryNrLabel;
	private JLabel statusLabel;

	private TitleView titlePanel;
	
	public ItemView() {
		createPanel();		
		
	}

	public ItemView(Item item) {
		createPanel();	
		setItem(item);
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
		
		titlePanel = new TitleView();
		titlePanel.setName("title");
		titlePanel.setBounds(10,85,552,158);
		add(titlePanel);
		
		
	}
	@Override
	public void disableAllAttributes() {
		super.disableAllAttributes();
		titlePanel.disableAllAttributes();
	}
	
	public void setItem(Item item) {
		this.entity=item;
		inventoryNrField.setText(item.getInventoryNr());
		statusField.setText(item.getStatus().toString());
		titlePanel.setTitle(item.getItemInfo());
	}
	public Item getItem() {
		return this.entity;
	}
}
