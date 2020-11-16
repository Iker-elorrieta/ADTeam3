package modelo;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Clase que lee el Xml del Archivo 
 *
 */
public class LeerXml extends DefaultHandler  {
	
	boolean titulo = false;
	boolean editorial = false;
	boolean paginas = false;
	boolean altura = false;
	boolean notas = false;
	boolean isbn = false;
	boolean materias = false;
	final String parametroTitulo = "titulo",	 parametroEditorial = "editorial", 
				 parametroPaginas = "paginas",	 parametroAltura = "altura", 
				 parametroNotas = "notas",		 parametroIsbn = "isbn", 
				 parametroMaterias = "materias";

	private ArrayList<Libro> listalibros= new ArrayList<Libro>();
	Libro libro = new Libro();

	@Override
	public void startElement(String uri, String localName, String Name, Attributes attributos) throws SAXException {
		if (Name.equals(parametroTitulo))
			titulo = true;
		if (Name.equals(parametroEditorial))
			editorial = true;
		if (Name.equals(parametroPaginas))
			paginas = true;
		if (Name.equals(parametroAltura))
			altura = true;
		if (Name.equals(parametroNotas))
			notas = true;
		if (Name.equals(parametroIsbn))
			isbn = true;
		if (Name.equals(parametroMaterias))
			materias = true;
	}

	public ArrayList<Libro> obtenerlibros() {
		return listalibros;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(titulo){
        	libro.setTitulo(new String(ch, start, length));
        	titulo = false;
        }
		if(editorial){
			libro.setEditorial(new String(ch, start, length));
			editorial = false;
        }
		if(paginas){
        	libro.setPaginas(Integer.parseInt(new String(ch, start, length)));
        	paginas = false;
        }
		if(altura){
        	libro.setAltura(Double.parseDouble(new String(ch, start, length)));
        	altura = false;
        }
		if (notas) {
			libro.setNotas(new String(ch, start, length));
			notas=false;
		}
		if (isbn) {			
			libro.setIsbn(new String(ch, start, length));
			isbn=false;
		}
		if (materias) {			
			libro.setMaterias(new String(ch, start, length));
			materias=false;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("libro")){
			listalibros.add(libro);
			libro = new Libro();
    	}
	}
	
}
