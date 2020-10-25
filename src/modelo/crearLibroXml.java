package modelo;

import java.io.File;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class crearLibroXml {

	public static boolean crearLibro(Scanner teclado) {

		boolean correcto = false;
		
		Libro lLibro = main.Demo.crearLibro(teclado);
		
		try {

			// clases para leer XML
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(Variables.urlXml));

			// preparar el archivo XML para obtener los datos
			doc.getDocumentElement().normalize();
			Node nodoRaiz = doc.getDocumentElement();

			// agregamos una nueva etiqueta al doc
			// primero creamos la etiqueta
			Element nuevoLibro = doc.createElement("libro");
			// creamos sus etiquetas hijas
			Element titulo = doc.createElement("titulo");
			titulo.setTextContent(lLibro.getTitulo());
			// editorial
			Element editorial = doc.createElement("editorial");
			editorial.setTextContent(lLibro.getEditorial());
			// paginas
			Element paginas = doc.createElement("paginas");
			paginas.setTextContent(""+lLibro.getPaginas());
			// altura
			Element altura = doc.createElement("altura");
			altura.setTextContent(""+lLibro.getAltura());
			// notas
			Element notas = doc.createElement("notas");
			notas.setTextContent(lLibro.getNotas());
			// isbn
			Element isbn = doc.createElement("isbn");
			isbn.setTextContent(""+lLibro.getIsbn());
			// materias
			Element materias = doc.createElement("materias");
			materias.setTextContent(lLibro.getMaterias());
			
			nuevoLibro.appendChild(titulo);
			nuevoLibro.appendChild(editorial);
			nuevoLibro.appendChild(paginas);
			nuevoLibro.appendChild(altura);
			nuevoLibro.appendChild(notas);
			nuevoLibro.appendChild(isbn);
			nuevoLibro.appendChild(materias);
			
			nodoRaiz.appendChild(nuevoLibro);
			
			
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer trasnFormer = transFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(Variables.urlXml));
			trasnFormer.transform(source, result);
			correcto = true;
			System.out.println("libro creado correctamente ");
		} catch (Exception e) {
			correcto = false;
			System.out.println("no se ha creado el libro");
		}
		return correcto;
	}

}
