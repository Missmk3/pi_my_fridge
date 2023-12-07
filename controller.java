package src.main.java.pmf.controller;
import pmf.model.*;
public class controller {
	private pmf.model.model model;
	private pmf.controller.Port Port;
	public controller(pmf.model.model model) {
		this.model=model;
	}
	public void updateDatafromArduino(double T_int,double T_ext,double H_int,double Rosee,double Csg) {
		model.updateData(T_int, T_ext, H_int, Rosee, Csg);
	}
	public void setConsigne(double Consigne) {
		model.updateData(model.getTemperature_Interieure(),model.getTemperature_Exterieure(),model.getHumidite_Interieure(),model.getPoint_De_Rosee(),model.getConsigne());
 
 		
	}
	public void updatePortName(String portName) {
		// TODO Auto-generated method stub
		
	}
	public void connectToPort(String selectedPort) {
		Port.initialize(selectedPort);
	}

}
