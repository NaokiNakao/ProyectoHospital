package logico;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Clinica {

	private ArrayList<Paciente> misPacientes;
	private ArrayList<Usuario> misUsuarios;
	private ArrayList<CitaMedica> citasMedicas;
	private ArrayList<Enfermedad> misEnfermedades;
	private ArrayList<Vacuna> misVacunas;
	public static Clinica clinica = null;
	
	private Clinica() {
		this.misPacientes = new ArrayList<Paciente>();
		this.misUsuarios = new ArrayList<Usuario>();
		this.citasMedicas = new ArrayList<CitaMedica>();
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

	public ArrayList<Enfermedad> getMisEnfermedades() {
		return misEnfermedades;
	}

	public ArrayList<Vacuna> getMisVacunas() {
		return misVacunas;
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

	public void setMisEnfermedades(ArrayList<Enfermedad> misEnfermedades) {
		this.misEnfermedades = misEnfermedades;
	}

	public void setMisVacunas(ArrayList<Vacuna> misVacunas) {
		this.misVacunas = misVacunas;
	}
	
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
	
	public boolean inicioSesionValido(String reqLogin, String reqPassword) {
		boolean valido = false;
		int i = 0;
		
		while (!valido && i < misUsuarios.size()) {
			if (misUsuarios.get(i).getLogin().equalsIgnoreCase(reqLogin) && misUsuarios.get(i).getPassword().equalsIgnoreCase(reqPassword)) {
				valido = true;
			}
			i++;
		}
		
		return valido;
	}
	
	/*
	 * Retorna un entero que representa la cantidad de casos de una 
	 * enfermedad declarados en cierta fecha.
	*/
	public int casosEnfermedadPorFecha(String codigoEnfermedad, Date fecha) {
		int casos = 0;
		
		for (Paciente paciente : misPacientes) {
			for (Consulta consulta : paciente.getMisConsultas()) {
				if (consulta.getEnfermedad().getCodigoEnfermedad().equalsIgnoreCase(codigoEnfermedad) && consulta.getFechaConsulta().equals(fecha)) {
					casos++;
				}
			}
		}
		
		return casos;
	}
	
	/*
	 * Retorna un arreglo de enteros que representa la catidad de hombres
	 * y mujeres que se les ha diagnosticado cierta enfermedad. 
	 * casos[0] --> cantidad de hombres
	 * casos[1] --> cantidad de mujeres
	*/
	public int[] casosEnfermedadPorGenero(String codigoEnfermedad) {
		int[] casos = new int[2];
		
		for (Paciente paciente : misPacientes) {
			for (Consulta consulta : paciente.getMisConsultas()) {
				if (consulta.getEnfermedad().getCodigoEnfermedad().equalsIgnoreCase(codigoEnfermedad)) {
					
					if (paciente.getGenero().equalsIgnoreCase("Hombre")) {
						casos[0]++;
					}
					else if (paciente.getGenero().equalsIgnoreCase("Mujer")) {
						casos[1]++;
					}
					
				}
			}
		}
		
		return casos;
	}
	
	/*
	 * Retorna un ArrayList con las vacunas que protegen contra
	 * cierta enfermedad.
	*/
	public ArrayList<Vacuna> vacunasParaEnfermedad(String codigoEnfermedad) {
		ArrayList<Vacuna> vacunasDisponibles = new ArrayList<Vacuna>();
		
		for (Vacuna vacuna : misVacunas) {
			for (Enfermedad enfermedad : vacuna.getProteccion()) {
				if (enfermedad.getCodigoEnfermedad().equalsIgnoreCase(codigoEnfermedad)) {
					vacunasDisponibles.add(vacuna);
				}
			}
		}
		
		return vacunasDisponibles;
	}

}









