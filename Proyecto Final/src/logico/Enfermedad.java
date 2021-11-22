package logico;

public class Enfermedad {

	private String codigoEnfermedad;
	private String nombreEnfermedad;
	private String tipoEnfermedad;
	private String descripcionEnfermedad;
	
	public Enfermedad(String codigoEnfermedad, String nombreEnfermedad, String tipoEnfermedad, String descripcionEnfermedad) {
		this.codigoEnfermedad = codigoEnfermedad;
		this.nombreEnfermedad = nombreEnfermedad;
		this.tipoEnfermedad = tipoEnfermedad;
		this.descripcionEnfermedad = descripcionEnfermedad;
	}

	public String getCodigoEnfermedad() {
		return codigoEnfermedad;
	}

	public String getNombreEnfermedad() {
		return nombreEnfermedad;
	}

	public String getTipoEnfermedad() {
		return tipoEnfermedad;
	}

	public String getDescripcionEnfermedad() {
		return descripcionEnfermedad;
	}

	public void setCodigoEnfermedad(String codigoEnfermedad) {
		this.codigoEnfermedad = codigoEnfermedad;
	}

	public void setNombreEnfermedad(String nombreEnfermedad) {
		this.nombreEnfermedad = nombreEnfermedad;
	}

	public void setTipoEnfermedad(String tipoEnfermedad) {
		this.tipoEnfermedad = tipoEnfermedad;
	}

	public void setDescripcionEnfermedad(String descripcionEnfermedad) {
		this.descripcionEnfermedad = descripcionEnfermedad;
	}

}
