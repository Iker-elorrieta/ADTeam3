package modelo;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class leerPrincipalXml {	
	

	public static ArrayList<Libro> leerPrincipal(ArrayList<Libro> listaLibros,String rutaFichero)  {

		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			leerXml leerXml = new leerXml();
			SAXParser parser = factory.newSAXParser(); 
			parser.parse(rutaFichero, leerXml); 
			listaLibros = leerXml.obtenerlibros();
		} catch (IOException e) {
			System.out.println("error leerPrincipalXml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		return listaLibros;
		
	}

}

