package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import modelo.Libro;
import modelo.Metodos;
import modelo.Patrones;
import modelo.Variables;
import modelo.leerPrincipalXml;

class pruebasFichero {

	// metodo cargar
	@Test
	void testCargarListas(){
		Variables.urlTxt = ".\\Ficheros\\Fichero1.txt";
		Variables.ficheroTxt = new File(Variables.urlTxt);
		boolean result = Metodos.cargarLista(Variables.ficheroTxt);
		assertEquals(true, result);
	} 

	@Test
	void testCargarListasFalse(){
		File ficheroTest = new File("fichero.txt");
		boolean result = Metodos.cargarLista(ficheroTest);
		assertEquals(false, result);
	}
	// fin metodo cargar

	// metodo crear xml
	
	@Test
	void testListarTxt() {
		boolean result = Metodos.listar(Variables.listaLibros);
		assertEquals(true, result);
	}

	// metodo utilidades
	@Test
	void testUtilidades() {
		boolean result = modelo.Utilidades.validar(Patrones.titulo.getNombre(), "camilo");
		assertEquals(true, result);
	}

	//libro test
	@Test
	void testLibro() {
		Libro libro;
		 String titulo="prueba";
		 String editorial="casa";
		 int paginas=22;
		 double altura=12;
		 String notas="ninguna";
		 int isbn=12356231;
		 String materias="no hay";
		 
		 libro=new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);
	}	
	
	@Test
	void testMostrarLibro() {
		Libro libro;
		 String titulo="prueba";
		 String editorial="casa";
		 int paginas=22;
		 double altura=12;
		 String notas="ninguna";
		 int isbn=12356231;
		 String materias="no hay";
		 
		 libro=new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);
		 
		 libro.mostrar();		 
	}
	
	@Test
	void testDemoMenu1() {
		String input = "1 \n 2 \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu2() {
		String input = "2 \n 1 \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenu3() {
		String input = "3 \n 1 \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
		assertEquals(true, result);
	}
	
	@Test
	void testDemoMenuFalse() {
		String input = "3 \n";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
	    Scanner teclado = new Scanner(System.in); 
		boolean result=main.Demo.menu(teclado);
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
	void testCrearXml() {
		boolean result=modelo.crearXml.crearXml(Variables.listaLibros);
		assertEquals(true, result);
	}
	
	
	
}
