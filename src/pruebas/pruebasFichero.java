package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import modelo.Libro;
import modelo.Metodos;
import modelo.Patrones;
import modelo.Variables;
import modelo.leerPrincipalXml;

class pruebasFichero {

	// metodo cargar
	@Test
	void testCargarListas() throws IOException {
		boolean result = Metodos.cargarLista(Variables.ficheroTxt);
		assertEquals(true, result);
	}

	@Test
	void testCargarListasFalse() throws IOException {
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
	void testLeerXml() throws SAXException, ParserConfigurationException {
		boolean result = leerPrincipalXml.leerPrincipal();
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
	// metodo modXml
	@Test
	void testMenuTxtOpcion1() {
		boolean result = main.Demo.menuTxt(1);
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
	void testMenuXml2()  {
		boolean result = main.Demo.menuXml(2);
		assertEquals(true, result);	 
	}

	@Test
	void testMenuXml5()  {
		boolean result = main.Demo.menuXml(6);
		assertEquals(true, result);	 
	}
	@Test
	void testMenuCsv()  {
		modelo.ficheroCsv.menuCsv(1); 
	}
	@Test
	void testModxml() throws ParserConfigurationException, SAXException, IOException  {
		
		File xmlFile = new File(Variables.urlXml);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFile);
		doc.getDocumentElement().normalize();
		boolean result = modelo.modificarXml.modValor(doc, "2", "notas", "hola");
		assertEquals(true, result);	 
	}
	
	@Test
	void testDemoMenuCsv1() {
		boolean result = main.Demo.menuCsv(1);;
		assertEquals(true, result);	 
	}
	
	
//	@Test
//	void testMenuXml4()  {
//		boolean result = main.Demo.menuXml(4);
//		assertEquals(true, result);	 
//	}
	
//	@Test
//	void testMenuTxtOpcion3() {
//		boolean result = main.Demo.menuTxt(3);
//		assertEquals(true, result);	 
//	}
	
//	@Test
//	void testMenuCsv4()  {
//		modelo.ficheroCsv.menuCsv(4); 
//	}
	

}
