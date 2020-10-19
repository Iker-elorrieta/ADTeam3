package modelo;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class leerPrincipalXml {	
	public static boolean leerPrincipal(ArrayList<Libro> listaLibros,String rutaFichero) {
		boolean correcto = false;
		try {
			rutaFichero =Variables.Xml; 
			SAXParserFactory factory = SAXParserFactory.newInstance();
			leerXml leerXml = new leerXml();
			SAXParser parser = factory.newSAXParser(); 
			parser.parse(rutaFichero, leerXml); 
			Variables.listaLibrosxml = leerXml.obtenerlibros();
			
			correcto=true;
		} catch (IOException e) {
			System.out.println("error leerPrincipalXml");
			correcto=false;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			correcto=false;
		} catch (SAXException e) {
			e.printStackTrace();
			correcto=false;
		}
		return correcto;
	}

}

