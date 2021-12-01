package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Clinica implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<CitaMedica> citasMedicas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	private static Clinica clinica;
	private static Usuario loginUser; 
	private String dbPath = "db.dat"; // nombre del archivo donde se almacena la información
	
	private Clinica() {
		this.misPacientes = new ArrayList<Paciente>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.citasMedicas = new ArrayList<CitaMedica>();
		this.misConsultas = new ArrayList<Consulta>();
		this.misEnfermedades = new ArrayList<Enfermedad>();
		this.misVacunas = new ArrayList<Vacuna>();
	}

	public static Clinica getInstance() {
		if (clinica == null) {
			clinica = new Clinica();
		}
		return clinica;
	}

	public ArrayList<Paciente> getMisPacientes() {
		return misPacientes;
	}

	public ArrayList<Usuario> getMisUsuarios() {
		return misUsuarios;
	}

	public ArrayList<CitaMedica> getCitasMedicas() {
		return citasMedicas;
	}
	
	public ArrayList<Consulta> getMisConsultas() {
		return misConsultas;
	}

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
	}
	
	public static void setClinica(Clinica clinica) {
		Clinica.clinica = clinica;
	}

	public static Usuario getLoginUser() {
		return loginUser;
	}
	
	public String getDbPath() {
		return dbPath;
	}

	public void setMisPacientes(ArrayList<Paciente> misPacientes) {
		this.misPacientes = misPacientes;
	}

	public void setMisUsuarios(ArrayList<Usuario> misUsuarios) {
		this.misUsuarios = misUsuarios;
	}

	public void setCitasMedicas(ArrayList<CitaMedica> citasMedicas) {
		this.citasMedicas = citasMedicas;
	}
	
	public void setMisConsultas(ArrayList<Consulta> misConsultas) {
		this.misConsultas = misConsultas;
	}

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
	public void setDbPath(String dbPath) {
		this.dbPath = dbPath;
	}

	public static void setLoginUser(Usuario loginUser) {
		Clinica.loginUser = loginUser;
	}
	
	/*
	 * Retorna un String de caracteres alfanuméricos aleatorios
	 * dada una longitud para este. 
	*/
	public String generadorCodigo(int longitud) {
		String caracteres = "0123456789";
		Random rand = new Random();
		char[] aux = new char[longitud];
		String codigo = "";
		
		for (int i = 0; i < longitud; i++) {
			aux[i] = caracteres.charAt(rand.nextInt(caracteres.length()));
		}
							
		for (int i = 0; i < aux.length; i++) {
			codigo += aux[i];
		}
		
		return codigo;
	}
	
	/*
	 * Retorna un entero que representa la cantidad total de casos
	 * que se han reportado para cierta enfermedad dado su código.
	*/
	public int casosEnfermedadTotal(String codigoEnfermedad) {
		int total = 0;
		
		for (Paciente paciente : misPacientes) {
			for (Enfermedad enfermedad : paciente.getHistorial().getPadecimientos()) {
				if (enfermedad.getCodigo().equalsIgnoreCase(codigoEnfermedad)) {
					total++;
				}
			}
		}
		
		return total;
	}
	
	/*
	 * Dado el código de una enfermedad y una fecha, retorna un entero
	 * que reprenta la cantidad de casos diagnosticados en esa fecha. 
	*/
	public int casosEnfermedadPorFecha(String codigoEnfermedad, Date fecha) {
		int total = 0;
		
		for (Paciente paciente : misPacientes) {
			for (Consulta consulta : paciente.getMisConsultas()) {
				if (consulta.getEnfermedad().getCodigo().equalsIgnoreCase(codigoEnfermedad) && consulta.getFechaConsulta().equals(fecha)) {
					total++;
				}
			}
		}
		
		return total;
	}
	
	/*
	 * Dado el código de una enfermedad, retonrna un arreglo de números
	 * reales con la tasa de hombres y de mujeres que la padecieron.
	 * tasa[0] --> porcentaje de hombres
	 * tasa[1] --> porcentaje de mujeres
	*/
	public float[] porcentajeEnfermedadPorGenero(String codigoEnfermedad) {
		float[] tasa = new float[2];
		
		for (Paciente paciente : misPacientes) {
			for (Enfermedad enfermedad : paciente.getHistorial().getPadecimientos()) {
				if (enfermedad.getCodigo().equalsIgnoreCase(codigoEnfermedad)) {
					if (paciente.getGenero().equalsIgnoreCase("Hombre")) {
						tasa[0]++;
					}
					else if (paciente.getGenero().equalsIgnoreCase("Mujer")) {
						tasa[1]++;
					}
				}
			}
		}
		
		int casosTotales = casosEnfermedadTotal(codigoEnfermedad);
		for (int i = 0; i < 2; i++) {
			tasa[i] = tasa[i] * 100 / casosTotales;
		}
		
		return tasa;
	}
	
	/*
	 * Retorna un ArrayList<Vacuna> con las vacunas que ofrecen protección
	 * para cierta enfermedad dado su código.
	*/
	public ArrayList<Vacuna> vacunasParaEnfermedad(String codigoEnfermedad) {
		ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();
		
		for (Vacuna vacuna : misVacunas) {
			for (Enfermedad enfermedad : vacuna.getProteccion()) {
				if (enfermedad.getCodigo().equalsIgnoreCase(codigoEnfermedad)) {
					vacunasDisponibles.add(vacuna);
				}
			}
		}
		
		return vacunasDisponibles;
	}
	
	/*
	 * Dado el id de un médico y una fecha, verifica si este está disponible.
	 * En caso afirmativo, devuelve "true"; devuelve "false" en caso contrario. 
	*/
	public boolean medicoDisponible(Date fecha, String idMedico) {
		boolean disponible = true;
		int i = 0;
		
		while (disponible && i < citasMedicas.size()) {
			if (citasMedicas.get(i).getFechaCita().equals(fecha) && citasMedicas.get(i).getMedico().getId().equalsIgnoreCase(idMedico)) {
				disponible = false;
			}
			i++;
		}
		
		return disponible;
	}
	
	public Paciente buscarPaciente(String cod) {
		Paciente aux = null;
		boolean encontrado = false;
		int i = 0;
		
		while(!encontrado && i < misPacientes.size()) {
			if(misPacientes.get(i).getCedula().equalsIgnoreCase(cod)) {
				aux = misPacientes.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return aux;
	}
	
	/*
	 * Valida las credenciales de inicio de sesión para un usuario 
	 * (administrador o médico) dado su login y password. 
	*/
	public boolean validacionCredenciales(String login, String password) {
		boolean validacion = false;
		Usuario user = null;
		int i = 0;
		
		while (!validacion && i < misUsuarios.size()) {
			user = misUsuarios.get(i);
			if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
				loginUser = user;
				validacion = true;
			}
			i++;
		}
		
		return validacion;
	}
	
	public void registroUsuario(Usuario user) {
		misUsuarios.add(user);
	}
	
	//////////////////// Utils (Vacuna) ////////////////////
	
	public Vacuna buscarVacunaByCodigo(String codigoVacuna) {
		Vacuna vacuna = null;
		boolean encontrado = false;
		int i = 0;
		
		while (!encontrado && i < misVacunas.size()) {
			if (misVacunas.get(i).getCodigo().equalsIgnoreCase(codigoVacuna)) {
				vacuna = misVacunas.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return vacuna;
	}

	public void eliminarVacuna(String codigo) {
		// TODO Auto-generated method stub
		
	}
	
	////////////////////Utils (Enfermedad) ////////////////////

	public Enfermedad buscarEnfermedadByCodigo(String codigoEnfermedad) {
		Enfermedad enfermedad = null;
		boolean encontrada = false;
		int i = 0;
		
		while (!encontrada && i < misEnfermedades.size()) {
			if (misEnfermedades.get(i).getCodigo().equalsIgnoreCase(codigoEnfermedad)) {
				enfermedad = misEnfermedades.get(i);
				encontrada = true;
			}
			i++;
		}
		
		return enfermedad;
	}
	public void insertarEnfermedad (Enfermedad enfermedad)
	{
		misEnfermedades.add(enfermedad);
	}
		
}











