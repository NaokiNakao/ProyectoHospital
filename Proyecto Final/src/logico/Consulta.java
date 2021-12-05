package logico;

import java.io.Serializable;
import java.util.Date;

public class Consulta implements Serializable {

	private static final long serialVersionUID = -6962941984145229145L;
	private String codigo;
	private Date fechaConsulta;
	private String sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	private Medico miMedico;
	
	public Consulta(String codigo, Date fechaConsulta, String sintomas, String diagnostico, Medico miMedico) {
		this.codigo = codigo;
		this.fechaConsulta = fechaConsulta;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.miMedico = miMedico;
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
	
	public Medico getMiMedico() {
		return miMedico;
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

	public void setMiMedico(Medico miMedico) {
		this.miMedico = miMedico;
	}

}
