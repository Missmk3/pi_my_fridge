package pmf.model;
import java.util.Observable;
public class model extends Observable{
	private double Temperature_Interieure;
	private double Temperature_Exterieure;
	private double Humidite_Interieure;
	private double Point_De_Rosee;
	private double Consigne;
	
	public void updateData(double T_int,double T_Ext,double H_Int,double Rosee,double Csg) {
		Temperature_Interieure=T_int;
		Temperature_Exterieure=T_Ext;
		Humidite_Interieure=H_Int;	
		Point_De_Rosee=Rosee;
		Consigne=Csg;
		setChanged();
		notifyObservers();
		
	}

	public double getTemperature_Interieure() {
		return Temperature_Interieure;
	}

	public double getTemperature_Exterieure() {
		return Temperature_Exterieure;
	}

	public double getHumidite_Interieure() {
		return Humidite_Interieure;
	}

	public double getPoint_De_Rosee() {
		return Point_De_Rosee;
	}

	public double getConsigne() {
		return Consigne;
	}
	

}
