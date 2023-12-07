package pmf.main;
import java.util.List;

import pmf.controller.*;
import pmf.model.*;
import pmf.view.*;
public class PmfApp {
	public static void main(String[]args) {
		model model= new model();
		controller controller=new controller(model);
		logview logview=new logview(model,controller);
		Graphview Graphview=new Graphview(model);
		Port serialReader = new Port(controller);
		// Afficher les ports disponibles dans la vue
        List<String> availablePorts = serialReader.getAvailablePorts();
        logview.displayAvailablePorts(availablePorts);
        // Vous pouvez maintenant permettre à l'utilisateur de sélectionner un port dans votre vue
        // et initialiser le SerialReader avec le port sélectionné.
        // Par exemple :
        String selectedPort = logview.getSelectedPort();
        if (selectedPort != null) {
            serialReader.initialize(selectedPort);
	}
        // Simulation de données (remplacer par la logique Arduino)
        for (int i = 0; i < 10; i++) {
            double 	T_Int = Math.random() * 10 + 10;
            double T_Ext = Math.random() * 10 + 20;
            double H_Int = Math.random() * 20 + 40;
            double Rosee = T_Ext - ((100 - H_Int) / 5);
            double Csg = 15.0; 
            controller.updateDatafromArduino(T_Int, T_Ext, H_Int,Rosee , Csg);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
