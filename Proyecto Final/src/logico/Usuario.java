package logico;

public abstract class Usuario {

	protected String id;
	protected String login;
	protected String password;
	protected String nombre;
	protected String telefono;
	
	public Usuario(String id, String login, String password, String nombre, String telefono) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.nombre = nombre;
		this.telefono = telefono;
	}

	public String getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}