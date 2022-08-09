package logico;

import java.util.ArrayList;
import java.util.Date;

public class Paciente {

	private String cedula;
	private String nombre;
	private String genero;
	private String fechaNacimiento;
	private String direccion;
	private String telefono;
	private HistoriaClinica historial;
	
	public Paciente(String cedula, String nombre, String genero, String fechaNacimiento, String direccion, String telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public String getGenero() {
		return genero;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public HistoriaClinica getHistorial() {
		return historial;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setHistorial(HistoriaClinica historial) {
		this.historial = historial;
	}
	
}
