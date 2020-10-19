package modelo;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class leerPrincipalXml {
	static String rutaFichero =Variables.Xml;
	
	public static boolean leerPrincipal() throws SAXException, ParserConfigurationException {
		boolean correcto = false;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			leerXml leerXml = new leerXml();
			SAXParser parser = factory.newSAXParser(); 
			parser.parse(rutaFichero, leerXml); 
			Variables.listaLibrerias[0] = leerXml.obtenerlibros();
			int numerolibro=0;
						
			System.out.println("Numero de libros: " + numerolibro);
			correcto=true;
		} catch (IOException e) {
			System.out.println("error leerPrincipalXml");
			correcto=false;
		}
		return correcto;
	}

}

