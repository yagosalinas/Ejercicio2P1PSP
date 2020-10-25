package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Tarea_3.NetLogger;

class NetLoggerTest {

	private static final String RUTA_ACTIVIDAD = "actividad";
	private final static String RUTA_NET = "net";

	@Test
	void crearEstructuraDirectorios() {
		NetLogger.crearEstructuraDirectorios();
		File dir = new File(RUTA_ACTIVIDAD);
		if (!dir.exists()) {
			fail();
		} else if (!(new File(RUTA_NET)).exists()) {
			fail();
		} else {
			assertTrue(true);
		}
	}

	@Test
	void registrarAccesoUsuario() {
		String user = System.getProperty("user.name");
		String nombreFich = RUTA_ACTIVIDAD + "\\" + user + ".log";
		long linea1 = 0;
		File log = new File(nombreFich);
		try {
			if(log.exists()) {
				FileReader fr = new FileReader(nombreFich);
				BufferedReader bf = new BufferedReader(fr);
				linea1 = bf.lines().count();
				bf.close();
			}
		} catch (IOException e) {
			fail(e.toString());
		}
		try {
			NetLogger.registrarAccesoUsuario();
			File fichero = new File(nombreFich);
			FileReader fr = new FileReader(nombreFich);
			BufferedReader bf1 = new BufferedReader(fr);
			long linea2 = bf1.lines().count();
			fr.close();
			assertEquals(linea1 + 1, linea2);
		} catch (IOException e1) {
			fail(e1.toString());
		}

	}

	
	@Test
	void getNumeroUltimoNetStatLog() {
		File fichero = new File(RUTA_NET + "//netstat_1.log");
		try {
			fichero.createNewFile();
		} catch (IOException e) {
			fail(e.toString());
		}
		File fichero25 = new File(RUTA_NET + "//netstat_25.log");
		try {
			fichero25.createNewFile();
		} catch (IOException e1) {
			fail(e1.toString());
		}
		int num = NetLogger.getNumeroUltimoNetStatLog();
		assertEquals(num, 25);
		fichero.delete();
		fichero25.delete();
	}
	@Test
	void leerResultadoNetStat() {
		try {
			List<String> lista=NetLogger.leerResultadoNetStat();
			assertEquals(lista.size(), 116);
		} catch (IOException e) {
			fail(e.toString());
		}
	}
	@Test
	void escribirNetStatLog() {
		String rutaFich= "fichero.txt";
		
			List<String> netstat = new ArrayList<String>(); 
			String cero= "cero";
			String uno= "uno";
			netstat.add(0, cero);
			netstat.add(1, uno);
			try {
				NetLogger.escribirNetStatLog(rutaFich, netstat);
			} catch (IOException e) {
				fail(e.toString());
			}
			try {
				List<String> lista = new ArrayList<String>();
				BufferedReader read = new BufferedReader(new FileReader(RUTA_NET+"\\"+rutaFich));
				String linea;
				while ((linea = read.readLine()) != null) {
					lista.add(linea);
				} 
				read.close();
				assertEquals(true, lista.get(1).equals(cero) && lista.get(2).equals(uno));
			} catch (IOException e) {
				fail(e.toString());
			}
			File fichero = new File(RUTA_NET+"\\"+rutaFich);
			fichero .delete();
			
	}
	void leerNetStatLog() {
		String nombreFichero = "fichero.txt";
		File fichero = new File(nombreFichero);
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(fichero, true));
			pw.println("hola");
			pw.println("hola");
			pw.println("hola");
			pw.close();
		} catch (IOException e) {
			fail(e.toString());
		}
		
		try {
			NetLogger.leerNetStatLog(nombreFichero);
			
		} catch (IOException e) {
			fail(e.toString());
		}
		
	}
	void escribirEnErrLog() {
		String mensaje= "Error";
		File fichero = new File("error.log");
		try {
			NetLogger.escribirEnErrLog(mensaje);
			BufferedReader read = new BufferedReader(new FileReader(fichero));
			read.readLine();
			String texto=read.readLine();
			assertEquals(mensaje, texto);
		} catch(IOException e) {
			fail(e.toString());
		}
	}
	
	
}
