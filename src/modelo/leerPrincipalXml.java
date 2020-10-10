package modelo;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;


public class leerPrincipalXml {
	static String rutaFichero =Variables.urlXml;
	public static boolean leerPrincipal() {
		boolean correcto = false;
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			leerXml leerXml = new leerXml();
			SAXParser parser = factory.newSAXParser();
			parser.parse(rutaFichero, leerXml);
			ArrayList<Libro> listaLibros = leerXml.obtenerlibros();
			int numerolibro=0;
			
			for (Libro libros : listaLibros) {
				numerolibro++;
				System.out.println("libro:" + numerolibro);
				System.out.println("titulo: " + libros.getTitulo());
				System.out.println("editorial: " + libros.getEditorial());
				System.out.println("paginas: " + libros.getPaginas());
				System.out.println("altura: " + libros.getAltura());
				System.out.println("notas: " + libros.getNotas());
				System.out.println("isbn: " + libros.getIsbn());
				System.out.println("materias: " + libros.getMaterias());
				System.out.println(" ");

			}
			System.out.println("Numero de libros: " + numerolibro);
			correcto=true;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			correcto=false;
		} catch (IOException e) {
			e.printStackTrace();
			correcto=false;
		} catch (SAXException e) {
			e.printStackTrace();
			correcto=false;
		}
		return correcto;
	}

}

