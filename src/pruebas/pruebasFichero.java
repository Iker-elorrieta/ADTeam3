package pruebas;


import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import modelo.Metodos;
import modelo.Utilidades;
import modelo.Variables;
import modelo.leerPrincipalXml;

class pruebasFichero {

//	@Test
//	void testLeerXml() throws SAXException,ParserConfigurationException {
//		boolean result = leerPrincipalXml.leerPrincipal();
//		assertEquals(true, result);
//	}
	
//	@Test
//	void testLeerTxt() {
//		boolean result = Metodos.listar();
//		assertEquals(true, result);
//	}
//
//	@Test
//	void testCargarLista() {
//		boolean result = Metodos.cargarLista(Variables.ficheroTxt);
//		assertEquals(true, result);
//	}
//	
//	@Test
//	void testEscribir() {
//		boolean result = Metodos.escribir(Variables.listaLibros);
//		assertEquals(true, result);
//	}
//		
//	@Test
//	void testMenuFicheros() {
//		boolean result = main.Demo.menu();
//		assertEquals(true, result);
//	}
//	
//	@Test
//	void testMenuXml() {
//		boolean result = main.Demo.menuXml(2);
//		assertEquals(true, result);
//	}
//	
	@Test
	void testMenuTxt() {
		boolean result = main.Demo.menuTxt(1);
		assertEquals(true, result);
		
		result = main.Demo.menuTxt(2);
		assertEquals(true, result);
	}
	
	
//	
//	@Test
//	void testCrearXml() {
//		boolean result = modelo.crearXml.crearXml(Variables.listaLibros);
//		assertEquals(true, result);
//	}
//	
//	@Test
//	void testModXml() {
//		boolean result = modelo.modificarXml.modXml();
//		assertEquals(true, result);
//	}
//	
//	
	
	
}
