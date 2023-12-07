package pmf.controller;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DataObject {
	 @JsonProperty("T_Int")
	    private double T_Int;
	    @JsonProperty("T_Ext")
	    private double T_Ext;
	    @JsonProperty("H_Int")
	    private double H_Int;
	    @JsonProperty("Rosee")
	    private double Rosee;
	    @JsonProperty("Csg")
	    private double Csg;
	    // Constructeur par défaut nécessaire pour Jackson
	    public DataObject() {
	    }

	    // Constructeur avec tous les champs
	    public DataObject(double T_Int, double T_Ext, double H_Int, double Rosee,double Csg) {
	        this.T_Int = T_Int;
	        this.T_Ext = T_Ext;
	        this.H_Int = H_Int;
	        this.Rosee = Rosee;
	        this.Csg=Csg;
	   
	    }
	    //Getters and setters

		public double getT_Int() {
			return T_Int;
		}

		public void setT_Int(double t_Int) {
			T_Int = t_Int;
		}

		public double getT_Ext() {
			return T_Ext;
		}

		public void setT_Ext(double t_Ext) {
			T_Ext = t_Ext;
		}

		public double getH_Int() {
			return H_Int;
		}

		public void setH_Int(double h_Int) {
			H_Int = h_Int;
		}

		public double getRosee() {
			return Rosee;
		}

		public void setRosee(double rosee) {
			Rosee = rosee;
		}

		public double getCsg() {
			return Csg;
		}

		public void setCsg(double csg) {
			Csg = csg;
		}
}
