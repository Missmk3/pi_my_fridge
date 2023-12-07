package pmf.controller;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.SerialPort;

public class SerialEventListener implements gnu.io.SerialPortEventListener {
	private InputStream input;
	private StringBuilder currentData;
	private ObjectMapper objectMapper;
	private List<DataObject> dataList;  // Liste pour stocker les données
	private SerialPort serialPort;
	//Modifier le constructeur pour accepter les paramètres du serial Port
	 public SerialEventListener(SerialPort serialPort) throws IOException {
	        this.serialPort = serialPort;
	        this.input = serialPort.getInputStream();
	        this.currentData = new StringBuilder();
	        this.objectMapper = new ObjectMapper();
	    }
	 public void serialEvent(SerialPortEvent event) {
	        if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
	            try {
	                // Lire les données du flux d'entrée
	                String data = readDataFromSerial();

	                // Ajouter les données lues au tampon en cours
	                currentData.append(data);

	                // Vérifier si une ligne complète a été reçue
	                if (currentData.toString().contains("\n")) {
	                    // Traiter les données
	                    processData(currentData.toString());

	                    // Réinitialiser le tampon
	                    currentData.setLength(0);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private String readDataFromSerial() throws IOException {
	        // Lire les données du flux d'entrée
	        byte[] buffer = new byte[1024];
	        int len = input.read(buffer);
	        return new String(buffer, 0, len);
	    }

	    private void processData(String data) {
	        // Exemple de traitement : séparer les données par '\t' (tabulation)
	        String[] values = data.trim().split("\t");
	        if (values.length == 4) {
	        	try {
	        		double T_Int=extractValue(values[0]);
	        		double T_Ext=extractValue(values[1]);
	        		double H_Int=extractValue(values[2]);
	        		double Rosee=extractValue(values[3]);
	        		double Csg= extractValue(values[4]);

	                // Créer un objet DataObject pour stocker les données
	                DataObject dataObject = new DataObject(	T_Int, T_Ext, H_Int, Rosee,Csg);
	                dataList.add(dataObject);

	                // Convertir l'objet en JSON
	                String jsonData = objectMapper.writeValueAsString(dataObject);

	                // Écrire le JSON dans un fichier
	                writeJsonToFile(jsonData);
	            } catch (NumberFormatException | IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    private double extractValue(String valueString) {
	        // Extraire la valeur double à partir de la chaîne
	        return Double.parseDouble(valueString.replaceAll("[^\\d.]", ""));
	    }

	    private void writeJsonToFile(String jsonData) throws IOException {
	        try (FileWriter fileWriter = new FileWriter("data.json")) {
	            fileWriter.write(jsonData);
	        }
	    }
	        	
	        
}
