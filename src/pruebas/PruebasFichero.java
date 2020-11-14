package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import modelo.Libro;
import modelo.Metodos;
import modelo.Variables;

/**
 * Clase de pruebas del programa
 */
class PruebasFichero {

	private File fichero;

	// metodos demo
	@Test
	void testDemoComprobacionDouble() {
		String input = "s \n 2 \n 22";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		double result = main.Demo.comprobacionDatoDouble(teclado);
		assertEquals(2, result);
	}

	@Test
	void testDemoComprobacionEntero() {
		String input = "s \n 2 \n 22";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		double result = main.Demo.comprobacionDatoInt(teclado);
		assertEquals(2, result);
	}

	@Test
	void testInicioPrograma() {
		String input = "1 \n 1 \n n \n ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.inicioPrograma(teclado);
		assertEquals(true, result);
	}

	@Test
	void testInicioProgramaFalse() {
		String input = "s n \n ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.inicioPrograma(teclado);
		assertEquals(false, result);
	}

	@Test
	void testDemoEntrada1() {
		String input = "4 \n 2 \n";
		int min = 1;
		int max = 3;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int result = main.Demo.entradaInt(min, max, teclado);
		assertEquals(2, result);
	}

	@Test
	void testDemoEntrada2() {
		String input = "a \n 2 \n";
		int min = 1;
		int max = 3;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int result = main.Demo.entradaInt(min, max, teclado);
		assertEquals(2, result);
	}

	@Test
	void testDemoConfirmacion1() {
		String input = "a \n s \n ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.confirmacionSN(teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoConfirmacion2() {
		String input = "132 \n a \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.confirmacionSN(teclado);
		assertEquals(false, result);
	}

	@Test
	void testDemoMenu1() {
		String input = "1 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenu2() {
		String input = "2 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenu3() {
		String input = "3 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}

	@Test
	void testWin() {
		boolean result = Metodos.isWindows();
		assertEquals(true, result);
	}

	@Test
	void testUnix() {
		boolean result = Metodos.isUnix();
		assertEquals(false, result);
	}

	// test txt
	@Test
	void testListarTxt() {
		ArrayList<Libro> lista = new ArrayList<Libro>();
		fichero = new File(pruebaSys("txt"));
		lista = Metodos.cargarLista(fichero, lista);
		boolean result = Metodos.listar(lista);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuTxt() {
		String input = "1 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 1;
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuTxt2() {
		String input = ".  \n El avion de los suenos \n . \n DreamWork \n $ \n pegi 8 \n . \n Educativo \n 3,3 \n 5 \n 5 \n a \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 2;
		Variables.urlTxt= pruebaSys("txt");
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuTxt3() {
		String input = "fichero";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 3;
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuTxt4() {
		String input = "fichero";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}
	
	// test xml
	@Test
	void testDemoMenuXml4() {
		String input = "libreria";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuXml(opcion, teclado);
		assertEquals(false, result);
	}

	@Test
	void testDemoMenuXml2() {
		String input = ".  \n El avion de los suenos \n . \n DreamWork \n $ \n pegi 8 \n . \n Educativo \n a \n 3,3 \n 5 \n 5 \n a \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 2;
		Variables.urlXml = pruebaSys("xml");
		boolean result = main.Demo.menuXml(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuXml3() {
		String input = "libreria";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 3;
		boolean result = main.Demo.menuXml(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuXmlFalse() {
		String input = "35";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 5;
		boolean result = main.Demo.menuXml(opcion, teclado);
		assertEquals(false, result);
	}

	@Test
	void testLeerPrincipal() {
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		listaLibro = modelo.LeerPrincipalXml.leerPrincipal(listaLibro, pruebaSys("xml"));
		ArrayList<Libro> lista = new ArrayList<Libro>();
		for (int i = 0; i < listaLibro.size(); i++) {
			Libro libro = new Libro("android", "elorrieta", 200, 21, "no", 12352, "fundamentos");
			lista.add(libro);
		}
		int tamLista1 = listaLibro.size();
		int tamLista2 = lista.size();
		assertEquals(tamLista1, tamLista2);
	}

	@Test
	void testCrearXml() {
		String input = ".  \n El avion de los suenos \n . \n DreamWork \n $ \n pegi 8 \n . \n Educativo \n a \n 3,3 \n 5 \n 5 \n a \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		Variables.urlXml = pruebaSys("xml");
		boolean result = modelo.CrearLibroXml.crearLibro(teclado, pruebaSys("xml"));
		assertEquals(false, result);
	}

	@Test
	void testCrearXmlBase() {
		boolean result = modelo.CrearXml.generateXml(pruebaSys("xml"));
		assertEquals(true, result);
	}

	@Test
	void testCrearXmlBaseFalse() {
		boolean result = modelo.CrearXml.generateXml(pruebaSys("ml"));
		assertEquals(false, result);
	}

	@Test
	void testEliminarFichero() {
		String input = "prueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Metodos.eliminarFichero(teclado, ".xml");
		assertEquals(false, result);
	}

	// test Libro
	@Test
	void testLibro() {
		Libro libro = new Libro();

		String libros = libro.mostrar();

		String result = "Titulo: " + libro.getTitulo() + "\n" + "Editorial: " + libro.getEditorial() + "\n"
				+ "Paginas: " + libro.getPaginas() + "\n" + "Altura: " + libro.getAltura() + "\n" + "Notas: "
				+ libro.getNotas() + "\n" + "Isbn: " + libro.getIsbn() + "\n" + "Materias: " + libro.getMaterias();
		assertEquals(libros, result);
	}

	// test csv
	@Test
	void testCrearCsv() {
		String input = ".  \n El avion de los suenos \n . \n DreamWork \n $ \n pegi 8 \n . \n Educativo \n a \n 3,3 \n 5 \n 5 \n a \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		Variables.urlCsv = pruebaSys("csv");
		boolean result = modelo.FicheroCsv.crearArchivoCSV(teclado);
		assertEquals(false, result);
	}

	@Test
	void testDemoMenuCsv3() {
		String input = "fichero";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 3;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuCsv() {
		String input = ".  \n El avion de los suenos \n . \n DreamWork \n $ \n pegi 8 \n . \n Educativo \n a \n 3,3 \n 5 \n 5 \n a \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 2;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuCsv4() {
		String input = "ficheroCg";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuCsvFalse() {
		String input = "ficheroC";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(false, result);
	}

	@Test
	void testCargarCsv() {
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		String input = "Esta vez si \n Venga \n A que va \n Ya te digo \n 3,3 \n 3  \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		listaLibro = modelo.FicheroCsv.cargarCsv(teclado);
		ArrayList<Libro> lista = new ArrayList<Libro>();
		for (int i = 0; i < listaLibro.size(); i++) {
			Libro libro = new Libro("android", "elorrieta", 200, 21, "no", 12352, "fundamentos");
			lista.add(libro);
		}
		int tamLista1 = listaLibro.size();
		int tamLista2 = lista.size();
		assertEquals(tamLista1, tamLista2);
	}

	@Test
	void testEliminar() {
		String input = "ficheroCg";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = modelo.Metodos.eliminarFichero(teclado, ".csv");
		assertEquals(true, result);
	}

	// test de validacion
	@Test
	void testValidacion() {
		String patron = "[a-z0-9\\.\\s]{1,100}";
		String dato = "Esta vez si.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}

	@Test
	void testValidacionFalse() {
		String patron = "[a-z0-9\\.\\s]{1,100}";
		String dato = "";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void testValidacionFalse2() {
		String patron = "[a-z0-9\\.\\s]{1,2} \n  ";
		String dato = "asfgas,gfas-gasg \n 12412";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void testValidacionFalse3() {
		String patron = "[0-9]{1,4}";
		String dato = "ads \n 125734";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void testValidacionFals() {
		String patron = "[0-9]{4,1}";
		String dato = "";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void testValidacionFa() {
		String patron = "[9-0]{1,4}";
		String dato = "";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void testValidaFa() {
		String patron = "[a-z]{1,100}";
		String dato = "4211.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	String pruebaSys(String tipo) {
		String urlTxt = "";
		String urlXml = "";
		String urlCsv = "";

		String sistema = System.getProperty("os.name").toLowerCase();

		if (sistema.indexOf("win") >= 0) {
			urlTxt = ".\\Ficheros\\Fichero1.txt";
			urlXml = ".\\Ficheros\\libreria.xml";
			urlCsv = ".\\Ficheros\\fichero.csv";
		} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0 || sistema.indexOf("aix") > 0) {
			urlTxt = "./Ficheros/Fichero1.txt";
			urlXml = "./Ficheros/libreria.xml";
			urlCsv = "./Ficheros/Fichero3.csv";
		}

		switch (tipo) {
		case "txt":
			return urlTxt;

		case "csv":
			return urlCsv;

		case "xml":
			return urlXml;
		}
		return "";

	}

}
