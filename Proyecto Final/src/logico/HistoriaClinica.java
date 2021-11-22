package logico;

import java.util.ArrayList;

public class HistoriaClinica {

	private String codigoHistorial;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	
	public HistoriaClinica(String codigoHistorial) {
		this.codigoHistorial = codigoHistorial;
		this.misConsultas = new ArrayList<Consulta>();
		this.misVacunas = new ArrayList<Vacuna>();
	}

	public String getCodigoHistorial() {
		return codigoHistorial;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}

	public void setCodigoHistorial(String codigoHistorial) {
		this.codigoHistorial = codigoHistorial;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public void agregarConsulta(Consulta consulta) {
		misConsultas.add(consulta);
	}
	
	public void agregarVacuna(Vacuna vacuna) {
		misVacunas.add(vacuna);
	}
	
}
