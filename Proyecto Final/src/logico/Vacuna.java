package logico;

import java.util.ArrayList;

public class Vacuna {
	
	private String codigoVacuna;
	private String nombreVacuna;
	private String fabricante;
	private ArrayList<Enfermedad> proteccion; // enfermedades para la cual ofrece protección
	private String tipoVacuna; // inactivada, atenuada, vector, ARN
	private String formaAdministracion; // instramuscular, intradérmica, subcutánea, endovenosa, oral
	
	public Vacuna(String codigoVacuna, String nombreVacuna, String fabricante, ArrayList<Enfermedad> proteccion, String tipoVacuna, String formaAdministracion) {
		this.codigoVacuna = codigoVacuna;
		this.nombreVacuna = nombreVacuna;
		this.fabricante = fabricante;
		this.proteccion = proteccion;
		this.tipoVacuna = tipoVacuna;
		this.formaAdministracion = formaAdministracion;
	}

	public String getCodigoVacuna() {
		return codigoVacuna;
	}

	public String getNombreVacuna() {
		return nombreVacuna;
	}

	public String getFabricante() {
		return fabricante;
	}

	public ArrayList<Enfermedad> getProteccion() {
		return proteccion;
	}

	public String getTipoVacuna() {
		return tipoVacuna;
	}

	public String getFormaAdministracion() {
		return formaAdministracion;
	}

	public void setCodigoVacuna(String codigoVacuna) {
		this.codigoVacuna = codigoVacuna;
	}

	public void setNombreVacuna(String nombreVacuna) {
		this.nombreVacuna = nombreVacuna;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void setProteccion(ArrayList<Enfermedad> proteccion) {
		this.proteccion = proteccion;
	}

	public void setTipoVacuna(String tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}

	public void setFormaAdministracion(String formaAdministracion) {
		this.formaAdministracion = formaAdministracion;
	}

}
