package logico;

import java.util.Date;

public class CitaMedica {

	private String codigo;
	private Date fechaCita;
	private String nombrePersona;
	private String telefono;
	private Medico medico;
	
	public CitaMedica(String codigo, Date fechaCita, String nombrePersona, String telefono, Medico medico) {
		this.codigo = codigo;
		this.fechaCita = fechaCita;
		this.nombrePersona = nombrePersona;
		this.telefono = telefono;
		this.medico = medico;
	}

	public String getCodigo() {
		return codigo;
	}

	public Date getFechaCita() {
		return fechaCita;
	}

	public String getNombrePersona() {
		return nombrePersona;
	}

	public String getTelefono() {
		return telefono;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setFechaCita(Date fechaCita) {
		this.fechaCita = fechaCita;
	}

	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
}
