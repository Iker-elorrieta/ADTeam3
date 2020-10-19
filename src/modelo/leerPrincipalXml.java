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
	
	public static boolean leerPrincipal() throws SAXException, ParserConfigurationException {
		boolean correcto = false;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			leerXml leerXml = new leerXml();
			SAXParser parser = factory.newSAXParser(); 
			parser.parse(rutaFichero, leerXml); 
			ArrayList<Libro> listaLibros = leerXml.obtenerlibros();
			int numerolibro=0;
			
//			for (Libro libros : listaLibros) {
//				numerolibro++;
//				System.out.println("libro id:" + numerolibro);
//				System.out.println("titulo: " + libros.getTitulo());
//				System.out.println("editorial: " + libros.getEditorial());
//				System.out.println("paginas: " + libros.getPaginas());
//				System.out.println("altura: " + libros.getAltura());
//				System.out.println("notas: " + libros.getNotas());
//				System.out.println("isbn: " + libros.getIsbn());
//				System.out.println("materias: " + libros.getMaterias());
//				System.out.println(" ");
//			}
			
			Metodos.listar(listaLibros);
			
			System.out.println("Numero de libros: " + numerolibro);
			correcto=true;
		} catch (IOException e) {
			System.out.println("error leerPrincipalXml");
			correcto=false;
		}
		return correcto;
	}

}

