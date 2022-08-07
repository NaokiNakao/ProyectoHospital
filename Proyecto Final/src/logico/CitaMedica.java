package logico;

import java.util.Date;

public class CitaMedica {

	private String codigo;
	private Date fechaCita;
	private Medico medico;
	private Paciente paciente;
	//private String nombrePersona;
	//private String telefono;
	//private String cedulaPersona;
	//private String generoPersona;
	//private Date fechaNacimientoPersona;
	//private String direccionPersona;
	//private HistoriaClinica historialPersona;
	//private Date fechaN;
	
	
	public CitaMedica(String codigo, Date fechaCita, Medico medico, Paciente paciente) {
		this.codigo = codigo;
		this.fechaCita = fechaCita;
		this.medico = medico;
		this.paciente = paciente;
		//this.nombrePersona = nombrePersona;
		//this.telefono = telefono;
		//this.cedulaPersona = cedulaPersona;
		//this.generoPersona = generoPersona;
		//this.fechaNacimientoPersona = fechaNacimientoPersona;
		//this.direccionPersona = direccionPersona;
		//this.fechaN = fechaN;
	}

	public String getCodigo() {
		return codigo;
	}

	public Date getFechaCita() {
		return fechaCita;
	}
	
	public Medico getMedico() {
		return medico;
	}
	
	public Paciente getPaciente() {
		return paciente;
	}

	/*
	 * public String getNombrePersona() { return nombrePersona; }
	 */

	/*
	 * public String getTelefono() { return telefono; }
	 */

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}
	
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/*
	 * public void setNombrePersona(String nombrePersona) { this.nombrePersona =
	 * nombrePersona; }
	 * 
	 * public void setTelefono(String telefono) { this.telefono = telefono; }
	 * 
	 * public String getCedulaPersona() { return cedulaPersona; }
	 * 
	 * public void setCedulaPersona(String cedulaPersona) { this.cedulaPersona =
	 * cedulaPersona; }
	 * 
	 * public String getGeneroPersona() { return generoPersona; }
	 * 
	 * public void setGeneroPersona(String generoPersona) { this.generoPersona =
	 * generoPersona; }
	 * 
	 * public Date getFechaNacimientoPersona() { return fechaNacimientoPersona; }
	 * 
	 * public void setFechaNacimientoPersona(Date fechaNacimientoPersona) {
	 * this.fechaNacimientoPersona = fechaNacimientoPersona; }
	 * 
	 * public String getDireccionPersona() { return direccionPersona; }
	 * 
	 * public void setDireccionPersona(String direccionPersona) {
	 * this.direccionPersona = direccionPersona; }
	 * 
	 * public HistoriaClinica getHistorialPersona() { return historialPersona; }
	 * 
	 * public void setHistorialPersona(HistoriaClinica historialPersona) {
	 * this.historialPersona = historialPersona; }
	 * 
	 * public Date getFechaN() { return fechaN; }
	 * 
	 * public void setFechaN(Date fechaN) { this.fechaN = fechaN; }
	 */
	
}
