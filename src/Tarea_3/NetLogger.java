package Tarea_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author yagos<br>
 *         Enlaces:<br>
 *         {@link java.io.File}<br>
 *         {@link java.io.FileWriter}<br>
 *         {@link java.io.PrintWriter}<br>
 *         {@link java.io.FileReader}<br>
 *         {@link java.io.BufferedReader}<br>
 * 
 */
public class NetLogger {

	/**
	 * <p>
	 * Declara el <strong>directorio actividad</strong> como una
	 * <strong>constante</strong>
	 * </p>
	 * 
	 * @since 1.0
	 */
	private final static String RUTA_ACTIVIDAD = "actividad";
	/**
	 * <p>
	 * Declara el <strong>directorio net</strong> como una
	 * <strong>constante</strong>
	 * </p>
	 * 
	 * @since 1.0
	 */
	private final static String RUTA_NET = "net";

	/**
	 * <p>
	 * Devuelve un String correspondiente a la <strong>fecha y hora actual<strong>
	 * Ejemplos:<br>
	 * &nbsp &nbsp &nbsp {@code "[25/02/2020 13:09:41]"}<br>
	 * &nbsp &nbsp &nbsp {@code "[01/12/2021 02:10:04]"}<br>
	 * 
	 * @return fecha y hora actual
	 */
	private static String getTimeStamp() {
		DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String fecha = formatoFecha.format(cal.getTime());
		return "[" + fecha + "]";
	}

	/**
	 * <p>
	 * <strong>Comprueba si existe</strong> la estructura de
	 * <strong>directorios</strong> ("actividad" y "net") y <strong>si no</strong>
	 * es así <strong>la crea</strong>
	 * </p>
	 * Ejemplo:<br>
	 * &nbsp &nbsp &nbsp {@code crea directorios "actividad" y "net"}<br>
	 * &nbsp &nbsp &nbsp {@code File dir = new File(RUTA_ACTIVIDAD); }<br>
	 * &nbsp &nbsp &nbsp {@code if (!dir.exists())}<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp {@code dir.mkdir(); }<br>
	 * &nbsp &nbsp &nbsp {@code dir = new File(RUTA_NET) }<br>
	 * &nbsp &nbsp &nbsp {@code if (!dir.exists()) }<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp {@code dir.mkdir();}<br>
	 * 
	 * @since 1.0
	 */
	public static void crearEstructuraDirectorios() {
		File dir = new File(RUTA_ACTIVIDAD);
		if (!dir.exists()) {
			dir.mkdir();
		}
		dir = new File(RUTA_NET);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

	/**
	 * <p>
	 * <strong>Escribe la fecha y hora</strong> actuales en el <strong>fichero
	 * correspondiente al usuario</strong> que esté ejecutando la aplicación.
	 * </p>
	 * Ejemplo:<br>
	 * &nbsp &nbsp &nbsp{@code String user = System.getProperty("user.name");}<br>
	 * &nbsp &nbsp
	 * &nbsp{@code File log = new File(RUTA_ACTIVIDAD + "\\" + user + ".log");}<br>
	 * &nbsp &nbsp
	 * &nbsp{@code PrintWriter pw = new PrintWriter(new FileWriter(log, true));}<br>
	 * &nbsp &nbsp &nbsp{@code pw.println(getTimeStamp());}<br>
	 * &nbsp &nbsp &nbsp{@code pw.close();}<br>
	 * 
	 * @throws IOException Las excepciones se produciran si no existe la ruta
	 * @since 1.0
	 */
	public static void registrarAccesoUsuario() throws IOException {
		String user = System.getProperty("user.name");
		File log = new File(RUTA_ACTIVIDAD + "\\" + user + ".log");
		PrintWriter pw = new PrintWriter(new FileWriter(log, true));
		pw.println(getTimeStamp());
		pw.close();
	}

	/**
	 * <p>
	 * <strong>Lee todos los ficheros netstat_x.log<strong> del directorio .\net y
	 * <strong>devuelve la x</strong> de aquel que tenga <strong>mayor x</strong>.
	 * </p>
	 * <p>
	 * Por ejemplo, si encuentra los fichero netstat_1.log, netstat_5.log y
	 * netstat_16.log devolverá el número 16. Si no hubiese ningún fichero devolverá
	 * el número 0.
	 * </p>
	 * Ejemplo:<br>
	 * &nbsp &nbsp &nbsp{@code File dir = new File(RUTA_NET);}<br>
	 * &nbsp &nbsp &nbsp{@code String[] lista = dir.list();}<br>
	 * <br>
	 * &nbsp &nbsp &nbsp{@code int mayor = 0;}<br>
	 * &nbsp &nbsp &nbsp{@code for (String file : lista) }<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp
	 * &nbsp{@code file = file.substring(8, file.length() - 4); }<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp
	 * &nbsp{@code int num = Integer.parseInt(file);}<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp{@code if (num > mayor) }<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp{@code mayor = 16;}<br>
	 * &nbsp &nbsp &nbsp{@code return 16;}<br>
	 * 
	 * @return Devuelve el número más alto de netstat_x.log
	 * @since 1.0
	 */
	public static int getNumeroUltimoNetStatLog() {
		File dir = new File(RUTA_NET);
		String[] lista = dir.list();
		int mayor = 0;
		for (String file : lista) {
			file = file.substring(8, file.length() - 4);
			int num = Integer.parseInt(file);
			if (num > mayor) {
				mayor = num;
			}
		}
		return mayor;
	}

	public static void reordenarNetStatLog() {
		File dir = new File(RUTA_NET);
		File a;
		File b;
		String[] lista = dir.list();
		int i = 1;
		for (String file : lista) {
			int num = Integer.parseInt(file.substring(8, file.length() - 4));
			if (num != i) {
				a = new File(RUTA_NET+"\\netstat_" + num + ".log");
				b = new File(RUTA_NET+"\\netstat_" + i + ".log");
				a.renameTo(b);
			}
			i++;
		}
	}

	/**
	 * <p>
	 * <strong>Añade al fichero</strong> <em>nombreFichero</em> del directorio net
	 * todas las <strong>líneas</strong> de la <strong>lista netstat</strong>
	 * </p>
	 * Ejemplo:<br>
	 * &nbsp &nbsp
	 * &nbsp{@code PrintWriter pw = new PrintWriter(new FileWriter(RUTA_NET + "\\" + nombreFichero, true));}<br>
	 * &nbsp &nbsp &nbsp{@code pw.println(getTimeStamp());}<br>
	 * &nbsp &nbsp &nbsp{@code for (String linea : netstat) }<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp{@code pw.println(linea);}<br>
	 * &nbsp &nbsp &nbsp{@code pw.close();}<br>
	 * 
	 * @param nombreFichero (netstat_x.log)
	 * @param lista         con todas las lineas del comando netstat siendo cada
	 *                      linea un elemento de la lista
	 * @throws IOException Las excepciones se produciran si no existe la ruta o la
	 *                     lista
	 * @since 1.0
	 */
	public static void escribirNetStatLog(String nombreFichero, List<String> netstat) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(RUTA_NET + "\\" + nombreFichero, true));
		pw.println(getTimeStamp());
		for (String linea : netstat) {
			pw.println(linea);
		}
		pw.close();
	}

	public static void escribirNetStatSobrante(String nombreFichero, List<String> netstat, int contador)
			throws IOException {
		PrintWriter file = new PrintWriter(new FileWriter(RUTA_NET + "\\" + nombreFichero, true));
		int j;
		file.println(getTimeStamp());
		for (j = contador; j < netstat.size(); j++) {
			file.println(netstat.get(j));
		}
		file.close();
	}

	/**
	 * <p>
	 * <strong>Ejecuta el comando netstat /a</strong> y <strong>devuelve</strong>
	 * una <strong>lista con todas las líneas</strong> siendo cada linea un
	 * elemento.
	 * </p>
	 * Ejemplo:<br>
	 * Process netstat = Runtime.getRuntime().exec("netstat -s");}<br>
	 * &nbsp &nbsp &nbsp{@code List<String> lista = new ArrayList<String>();}<br>
	 * &nbsp &nbsp
	 * &nbsp{@code BufferedReader read = new BufferedReader(new InputStreamReader(netstat.getInputStream()));}<br>
	 * &nbsp &nbsp &nbsp{@code String linea;}<br>
	 * &nbsp &nbsp &nbsp{@code while ((linea = read.readLine()) != null)}<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp{@code lista.add(linea);}<br>
	 * &nbsp &nbsp &nbsp{@code read.close();}<br>
	 * &nbsp &nbsp &nbsp{@code return lista;}<br>
	 * 
	 * @return todo el contenido de netstat -s en forma de lista siendo cada linea
	 *         un elemento
	 * @throws IOException Las excepciones se produciran si no existe la ruta o la
	 *                     lista falla
	 * @since 1.0
	 */
	public static List<String> leerResultadoNetStat() throws IOException {
		Process netstat = Runtime.getRuntime().exec("netstat -s");
		List<String> lista = new ArrayList<String>();
		BufferedReader read = new BufferedReader(new InputStreamReader(netstat.getInputStream()));
		String linea;
		while ((linea = read.readLine()) != null) {
			lista.add(linea);
		}
		read.close();
		return lista;
	}

	/**
	 * <p>
	 * Donde nombreFichero será del tipo netstat_x del directorio net.
	 * <strong>Devuelve una lista</strong> con todas las <strong>líneas del
	 * fichero</strong> indicado.
	 * </p>
	 * Ejemplo:<br>
	 * &nbsp &nbsp &nbsp{@code List<String> lista = new ArrayList<String>();}<br>
	 * &nbsp &nbsp
	 * &nbsp{@code BufferedReader read = new BufferedReader(new FileReader(RUTA_NET+"\\"+nombreFichero));}<br>
	 * &nbsp &nbsp &nbsp{@code String linea;}<br>
	 * &nbsp &nbsp &nbsp{@code while ((linea = read.readLine()) != null) }<br>
	 * &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp{@code lista.add(linea);}<br>
	 * &nbsp &nbsp &nbsp{@code read.close();}<br>
	 * &nbsp &nbsp &nbsp{@code return lista;}<br>
	 * 
	 * @param nombreFichero (netstat_x.log)
	 * @return Devuelve una lista con todas las líneas del fichero indicado
	 * @throws IOException Las excepciones se produciran si no existe la ruta o la
	 *                     lista falla
	 * @since 1.0
	 */
	public static List<String> leerNetStatLog(String nombreFichero) throws IOException {
		List<String> lista = new ArrayList<String>();
		BufferedReader read = new BufferedReader(new FileReader(RUTA_NET + "\\" + nombreFichero));
		String linea;
		while ((linea = read.readLine()) != null) {
			lista.add(linea);
		}
		read.close();
		return lista;
	}

	/**
	 * <p>
	 * <strong>Escribe en la ruta err.log</strong> una nueva entrada con la
	 * <strong>fecha y hora</strong> actuales y el <strong>mensaje de
	 * error</strong>.
	 * </p>
	 * Ejemplo:<br>
	 * &nbsp &nbsp
	 * &nbsp{@code PrintWriter printErr = new PrintWriter(new File("error.log"));}<br>
	 * &nbsp &nbsp &nbsp{@code printErr.println(NetLogger.getTimeStamp());}<br>
	 * &nbsp &nbsp
	 * &nbsp{@code printErr.println(java.lang.ArrayIndexOutOfBoundsException);}<br>
	 * 
	 * @param mensaje de error
	 * @throws FileNotFoundException Las excepciones se produciran si no existe el
	 *                               fichero o se crea mal
	 * @since 1.0
	 */
	public static void escribirEnErrLog(String mensaje) throws FileNotFoundException {
		PrintWriter printErr = new PrintWriter(new File("error.log"));
		printErr.println(NetLogger.getTimeStamp());
		printErr.println(mensaje);
	}

	/**
	 * <p>
	 * Se ejecutan todos los metodos anteriormente mencionados de manera que:<br>
	 * Se crean todos los directorios si no estan. Registra a un usuario si no lo
	 * esta en el directorio actividad y pone la fecha y hora actuales en el ficero
	 * usuario.log. Crea una nueva entrada dentro de "net" concretamente en el
	 * "netstat_x.log" con la "x" más alta (netstat_1.log en caso de no haber) o la
	 * siguente (más alta +1), dependiendo si la suma de las líneas de lo escrito y
	 * lo por escribir es menor o igual a 200 (irá a la más alta) o mayor(a la
	 * siguiente). Ya sabiendo donde realizar la nueva entrada se escribe fecha y
	 * hora más la información de la netstat en dicho archivo. En caso de algún
	 * error quedará registrado en err.log
	 * </p>
	 * 
	 * @throws IOException Las excepciones se produciran si no existe alguna de las
	 *                     las rutas o las lista fallan
	 * @since 1.0
	 */
	public static void main(String[] args) throws IOException {
		NetLogger.crearEstructuraDirectorios();
		NetLogger.reordenarNetStatLog();
		try {
			int ultimoNetStat = NetLogger.getNumeroUltimoNetStatLog();
			String rutaFich = "netStat_" + ultimoNetStat + ".log";
			File fich = new File(rutaFich);
			List<String> resultado = NetLogger.leerResultadoNetStat();
			NetLogger.registrarAccesoUsuario();
			// Si el ultimoNetStat es 0 escribimos en netstat_1.log
			if (ultimoNetStat == 0) {
				NetLogger.escribirNetStatLog("netStat_1.log", resultado);
				// Si no es 0 leemos el contenido del netstat_ultimoNetStat.log
			} else {
				List<String> lista = NetLogger.leerNetStatLog(rutaFich);
				int i = lista.size();
				int j = 0;
				// Si el contenido de netstat_ultimoNetStat.log+resultado es mayor o igual a 200
				// escribimos en netstat_ultimoNetStat+1.log
				
				if (i + resultado.size() > 200) {
					lista.size();
					PrintWriter file = new PrintWriter(new FileWriter(RUTA_NET + "\\" + rutaFich, true));
					file.println(getTimeStamp());
					while (i < 198) {
						file.println(resultado.get(j));
						i++;
						j++;
					}
					file.close();
					rutaFich = "\\netstat_" + (getNumeroUltimoNetStatLog() + 1) + ".log";
					file.println(getTimeStamp());
					escribirNetStatSobrante(rutaFich, resultado, j);
				} else {
					escribirNetStatLog(rutaFich, resultado);
				}
			}
		} catch (IOException e) {
			String error = "Error, " + e;
			try {
				NetLogger.escribirEnErrLog(error);
			} catch (FileNotFoundException e1) {
				File erroresLog = new File("err.log");
				erroresLog.createNewFile();
			}
		}

	}

}
