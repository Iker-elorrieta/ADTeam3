package modelo;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CrearXml {

	/**
	 * Metodo que genera Xml base cuando no existe el fichero
	 * @param ruta
	 * @return boolean 
	 */
	public static boolean generateXml(String ruta) {
		boolean correc = false;
		String name = "libreria";

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, name, null);
			document.setXmlVersion("1.0");
			Libro lLibro = new Libro();
			lLibro.setTitulo("base");
			lLibro.setEditorial("base");
			lLibro.setNotas("base");
			lLibro.setMaterias("base");
			lLibro.setAltura((double) 0);
			lLibro.setPaginas(0);
			lLibro.setIsbn(0);

			// Main Node
			Element raiz = document.getDocumentElement();
			// agregamos una nueva etiqueta al doc
			// primero creamos la etiqueta
			Element nuevoLibro = document.createElement("libro");
			// creamos sus etiquetas hijas
			Element titulo = document.createElement("titulo");
			titulo.setTextContent(lLibro.getTitulo());
			// editorial
			Element editorial = document.createElement("editorial");
			editorial.setTextContent(lLibro.getEditorial());
			// paginas
			Element paginas = document.createElement("paginas");
			paginas.setTextContent("" + lLibro.getPaginas());
			// altura
			Element altura = document.createElement("altura");
			altura.setTextContent("" + lLibro.getAltura());
			// notas
			Element notas = document.createElement("notas");
			notas.setTextContent(lLibro.getNotas());
			// isbn
			Element isbn = document.createElement("isbn");
			isbn.setTextContent("" + lLibro.getIsbn());
			// materias
			Element materias = document.createElement("materias");
			materias.setTextContent(lLibro.getMaterias());

			nuevoLibro.appendChild(titulo);
			nuevoLibro.appendChild(editorial);
			nuevoLibro.appendChild(paginas);
			nuevoLibro.appendChild(altura);
			nuevoLibro.appendChild(notas);
			nuevoLibro.appendChild(isbn);
			nuevoLibro.appendChild(materias);

			raiz.appendChild(nuevoLibro);
			// Generate XML
			Source source = new DOMSource(document);
			// Indicamos donde lo queremos almacenar
			Result result = new StreamResult(new File(ruta)); // nombre del archivo
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			correc = true;
		} catch (Exception e) {
			System.out.println("error crear libroxml");
			correc = false;
		}

		return correc;
	}
}
