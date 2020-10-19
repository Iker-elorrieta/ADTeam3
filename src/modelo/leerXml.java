package modelo;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class leerXml extends DefaultHandler  {
	 

	boolean titulo = false;
	boolean editorial = false;
	boolean paginas = false;
	boolean altura = false;
	boolean notas = false;
	boolean isbn = false;
	boolean materias = false;
	
	private ArrayList<Libro> listalibros= new ArrayList<Libro>();
	Libro libro = new Libro();

	@Override
	public void startElement(String uri, String localName, String Name, Attributes attributos) throws SAXException {
		if (Name.equals("titulo"))
			titulo = true;
		if (Name.equals("editorial"))
			editorial = true;
		if (Name.equals("paginas"))
			paginas = true;
		if (Name.equals("altura"))
			altura = true;
		if (Name.equals("notas"))
			notas = true;
		if (Name.equals("isbn"))
			isbn = true;
		if (Name.equals("materias"))
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
			libro.setIsbn(Integer.parseInt(new String(ch, start, length)));
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
