//package modelo;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.parsers.SAXParser;
//import javax.xml.parsers.SAXParserFactory;
//import javax.xml.transform.OutputKeys;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NamedNodeMap;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//public class modificarXml {
//
//	static String rutaFichero = Variables.urlXml;
//
//	public static boolean modXml() {
//		boolean correcto =false;
//		Scanner sc = new Scanner(System.in);
//		try {
//			int opcion;
//			String idLibro = null, atributo = null, texto = null;
//			System.out.println("¿Que desea modificar?");
//			System.out.println("1-titulo");
//			System.out.println("2-editorial");
//			System.out.println("3-paginas");
//			System.out.println("4-altura");
//			System.out.println("5-notas");
//			System.out.println("6-isbn");
//			System.out.println("7-materias");
//			opcion = sc.nextInt();
//			//leer xml
//			SAXParserFactory factory = SAXParserFactory.newInstance();
//			leerXml leerXml = new leerXml();
//			SAXParser parser = factory.newSAXParser();
//			parser.parse(rutaFichero, leerXml);
//
//			switch (opcion) {
//			case 1:
//				atributo = "titulo";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//			case 2:
//				atributo = "editorial";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//			case 3:
//				atributo = "paginas";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//			case 4:
//				atributo = "altura";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//			case 5:
//				atributo = "notas";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//			case 6:
//				atributo = "isbn";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//			case 7:
//				atributo = "materias";
//				System.out.println("ingrese el id del libro");
//				idLibro = sc.next();
//				System.out.println("ingrese el nuevo texto");
//				texto = sc.next();
//				break;
//
//			}
//
//			File xmlFile = new File(rutaFichero);
//			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder dBuilder;
//
//			dBuilder = dbFactory.newDocumentBuilder();
//			Document doc = dBuilder.parse(xmlFile);
//			doc.getDocumentElement().normalize();
//			// update Element value
//			if (modValor(doc, idLibro, atributo, texto)) {
//				
//				// write the updated document to file or console
//				doc.getDocumentElement().normalize();
//				TransformerFactory transformerFactory = TransformerFactory.newInstance();
//				Transformer transformer = transformerFactory.newTransformer();
//				DOMSource source = new DOMSource(doc);
//				StreamResult result = new StreamResult(new File(rutaFichero));
//				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//				transformer.transform(source, result);
//				System.out.println("XML file updated successfully");
//				correcto =true;
//			}else {
//				System.out.println("error metodo modValor");
//				correcto=false;
//			}	
//		} catch (Exception e) {
//			System.out.println("error modXml" + e);
//			correcto =false;
//		}
//		return correcto;
//	}
//
//	private static boolean modValor(Document doc, String idLibro, String atrib, String texto) {
//		boolean correcto=false;
//		try {
//			NodeList libro = doc.getElementsByTagName("libro");
//			Element emp = null;
//			// loop for each libro
//			for (int i = 0; i < libro.getLength(); i++) {
//				emp = (Element) libro.item(i);
//				Node name = emp.getElementsByTagName(atrib).item(0).getFirstChild();
//				if (emp.getAttribute("id").equals(idLibro)) {
//					name.setNodeValue(texto);
//				}
//			}
//			correcto=true;
//		} catch (Exception e) {
//			System.out.println("error updateElementoValue" + e);
//			correcto=false;
//		}
//		return correcto;	
//	}
//
//}
