package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Clinica {

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
	
	//////////////////// M�todos para estad�sticas de enfermedades ////////////////////
	
	/*
	 * Retorna un String de caracteres alfanum�ricos aleatorios
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
	 * que se han reportado para cierta enfermedad dado su c�digo.
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
	 * Dado el c�digo de una enfermedad y una fecha, retorna un entero
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
	 * Dado el c�digo de una enfermedad, retonrna un arreglo de n�meros
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
	 * Retorna un ArrayList<Vacuna> con las vacunas que ofrecen protecci�n
	 * para cierta enfermedad dado su c�digo.
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
	
	
	public Paciente buscarPaciente(String cedula) throws SQLException {
		
		Paciente paciente= null;
		
		String query = "select paciente.* from paciente where ced_paciente = ?";
		PreparedStatement stament = ConexionSQL.getConexion().prepareStatement(query);
		stament.setString(1, cedula);
		ResultSet result = stament.executeQuery();
		
		paciente = new Paciente(cedula, result.getString("nombre"), result.getString("genero"), result.getDate("fecha_nac"), 
				result.getInt("cod_ciudad"),result.getString("telefono"));
		
		result.close();
		stament.close();

		return paciente;
	}
	
	/*
	 * Valida las credenciales de inicio de sesi�n para un usuario 
	 * (administrador o m�dico) dado su login y password. 
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
	
	
	////////////////////Utils (Usuario) ////////////////////
	
	public Usuario buscarUsuarioById(String idUsuario) throws SQLException {
		Usuario user = null;
		String query = null;
		
		if(idUsuario.contains("M")) {
			query = "select * from medico where cod_medico = ?";
		}else if(idUsuario.contains("A")) {
			query = "select * from administrador where cod_admin = ?";
		}
		
		PreparedStatement stament = ConexionSQL.getConexion().prepareStatement(query);
		stament.setString(1, idUsuario);
		ResultSet res = stament.executeQuery();
		
		user = new Usuario(idUsuario, res.getString("username"),res.getString("password") 
				,res.getString("nombre"), res.getString("apellido"), res.getString("telefono"));	
		
		stament.close();
		res.close();
		
		return user;
	}
	
	//////////////////// Utils (Vacuna) ////////////////////
	
	public Vacuna buscarVacunaByCodigo(String codigoVacuna) throws SQLException {
		Vacuna vacuna = null;

		String query1 = "select * from vacuna where cod_vacuna = ?";
		String query3 = "select nombre_fab from fabricante,vacuna where vacuna.cod_vacuna = ? and fabricante.cod_fab = vacuna.cod_fab";
		
		PreparedStatement stament1 = ConexionSQL.getConexion().prepareStatement(query1);
		stament1.setString(1, codigoVacuna);
		ResultSet res = stament1.executeQuery();
		
		PreparedStatement stament2 = ConexionSQL.getConexion().prepareStatement(query3);
		stament2.setString(1, codigoVacuna);
		ResultSet res2 = stament2.executeQuery();
		
		
		vacuna = new Vacuna(codigoVacuna, res.getString("nombre_vacuna"), res2.getString("nombre_fab"), misEnfermedades, res.getString("tipo_vacuna"), res.getString("forma_admin"));
		
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
		
		String InsertConsul = "Insert Into consulta(fecha_consulta,diagnostico,cod_medico,cod_historia) Values (?,?,?,?)";
		PreparedStatement consulta = null;
		
		try {
		consulta = ConexionSQL.getConexion().prepareStatement(InsertConsul);
		consulta.setDate(1, (java.sql.Date) c.getFechaConsulta());
		consulta.setString(2, c.getDiagnostico());
		consulta.setString(3, medico.getId());
		consulta.setString(4, b.getCodigo());
		
		consulta.executeUpdate();
		
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta");
			e.printStackTrace();
			
		}
		
		
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
		
		String InsertConsul = "Insert Into medico (cod_medico,nombre,apellido,username,pass,telefono) Values (?,?,?,?,?,?)";
		PreparedStatement consulta = null;
		
		try {
		consulta = ConexionSQL.getConexion().prepareStatement(InsertConsul);
		consulta.setString(1,med.getId());
		consulta.setString(2,med.getNombre());
		consulta.setString(3,med.getApellido());
		consulta.setString(4,med.getLogin());
		consulta.setString(5,med.getPassword());
		consulta.setString(6,med.getTelefono());
		
		consulta.executeUpdate();
		
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta");
			e.printStackTrace();
		}
		
		String InsertConsul2 = "Insert Into especialidad (nombre_especialidad) Values (?)";
		PreparedStatement consulta2 = null;
		
		try {
			consulta2 = ConexionSQL.getConexion().prepareStatement(InsertConsul2);
			consulta2.setString(1,med.getEspecialidad());
		
		consulta.executeUpdate();
		
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta");
			e.printStackTrace();
		}
		
		
		String InsertConsul3 = "Insert Into medico_especialidad (cod_medico,cod_especialidad) Values (?,?)";
		PreparedStatement consulta3 = null;
		
		try {
			consulta3 = ConexionSQL.getConexion().prepareStatement(InsertConsul3);
			consulta3.setString(1,med.getId());
			consulta3.setString(2,med.getEspecialidad());
		
		
		consulta.executeUpdate();
		
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta");
			e.printStackTrace();
		}
		
		
	}
	
	/*
	 * Dado el id de un m�dico y una fecha, verifica si este est� disponible.
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
	
	
	
public Usuario buscarUsuarioByLoginMed(String login) throws SQLException {
		
		Usuario user = null;

		Statement stament = ConexionSQL.getConexion().createStatement();
		String consulta = "select * from medico,administrador where medico.loggin = "+ login+"or administrador.loggin = "+ login;
		ResultSet result = stament.executeQuery(consulta);
		
		if(result.getString(1).contains("M")) {
		
			user= new Usuario(result.getString("cod_medico"), login, result.getString("passwordd"), result.getString("nombre"), result.getString("apellido"), 
				result.getString("telefono"));
			
		}else if(result.getString(1).contains("A")){
			
			user= new Usuario(result.getString("cod_admin"), login, result.getString("passwordd"), result.getString("nombre"), result.getString("apellido"), 
					result.getString("telefono"));
			
		}
		
		stament.close();
		result.close();
		
		return user;
		
	}
	
	public  String BuscarProvinciaByCod(int cod) throws SQLException {
		
		
		
		Statement provincia = ConexionSQL.getConexion().createStatement();
		String consulta = "select nombre_provincia from provincia where cod_provincia = "+cod;
		ResultSet result = provincia.executeQuery(consulta);
		
		provincia.close();
		result.close();
	
		return result.getString("nombre_provincia");
	
	}
	
	
	public String BuscarProvinciaByNombre(String nombre_provincia) throws SQLException {
		

		
		String consulta = "select cod_provincia from provincia where nombre_provincia = ?";
		PreparedStatement provincia = ConexionSQL.getConexion().prepareStatement(consulta);
		provincia.setString(1, nombre_provincia);
		
		ResultSet result = provincia.executeQuery();
		provincia.close();
		result.close();	
		
		return result.getString("cod_provincia");	

	}
	
	public String BuscarEspecialidadByNombre(String nombre_provincia) throws SQLException {
		

		
		String consulta = "select cod_provincia from provincia where nombre_provincia = ?";
		PreparedStatement provincia = ConexionSQL.getConexion().prepareStatement(consulta);
		provincia.setString(1, nombre_provincia);
		
		ResultSet result = provincia.executeQuery();
		provincia.close();
		result.close();	
		
		return result.getString("cod_provincia");	

	}
	
	
}











