package modelo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class leerPrincipalXml {
	static String rutaFichero =Variables.Xml;
	
	public static boolean leerPrincipal(ArrayList<Libro> listaLibros) {
		boolean correcto = false;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			leerXml leerXml = new leerXml();
			SAXParser parser = factory.newSAXParser(); 
			parser.parse(rutaFichero, leerXml); 
			listaLibros = leerXml.obtenerlibros();
			
			Metodos.listar(listaLibros);
			
			correcto=true;
		} catch (IOException e) {
			System.out.println("error leerPrincipalXml");
			correcto=false;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		return correcto;
	}

}

