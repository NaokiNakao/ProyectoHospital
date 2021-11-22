package logico;

public class Medico extends Usuario {

	private String especialidad;

	public Medico(String id, String login, String password, String nombre, String telefono, String especialidad) {
		super(id, login, password, nombre, telefono);
		this.especialidad = especialidad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
