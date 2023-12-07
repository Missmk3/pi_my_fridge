package pmf.view;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import pmf.controller.controller;
import pmf.model.*;
import pmf.controller.*;
import pmf.model.model;

public class logview extends JFrame implements Observer{
	private pmf.model.model model;
	private pmf.controller.controller controller;
	private JLabel T_IntLabel;
	private JLabel T_ExtLabel;
	private JLabel H_IntLabel;
	private JLabel RoseeLabel;
	private JLabel CsgLabel;
	private JTextField CsgTextField;
	private JButton CsgButton;
	private JComboBox<String> portComboBox;
	private JButton connectButton;
	public logview(pmf.model.model model, pmf.controller.controller controller) {
		this.model=model;
		this.controller=controller;
		model.addObserver(this);
		initUI();
	}
	private void initUI() {
		T_IntLabel=new JLabel("Température Intérieure");
		T_ExtLabel=new JLabel("Température Extérieure");
		H_IntLabel=new JLabel("Humidité Intérieure");
		RoseeLabel=new JLabel("Point de rosée");
		CsgLabel=new JLabel("Consigne");
		CsgTextField=new JTextField(5);
		CsgButton=new JButton("Choisir une consigne de votre choix");
		CsgButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                try {
                	double Csg=Double.parseDouble(CsgTextField.getText());
                	controller.setConsigne(Csg);
                }catch (NumberFormatException ex) {
                	JOptionPane.showMessageDialog(logview.this,"Veuillez entrer un nombre valide pour la consigne si non vous aurez à faire à CAMPBELL SIGNE","Erreur",JOptionPane.ERROR_MESSAGE);
                }
			}
                });
		//comboBox pour afficher les ports disponibles
		portComboBox=new JComboBox<>();
		connectButton=new JButton("connecter");
		connectButton.addActionListener(new ActionListener() {
			 @Override
	            public void actionPerformed(ActionEvent e) {
	                String selectedPort = (String) portComboBox.getSelectedItem();
	                portComboBox.getSelectedItem();
	                if(selectedPort != null) {
	                    controller.connectToPort(selectedPort);
	                    connectButton.setEnabled(false);  // Évite de se reconnecter pendant la connexion
	                }
			 }
			 });
		

        setLayout(new GridLayout(6, 2));
        add(T_IntLabel);
        add(new JLabel());
        add(T_ExtLabel);
        add(new JLabel());
        add(H_IntLabel);
        add(new JLabel());
        add(RoseeLabel);
        add(new JLabel());
        add(CsgLabel);
        add(CsgTextField);
        add(CsgButton);
        add(new JLabel("Sélectionner le port:"));
        add(portComboBox);
        add(connectButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        }
	// Méthode pour afficher les ports disponibles dans la ComboBox
    public void displayAvailablePorts(List<String> portNames) {
        portComboBox.removeAllItems();
        for (String portName : portNames) {
            portComboBox.addItem(portName);
	
		}
	}
    public void update(Observable o, Object arg) {
        T_IntLabel.setText("Température intérieure: " + model.getTemperature_Interieure() + " °C");
        T_ExtLabel.setText("Température extérieure: " + model.getTemperature_Exterieure() + " °C");
        H_IntLabel.setText("Humidité intérieure: " + model.getHumidite_Interieure() + " %");
        RoseeLabel.setText("Point de rosée: " + model.getPoint_De_Rosee() + " °C");
        CsgLabel.setText("Consigne: " + model.getConsigne() + " °C");
    }

    public String getSelectedPort() {
        // Récupérer le port sélectionné à partir de la ComboBox
        Object selectedPort = portComboBox.getSelectedItem();

        // Vérifier si un port a été sélectionné et le convertir en String si nécessaire
        if (selectedPort != null) {
            return selectedPort.toString();
        } else {
            return null;
        }

}}
