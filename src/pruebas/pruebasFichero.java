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

class pruebasFichero {

	private File fichero; 
	
	@Test
	void testListarTxt() {
		ArrayList<Libro> lista = new ArrayList<Libro>();
		fichero = new File(pruebaSys("txt"));
		lista = Metodos.cargarLista(fichero, lista);
		boolean result = Metodos.listar(lista);
		assertEquals(true, result);
	}

	@Test
	void testDemoEntrada1() {
		String input = "4 \n 2 \n";
		int min = 1;
		int max = 3;
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		int result=main.Demo.entradaInt(min, max, teclado);
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
		int result=main.Demo.entradaInt(min, max, teclado);
		assertEquals(2, result);
	}

	@Test
	void testDemoConfirmacion1() {
		String input = "a \n s \n ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.confirmacionSN(teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoConfirmacion2() {
		String input = "a \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.confirmacionSN(teclado);
		assertEquals(false, result);
	}
	
	@Test
	void testDemoConfirmacion() {
		String input = "a \n n \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.confirmacionSN(teclado);
		assertEquals(false, result);
	}
	
	@Test
	void testLeerPrincipal() {
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		listaLibro=modelo.leerPrincipalXml.leerPrincipal(listaLibro, pruebaSys("xml"));
		ArrayList<Libro> lista = new ArrayList<Libro>();
		for (int i = 0; i < listaLibro.size() ; i++)
		{
			Libro libro = new Libro("android", "elorrieta", 200, 21, "no", 12352, "fundamentos");
			lista.add(libro);
		}
		int tamañoLista1=listaLibro.size();
		int tamañoLista2=lista.size();
		assertEquals(tamañoLista1, tamañoLista2);
	}
	
	@Test
	void testDemoMenu1() {
		String input = "1 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu2() {
		String input = "2 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu3() {
		String input = "3 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testWin() {
		boolean result=Metodos.isWindows();
		assertEquals(true, result);
	}
	
	@Test
	void testUnix() {
		boolean result=Metodos.isUnix();
		assertEquals(false, result);
	}
	
	@Test
	void testDemoMenuTxt() {
		String input = "1 \n 1 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		int opcion=1;
		boolean result=main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenuTxt2() {
		String input = "Esta vez si \n Venga \n A que va \n Ya te digo \n 3,3 \n 3  \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		int opcion=2;
		boolean result=main.Demo.menuTxt(opcion, teclado);
		assertEquals(true, result);
	}

	@Test
	void testDemoMenuXml() {
		String input = "4 ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		int opcion=4;
		boolean result=main.Demo.menuXml(opcion, teclado);
		assertEquals(false, result);
	}

	@Test
	void testLibro() {
		Libro libro = new Libro();
		
		String libros =libro.mostrar();
		
		String result = "Titulo: " + libro.getTitulo() + "\n" +
				"Editorial: " + libro.getEditorial() + "\n" +
				"Paginas: " + libro.getPaginas() + "\n" +
				"Altura: " + libro.getAltura() + "\n" +
				"Notas: " + libro.getNotas() + "\n" + 
				"Isbn: " + libro.getIsbn() + "\n" +
				"Materias: " + libro.getMaterias();
		assertEquals(libros, result);
	}
	
	@Test
	void testCrearCsv() {
		String input = "Esta vez si \n Venga \n A que va \n Ya te digo \n 3,3 \n 3  \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=modelo.ficheroCsv.crearArchivoCSV(teclado);
		assertEquals(false, result);
	}

	@Test
	void testDemoMenuCsv() {
		String input = "Esta vez si \n Venga \n A que va \n Ya te digo \n 3,3 \n 3  \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		int opcion=2;
		boolean result=main.Demo.menuCsv(opcion, teclado);
		assertEquals(true, result);
	}
	@Test
	void testCrearXml() {
		String input = "Esta vez si \n Venga \n A que va \n Ya te digo \n 3,3 \n 3  \n 123151 \n n  ";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=modelo.crearLibroXml.crearLibro(teclado);
		assertEquals(true, result);
	}

	String pruebaSys(String tipo)
	{
		 String urlTxt = "";
		 String urlXml = "";
		 String urlCsv = "";
		 
		String sistema = System.getProperty("os.name").toLowerCase();
		
		if(sistema.indexOf("win") >= 0)
		{
			urlTxt = ".\\Ficheros\\Fichero1.txt";
			urlXml = ".\\Ficheros\\libreria.xml";
			urlCsv = ".\\Ficheros\\fichero.csv";
		}
		else if (sistema.indexOf("nix") >= 0 || sistema.indexOf("nux") >= 0 || sistema.indexOf("aix") > 0 )
		{
			urlTxt = "./Ficheros/Fichero1.txt";  
			urlXml = "./Ficheros/libreria.xml";
			urlCsv = "./Ficheros/Fichero3.csv";
		}

		
		switch (tipo) 
		{
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
