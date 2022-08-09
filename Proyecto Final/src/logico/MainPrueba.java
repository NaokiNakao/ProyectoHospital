package logico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainPrueba {

	public static void main(String[] args) throws SQLException {
		
		Clinica.getInstance().insertarConsulta(null, null, null, null, null);
		
}}












