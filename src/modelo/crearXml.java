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

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;


public class crearXml {
	
	public static boolean crearXml(ArrayList<Libro> listaLibros) {
		int numeroId=0;
		boolean correcto =false;
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
				numeroId++;
				// Creo los elementos
				Element libreria = documento.createElement("libreria");
				Element libro = documento.createElement("libro");
				// atributo para el nodo libro
				Attr attr = documento.createAttribute("id");
				attr.setValue(String.valueOf(numeroId));
				libro.setAttributeNode(attr);
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
				

				libreria.appendChild(libro);

				documento.getDocumentElement().appendChild(libro);
			}

			Source source = new DOMSource(documento);
			// ruta donde se crea
			Result result = new StreamResult(new File(Variables.Xml));
			// crea el fichero XML
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			correcto = true;
		} catch (ParserConfigurationException ex) {
			System.out.println(ex.getMessage());
			correcto=false;
		}catch (TransformerException ex) {
			System.out.println(ex.getMessage());
			correcto=false;
		}
		return	correcto;
	}

}
