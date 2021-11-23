package logico;

import java.util.Date;

public class Consulta {

	private String codigo;
	private Date fechaConsulta;
	private String sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	
	public Consulta(String codigo, Date fechaConsulta, String sintomas, String diagnostico) {
		this.codigo = codigo;
		this.fechaConsulta = fechaConsulta;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
	}

	public String getCodigo() {
		return codigo;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public String getSintomas() {
		return sintomas;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

}
