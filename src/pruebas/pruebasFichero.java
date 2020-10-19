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
	void testCrearXml() {
		boolean result = modelo.crearXml.crearXml(Variables.listaLibros);
		assertEquals(true, result);
	}

	// metodo leer xml
	@Test
	void testLeerXml() {
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		boolean result = leerPrincipalXml.leerPrincipal(listaLibro);
		assertEquals(true, result); 		
	}

	// metodo Txt
	@Test
	void testEscribirTxt() throws IOException {
		boolean result = Metodos.escribir(Variables.listaLibros);
		assertEquals(true, result);
	}
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
	
	
	


}
