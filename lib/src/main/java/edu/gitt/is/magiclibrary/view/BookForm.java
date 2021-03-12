package edu.gitt.is.magiclibrary.view;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class BookForm extends JFrame {

	private BindingGroup m_bindingGroup;
	private JPanel m_contentPane;
	private edu.gitt.is.magiclibrary.model.entities.Book book = new edu.gitt.is.magiclibrary.model.entities.Book();
	private JTextField authorJTextField;
	private JTextField idJTextField;
	private JTextField isbnJTextField;
	private JTextField nameJTextField;
//	private JSlider pagesJSlider;
	private JTextField pagesJTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookForm frame = new BookForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		m_contentPane = new JPanel();
		setContentPane(m_contentPane);
		//
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0E-4 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4 };
		m_contentPane.setLayout(gridBagLayout);

		JLabel authorLabel = new JLabel("Author:");
		GridBagConstraints labelGbc_0 = new GridBagConstraints();
		labelGbc_0.insets = new Insets(5, 5, 5, 5);
		labelGbc_0.gridx = 0;
		labelGbc_0.gridy = 0;
		m_contentPane.add(authorLabel, labelGbc_0);

		authorJTextField = new JTextField();
		GridBagConstraints componentGbc_0 = new GridBagConstraints();
		componentGbc_0.insets = new Insets(5, 0, 5, 5);
		componentGbc_0.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_0.gridx = 1;
		componentGbc_0.gridy = 0;
		m_contentPane.add(authorJTextField, componentGbc_0);

		JLabel idLabel = new JLabel("Id:");
		GridBagConstraints labelGbc_1 = new GridBagConstraints();
		labelGbc_1.insets = new Insets(5, 5, 5, 5);
		labelGbc_1.gridx = 0;
		labelGbc_1.gridy = 1;
		m_contentPane.add(idLabel, labelGbc_1);

		idJTextField = new JTextField();
		GridBagConstraints componentGbc_1 = new GridBagConstraints();
		componentGbc_1.insets = new Insets(5, 0, 5, 5);
		componentGbc_1.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_1.gridx = 1;
		componentGbc_1.gridy = 1;
		m_contentPane.add(idJTextField, componentGbc_1);

		JLabel isbnLabel = new JLabel("Isbn:");
		GridBagConstraints labelGbc_2 = new GridBagConstraints();
		labelGbc_2.insets = new Insets(5, 5, 5, 5);
		labelGbc_2.gridx = 0;
		labelGbc_2.gridy = 2;
		m_contentPane.add(isbnLabel, labelGbc_2);

		isbnJTextField = new JTextField();
		GridBagConstraints componentGbc_2 = new GridBagConstraints();
		componentGbc_2.insets = new Insets(5, 0, 5, 5);
		componentGbc_2.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_2.gridx = 1;
		componentGbc_2.gridy = 2;
		m_contentPane.add(isbnJTextField, componentGbc_2);

		JLabel nameLabel = new JLabel("Name:");
		GridBagConstraints labelGbc_3 = new GridBagConstraints();
		labelGbc_3.insets = new Insets(5, 5, 5, 5);
		labelGbc_3.gridx = 0;
		labelGbc_3.gridy = 3;
		m_contentPane.add(nameLabel, labelGbc_3);

		nameJTextField = new JTextField();
		GridBagConstraints componentGbc_3 = new GridBagConstraints();
		componentGbc_3.insets = new Insets(5, 0, 5, 5);
		componentGbc_3.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_3.gridx = 1;
		componentGbc_3.gridy = 3;
		m_contentPane.add(nameJTextField, componentGbc_3);

		JLabel pagesLabel = new JLabel("Pages:");
		GridBagConstraints labelGbc_4 = new GridBagConstraints();
		labelGbc_4.insets = new Insets(5, 5, 5, 5);
		labelGbc_4.gridx = 0;
		labelGbc_4.gridy = 4;
		m_contentPane.add(pagesLabel, labelGbc_4);

	//	pagesJSlider = new JSlider();
		pagesJTextField = new JTextField();
		GridBagConstraints componentGbc_4 = new GridBagConstraints();
		componentGbc_4.insets = new Insets(5, 0, 5, 5);
		componentGbc_4.fill = GridBagConstraints.HORIZONTAL;
		componentGbc_4.gridx = 1;
		componentGbc_4.gridy = 4;
//		m_contentPane.add(pagesJSlider, componentGbc_4);
		m_contentPane.add(pagesJTextField, componentGbc_4);

		if (book != null) {
			m_bindingGroup = initDataBindings();
		}
	}

	public edu.gitt.is.magiclibrary.model.entities.Book getBook() {
		return book;
	}

	public void setBook(edu.gitt.is.magiclibrary.model.entities.Book newBook) {
		setBook(newBook, true);
	}

	public void setBook(edu.gitt.is.magiclibrary.model.entities.Book newBook, boolean update) {
		book = newBook;
		if (update) {
			if (m_bindingGroup != null) {
				m_bindingGroup.unbind();
				m_bindingGroup = null;
			}
			if (book != null) {
				m_bindingGroup = initDataBindings();
			}
		}
	}

	protected BindingGroup initDataBindings() {
		Property authorProperty = BeanProperty.create("author");
		Property textProperty = BeanProperty.create("text");
		AutoBinding autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ, book, authorProperty, authorJTextField, textProperty);
		autoBinding.bind();
		//
		Property idProperty = BeanProperty.create("id");
		Property textProperty_1 = BeanProperty.create("text");
		AutoBinding autoBinding_1 = Bindings.createAutoBinding(UpdateStrategy.READ, book, idProperty, idJTextField, textProperty_1);
		autoBinding_1.bind();
		//
		Property isbnProperty = BeanProperty.create("isbn");
		Property textProperty_2 = BeanProperty.create("text");
		AutoBinding autoBinding_2 = Bindings.createAutoBinding(UpdateStrategy.READ, book, isbnProperty, isbnJTextField, textProperty_2);
		autoBinding_2.bind();
		//
		Property nameProperty = BeanProperty.create("name");
		Property textProperty_3 = BeanProperty.create("text");
		AutoBinding autoBinding_3 = Bindings.createAutoBinding(UpdateStrategy.READ, book, nameProperty, nameJTextField, textProperty_3);
		autoBinding_3.bind();
		//
		Property pagesProperty = BeanProperty.create("pages");
		Property textProperty_4 = BeanProperty.create("value");
		AutoBinding autoBinding_4 = Bindings.createAutoBinding(UpdateStrategy.READ, book, pagesProperty, pagesJTextField, textProperty_4);
		autoBinding_4.bind();
		//
		Property jTextFieldBeanProperty = BeanProperty.create("text");
		AutoBinding autoBinding_5 = Bindings.createAutoBinding(UpdateStrategy.READ, book, pagesProperty, pagesJTextField, jTextFieldBeanProperty);
		autoBinding_5.bind();
		//
		BindingGroup bindingGroup = new BindingGroup();
		//
		bindingGroup.addBinding(autoBinding);
		bindingGroup.addBinding(autoBinding_1);
		bindingGroup.addBinding(autoBinding_2);
		bindingGroup.addBinding(autoBinding_3);
		bindingGroup.addBinding(autoBinding_4);
		bindingGroup.addBinding(autoBinding_5);
		return bindingGroup;
	}
}
