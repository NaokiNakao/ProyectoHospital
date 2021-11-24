package logico;

import java.util.ArrayList;

public class Medico extends Usuario {

	private String especialidad;
	private ArrayList<Consulta> misConsultas;

	public Medico(String id, String login, String password, String nombre, String telefono, String especialidad) {
		super(id, login, password, nombre, telefono);
		this.especialidad = especialidad;
		this.misConsultas = new ArrayList<>();
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

}
