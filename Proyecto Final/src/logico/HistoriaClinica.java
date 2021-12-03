package logico;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class HistoriaClinica {

	private String codigo;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Vacuna> misVacunas;
	private ArrayList<Enfermedad> padecimientos;
	
	public HistoriaClinica(String codigo) {
		this.codigo = codigo;
		this.misConsultas = new ArrayList<Consulta>();
		this.misVacunas = new ArrayList<Vacuna>();
		this.padecimientos = new ArrayList<Enfermedad>();
	}

	public String getCodigo() {
		return codigo;
	}

	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	
	public ArrayList<Enfermedad> getPadecimientos() {
		return padecimientos;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public void setPadecimientos(ArrayList<Enfermedad> padecimientos) {
		this.padecimientos = padecimientos;
	}
	
	public void agregarConsulta(Consulta consulta) {
		misConsultas.add(consulta);
	}
	
	public void agregarVacuna(Vacuna vacuna) {
		misVacunas.add(vacuna);
	}
	
	public void agregarPadecimiento(Enfermedad padecimiento) {
		padecimientos.add(padecimiento);
	}
	
}
