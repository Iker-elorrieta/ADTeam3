package modelo;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class crearXml {
	
	public static void crearXml(ArrayList<Libro> listaLibros) {

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();

			// Creo un documento con un elemento raiz
			Document documento = implementation.createDocument(null, "Libreria", null);
			documento.setXmlVersion("1.0");
			documento.getDocumentElement().setAttributeNS("http://www.w3.org/2001/XMLSchema-instance",
					"xsi:noNamespaceSchemaLocation", "my.xsd");
			
			
			for (Libro lLibro : listaLibros) {

				// Creo los elementos
				Element libreria = documento.createElement("libreria");
				Element libro = documento.createElement("libro");
				// titulo
				Element titulo = documento.createElement("titulo");
				Text textTitulo = documento.createTextNode(lLibro.getTitulo());
				titulo.appendChild(textTitulo);
				libro.appendChild(titulo);
				// editorial
				Element editorial = documento.createElement("editorial");
				Text textEditorial = documento.createTextNode(lLibro.getEditorial());
				editorial.appendChild(textEditorial);
				libro.appendChild(editorial);
				// paginas
				Element paginas = documento.createElement("paginas");
				Text textPaginas = documento.createTextNode(lLibro.getPaginas() + "");
				paginas.appendChild(textPaginas);
				libro.appendChild(paginas);
				// altura
				Element altura = documento.createElement("altura");
				Text textAltura = documento.createTextNode(lLibro.getAltura() + "");
				altura.appendChild(textAltura);
				libro.appendChild(altura);
				// notas
				Element notas = documento.createElement("notas");
				Text textNotas = documento.createTextNode(lLibro.getNotas());
				notas.appendChild(textNotas);
				libro.appendChild(notas);
				// isbn
				Element isbn = documento.createElement("isbn");
				Text textIsbn = documento.createTextNode(lLibro.getIsbn() + "");
				isbn.appendChild(textIsbn);
				libro.appendChild(isbn);
				// materias
				Element materias = documento.createElement("materias");
				Text textMaterias = documento.createTextNode(lLibro.getMaterias());
				materias.appendChild(textMaterias);
				libro.appendChild(materias);

				// Añado al elemento coches el elemento coche
				libreria.appendChild(libro);

				// A�ado al root el elemento coches
				documento.getDocumentElement().appendChild(libreria);
			}
			// Asocio el source con el Document
			Source source = new DOMSource(documento);
			// Creo el Result, indicado que fichero se va a crear
			Result result = new StreamResult(new File(".\\Ficheros\\libreria.xml"));

			// Creo un transformer, se crea el fichero XML
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);

		} catch (ParserConfigurationException | TransformerException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
