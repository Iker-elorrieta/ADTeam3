package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import main.Demo;
import modelo.Libro;
import modelo.Metodos;
import modelo.Variables;

/**
 * Clase de pruebas del programa
 */
class PruebasFichero {

	// Test Metodos sin requeridos
	@Test
	void testDemoComprobacionDouble() {
		String input = "s\n 2 \n 22\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		double result = Metodos.comprobacionDatoDouble(teclado);
		assertEquals(2, result);
	}

	@Test
	void testDemoComprobacionEntero() {
		String input = "s\n 2 \n 22 \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		double result = Metodos.comprobacionDatoInt(teclado);
		assertEquals(2, result);
	}
	
	@Test
	void testDemoEntrada1() {
		String input = "4 \n 2 \n";
		int min = 1;
		int max = 3;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int result = Metodos.entradaInt(min, max, teclado);
		assertEquals(2, result);
	}
	
	@Test
	void testDemoEntrada2() {
		String input = "a\n 2 \n";
		int min = 1;
		int max = 3;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int result = Metodos.entradaInt(min, max, teclado);
		assertEquals(2, result);
	}
	
	@Test
	void testDemoConfirmacion1() {
		String input = "a\ns\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Metodos.confirmacionSN(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoConfirmacion2() {
		String input = "132 \na\nn\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Metodos.confirmacionSN(teclado);
		assertEquals(false, result);
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
	
	// test de validacion
	@Test
	void validaCaracterEspecialRango() {
		String patron = "[%-z]{1,100}";
		String dato = "a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaFaltaBarraRango() {
		String patron = "[az]{1,100}";
		String dato = "a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaFaltaBarraSegundoRango() {
		String patron = "[a-z90]{1,100}";
		String dato = "a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaEspacioSinAsignar() {
		String patron = "[a-z9-0]{1,100}";
		String dato = "hol a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaDemasiadosPuntos() {
		String patron = "[a-z9-0\\.]{1,100}";
		String dato = "solo.puede.poner.un.punto";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaEspacioSinAsignarLetras() {
		String patron = "[a-z]{1,100}";
		String dato = "hol a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaEspacioSinAsignarNumeros() {
		String patron = "[0-9]{1,100}";
		String dato = "hol a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaPuntosSinAsignarNumeros() {
		String patron = "[0-9]{1,100}";
		String dato = "hol.a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaPuntosSinAsignarLetras() {
		String patron = "[0-9]{1,100}";
		String dato = "hol.a";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaDatoCompleto() {
		String patron = "[a-z0-9\\s\\.]{1,100}";
		String dato = "Esta vez si.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}

	@Test
	void validaEspacioBlanco() {
		String patron = "[a-z0-9\\s\\.]{1,100}";
		String dato = "";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void validaLongitudNumeroLetra() {
		String patron = "[a-z9-0\\.\\s]{1,2} \n";
		String dato = "asfgas,gfas-gasg \n 12412";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarLetraNumeroSinEspacio() {
		String patron = "[z-a9-0\\.]{1,50}";
		String dato = "ab ce.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarLetraNumeroLongitud() {
		String patron = "[a-z0-9]{1,50}";
		String dato = "1234";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}
	
	@Test
	void validarLetraNumerosFueraRango() {
		String patron = "[a-b0-9]{50,1}";
		String dato = "c";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarLetraFueraRango() {
		String patron = "[a-b]{50,1}";
		String dato = "c";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarNumeroLetraFueraRango() {
		String patron = "[a-b0-9]{50,1}";
		String dato = "c";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void validaLongitudNumero() {
		String patron = "[0-9]{1,4}";
		String dato = "ads \n 125734";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void validaNumeroBlanco() {
		String patron = "[9-0]{4,1}";
		String dato = "";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}

	@Test
	void validaNumero() {
		String patron = "[9-0]{1,4}";
		String dato = "234";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}

	@Test
	void validaLetraIncorrecta() {
		String patron = "[a-z]{1,50}";
		String dato = "4211.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validaLetraIncorrectaPunto() {
		String patron = "[a-z]{1,50}";
		String dato = "4211";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarLetraEspacioPunto() {
		String patron = "[z-a\\s\\.]{1,50}";
		String dato = "ab ce.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}
	
	@Test
	void validarLetraNumeroEspacioPunto() {
		String patron = "[z-a9-0\\s\\.]{1,50}";
		String dato = "ab ce.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}
	
	@Test
	void validarLetraSinEspacioPunto() {
		String patron = "[z-a]{1,50}";
		String dato = "ab ce.";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarNumeroFueraRango() {
		String patron = "[4-5]{1,50}";
		String dato = "7";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarLetraNumeroFueraRango() {
		String patron = "[a-z4-5]{1,50}";
		String dato = "7";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarNumeroIsbnCorrecto() {
		String patron = "[0-9\\-]{17,17}";
		String dato = "123-456-789-111-1";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(true, result);
	}
	
	@Test
	void validarNumeroIsbnSinBarra() {
		String patron = "[0-9]{17,17}";
		String dato = "123-456-789-111-1";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	@Test
	void validarNumeroIsbnAcabarEnBarra() {
		String patron = "[0-9\\-]{1,30}";
		String dato = "123-456-789-111-1-";
		boolean result = modelo.Metodos.validacion(patron, dato);
		assertEquals(false, result);
	}
	
	// ------------- Test Programa -----------
	
	// crear ficheros
	
	@Test
	void ficheroSinEncontrarMenu() {
		String input = "s \n 1 \n 1 \nn\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	// Prueba crear fichero xml
	@Test
	void testDemoMenuXml4() {
		String input = "ficheroPrueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		String guardarUrl = Variables.urlXml;
		if(pruebaSys("xml").contains("\\"))
			Variables.urlXml = pruebaSys("xml").substring(0,pruebaSys("xml").indexOf("\\"));
		else
			Variables.urlXml = pruebaSys("xml").substring(0,pruebaSys("xml").indexOf("/"));
		boolean result = Metodos.generateXml(pruebaSys(Variables.urlXml));
		Variables.urlXml = guardarUrl;
		result = main.Demo.menuXml(opcion, teclado);
		assertEquals(false, result);
	}
	
	// Prueba crear fichero xml
		@Test
		void testDemoMenuXmlFicheroExistente() {
			String input = "ficheroPrueba";
			InputStream in = new ByteArrayInputStream(input.getBytes());
			System.setIn(in);
			Scanner teclado = new Scanner(System.in);
			int opcion = 4;
			boolean result = main.Demo.menuXml(opcion, teclado);
			assertEquals(false, result);
		}
	
	// Prueba crear fichero txt
	@Test
	void testDemoMenuTxt4() {
		String input = "ficheroPrueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}
	
	// Prueba crear fichero csv
	@Test
	void testDemoMenuCsv4() {
		String input = "ficheroPrueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		if(pruebaSys("csv").contains("\\"))
			Variables.urlCsv = pruebaSys("csv").substring(0,pruebaSys("csv").indexOf("\\"));
		else
			Variables.urlCsv = pruebaSys("csv").substring(0,pruebaSys("csv").indexOf("/"));
		boolean result = Metodos.crearArchivoCSV(teclado);
		
		Variables.urlCsv = pruebaSys("csv");
		
		result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}
	
	//una vez creados los rellenamos
	
	@Test
	void testDemoMenuXml2() {
		String input = ". \nEl avion de los suenos\n . \nDreamWork\n $ \npegi 8\n . \nEducativo\n3.3\n5 \n123-456-789-111-1\n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 2;
		Variables.urlXml = pruebaSys("xml");
		Variables.ficheroXml = new File(Variables.urlXml);
		boolean result = main.Demo.menuXml(opcion, teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenuTxt2() {
		String input = ". \nEl avion de los suenos\n . \nDreamWork\n $ \npegi 8\n . \nEducativo\n3.3\n5 \n123-456-789-111-1\n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 2;
		
		Variables.urlTxt = pruebaSys("txt");
		Variables.ficheroTxt = new File(Variables.urlTxt);
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenuCsv() {
		String input = ". \nEl avion de los suenos\n . \nDreamWork\n $ \npegi 8\n . \nEducativo\n3.3\n5 \n123-456-789-111-1\n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 2;
		Variables.urlCsv = pruebaSys("csv");
		Variables.ficheroCsv = new File(Variables.urlCsv);
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testCargarCsv() {
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		String input = ". \nEl avion de los suenos\n . \nDreamWork\n $ \npegi 8\n . \nEducativo\n3.3\n5 \n123-456-789-111-1\n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		Variables.urlCsv = pruebaSys("csv");
		listaLibro = Metodos.cargarCsv(teclado);
		ArrayList<Libro> lista = new ArrayList<Libro>();
		for (int i = 0; i < listaLibro.size(); i++) {
			Libro libro = new Libro("El avion de los suenos", "DreamWork", 5, 3.3, "pegi 8", "123-456-789-111-1", "Educativo");
			lista.add(libro);
		}
		int tamLista1 = listaLibro.size();
		int tamLista2 = lista.size();
		assertEquals(tamLista1, tamLista2);
	}
	
	// despues de rellenarlos comprobamos si se muestran
	@Test
	void inicio() {
		String input = "s \n 1 \n 1 \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.inicioPrograma(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu1() {
		String input = "s \n 1 \n 1 \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		Variables.urlXml = pruebaSys("xml");
		boolean result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu2() {
		String input = "s \n 2 \n 1 \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		if(pruebaSys("txt").contains("\\"))
			Variables.urlTxt = pruebaSys("txt").substring(0,pruebaSys("txt").indexOf("\\"));
		else
			Variables.urlTxt = pruebaSys("txt").substring(0,pruebaSys("txt").indexOf("/"));
		Variables.ficheroTxt = new File(Variables.urlTxt);
		boolean result = main.Demo.menu(teclado);
		
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		teclado = new Scanner(System.in);
		Variables.urlTxt = pruebaSys("txt");
		Variables.ficheroTxt = new File(Variables.urlTxt);
		result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu3() {
		String input = "s \n 3 \n 1 \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		Variables.urlCsv = pruebaSys("csv");
		boolean result = main.Demo.menu(teclado);
		assertEquals(true, result);
	}

	@Test
	void testInicioProgramaFalse() {
		String input = "s\n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = main.Demo.inicioPrograma(teclado);
		assertEquals(false, result);
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
		Variables.urlXml = pruebaSys("xml");
		ArrayList<Libro> listaLibro = Metodos.leerXml(pruebaSys("xml"));
		ArrayList<Libro> lista = new ArrayList<Libro>();
		for (int i = 0; i < listaLibro.size(); i++) {
			Libro libro = new Libro("android", "elorrieta", 200, 21, "no", "123-456-789-111-1", "fundamentos");
			lista.add(libro);
		}
		int tamLista1 = listaLibro.size();
		int tamLista2 = lista.size();
		assertEquals(tamLista1, tamLista2);
	}
	
	//permisos
	@Test
	void testPermisos() {
		String input = "1 \n juan \n ficheroPrueba.txt \n F \n 3";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Metodos.cambioPermiso(teclado);
		assertEquals(false, result);
	}

	@Test
	void testCrearXmlBaseFalse() {
		boolean result = Metodos.generateXml(pruebaSys("ml"));
		assertEquals(false, result);
	}
	
	@Test
	void testDemoMenuCsvParaMover() {
		String input = "muevemePorfa";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenuCsvParaMoverFalso() {
		String input = "ficheroPrueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(false, result);
	}
	
	// mover ficheros
	@Test
	void testMoverFicheroRutaExacta() {
		String input = "n \n 2 \n s \n"+pruebaSys("carpeta")+"\n"+pruebaSys("fichero")+"\n s \n"+pruebaSys("fichero")+"\n"+pruebaSys("carpeta")+"\n s \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenuTxtArchivoNoEncontrado() {
		String input = "moveMePlz";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 4;
		boolean result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testMoverFicheroRutaManualmenteFalse() {
		String input = "n \n n \n s \n 2 \n s \n 7 \n n \n s \n 2 \n 2 \n 2 \n 2 \n s \n 2 \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Demo.moverFichero(teclado);
		assertEquals(false, result);
	}
	
	// mover ficheros
	@Test
	void testMoverFicheroRutaManualmente() {
		String input = "n \n 2 \n n \n n \n s \n 2 \n s \n 7 \n n \n s \n 2 \n 2 \n 2 \n 2 \n s \n 2 \n s \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		boolean result = Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	//mover entre carpetas por consola
	@Test
	void testMoverEntreCarpetas() {
		String input = "1 \n 2 \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		File archivo = new File(pruebaSys("txt").substring(2));
		String ruta = archivo.getAbsolutePath();
		String result = "";
		if(ruta.contains("\\"))
		{
			ruta = ruta.substring(0,ruta.lastIndexOf("\\"));
			ruta = ruta.substring(0,ruta.lastIndexOf("\\"));
			result = Metodos.moverEntreCarpetas(teclado, ruta);
			assertEquals(archivo.getAbsoluteFile().toString().substring(0,archivo.getAbsoluteFile().toString().lastIndexOf("\\")), result);
		}
		else if(ruta.contains("/"))
		{
			ruta = ruta.substring(0,ruta.lastIndexOf("/"));
			ruta = ruta.substring(0,ruta.lastIndexOf("/"));
			result = Metodos.moverEntreCarpetas(teclado, ruta.substring(0,ruta.lastIndexOf("/",ruta.lastIndexOf("/"))));
			assertEquals(archivo.getAbsoluteFile().toString().substring(0,archivo.getAbsoluteFile().toString().lastIndexOf("/")), result);
		}
	}
		
	// Prueba borrar fichero txt
	@Test
	void testDemoMenuTxt3() {
		String input = "abddes";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 3;
		boolean result = main.Demo.menuTxt(opcion, teclado);
		input = "ficheroPrueba";
		in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		opcion = 3;
		result = main.Demo.menuTxt(opcion, teclado);
		assertEquals(false, result);
	}
	
	// Prueba borrar fichero xml
	@Test
	void testDemoMenuXml3() {
		String input = "ficheroPrueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 3;
		boolean result = main.Demo.menuXml(opcion, teclado);
		assertEquals(true, result);
	}
	
	// Prueba borrar fichero csv
	@Test
	void testDemoMenuCsv3() {
		String input = "ficheroPrueba";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		Scanner teclado = new Scanner(System.in);
		int opcion = 3;
		boolean result = main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
//		borrarPruebas();
	}
	
//	void borrarPruebas()
//	{
//		String urlTxt = "";
//		String urlXml = "";
//		String urlCsv = "";
//		String prueba = "";
//		String sistema = System.getProperty("os.name").toLowerCase();
//
//		if (sistema.indexOf("win") >= 0) {
//			urlTxt = ".\\Ficheros\\ficheroPrueba.txt";
//			urlXml = ".\\Ficheros\\ficheroPrueba.xml";
//			urlCsv = ".\\Ficheros\\ficheroPrueba.csv";
//			prueba = ".\\Ficheros\\abcdef.csv";
//		} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0 || sistema.indexOf("aix") > 0) {
//			urlTxt = "./Ficheros/ficheroPrueba.txt";
//			urlXml = "./Ficheros/ficheroPrueba.xml";
//			urlCsv = "./Ficheros/ficheroPrueba.csv";
//			prueba = "./Ficheros/abcdef.csv";
//		}
//		
//		File fichero = new File(urlTxt);
//		fichero.delete();
//		fichero = new File(urlXml);
//		fichero.delete();
//		fichero = new File(urlCsv);
//		fichero.delete();
//		fichero = new File(prueba);
//		fichero.delete();
//	}
	
	//rutas en linux y windows
	String pruebaSys(String tipo) {
		String urlTxt = "";
		String urlXml = "";
		String urlCsv = "";
		String urlMoverFichero = "";
		String urlMoverDirectorio = "";
		String sistema = System.getProperty("os.name").toLowerCase();

		if (sistema.indexOf("win") >= 0) {
			urlTxt = ".\\Ficheros\\ficheroPrueba.txt";
			urlXml = ".\\Ficheros\\ficheroPrueba.xml";
			urlCsv = ".\\Ficheros\\ficheroPrueba.csv";
			urlMoverFichero = "D:\\Steff\\Reto\\ADTeam3\\Ficheros\\muevemePorfa.csv";
			urlMoverDirectorio = "D:\\basura";
		} else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0 || sistema.indexOf("aix") > 0) {
			urlTxt = "./Ficheros/ficheroPrueba.txt";
			urlXml = "./Ficheros/ficheroPrueba.xml";
			urlCsv = "./Ficheros/ficheroPrueba.csv";
			urlMoverFichero = "D:/Steff/Reto/ADTeam3/Ficheros/muevemePorfa.csv";
			urlMoverDirectorio = "D:/basura";
		}

		switch (tipo) {
		case "txt":
			return urlTxt;

		case "csv":
			return urlCsv;

		case "xml":
			return urlXml;
			
		case "fichero":
			return urlMoverFichero;
			
		case "carpeta":
			return urlMoverDirectorio;
		}
		return "";

	}

}
