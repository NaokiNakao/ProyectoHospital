package logico;

import java.util.ArrayList;

public class Vacuna {
	
	private String codigo; // una letra y seis (6) d�gitos --> "V-[c�digo]"
	private String nombreVacuna;
	private String fabricante;
	private ArrayList<Enfermedad> proteccion; // enfermedades para la cual ofrece protecci�n
	private String tipoVacuna; // inactivada, atenuada, vector, ARN
	private String formaAdministracion; // intramuscular, intrad�rmica, subcut�nea, endovenosa, oral
	
	public Vacuna(String codigo, String nombreVacuna, String fabricante, ArrayList<Enfermedad> proteccion, String tipoVacuna, String formaAdministracion) {
		this.codigo = codigo;
		this.nombreVacuna = nombreVacuna;
		this.fabricante = fabricante;
		this.proteccion = proteccion;
		this.tipoVacuna = tipoVacuna;
		this.formaAdministracion = formaAdministracion;
	}

	public String getCodigo() {
		return codigo;
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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
