package logico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	private String hashkey = "grupo2";
	
	
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
	
	/*NECESITA SQL NAOKI*/
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
	
	/*NECESITA SQL MISAEL*/
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
	
	/*NECETIA SQL NAOKI  TAAA FEEAAAA*/
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
	
	/*MUY NECESARIA MISAEL */  /*SUSTUIDA EN CLASE VISUAL ESTADISTICAENF
	public ArrayList<Vacuna> vacunasParaEnfermedad(String codigoEnfermedad) {//PREGUNTALE A LA PROF
		ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();
		
		for (Vacuna vacuna : misVacunas) {
			for (Enfermedad proteccion : vacuna.getProteccion()) {
				if (proteccion.getCodigo().equalsIgnoreCase(codigoEnfermedad)) {
					vacunasDisponibles.add(vacuna);
				}
			}
		}
		
		return vacunasDisponibles;
	}*/
	
	
	public Paciente buscarPaciente(String cedula) throws SQLException {//probada en main
		
		Paciente paciente= null;
		
		String query = "select paciente.* from paciente where ced_paciente = ?";
		PreparedStatement statement = ConexionSQL.getConexion().prepareStatement(query);
		statement.setString(1, cedula);
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
		paciente = new Paciente(cedula, result.getString("nombre"), result.getString("genero"), result.getDate("fecha_nac"), 
				result.getString("cod_ciudad"),result.getString("telefono"));
		}
		result.close();
		statement.close();

		return paciente;
	}
	
	/*
	 * Valida las credenciales de inicio de sesi�n para un usuario 
	 * (administrador o m�dico) dado su login y password. 
	*/
	
	
	/*NECESARIA NAOKI*/
	public boolean validacionCredenciales(String login, String inputPassword) {
		boolean validacion = false;
		String codUsuario = buscarUsuarioByUsername(login).getId();
		
		if(codUsuario != null) {
			String query = null;
			
			if(codUsuario.contains("M")) {
				query = "select convert(nvarchar(20), DECRYPTBYPASSPHRASE(?, encrypted_password)) as password "
						+ "from medico "
						+ "where cod_medico = ?";
			} 
			else if(codUsuario.contains("A")) {
				query = "select convert(nvarchar(20), DECRYPTBYPASSPHRASE(?, encrypted_password)) as password "
						+ "from administrador "
						+ "where cod_admin = ?";
			}
		
			PreparedStatement statement = null;
			
			try {
				statement = ConexionSQL.getConexion().prepareStatement(query);
				statement.setString(1, hashkey);
				statement.setString(2, codUsuario);
				ResultSet result = statement.executeQuery();
				String decryptedPassword = null;
				
				while(result.next()) {
					decryptedPassword = result.getString("password");
				}
				
				if(inputPassword.equals(decryptedPassword)) {
					validacion = true;
				}
				
				statement.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return validacion;
	}
	
	
	////////////////////Utils (Usuario) ////////////////////
	
	public Usuario buscarUsuarioById(String idUsuario) throws SQLException {  //Probar main
		Usuario user = null;
		String query = null;
		
		if(idUsuario.contains("M")) {
			query = "select * from medico where cod_medico = ?";
		}else if(idUsuario.contains("A")) {
			query = "select * from administrador where cod_admin = ?";
		}
		
		PreparedStatement statement = ConexionSQL.getConexion().prepareStatement(query);
		statement.setString(1, idUsuario);
		ResultSet res = statement.executeQuery();
		
		while(res.next()) {
			
			user = new Usuario(idUsuario, res.getString("username"),res.getString("encrypted_password") 
				,res.getString("nombre"), res.getString("apellido"), res.getString("telefono"));	
		}
		statement.close();
		res.close();
		
		return user;
	}
	
	//////////////////// Utils (Vacuna) ////////////////////
	
	public Vacuna buscarVacunaByCodigo(String codigoVacuna) throws SQLException { //Probada main
		Vacuna vacuna = null;

		String query1 = "select * from vacuna where cod_vacuna = ?";
		String query3 = "select nombre_fab from fabricante,vacuna where vacuna.cod_vacuna = ? and fabricante.cod_fab = vacuna.cod_fab";
		
		PreparedStatement statement1 = ConexionSQL.getConexion().prepareStatement(query1);
		statement1.setString(1, codigoVacuna);
		ResultSet res = statement1.executeQuery();
		
		PreparedStatement statement2 = ConexionSQL.getConexion().prepareStatement(query3);
		statement2.setString(1, codigoVacuna);
		ResultSet res2 = statement2.executeQuery();
		
		while(res.next() && res2.next()) {
			
			vacuna = new Vacuna(codigoVacuna, res.getString("nombre_vacuna"), 
					res2.getString("nombre_fab"), misEnfermedades, res.getString("tipo_vacuna"), res.getString("forma_admin"));
		}
		return vacuna;
	}
	
	
	
	/*NO ES NECESARIA*/
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
	
	
	/*NO NECESARIA*/
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
	
	
	/*NECESARIA NAOKI*/
	public boolean agregarVacuna(Vacuna nuevaVacuna) { //probada main
		boolean realizado = false;
		PreparedStatement statement = null;
		String sql = "insert into vacuna (cod_vacuna, nombre_vacuna, tipo_vacuna, forma_admin, cod_fab) "
				+ "values (?, ?, ?, ?, ?)";
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(sql);
			statement.setString(1, nuevaVacuna.getCodigo());
			statement.setString(2, nuevaVacuna.getNombreVacuna());
			statement.setString(3, nuevaVacuna.getTipoVacuna());
			statement.setString(4, nuevaVacuna.getFormaAdministracion());
			statement.setInt(5, Clinica.getInstance().buscarCodFabByNombreFab(nuevaVacuna.getFabricante()));
			
			statement.executeUpdate();
			statement.close();
			realizado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return realizado;
	}
	
	/*NECESARIA MISAEL*/ 
	public boolean modificarVacuna(Vacuna modificacion) {
		boolean actualizado = false;
		
		return actualizado;
	}
	
	/*NECESARIA NAOKI*/
	public boolean eliminarVacuna(String codVacuna) throws SQLException { //probada main
		boolean realizado = false;
		PreparedStatement statement = null;
		String sql = "delete from vacuna "
				+ "where cod_vacuna = ?";
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(sql);
			statement.setString(1, codVacuna);
			
			statement.executeUpdate();
			statement.close();
			realizado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return realizado;
	}
	
	public int buscarCodFabByNombreFab(String nombreFab) {
		int codVacuna = 0;
		String query = "select cod_fab "
				+ "from fabricante "
				+ "where nombre_fab = ?";
		PreparedStatement statement = null;
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(query);
			statement.setString(1, nombreFab);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				codVacuna = result.getInt("cod_fab");
			}
			
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return codVacuna;
	}
	
	////////////////////Utils (Enfermedad) ////////////////////
	
	/*NECESARIA MISAEL*/
	public Enfermedad buscarEnfermedadByCodigo(String codigoEnfermedad) throws SQLException { //conflicto de tipos en cod_tipo
		Enfermedad enfermedad = null;
	
		String query = "select * from enfermedad where cod_enf = ?";
		PreparedStatement consul = ConexionSQL.getConexion().prepareStatement(query);
		consul.setString(1, codigoEnfermedad);
		
		ResultSet resul = consul.executeQuery();
		
		while(resul.next()) {
			
			enfermedad = new Enfermedad(codigoEnfermedad, resul.getString("nombre_enf"), codigoEnfermedad, resul.getString("desc_enf"));
			
		}
		consul.close();
		resul.close();
		
		return enfermedad;
	}
	
	/*NECESARIA NAOKI*/
	public boolean insertarEnfermedad (Enfermedad enfermedad, int codTipo) throws SQLException {
		boolean realizado = false;
		PreparedStatement statement = null;
		String sql = "insert into enfermedad (cod_enf, nombre_enf, desc_enf, cod_tipo) "
				+ "values (?, ?, ?, ?);";
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(sql);
			statement.setString(1, enfermedad.getCodigo());
			statement.setString(2, enfermedad.getNombreEnfermedad());
			statement.setString(3, enfermedad.getDescripcionEnfermedad());
			statement.setInt(4, codTipo);
			
			statement.executeUpdate();
			statement.close();
			realizado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return realizado;
	}
	
	/*NECESARIA MISAEL*/
	public Medico buscarMedicoByCodigo(String cod) throws SQLException {
		Medico medico = null;
		
		String query = "select * from medico where cod_medico = ?";
		PreparedStatement consul = ConexionSQL.getConexion().prepareStatement(query);
		consul.setString(1, cod);
		
		ResultSet resul = consul.executeQuery();
		
		while(resul.next()) {
			
			medico = new Medico(cod, resul.getString("username"), "Contrase�a", resul.getString("nombre"), resul.getString("apellido"),
					resul.getString("telefono"));
			
		}
		consul.close();
		resul.close();
		
		
		return medico;
		
	}
	
	/*NECESARIA NAOKI*/
	public boolean insertarCita(CitaMedica cita) {
		boolean realizado = false;
		PreparedStatement statement = null;
		String sql = "insert into cita (cod_cita, fecha_cita, cod_medico, ced_paciente) "
				+ "values (?, ?, ?, ?);";
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(sql);
			statement.setString(1, cita.getCodigo());
			statement.setDate(2, (java.sql.Date) cita.getFechaCita());
			statement.setString(3, cita.getMedico().getId());
			statement.setString(4, cita.getCedulaPersona());
			
			statement.executeUpdate();
			statement.close();
			realizado = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return realizado;
	}
	
	/*NECESARIA MISAEL*/
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
	
	/*sql*//*Probar Main*/public void insertarConsulta(Consulta c, Medico medico,CitaMedica cita, Paciente p,HistoriaClinica b) {
		
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
	
	/*REVISION*/
	public void insertarConsultaV2(Consulta c, Medico medico,CitaMedica cita, Paciente p,HistoriaClinica b) {
		misConsultas.add(c);
		medico.getMisConsultas().add(c);
		medico.getMisCitas().remove(cita);
		b.getMisConsultas().add(c);
		
	}
	
	/*NECESARIA NAOKI*/
	public void insertarCita(CitaMedica cita, Medico medico) {
		
		citasMedicas.add(cita);
		medico.getMisCitas().add(cita);
		
	}
	
	/*NECESARIA MISAEL*/
	public boolean citaByCedula(String cedula, Date fecha) {
		boolean bandera = false;
		
		for (CitaMedica citaMedica : citasMedicas) {
			if(citaMedica.getCedulaPersona().equalsIgnoreCase(cedula) && citaMedica.getFechaCita().equals(fecha)) {
				bandera = true;
			}
		}
		
		return bandera;
	}
	
	
	public void insertarUsuario(Usuario med,String especialidad) throws SQLException { ///probada
		
		
		String InsertConsul = null;
		PreparedStatement consulta = null;
		
		if(med.getId().contains("M")) {
		
			 InsertConsul = "Insert Into medico (cod_medico,nombre,apellido,username,encrypted_password,telefono) Values (?,?,?,?,?,?)";

		try {
		consulta = ConexionSQL.getConexion().prepareStatement(InsertConsul);
		consulta.setBytes(1, med.getId().getBytes());
		consulta.setString(2,med.getNombre());
		consulta.setString(3,med.getApellido());
		consulta.setString(4,med.getLogin());
		consulta.setBytes(5,med.getPassword().getBytes());
		consulta.setString(6,med.getTelefono());
		
		consulta.executeUpdate();
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta de insertar medico");
			e.printStackTrace();
		}finally {
			
			if(consulta != null)
			consulta.close();
		}
		
		if(buscarCodEspecialidadByNombre(especialidad)== 0) {
		
		String InsertConsul2 = "Insert Into especialidad (nombre_especialidad) Values (?)";
		PreparedStatement consulta2 = null;
		
		try {
			consulta2 = ConexionSQL.getConexion().prepareStatement(InsertConsul2);
			consulta2.setString(1,especialidad);
			consulta2.executeUpdate();
		
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta de insertar especialidad");
			e.printStackTrace();
		}finally {
			
			if(consulta2 != null)
				consulta2.close();
			}
		}
		
		String InsertConsul3 = "Insert Into medico_especialidad (cod_medico,cod_especialidad) Values (?,?)";
		PreparedStatement consulta3 = null;
		
		try {
			consulta3 = ConexionSQL.getConexion().prepareStatement(InsertConsul3);
			consulta3.setString(1,med.getId());
			consulta3.setInt(2,buscarCodEspecialidadByNombre(especialidad));
			consulta3.executeUpdate();
		
		} catch (SQLException e) {
			
			System.out.println("Fallo la consulta medico especialidad");
			e.printStackTrace();
		}finally {
			
			if(consulta3 != null)
				consulta3.close();
			
			}
		}else if (med.getId().contains("A")) {
			 InsertConsul = "Insert Into administrador (cod_admin,puesto_laboral,nombre,apellido,username,encrypted_password,telefono)"
						+ "values (?,?,?,?,?,?,?)";
			 try {
			 	consulta = ConexionSQL.getConexion().prepareStatement(InsertConsul);
				consulta.setString(1,med.getId());
				consulta.setString(2,especialidad);
				consulta.setString(3,med.getNombre());
				consulta.setString(4,med.getApellido());
				consulta.setString(5,med.getLogin());
				consulta.setBytes(6,med.getPassword().getBytes());
				consulta.setString(7,med.getTelefono());
			 
				consulta.executeUpdate(); 
			 }catch (SQLException e) {
				 System.out.println("Fallo insertar admin");
				 e.printStackTrace();
			 	}
			}
			
		
	}
	
	public void modificarUsuario(Usuario user,String texto) {
		PreparedStatement statement = null;
		
		if(user.getId().contains("M")) {
			String update = "UPDATE medico "
					+ "SET apellido = ?, nombre= ? , username = ?, telefono = ?, encrypted_password = encryptbypassphrase(?, ?) "
					+ "WHERE medico.cod_medico = ?";
			
			try {
				statement = ConexionSQL.getInstance().getConexion().prepareStatement(update);
				statement.setString(1, user.getApellido());
				statement.setString(2, user.getNombre());
				statement.setString(3, user.getLogin());
				statement.setString(4, user.getTelefono());
				statement.setString(5, hashkey);
				statement.setString(6, user.getPassword());
				statement.setString(7, user.getId());
				statement.executeUpdate();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		else if(user.getId().contains("A")) {
			String update = "UPDATE administrador "
					+ "SET apellido = ?, nombre= ?, username = ?, telefono = ?, puesto_laboral = ?, encrypted_password = encryptbypassphrase(?, ?) "
					+ "WHERE administrador.cod_admin = ?";
			
			try {
				statement = ConexionSQL.getInstance().getConexion().prepareStatement(update);
				statement.setString(1, user.getApellido());
				statement.setString(2, user.getNombre());
				statement.setString(3, user.getLogin());
				statement.setString(4, user.getTelefono());
				statement.setString(5, texto);
				statement.setString(6, hashkey);
				statement.setString(7, user.getPassword());
				statement.setString(8, user.getId());
				statement.executeUpdate();
			}catch (SQLException e1) {
					e1.printStackTrace();
			}
		
		}
	}
	
	
	public int buscarCodEspecialidadByNombre(String nombre_especialidad) throws SQLException /*Probada main*/{
	
		int cod_especialidad = 0;
		
		String consulta = "select cod_especialidad from especialidad where nombre_especialidad = ?";
		PreparedStatement statement = ConexionSQL.getConexion().prepareStatement(consulta);
		statement.setString(1, nombre_especialidad);
		ResultSet result = statement.executeQuery();
		
		
		while (result.next()) {
			
			cod_especialidad = result.getInt("cod_especialidad");
		}
		
		statement.close();
		result.close();
		
	return cod_especialidad;
}

	public String buscarEspecialidadByCod(int cod_especialidad) throws SQLException /*Probada main*/{
		
		String especialidad = null;
		
		String consulta = "select nombre_especialidad from especialidad where cod_especialidad = ?";
		PreparedStatement statement = ConexionSQL.getConexion().prepareStatement(consulta);
		statement.setInt(1, cod_especialidad);
		
		ResultSet res = statement.executeQuery();
		
		while(res.next()) {
			especialidad = res.getString("nombre_especialidad");
		}
		
		statement.close();
		res.close();
		
		
		return especialidad;
	}
	
	public String buscarEspecialidadByCodMedico(String cod) throws SQLException {
		
		String especialidad = null;
		
		String query = "select especialidad.* from especialidad,medico,medico_especialidad "
				+ "where medico_especialidad.cod_medico = medico.cod_medico "
				+ "and medico_especialidad.cod_especialidad = especialidad.cod_especialidad and medico.cod_medico = ?";
		PreparedStatement statement = ConexionSQL.getConexion().prepareStatement(query);
		statement.setString(1, cod);
		
		ResultSet res = statement.executeQuery();
		
		while(res.next()) {
			especialidad = res.getString("nombre_especialidad");
		}
		
		return especialidad;
		
	}
	
	public String buscarPuestoLaboralByCodAdmin(String cod) {
		String puestoLaboral = null;
		String query = "select puesto_laboral "
				+ "from administrador "
				+ "where cod_admin = ?";
		PreparedStatement statement = null;
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(query);
			statement.setString(1, cod);
			ResultSet res = statement.executeQuery();
			
			while (res.next()) {
				puestoLaboral = res.getString("puesto_laboral");
			}
			
			statement.close();
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return puestoLaboral;
	}
	
	public String buscarEspecialidadCodByCodMedico(String cod) throws SQLException {
		
		String especialidad = null;
		
		String query = "select especialidad.* from especialidad,medico,medico_especialidad "
				+ "where medico_especialidad.cod_medico = medico.cod_medico "
				+ "and medico_especialidad.cod_especialidad = especialidad.cod_especialidad and medico.cod_medico = ?";
		PreparedStatement statement = ConexionSQL.getConexion().prepareStatement(query);
		statement.setString(1, cod);
		
		ResultSet res = statement.executeQuery();
		
		while(res.next()) {
			especialidad = res.getString("cod_especialidad");
		}
		
		return especialidad;
		
	}
	
	
	/*
	 * Dado el id de un m�dico y una fecha, verifica si este est� disponible.
	 * En caso afirmativo, devuelve "true"; devuelve "false" en caso contrario. 
	*/

	/*NECESARIA NAOKI*/
	public boolean medicoDisponible(String fechaHoraBuscada, String codMedicoBuscado) {
		boolean disponible = true;
		String query = "select count(*) as cantidad "
				+ "from cita_medica "
				+ "where fecha_hora_cita = ? and cod_medico = ?";
		PreparedStatement statement = null;
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(query);
			statement.setString(1, fechaHoraBuscada);
			statement.setString(2, codMedicoBuscado);
			
			ResultSet cantidadCitas = statement.executeQuery();
			int n = 0;
			
			while (cantidadCitas.next()) {
				n = cantidadCitas.getInt("cantidad");
			}
			
			statement.close();
			cantidadCitas.close();
			
			if (n > 0) {
				disponible = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return disponible;
	}
	
	/*NECESARIA MISAEL*/
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

	
	public Usuario buscarUsuarioByUsername(String username) {
		Usuario user = null;
		String query = "select medico.cod_medico as cod, medico.nombre as nombre, medico.apellido as apellido, medico.username as username, medico.encrypted_password as password, medico.telefono as telefono "
				+ "from medico "
				+ "where medico.username = ?  "
				+ "union "
				+ "select administrador.cod_admin, administrador.nombre, administrador.apellido, administrador.username, administrador.encrypted_password, administrador.telefono "
				+ "from administrador "
				+ "where administrador.username = ? ";
		PreparedStatement statement = null;
		
		try {
			statement = ConexionSQL.getConexion().prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, username);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				user = new Usuario(result.getString("cod"), username, result.getString("password"), result.getString("nombre"), result.getString("apellido"), 
					result.getString("telefono"));
			}
			
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public String BuscarNombreProvinciaByCod(int cod) throws SQLException /*Probada Main*/ {
		
		String nombre_provincia = null;
		
		Statement provincia = ConexionSQL.getConexion().createStatement();
		String consulta = "select nombre_provincia from provincia where cod_provincia = "+cod;
		ResultSet result = provincia.executeQuery(consulta);
		
		while(result.next()) {
			nombre_provincia = result.getString("nombre_provincia");
			
		}
		
		provincia.close();
		result.close();
	
		return nombre_provincia;
	}
	
	public String BuscarCodProvinciaByNombre(String nombre_provincia) throws SQLException /*Probada Main*/{
		
		String cod_provincia=null;
		
		String consulta = "select cod_provincia from provincia where nombre_provincia = ?";
		PreparedStatement provincia = ConexionSQL.getConexion().prepareStatement(consulta);
		provincia.setString(1, nombre_provincia);
		
		ResultSet result = provincia.executeQuery();
		
		while(result.next()) {
			cod_provincia = result.getString("cod_provincia");
		
		}
		
		provincia.close();
		result.close();	
		
		return cod_provincia;	

	}
	
	/*Misael*/
	public void InsertarAdmin(Administrador usu) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void cargarPadecimiento(Paciente paciente) {
		
	}
	
	private LocalDateTime formatoFechaHora(String fechaHora) {
		String formato = "yyyy-mm-dd hh:mm:ss";
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern(formato));
	}
	
}











