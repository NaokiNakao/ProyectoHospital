package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Clinica implements Serializable {

	private static final long serialVersionUID = 3627439187370750264L;
	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<CitaMedica> citasMedicas;
	private ArrayList<Consulta> misConsultas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	private static Clinica clinica;
	private static Usuario loginUser; 
	
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

	public static void setLoginUser(Usuario loginUser) {
		Clinica.loginUser = loginUser;
	}
	
	//////////////////// Métodos para estadísticas de enfermedades ////////////////////
	
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
			for (Consulta consulta : paciente.getHistorial().getMisConsultas()) {
				if (consulta.getEnfermedad().getCodigo().equalsIgnoreCase(codigoEnfermedad) && consulta.getFechaT().compareTo(fecha) == 0) {
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
					if (paciente.getGenero().equalsIgnoreCase("Masculino")) {
						tasa[0]++;
					}
					else if (paciente.getGenero().equalsIgnoreCase("Femenino")) {
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
			for (Enfermedad proteccion : vacuna.getProteccion()) {
				if (proteccion.getCodigo().equalsIgnoreCase(codigoEnfermedad)) {
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
	
	////////////////////Utils (Usuario) ////////////////////
	
	public Usuario buscarUsuarioById(String idUsuario) {
		Usuario user = null;
		boolean encontrado = false;
		int i = 0;
		
		while (!encontrado && i < misUsuarios.size()) {
			if (misUsuarios.get(i).getId().equalsIgnoreCase(idUsuario)) {
				user = misUsuarios.get(i);
				encontrado = true;
			}
			i++;
		}
		
		return user;
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
	
	public int indexByCodigoVacuna(String codigoVacuna) {
		int index = -1;
		boolean encontrado = false;
		int i = 0;
		
		while (!encontrado && i < misVacunas.size()) {
			if (misVacunas.get(i).getCodigo().equalsIgnoreCase(codigoVacuna)) {
				index = i;
				encontrado = true;
			}
			i++;
		}
		
		return index;
	}
	
	public boolean codigoVacunaValido(String codigoVacuna) {
		boolean validacion = true;
		int i = 0;
		
		if (codigoVacuna.length() != 8) {
			validacion = false;
		}
		
		while (validacion && i < misVacunas.size()) {
			if (misVacunas.get(i).getCodigo().equalsIgnoreCase(codigoVacuna)) {
				validacion = false;
			}
			i++;
		}
		
		return validacion;
	}
	
	public boolean agregarVacuna(Vacuna nuevaVacuna) {
		boolean operacionCorrecta = false;
		
		if (datosVacunaValidos(nuevaVacuna)) {
			misVacunas.add(nuevaVacuna);
			operacionCorrecta = true;
		}
		
		return operacionCorrecta;
	}
	
	private boolean datosVacunaValidos(Vacuna vacuna) {
		boolean validacion = false;
		
		if ( codigoVacunaValido(vacuna.getCodigo()) && (vacuna.getNombreVacuna() != null) && 
			(vacuna.getFabricante() != null) && (!vacuna.getTipoVacuna().equalsIgnoreCase("<Seleccionar>")) &&
			(!vacuna.getFormaAdministracion().equalsIgnoreCase("<Seleccionar>")) /*&& (vacuna.getProteccion().size() > 0)*/ ) {
			
			validacion = true;
		}
		
		return validacion;
	}
	
	public void modificarVacuna(Vacuna modificacion, int index) {
		if (index != -1) {
			misVacunas.set(index, modificacion);
		}
	}

	public void eliminarVacuna(int index) {
		misVacunas.remove(index);
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
	
	public void insertarEnfermedad (Enfermedad enfermedad) {
		misEnfermedades.add(enfermedad);
	}
		
	public Medico buscarMedicoByCodigo(String cod) {
		Medico medico = null;
		boolean encontrada = false;
		int i = 0;
		
		while (!encontrada && i < misUsuarios.size()) {
			if (misUsuarios.get(i).getId().equalsIgnoreCase(cod) && misUsuarios.get(i) instanceof Medico) {
				medico = (Medico) misUsuarios.get(i);
				encontrada = true;
			}
			i++;
		}
		
		return medico;
		
	}
	
	public void insertarCita(CitaMedica cita) {
		citasMedicas.add(cita);
	}
	
	public CitaMedica buscarCitaMedicaByCod(String cod) {
		CitaMedica cita = null;
		boolean encontrada = false;
		int i = 0;
		
		while (!encontrada && i < misUsuarios.size()) {
			if (citasMedicas.get(i).getCodigo().equalsIgnoreCase(cod)) {
				cita = citasMedicas.get(i);
				encontrada = true;
			}
			i++;
		}
		
		return cita;
	}
	
	public void insertarConsulta(Consulta c, Medico medico,CitaMedica cita, Paciente p,HistoriaClinica b) {
		misConsultas.add(c);
		medico.getMisConsultas().add(c);
		medico.getMisCitas().remove(cita);
		misPacientes.add(p);
		b.getMisConsultas().add(c);
		p.setHistorial(b);
	}
	
	public void insertarConsultaV2(Consulta c, Medico medico,CitaMedica cita, Paciente p,HistoriaClinica b) {
		misConsultas.add(c);
		medico.getMisConsultas().add(c);
		medico.getMisCitas().remove(cita);
		b.getMisConsultas().add(c);
		
	}
	
	
	public void insertarCita(CitaMedica cita, Medico medico) {
		
		citasMedicas.add(cita);
		medico.getMisCitas().add(cita);
		
	}
	
	public boolean citaByCedula(String cedula, Date fecha) {
		boolean bandera = false;
		
		for (CitaMedica citaMedica : citasMedicas) {
			if(citaMedica.getCedulaPersona().equalsIgnoreCase(cedula) && citaMedica.getFechaCita().equals(fecha)) {
				bandera = true;
			}
		}
		
		return bandera;
	}
	
	public void insertarMedico(Medico med) {
		misUsuarios.add(med);
	}
	
	
	public ArrayList<Medico> CargarMedicoDisponibles(Date fecha) {
		
		ArrayList<Medico> medicosDisp = new ArrayList<>();
		
		for (Usuario user : misUsuarios) {
			
			if(user instanceof Medico) {
				if(medicoDisponible(fecha, user.getId())) {
					medicosDisp.add((Medico) user);
				}
			}
		}
		
		return medicosDisp; 
	}
	
	public Usuario buscarUsuarioByLogin(String login) {
		
		Usuario user = null;
		boolean encontrada = false;
		int i = 0;
		
		while (!encontrada && i < misUsuarios.size()) {
			if (misUsuarios.get(i).getLogin().equalsIgnoreCase(login)) {
				user = misUsuarios.get(i);
				encontrada = true;
			}
			i++;
		}
		return user;
		
	}
	
	
	
}











