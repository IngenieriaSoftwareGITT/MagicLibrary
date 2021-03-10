/**
 * 
 */
package gitt.is.magiclibrary.vista;

import javax.swing.*;

import javax.swing.JButton;


import java.awt.*;              //for layout managers and more
import java.awt.event.*;        //for action events


/**
 * @author isa
 *
 */
public class TitleView extends JPanel{
	protected static final String name = "Nombre";
	protected static final String author = "Autor";
	protected static final String publishedAt = "Fecha de publicaci�n";
	protected static final String save = "Guardar";
	
	protected JLabel actionLabel;
	protected JTextField nameField;
	protected  JTextField authorField;
	protected JTextField publishedAtField;
	
	/**
	 * 
	 * @param listener referencia a la clase que atiende las acciones ocurridas en este panel
	 */
    TitleView(ActionListener listener){
    	 setLayout(new BorderLayout());

         //creamos una entrada de texto regular para el Nombre del t�tulo
         nameField = new JTextField(20);
         nameField.setActionCommand(name);
      //   nameField.addActionListener(listener);
         //etiquetada
         JLabel nameLabel = new JLabel(name + ": ");
         nameLabel.setLabelFor(nameField);
         
         //creamos una entrada de texto regular para el Autor del t�tulo
         authorField = new JTextField(20);
         authorField.setActionCommand(author);
    //     authorField.addActionListener(listener);
         //etiquetada
         JLabel authorLabel = new JLabel(author + ": ");
         authorLabel.setLabelFor(nameField);
         
         //creamos una entrada de texto regular para el Autor del t�tulo
         publishedAtField = new JTextField(20);
         authorField.setActionCommand(publishedAt);
  //       authorField.addActionListener(listener);
         //etiquetada
         JLabel publishedAtLabel = new JLabel(publishedAt + ": ");
         authorLabel.setLabelFor(publishedAtField);
         
        //Creamos una etiqueta para poder mostrar mensajes din�micos mientras el panel est� activo
         actionLabel = new JLabel("Datos del t�tulo");
         actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
         
        //Creamos el bot�n para grabar los datos
         JButton saveButton = new JButton(save);
         saveButton.addActionListener(listener);
        //etiquetamos el bot�n
         JLabel saveLabel = new JLabel(save + ": ");
         saveLabel.setLabelFor(saveButton);
         
         //Lay out  de los controles de texto y etiquetas
       //  JPanel textControlsPane = new JPanel();
         GridBagLayout gridbag = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();

    //     textControlsPane.setLayout(gridbag);
         this.setLayout(gridbag);

         JLabel[] labels = {nameLabel, authorLabel, publishedAtLabel};
         JTextField[] textFields = {nameField, authorField, publishedAtField};
     //    addLabelTextRows(labels, textFields, gridbag, textControlsPane);
         addLabelTextRows(labels, textFields, gridbag, this);

         c.gridwidth = GridBagConstraints.REMAINDER; //last
         c.anchor = GridBagConstraints.WEST;
         c.weightx = 1.0;
       
         this.add(actionLabel, c);
         this.add(saveButton,c);
         this.setBorder(
                 BorderFactory.createCompoundBorder(
                                 BorderFactory.createTitledBorder("Datos del t�tulo"),
                                 BorderFactory.createEmptyBorder(5,5,5,5)));
    }
    public void printMessage(String msg) {
    	actionLabel.setText(msg);
    }
    public String getName() {
    	return nameField.getText();
    }
    private void addLabelTextRows(JLabel[] labels,
            JTextField[] textFields,
            GridBagLayout gridbag,
            Container container) {
    	GridBagConstraints c = new GridBagConstraints();
    	c.anchor = GridBagConstraints.EAST;
    	int numLabels = labels.length;

    	for (int i = 0; i < numLabels; i++) {
    		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
    		c.fill = GridBagConstraints.NONE;      //reset to default
    		c.weightx = 0.0;                       //reset to default
    		container.add(labels[i], c);

    		c.gridwidth = GridBagConstraints.REMAINDER;     //end row
    		c.fill = GridBagConstraints.HORIZONTAL;
    		c.weightx = 1.0;
    		container.add(textFields[i], c);
    	}
    }
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Magic Library Ventana Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Listener listener = new Listener();
        TitleView titleView = new TitleView(listener);
        listener.setJFrame(titleView);

        //Add content to the window.
        frame.add(titleView);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                 //Turn off metal's use of bold fonts
		//UIManager.put("swing.boldMetal", Boolean.FALSE);
		createAndShowGUI();
            }
        });
    }

}
