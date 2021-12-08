package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Vacuna implements Serializable{
	
	private static final long serialVersionUID = 7941951698889619066L;
	private String codigo; // una letra y seis (6) dígitos --> "V-[código]"
	private String nombreVacuna;
	private String fabricante;
	private Enfermedad proteccion; // enfermedades para la cual ofrece protección
	private String tipoVacuna; // inactivada, atenuada, vector, ARN
	private String formaAdministracion; // intramuscular, intradérmica, subcutánea, endovenosa, oral
	
	public Vacuna(String codigo, String nombreVacuna, String fabricante, Enfermedad proteccion, String tipoVacuna, String formaAdministracion) {
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

	public Enfermedad getProteccion() {
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

	public void setProteccion(Enfermedad proteccion) {
		this.proteccion = proteccion;
	}

	public void setTipoVacuna(String tipoVacuna) {
		this.tipoVacuna = tipoVacuna;
	}

	public void setFormaAdministracion(String formaAdministracion) {
		this.formaAdministracion = formaAdministracion;
	}

}
