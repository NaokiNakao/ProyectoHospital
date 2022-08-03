package logico;

import java.io.Serializable;
import java.util.Date;

public class CitaMedica implements Serializable {

	private String codigo;
	private Date fechaCita;
	private String nombrePersona;
	private String telefono;
	private Medico medico;
	private String cedulaPersona;
	private String generoPersona;
	private Date fechaNacimientoPersona;
	private String direccionPersona;
	private HistoriaClinica historialPersona;
	private Date fechaN;
	
	public CitaMedica(String codigo, Date fechaCita, String nombrePersona, String telefono, Medico medico,
			String cedulaPersona, String generoPersona, Date fechaNacimientoPersona, String direccionPersona, Date fechaN) {
		
		this.codigo = codigo;
		this.fechaCita = fechaCita;
		this.nombrePersona = nombrePersona;
		this.telefono = telefono;
		this.medico = medico;
		this.cedulaPersona = cedulaPersona;
		this.generoPersona = generoPersona;
		this.fechaNacimientoPersona = fechaNacimientoPersona;
		this.direccionPersona = direccionPersona;
		this.fechaN = fechaN;
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

	public String getCedulaPersona() {
		return cedulaPersona;
	}

	public void setCedulaPersona(String cedulaPersona) {
		this.cedulaPersona = cedulaPersona;
	}

	public String getGeneroPersona() {
		return generoPersona;
	}

	public void setGeneroPersona(String generoPersona) {
		this.generoPersona = generoPersona;
	}

	public Date getFechaNacimientoPersona() {
		return fechaNacimientoPersona;
	}

	public void setFechaNacimientoPersona(Date fechaNacimientoPersona) {
		this.fechaNacimientoPersona = fechaNacimientoPersona;
	}

	public String getDireccionPersona() {
		return direccionPersona;
	}

	public void setDireccionPersona(String direccionPersona) {
		this.direccionPersona = direccionPersona;
	}

	public HistoriaClinica getHistorialPersona() {
		return historialPersona;
	}

	public void setHistorialPersona(HistoriaClinica historialPersona) {
		this.historialPersona = historialPersona;
	}

	public Date getFechaN() {
		return fechaN;
	}

	public void setFechaN(Date fechaN) {
		this.fechaN = fechaN;
	}
	
}
