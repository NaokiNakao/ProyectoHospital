package logico;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainPrueba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Medico med1 = new Medico("123", "Misael", "misael", "Misael", "mmg", "806521", "MMG");
		Clinica.getInstance().insertarMedico(med1);
		
		Medico med2 = new Medico("185", "Misael", "misael", "Misael", "mmg", "806521", "MMG");
		Clinica.getInstance().insertarMedico(med2);
		
		Date f = new Date(); 
		
		
		
		Enfermedad covid = new Enfermedad("1021", "covid", "Respiratoria", "Malisima compai");
		Clinica.getInstance().insertarEnfermedad(covid);
		
		Enfermedad covid2 = new Enfermedad("1028", "covid2", "Respiratoria", "Malisima compai");
		Clinica.getInstance().insertarEnfermedad(covid2);
		
		ArrayList<Enfermedad> r = new ArrayList<>();
		ArrayList<Enfermedad>t = new ArrayList<>();
		
		r.add(covid);
		r.add(covid2);
		t.add(covid2);
		
		Vacuna sinovac = new Vacuna("62054545", "Sinovac", "Yo", r, "P", "P");
		Vacuna rv = new Vacuna("89526936", "tula", "Tambien yo", r, "Ayh", "p");
		
		Clinica.getInstance().agregarVacuna(rv);
		Clinica.getInstance().agregarVacuna(sinovac);
		
		
		ArrayList<Vacuna> p = new ArrayList();
		p = Clinica.getInstance().getMisVacunas();
		
		/*for (int i = 0; i <Clinica.getInstance().vacunasParaEnfermedad("1021").size(); i++) {
			
			
			System.out.println(Clinica.getInstance().vacunasParaEnfermedad("1021").get(i).getNombreVacuna());
		}*/
		
		
		//String cod = "Cita-458282";
		
		//System.out.println(cod.substring(5));
		
		/*
		 SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	      Date d1 = null;
		try {
			d1 = sdformat.parse("2019-04-15");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      Date d2 = null;
		try {
			d2 = sdformat.parse("2019-08-10");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      System.out.println("The date 1 is: " + sdformat.format(d1));
	      System.out.println("The date 2 is: " + sdformat.format(d2));
	      if(d1.compareTo(d2) > 0) {
	         System.out.println("Date 1 occurs after Date 2");
	      } else if(d1.compareTo(d2) < 0) {
	         System.out.println("Date 1 occurs before Date 2");
	      } else if(d1.compareTo(d2) == 0) {
	         System.out.println("Both dates are equal");
	      }
	   }

		//CitaMedica r = new CitaMedica("895", f, "", "", med1, "", "", f, "");
		//Clinica.getInstance().insertarCita(r, med1);
		
	//	CitaMedica t = new CitaMedica("898", f, "", "", med2, "", "", f, "");
	//	Clinica.getInstance().insertarCita(t, med2);
		
		//System.out.println(Clinica.getInstance().medicoDisponible(f, "123"));
		
		/*
		for (int i = 0; i <Clinica.getInstance().CargarMedicoDisponibles(f).size(); i++) {
			
			System.out.println(Clinica.getInstance().CargarMedicoDisponibles(f).get(i).getId());
		}*/
	

}}


