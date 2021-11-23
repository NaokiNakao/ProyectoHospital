package logico;

public class Enfermedad {

	private String codigo;
	private String nombreEnfermedad;
	private String tipoEnfermedad;
	private String descripcionEnfermedad;
	
	public Enfermedad(String codigo, String nombreEnfermedad, String tipoEnfermedad, String descripcionEnfermedad) {
		this.codigo = codigo;
		this.nombreEnfermedad = nombreEnfermedad;
		this.tipoEnfermedad = tipoEnfermedad;
		this.descripcionEnfermedad = descripcionEnfermedad;
	}

	public String getCodigo() {
		return codigo;
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

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
