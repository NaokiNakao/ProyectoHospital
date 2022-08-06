package logico;

import java.util.ArrayList;

public class Medico extends Usuario {

	//private String especialidad;
	private ArrayList<CitaMedica> misCitas;
	private ArrayList<Consulta> misConsultas;

	public Medico(String id, String login, String password, String nombre, String apellido, String telefono, String especialidad) {
		super(id, login, password, nombre, apellido, telefono);
		//this.especialidad = especialidad;
		misCitas = new ArrayList<CitaMedica>();
		misConsultas = new  ArrayList<Consulta>();
	}

	/*public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}*/

	public ArrayList<CitaMedica> getMisCitas() {
		return misCitas;
	}

	public void setMisCitas(ArrayList<CitaMedica> misCitas) {
		this.misCitas = misCitas;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}
	
}
