package logico;

import java.util.ArrayList;
import java.util.Date;

public class Paciente {

	private String cedula;
	private String nombre;
	private String genero;
	private String fechaNacimiento;
	private int cod_ciudad;
	private String telefono;
	private HistoriaClinica historial;
	
	public Paciente(String cedula, String nombre, String genero, String fechaNacimiento, int cod_ciudad, String telefono) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.cod_ciudad = cod_ciudad;
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

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public void setHistorial(HistoriaClinica historial) {
		this.historial = historial;
	}

	public int getCod_ciudad() {
		return cod_ciudad;
	}

	public void setCod_ciudad(int cod_ciudad) {
		this.cod_ciudad = cod_ciudad;
	}
	
}
