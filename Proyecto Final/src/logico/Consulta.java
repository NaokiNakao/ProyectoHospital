package logico;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Consulta {

	private String codigo;
	private String fechaConsulta;
	private String sintomas;
	private String diagnostico;
	private Enfermedad enfermedad;
	private Medico miMedico;
	private Vacuna misVacunas;
	private String receta;
	private String fechaT;
	
	public Consulta(String codigo, String fechaConsulta, String sintomas, String diagnostico, Medico miMedico, String fechaT) {
		this.codigo = codigo;
		this.fechaConsulta = fechaConsulta;
		this.sintomas = sintomas;
		this.diagnostico = diagnostico;
		this.miMedico = miMedico;
		//this.receta = receta;
		this.fechaT = fechaT;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getFechaConsulta() {
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

	public void setFechaConsulta(String fechaConsulta) {
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

	public Vacuna getMisVacunas() {
		return misVacunas;
	}

	public void setMisVacunas(Vacuna misVacunas) {
		this.misVacunas = misVacunas;
	}

	public String getReceta() {
		return receta;
	}

	public void setReceta(String receta) {
		this.receta = receta;
	}

	public String getFechaT() {
		return fechaT;
	}

	public void setFechaT(String fechaT) {
		this.fechaT = fechaT;
	}

}
