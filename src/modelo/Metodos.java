package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Clase donde se apuntan los metodos que se usan en el programa.
 */
public class Metodos {
	public static final String SEPARATOR = ";";

	/**
	 *  Metodo para rellenar la lista del probrama con los libros apuntados en el
	 * fichero txt.
	 * @param fichero
	 * @param lista
	 * @return ArrayList<Libro>
	 */
	public static ArrayList<Libro> cargarLista(File fichero, ArrayList<Libro> lista)  {
		
		try {
			BufferedReader ficheroR = new BufferedReader(new FileReader(fichero));
			ArrayList<String[]> contenido = new ArrayList<String[]>();
			StringTokenizer token;
			String linea = "";
			while ((linea = ficheroR.readLine()) != null) {
				token = new StringTokenizer(linea, SEPARATOR);
				String[] tokens = new String[token.countTokens()];
				for(int i = 0; token.hasMoreTokens();i++)
				{
					tokens[i] = token.nextToken();
				}
				contenido.add(tokens);
			}

			for (int i = 0; i < contenido.size(); i++) {
				Libro libro = new Libro();
					for (int y = 0; y < contenido.get(i).length; y++) {
						if (y == 0) {
							libro.setTitulo(contenido.get(i)[y]);
						} else if (y == 1) {
							libro.setEditorial(contenido.get(i)[y]);
						} else if (y == 2) {
							libro.setPaginas(Integer.parseInt(contenido.get(i)[y]));
						} else if (y == 3) {
							libro.setAltura(Double.parseDouble(contenido.get(i)[y]));
						} else if (y == 4) {
							libro.setNotas(contenido.get(i)[y]);
						} else if (y == 5) {
							libro.setIsbn(contenido.get(i)[y]);
						} else if (y == 6) {
							libro.setMaterias(contenido.get(i)[y]);
						}
					}
					lista.add(libro);
			}
			if(contenido.size()!=0)
			{
				Variables.posicionNumero = contenido.size() - 1;
			}
			ficheroR.close();
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lista;
	}

	/**
	 * Metodo para insertar en el txt un libro.
	 * @param listaLibros
	 * @return boolean 
	 */
	public static boolean escribir(ArrayList<Libro> listaLibros ) {
		boolean correcto = false;
		try (BufferedWriter fichero = new BufferedWriter(new FileWriter(Variables.urlTxt, true));)
		{
			for (int i = Variables.posicionNumero; i < listaLibros.size(); i++) {
				fichero.write(listaLibros.get(i).getTitulo() + SEPARATOR + listaLibros.get(i).getEditorial() + SEPARATOR
							+ listaLibros.get(i).getPaginas() + SEPARATOR + listaLibros.get(i).getAltura() + SEPARATOR
							+ listaLibros.get(i).getNotas() + SEPARATOR + listaLibros.get(i).getIsbn() + SEPARATOR
							+ listaLibros.get(i).getMaterias() + SEPARATOR);
				fichero.newLine();
			}
			correcto=true;
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
			correcto=false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return correcto;
	}
	
	/**
	 * Metodo para mostrar los libros actualmente en el fichero y el programa.
	 * @param ArrayList listaLibros
	 * @return boolean 
	 */
	public static boolean listar(ArrayList<Libro> listaLibros) {

		boolean correcto = false; 
		String titulo;
		String editorial;
		String paginas;
		String altura;
		String notas;
		String isbn;
		String materias;
		
		try {
			System.out.println(" Titulo" + "\t\t" + " Editorial" + "\t" + " Paginas" + "\t" + "Altura" + "\t\t" + " Notas"
					+ "\t\t" + "Isbn" + "\t\t" + " Materias");
			for (int i = 0; i < listaLibros.size(); i++) {
				titulo = listaLibros.get(i).getTitulo() + "                       ";
				editorial = listaLibros.get(i).getEditorial() + "                 ";
				paginas = listaLibros.get(i).getPaginas() + "                     ";
				altura = listaLibros.get(i).getAltura() + "                       ";
				notas = listaLibros.get(i).getNotas() + "                         ";
				isbn = listaLibros.get(i).getIsbn() + "                           ";
				materias = listaLibros.get(i).getMaterias() + "                   ";

				System.out.println(titulo.substring(0, 10) + "\t" + editorial.substring(0, 10) + "\t "
						+ paginas.substring(0, 3) + "\t\t" + altura.substring(0, 4) + "\t\t" + notas.substring(0, 10)
						+ "\t" + isbn.substring(0, 13) + "\t" + materias.substring(0, 10));
			}
			correcto=true;
		} catch (Exception e) {
			System.out.println("error listar libros");
		}

		return correcto;
	}
	
	/** 
	 * Metodo de comprobacion de So para windows
	 */
	public static boolean isWindows() 
	{
		if(Variables.OS.indexOf("win") >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/** 
	 * Metodo de comprobacion de So para windows
	 */
	public static boolean isUnix() 
	{
		if(Variables.OS.indexOf("nix") >= 0 || Variables.OS.indexOf("nux") >= 0 || Variables.OS.indexOf("aix") > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 *  Metodo que elimina el fichero introducido por el usuario 
	 * @param Scanne teclado
	 * @param String extension
	 * @return boolean 
	 */
	public static boolean eliminarFichero(Scanner teclado,String extension) {
		boolean correcto=false;
			
		String nombreFichero;
		System.out.println("Introduzca nombre del fichero");
		nombreFichero = teclado.nextLine();
		String prefix = "";
		if (Metodos.isWindows())
			prefix = ".\\Ficheros\\";
		else if(Metodos.isUnix())
			prefix = "./Ficheros/";
		String ruta = prefix + nombreFichero + extension;
		File archivo = new File(ruta);
		
		if (archivo.exists()) {
			if (archivo.delete()) {
				System.out.println("El fichero ha sido borrado satisfactoriamente");
				correcto=true;
			}
		}else {
			System.out.println("No existe el fichero");
			correcto=false;
		}

		return correcto;
	}
	
	/**
	 * @param el patron puede tener [] y {}, dentro de [] se tendra que meter tipo de dato y de donde hasta donde
	 * y en {} se tendra que poner la longitud de esta separado el numero de minimo con , del maximo.
	 * @param en el dato se tendra que mandar el string para validar.
	 * 
	 * Posibles opciones: 
	 * insertar solo letras [a-z]
	 * insertar letras y espacios [a-z\\s]
	 * insertar solo numeros [0-9]
	 * insertar numeros y espacios [0-9\\s]
	 * insertar letras y numeros sin caracteres especiales [a-z0-9]
	 * insertar letras y numeros con espacios [a-z0-9\\s]
	 * longitud de la palabra {1,10} 1 = longitud minima || 10 = longitud maxima
	 * 
	 * @return retorna true o false si el dato para validar esta correcto
	 * 
	 * caracteres como ������ etc. se considera caracteres especiales
	 * con lo cual este metodo devolvera false en cuanto tenga alguno es estos caracteres.
	 */
	public static boolean validacion(String patron,String dato)
	{	// https://www.isbn-international.org/content/what-isbn#:~:text=An%20ISBN%20is%20an%20International,digit%20to%20validate%20the%20number.
		String simboloRangoInicio = "[",simboloRangoFinal = "]",simboloLongitudInicio= "{",simboloLongitudFinal = "}";
		String simboloEspacio = "\\s", simboloPunto = "\\.", simboloBarra = "\\-";
		char espacio = '\s',punto = '.', barra = '-';
		String coma = ",", dobleBarra = "\\";
		String rango = patron.substring(patron.indexOf(simboloRangoInicio)+1,patron.indexOf(simboloRangoFinal));
		String segundoRango;
	
		if(rango.contains(dobleBarra))
			segundoRango = patron.substring(patron.indexOf(barra)+2,patron.indexOf("\\"));
		else if(rango.length()>3)
			segundoRango = patron.substring(patron.indexOf(barra)+2,patron.indexOf(simboloRangoFinal));
		else
			segundoRango = "%";

		if(segundoRango.equals(""))
			segundoRango = "%";
		String principioSegundoRango = "";
		String finalSegundoRango = "";
		boolean espacios = false;
		boolean comas = false;
		boolean barras = false;
		
		if(patron.contains(simboloEspacio))
			espacios = true;

		if(patron.contains(simboloPunto))
			comas = true;

		if(patron.contains(simboloBarra))
			barras = true;
		
		//comprobar que el rango es una letra o un digito para prevenir la insercion de caracteres especiales
		if(Character.isLetter(segundoRango.charAt(0)) || Character.isDigit(segundoRango.charAt(0)) && !segundoRango.equals(""))
		{
			principioSegundoRango = segundoRango.substring(0,1);
			finalSegundoRango = segundoRango.substring(2);
		}
		else
		{
			segundoRango = "";
		}
		
		String principioRango = rango.substring(0,1);
		String finalRango;
		if(rango.contains(dobleBarra))
			finalRango = rango.substring(2,rango.indexOf(dobleBarra));
		else
			finalRango = rango.substring(2);
		
		String longitud = patron.substring(patron.indexOf(simboloLongitudInicio)+1,patron.lastIndexOf(simboloLongitudFinal));
		String principioLongitud = longitud.substring(0,longitud.indexOf(coma));
		String finalLongitud = longitud.substring(longitud.indexOf(coma)+1);

		//comprobar que el rango es una letra o un digito para prevenir la insercion de caracteres especiales
		if(!Character.isLetter(principioRango.toUpperCase().charAt(0)) && !Character.isDigit(principioRango.toUpperCase().charAt(0)))
		{
			System.out.println("No puede insertar caracter especial en el rango");
			return false;
		}
		else if(!rango.contains(barra+""))
		{
			System.out.println("Al rango le falta la barra de espacio.");
			return false;
		}
		else if(!segundoRango.equals("") && !segundoRango.contains(barra+""))
		{
			System.out.println("Al rango le falta la barra de espacio.");
			return false;
		}
		
		
		//comprobar que no se pueda poner un rango minimo mas peque�o que el maximo
		if(Integer.parseInt(finalLongitud) < Integer.parseInt(principioLongitud))
		{
			int cambio = Integer.parseInt(finalLongitud);
			finalLongitud = principioLongitud;
			principioLongitud = cambio + "";
		}
		
		//comprobar que el dato insertado esta dentro del rango
		if(dato.length() > Integer.parseInt(finalLongitud) || dato.length() < Integer.parseInt(principioLongitud))
		{
			System.out.println("El dato sobrepasa el limite.");
			return false;
		}
		
		if(dato.contains(punto+""))
		{
			if(dato.substring(dato.indexOf(punto)+1).contains(punto+""))
			{
				System.out.println("El dato contiene demasiados puntos.");
				return false;
			}
		}
		
		if(dato.charAt(dato.length()-1) == barra && barras)
		{
			System.out.println("El dato no puede acabar con una barra.");
			return false;
		}
		
		//comprobar caracteres especiales
		for(int i = 0; i < dato.length(); i++)
		{
			if((!Character.isLetter(dato.charAt(i)) && !Character.isDigit(dato.charAt(i))))
			{
				if((dato.charAt(i) == espacio && !espacios))
				{
					System.out.println("No puede introducir espacios.");
					return false;
				}
				else if (dato.charAt(i) == punto && !comas)
				{
					System.out.println("No puede introducir puntos.");
					return false;
				}
				else if(dato.charAt(i) == barra && !barras)
				{
					System.out.println("No puede introducir barras.");
					return false;
				}
				else if (dato.charAt(i) != espacio && dato.charAt(i) != punto && dato.charAt(i) != barra)
				{
					System.out.println("No puede introducir caracteres especiales.");
					return false;
				}
			}
		}
				
		//si el rango es una letra entrara en el if
		if(Character.isLetter(principioRango.toUpperCase().charAt(0)) && segundoRango.equals(""))
		{
			//si el rango inicial es mayor que el final se cambian de posiciones y empieza la busqueda
			if((int)finalRango.toUpperCase().charAt(0) < (int)principioRango.toUpperCase().charAt(0))
			{
				String cambio = principioRango;
				principioRango = finalRango;
				finalRango = cambio;
			}
			
			//comprobar que cada posicion del dato es una letra
			for(int i = 0 ; i < dato.length(); i++)
			{
				
				//comprobar si tiene digito
				if (Character.isDigit(dato.charAt(i)))
				{
					System.out.println("No puede insertar digitos.");
					return false;
				}
				else if (((int)dato.toUpperCase().charAt(i) > (int)finalRango.toUpperCase().charAt(0) || 
				   (int)dato.toUpperCase().charAt(i) < (int)principioRango.toUpperCase().charAt(0)))
				{
					 if (dato.charAt(i) != espacio && dato.charAt(i) != punto && dato.charAt(i) != barra)
					 {
						 System.out.println("No puede insertar caracteres especiales.");
						 return false;
					 }
				}
			}
		}
		else if(Character.isDigit(principioRango.toUpperCase().charAt(0)) && segundoRango.equals(""))
		{
			//comprobar si el rango de numeros inicial no es mayor que el final y acmbiarlos si es true
			if(Integer.parseInt(finalRango.toUpperCase().charAt(0)+"") < Integer.parseInt(principioRango.toUpperCase().charAt(0)+""))
			{
				String cambio = principioRango;
				principioRango = finalRango;
				finalRango = cambio;
			}
			
			for(int i = 0 ; i < dato.length(); i++)
			{
				//comprobar si es un digito y que no sea un espacio
				if((Character.isDigit((char)dato.toUpperCase().charAt(i))))
				{	
					if(Integer.parseInt(dato.toUpperCase().charAt(i)+"") < Integer.parseInt(principioRango) ||
					   Integer.parseInt(dato.toUpperCase().charAt(i)+"") > Integer.parseInt(finalRango))
					{
						System.out.println("Numero Fuera del rango");
						return false;
					}	
				}
			}
		}
		else 
		{
			for(int i = 0 ; i < dato.length(); i++)
			{
				if(Character.isLetter(dato.toUpperCase().charAt(i)))
				{
					if((int)finalRango.toUpperCase().charAt(0) < (int)principioRango.toUpperCase().charAt(0))
					{
						String cambio = principioRango;
						principioRango = finalRango;
						finalRango = cambio;
					}
					
					if (((int)dato.toUpperCase().charAt(i) > (int)finalRango.toUpperCase().charAt(0) || 
					   (int)dato.toUpperCase().charAt(i) < (int)principioRango.toUpperCase().charAt(0)))
					{
						 if (dato.charAt(i) != espacio && dato.charAt(i) != punto && dato.charAt(i) != barra)
						 {
							 System.out.println("No puede insertar caracteres especiales.");
							 return false;
						 }
					}
					
				}
				else
				{
					if((Character.isDigit(dato.toUpperCase().charAt(i))))
					{
						//comprobar si el rango de numeros inicial no es mayor que el final y acmbiarlos si es true
						if(Integer.parseInt(principioSegundoRango.toUpperCase().charAt(0)+"") < Integer.parseInt(finalSegundoRango.toUpperCase().charAt(0)+""))
						{
							String cambio = principioRango;
							principioRango = finalRango;
							finalRango = cambio;
						}
						
						if(Integer.parseInt(dato.charAt(i)+"") < Integer.parseInt(principioSegundoRango) ||
						   Integer.parseInt(dato.charAt(i)+"") > Integer.parseInt(finalSegundoRango))
						{
							System.out.println("Numero fuera del rango.");
							return false;
						}
					}
				}
			}
		}
		
		return true;
	}

	/**
	 * @param nombre del archivo que quiere econtrar con la extension correspondiente.
	 * 
	 * (este metodo no devuelvera todos los ficheros que hay con ese nombre en distintas carpetas
	 * ni comprueba el contenido, solo devuelve el primer fichero que encuentra)
	 * 
	 * Los espacios de la barra de progreso "Buscando[    ]" significa la cantidad de dispositivos
	 * de almacenamiento de datos que estan conectadas al ordenador, cada barra que aparece significa
	 * que ha acabado de buscar por un dispositivo (Ejemplo dispositivos C:\\ D:\\ etc...)
	 * si aparecen las barras de golpe significa que se encontro en el primer dispositivo.
	 * 
	 * @return devolvera la ruta de este
	 * 
	 */
	public static String buscarFichero(String nombreArchivo)
	{
		EncontrarFichero buscador = new EncontrarFichero(nombreArchivo);
		buscador.start();
		int i = 0;
		String progreso = "[";
		for(int y = 0 ; y < buscador.limiteProgreso(); y++)
			progreso += " ";
		progreso+="]";
		int anterior = buscador.getProgreso();
		System.out.print("Buscando"+progreso);
		while(buscador.getEstado().equals("buscando"))
		{
			try {
				if(buscador.getProgreso() != anterior)
				{
					anterior = buscador.getProgreso();
					progreso = progreso.substring(0,i) + "-" + progreso.substring(progreso.indexOf(" ")+1,progreso.indexOf("]")+1);
					System.out.print("\r"+"Buscando"+progreso);
					i++;
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		progreso = "[";
		for(int y = 0 ; y < buscador.limiteProgreso(); y++)
			progreso += "-";
		progreso+="]";
		System.out.print("\r"+"Buscando"+progreso);
		
		if(buscador.getEstado().equals("esperando recogida"))
		System.out.println("");
		return buscador.recogerResult();
	}

	/**
	 * metodo que crea libro Xml 
	 * @param teclado
	 * @param urlXml
	 * @return boolean 
	 */
	public static boolean crearLibro(Scanner teclado, String urlXml) {

		boolean correcto = false;
		// parametros libro;
		final String nombreElemento = "libro";
		final String titulo = "titulo",editorial = "editorial",paginas = "paginas",altura = "altura",notas = "notas",isbn = "isbn",materias = "materias";
		
		do {
			Libro lLibro = main.Demo.crearLibro(teclado);
			try {
				// clases para leer XML
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(new File(urlXml));

				// preparar el archivo XML para obtener los datos
				doc.getDocumentElement().normalize();
				Node nodoRaiz = doc.getDocumentElement();

				// agregamos una nueva etiqueta al doc
				// primero creamos la etiqueta
				Element nuevoLibro = doc.createElement(nombreElemento);
				// creamos sus etiquetas hijas
				Element parametroTitulo = doc.createElement(titulo);
				parametroTitulo.setTextContent(lLibro.getTitulo());
				// editorial
				Element parametroEditorial = doc.createElement(editorial);
				parametroEditorial.setTextContent(lLibro.getEditorial());
				// paginas
				Element parametroPaginas = doc.createElement(paginas);
				parametroPaginas.setTextContent("" + lLibro.getPaginas());
				// altura
				Element parametroAltura = doc.createElement(altura);
				parametroAltura.setTextContent("" + lLibro.getAltura());
				// notas
				Element parametroNotas = doc.createElement(notas);
				parametroNotas.setTextContent(lLibro.getNotas());
				// isbn
				Element parametroIsbn = doc.createElement(isbn);
				parametroIsbn.setTextContent("" + lLibro.getIsbn());
				// materias
				Element parametroMaterias = doc.createElement(materias);
				parametroMaterias.setTextContent(lLibro.getMaterias());

				nuevoLibro.appendChild(parametroTitulo);
				nuevoLibro.appendChild(parametroEditorial);
				nuevoLibro.appendChild(parametroPaginas);
				nuevoLibro.appendChild(parametroAltura);
				nuevoLibro.appendChild(parametroNotas);
				nuevoLibro.appendChild(parametroIsbn);
				nuevoLibro.appendChild(parametroMaterias);
				nodoRaiz.appendChild(nuevoLibro);

				TransformerFactory transFactory = TransformerFactory.newInstance();
				Transformer trasnFormer = transFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(Variables.urlXml));
				trasnFormer.transform(source, result);
				correcto = true;
				System.out.println("Libro creado correctamente ");
				System.out.println("�Desea crear otro libro?  S/N");
				
				correcto = confirmacionSN(teclado);
				
			} catch (Exception e) {
				correcto = false;		
				System.out.println("No se ha creado el libro");
			}
		} while (correcto);

		return correcto;
	}

	/**
	 * Metodo que genera Xml base cuando no existe el fichero
	 * @param ruta
	 * @return boolean 
	 */
	public static boolean generateXml(String ruta) {
		boolean correc = false;
		String name = "libreria";
		final String nombreElemento = "libro";
		final String titulo = "titulo",editorial = "editorial",paginas = "paginas",altura = "altura",notas = "notas",isbn = "isbn",materias = "materias";
		final String resetearString = "base";
		final int resetaerNum = 0;
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation implementation = builder.getDOMImplementation();
			Document document = implementation.createDocument(null, name, null);
			document.setXmlVersion(Variables.versionXml);
			Libro lLibro = new Libro();
			lLibro.setTitulo(resetearString);
			lLibro.setEditorial(resetearString);
			lLibro.setNotas(resetearString);
			lLibro.setMaterias(resetearString);
			lLibro.setAltura((double) resetaerNum);
			lLibro.setPaginas(resetaerNum);
			lLibro.setIsbn(resetaerNum+"");

			// Main Node
			Element raiz = document.getDocumentElement();
			// agregamos una nueva etiqueta al doc
			// primero creamos la etiqueta
			Element nuevoLibro = document.createElement(nombreElemento);
			// creamos sus etiquetas hijas
			Element parametroTitulo = document.createElement(titulo);
			parametroTitulo.setTextContent(lLibro.getTitulo());
			// editorial
			Element parametroEditorial = document.createElement(editorial);
			parametroEditorial.setTextContent(lLibro.getEditorial());
			// paginas
			Element parametroPaginas = document.createElement(paginas);
			parametroPaginas.setTextContent("" + lLibro.getPaginas());
			// altura
			Element parametroAltura = document.createElement(altura);
			parametroAltura.setTextContent("" + lLibro.getAltura());
			// notas
			Element parametroNotas = document.createElement(notas);
			parametroNotas.setTextContent(lLibro.getNotas());
			// isbn
			Element parametroIsbn = document.createElement(isbn);
			parametroIsbn.setTextContent(lLibro.getIsbn());
			// materias
			Element parametroMaterias = document.createElement(materias);
			parametroMaterias.setTextContent(lLibro.getMaterias());

			nuevoLibro.appendChild(parametroTitulo);
			nuevoLibro.appendChild(parametroEditorial);
			nuevoLibro.appendChild(parametroPaginas);
			nuevoLibro.appendChild(parametroAltura);
			nuevoLibro.appendChild(parametroNotas);
			nuevoLibro.appendChild(parametroIsbn);
			nuevoLibro.appendChild(parametroMaterias);
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

	/**
	 * Metodo que carga datos Csv
	 * @param sc
	 * @return ArrayList<Libro>
	 */
	public static ArrayList<Libro> cargarCsv(Scanner sc) {
		BufferedReader br = null;
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		
		try {

			String ruta = Variables.urlCsv;
			File archivo = new File(ruta);
			
			if (!archivo.exists()) {
				System.out.println("El fichero no existe");
			} else {
				br = new BufferedReader(new FileReader(ruta));
				String line = br.readLine();
				StringTokenizer tokens;
				while (null != line) {
					tokens = new StringTokenizer(line, SEPARATOR);

					Libro libro = new Libro();

					libro.setTitulo(tokens.nextToken());
					
					libro.setEditorial(tokens.nextToken());
					
					libro.setPaginas(Integer.parseInt(tokens.nextToken()));
					
					libro.setAltura(Double.parseDouble(tokens.nextToken()));
					
					libro.setNotas(tokens.nextToken());
					
					libro.setIsbn(tokens.nextToken());
					
					libro.setMaterias(tokens.nextToken());

					listaLibro.add(libro);
					line = br.readLine();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al leer fichero");
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return listaLibro;
	}

	/**
	 * Metodo que crea Archivos Csv
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean crearArchivoCSV(Scanner teclado) {

		boolean seguir = false;
		try {
			FileWriter fw = new FileWriter(Variables.urlCsv, true);
			do {

				Libro libro = main.Demo.crearLibro(teclado);

				fw.append(libro.getTitulo()).append(SEPARATOR);

				fw.append(libro.getEditorial()).append(SEPARATOR);
				fw.append(String.valueOf(libro.getPaginas())).append(SEPARATOR);
				fw.append(String.valueOf(libro.getAltura())).append(SEPARATOR);
				fw.append(libro.getNotas()).append(SEPARATOR);
				fw.append(String.valueOf(libro.getIsbn())).append(SEPARATOR);
				fw.append(libro.getMaterias()).append(SEPARATOR + "\n");

				fw.flush();

				System.out.println("Fichero creado con exito");
				System.out.println("�Desea crear otro libro?  S/N");

				seguir = confirmacionSN(teclado);

			} while (seguir);
			fw.close();

		} catch (IOException e) {
			System.out.println("Error al crear fichero ");
//			e.printStackTrace();
			seguir = true;
		}
		return seguir;
	}

	/**
	 *  Metodo que lee muestra por pantalla el Xml 
	 * @param listaLibros
	 * @param rutaFichero
	 * @return ArrayList<Libro>
	 */
	public static ArrayList<Libro> leerXml(String rutaFichero)  {
		ArrayList<Libro> listaLibros = new ArrayList<Libro>();
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			LeerXml leerXml = new LeerXml();
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

	/**
	 * Metodos para cambiar permisos.
	 * @param teclado
	 * @return
	 */
	public static boolean cambioPermiso(Scanner teclado) {

		int opcion;
		boolean correcto=false;
		try {

			do {
				System.out.println("�Que desea hacer?");
				System.out.println("1) Agregar permisos");
				System.out.println("2) Quitar permisos");
				System.out.println("3) Salir");
				opcion = entradaInt(1, 3, teclado);

				if (opcion == 1) {
					agregarPermiso(teclado);
					correcto = true;
				} else if (opcion == 2) {

					if (isUnix()) {
						agregarPermiso(teclado);
					} else {
						quitarPermiso(teclado);
					}
					correcto = true;
				} else if (opcion == 3) {
					correcto = false;
				}
			} while (correcto);

		} catch (Exception e) {
			e.getMessage();
			correcto = false;
		}
		return correcto;
	}

	/**
	 * Metodos para cambiar permisos.
	 * @param teclado
	 * @return
	 */
	private static boolean quitarPermiso(Scanner teclado) {
		String usuario;
		String nombreFichero;
		String nomFichero;
		boolean correcto=false;
		Process process;
		BufferedReader br;
		try {
			System.out.println("Ingrese el nombre del usuario al cual le dara permisos");
			usuario = teclado.next();
			teclado.nextLine();
			
			process = Runtime.getRuntime().exec("cmd /c net user " + usuario);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			if ((br.readLine()) == null) {
				System.out.println("el usario no existe");
			}else {
				
				System.out.println("Ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
				nomFichero = teclado.next();
				teclado.nextLine();

				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

				process = Runtime.getRuntime().exec("cmd /c ICACLS " + nombreFichero + " /remove " + usuario);
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String resultOfExecution = null;
				while ((resultOfExecution = br.readLine()) != null) {
					System.out.println(resultOfExecution);
				}
				correcto=true;
			}	
		} catch (Exception e) {
			e.getMessage();
			correcto=false;
		}
		return correcto;
	}

	/**
	 * Metodos para cambiar permisos.
	 * @param teclado
	 * @return
	 */
	private static boolean agregarPermiso(Scanner teclado) {
		String usuario;
		String nombreFichero;
		String permiso;
		String nomFichero;
		int permisoUnix;
		Runtime builder = Runtime.getRuntime();
		String permisoLetras = null;
		boolean correcto=false;
		Process process;
		BufferedReader br;
		try {
			if (isWindows()) {

				System.out.println("Ingrese el nombre del usuario al cual le dara permisos");
				usuario = teclado.next();
				teclado.nextLine();

				process = Runtime.getRuntime().exec("cmd /c net user " + usuario);
				br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				
				if ((br.readLine()) == null) {
					System.out.println("el usario no existe");
				}else {

					System.out.println("Ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
					nomFichero = teclado.next();
					teclado.nextLine();
					
					nombreFichero = modelo.Metodos.buscarFichero(nomFichero);
					System.out.println("que permiso desea agregar");
					System.out.println("F - acceso total");
					System.out.println("M - acceso de modificaci�n");
					System.out.println("RX - acceso de lectura y ejecucion");
					System.out.println("R - acceso de solo lectura");
					System.out.println("W - acceso de solo escritura");
					System.out.println("D - acceso de eliminacion");
					permiso = teclado.next();
					teclado.nextLine();

					process = Runtime.getRuntime().exec("cmd /c ICACLS " + nombreFichero + " /grant " + usuario + ":(" + permiso + ")");
					br = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String resultOfExecution = null;
					while ((resultOfExecution = br.readLine()) != null) {
						System.out.println(resultOfExecution);
					}
					correcto=true;
				}
				
				
			} else if (isUnix()) {

				System.out.println("Ingrese el nombre del archivo");
				nomFichero = teclado.next();
				teclado.nextLine();

				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

				System.out.println("que permiso desea cambiar");
				System.out.println("0 = sin acceso");
				System.out.println("1 = ejecuci�n");
				System.out.println("2 = escritura");
				System.out.println("3 = escritura y ejecuci�n");
				System.out.println("4 = lectura");
				System.out.println("5 = lectura y ejecuci�n");
				System.out.println("6 = lectura y escritura");
				System.out.println("7 = lectura, escritura y ejecuci�n");
				permisoUnix = entradaInt(0, 7, teclado);

				if (permisoUnix == 0) {
					permisoLetras = "---";
				} else if (permisoUnix == 1) {
					permisoLetras = "--x";
				} else if (permisoUnix == 2) {
					permisoLetras = "-w-";
				} else if (permisoUnix == 3) {
					permisoLetras = "-wr";
				} else if (permisoUnix == 4) {
					permisoLetras = "r--";
				} else if (permisoUnix == 5) {
					permisoLetras = "r-x";
				} else if (permisoUnix == 6) {
					permisoLetras = "rw-";
				} else if (permisoUnix == 7) {
					permisoLetras = "rwx";
				}

				String cmd = "chmod g=" + permisoLetras + "," + "o=" + permisoLetras + " " + nombreFichero;
				Process out = builder.exec(cmd);

				out.getInputStream();
				System.out.println("Cambio realizado");
				correcto=true;
			}

		} catch (Exception e) {
			System.out.println("No se realiazo el cambio de permiso");
			correcto=false;
		}
		return correcto;

	}

	/**
	 * Mediante este metodo se encuentra un directorio que se devuelve al metodo seleccionarDirectorio.
	 * @param teclado
	 * @param urlDefecto
	 * @return
	 */
	public static File encontrarDirectorio(Scanner teclado, String urlDefecto)
	{
		String url = urlDefecto;
		File archivoParaMover = null;
		
		System.out.println("�Sabe la ruta completa al directorio?");
		if(confirmacionSN(teclado))
		{
			System.out.print("Escriba la ruta: ");
			url = teclado.nextLine();
			archivoParaMover = new File(url);
			
			while(!archivoParaMover.isDirectory() && !url.equals("0"))
			{
				System.out.println("Ruta incorrecta, vuelve ha insertarla.");
				System.out.println("Si quiere salir inserte 0");
				url = teclado.nextLine();
				archivoParaMover = new File(url);
			}
			
			if(url.equals("0"))
			{
				url = urlDefecto;
			}
			else
			{
				return archivoParaMover = new File(url);
			}
		}
		
		System.out.println("�Quieres buscarlo manualmente?");
		if(confirmacionSN(teclado))
		{
			System.out.println("�Quiere buscarlo empezando por una ruta indicada o desde la ruta por defecto? responda con 1 o 2");
			int opcion = entradaInt(1,2,teclado);
			
			if(opcion == 1)
			{
				System.out.print("Escriba la ruta: ");
				url = teclado.nextLine();
				archivoParaMover = new File(seleccionarDirectorio(teclado, url));
				return archivoParaMover;
			}
			else if(opcion == 2)
			{
				archivoParaMover = new File(seleccionarDirectorio(teclado, urlDefecto));
				return archivoParaMover;
			}
		}
		
		return null;
	}
	
	/**
	 * Este metodo devolvera el directorio seleccionado despues de ense�ar los disponibles.
	 * Si el directorio que busca no se encuentra en esta lista volvera al metodo encontrarDirectorio.
	 * @param teclado
	 * @param url
	 * @return
	 */
	public static String seleccionarDirectorio(Scanner teclado, String url)
	{
		String result = "";
		String lista = ficheros(url,"carpetas");
		if(lista.equals(""))
		{
				System.out.println("No hay mas carpetas en esta ruta.");
				url = moverEntreCarpetas(teclado, url);
				return seleccionarDirectorio(teclado, url);
		}
		else if(!lista.equals(""))
		{
			System.out.println(lista);
			System.out.println("�La carpeta ha la que quiere mover se encuentra en esta lista?");
			if(confirmacionSN(teclado))
			{
				System.out.print("Escriba el numero del directorio: ");
				result = devolucion(url,"carpetas").get(entradaInt(1,devolucion(url,"carpetas").size(),teclado) - 1).getAbsolutePath();
				return result;
			}
			else
			{
				url = moverEntreCarpetas(teclado, url);
				return seleccionarDirectorio(teclado, url);
			}
		}
		
		return result;
	}
	
	/**
	 * Mediante este metodo se encuentra un fichero al quie quiere modificar.
	 * @param teclado
	 * @param urlDefecto
	 * @return
	 */
	public static File encontrarFichero(Scanner teclado, String urlDefecto)
	{
		String url = urlDefecto;
		File archivoParaMover = null;
		
		System.out.println("�Sabe la ruta completa al fichero?");
		if(confirmacionSN(teclado))
		{
			System.out.print("Escriba la ruta: ");
			url = teclado.nextLine();
			archivoParaMover = new File(url);
			
			while(!archivoParaMover.isFile() && !url.equals("0"))
			{
				System.out.println("Ruta incorrecta, vuelve ha insertarla.");
				System.out.println("Si quiere salir inserte 0");
				url = teclado.nextLine();
				archivoParaMover = new File(url);
			}
			
			if(url.equals("0"))
			{
				url = urlDefecto;
			}
			else
			{
				return archivoParaMover = new File(url);
			}
		}
		
		
		System.out.println("�Quiere escribir el nombre del fichero para que el programa lo busque?");
		System.out.println("Ten en cuenta que esto devolvera el primer fichero con ese nombre, no todos los ficheros con el nombre.");
		boolean respuesta;
		if(confirmacionSN(teclado))
		{
			do
			{
				System.out.println("Escriba el nombre del fichero: ");
				System.out.println("Si escribe el nombre equivocado tendra que esperar a que el metodos busque por todo el ordenador para volver ha intentar");
				url = buscarFichero(teclado.nextLine());
				System.out.println("�Es este el fichero que busca?: ");
				System.out.println(url);
				respuesta = confirmacionSN(teclado);
				if(!respuesta)
				{
					System.out.println("�Quiere volver ha intentar? Si introduce la ruta desde una carpeta mas exacta devolvera otra ruta.");
					respuesta = confirmacionSN(teclado);
					if(!respuesta)
						url = urlDefecto;
				}
				else
				{
					respuesta = false;
					return archivoParaMover = new File(url);
				}
			}while(respuesta);
			
		}
		
		
		System.out.println("�Quieres buscarlo manualmente?");
		if(confirmacionSN(teclado))
		{
			System.out.println("�Quiere buscarlo empezando por una ruta indicada o desde la ruta por defecto? responda con 1 o 2");
			int opcion = entradaInt(1,2,teclado);
			if(opcion == 1)
			{
				System.out.print("Escriba la ruta: ");
				url = teclado.nextLine();
				archivoParaMover = new File(seleccionarFichero(teclado, url));
				return archivoParaMover;
			}
			else if(opcion == 2)
			{
				archivoParaMover = new File(seleccionarFichero(teclado, urlDefecto));
				return archivoParaMover;
			}
		}
		
		
		return archivoParaMover;
	}
	
	/**
	 * Este metodo sirve para buscar ficheros y directorios dentro o fuera de otros,
	 * este va en cadena con el metodo sleccionarFichero.
	 * @param teclado
	 * @param url
	 * @return
	 */
	public static String moverEntreCarpetas(Scanner teclado, String url)
	{
		System.out.println(ficheros(url,"carpetas"));
		System.out.println("�Quiere ir en algun directorio de mas arriba o volver a un directorio anterior? 1 o 2");
		int opcion = entradaInt(1,2,teclado);
		
		if(opcion == 1)
		{
			System.out.println("Escriba el numero de la carpeta a la que quiere acceder.");
			ArrayList<File> directorios = devolucion(url,"carpetas");
			int carpeta = entradaInt(1, directorios.size(), teclado);
			return directorios.get(carpeta-1).getPath();
		}
		else
		{
			try
			{
				if(isWindows())
					url = url.substring(0,url.lastIndexOf("\\"));
				else
					url = url.substring(0,url.lastIndexOf("/"));
				return moverEntreCarpetas(teclado, url);
			}
			catch(Exception noHayMasBarras)
			{
				ArrayList<String> listaDispositivos = encontrarEspaciosAlmacenamiento();
				for(int i = 0 ; i < listaDispositivos.size() ; i++)
				{
					System.out.println((i+1) + ") " + listaDispositivos.get(i));
				}
				System.out.println("No puede ir mas atras, tiene que seleccionar un dispositivo: ");
				int dispositivo = entradaInt(1, listaDispositivos.size(), teclado);
				
				if(ficheros(listaDispositivos.get(dispositivo-1).substring(0,listaDispositivos.get(dispositivo-1).length()-1),"ficheros").equals("") || ficheros(listaDispositivos.get(dispositivo-1).substring(0,listaDispositivos.get(dispositivo-1).length()-1),"carpetas").equals(""))
					return listaDispositivos.get(dispositivo-1);
				else
					return listaDispositivos.get(dispositivo-1).substring(0,listaDispositivos.get(dispositivo-1).length()-1);
			}
		}
	}
	
	/**
	 * Este metodo devuelve un arraylist la ruta de todos los espacios de almacenamiento que estan conectadas al ordenador.
	 * @return
	 */
	public static ArrayList<String> encontrarEspaciosAlmacenamiento()
	{
		ArrayList<String> result = new ArrayList<String>();
		/*
			//System.out.println("Drive Letter: " + aDrive); ruta del dispositivo
			//System.out.println("\tType: " + fsv.getSystemTypeDescription(aDrive)); nombre del dispositivo
			//System.out.println();
		 FileSystemView fsv = FileSystemView.getFileSystemView();
		 * 
		 	https://stackoverflow.com/questions/21059703/how-can-a-java-program-list-all-partitions-and-get-the-free-space-of-them-on-lin
		 */
		 
		 File[] drives = File.listRoots();
		 
		 if (drives != null && drives.length > 0) {
		        for (File aDrive : drives) {
		        	result.add(aDrive.toString());
		        }
		 }
		 return result;
	}
	
	/**
	 * Este metodo Muestra los ficheros que hay en la ruta especificada y devuelve la ruta absoluta de este. 
	 * @param teclado
	 * @param url
	 * @return
	 */
	public static String seleccionarFichero(Scanner teclado, String url)
	{
		String result = "";
		String lista = ficheros(url,"Ficheros");
		if(lista.equals(""))
		{
			System.out.println("No hay ficheros en esta carpeta.");
			lista = ficheros(url,"carpetas");
			if(lista.equals(""))
				System.out.println("No hay mas carpetas en esta ruta.");
			else
			{
				url = moverEntreCarpetas(teclado, url);
				return seleccionarFichero(teclado, url);
			}
		}
		else
		{
			System.out.println(lista);
			System.out.println("�El fichero que quiere mover se encuentra en esta lista?");
			if(confirmacionSN(teclado))
			{
				System.out.print("Escriba el numero del archivo: ");
				
				result = devolucion(url,"ficheros").get(entradaInt(1,devolucion(url,"ficheros").size(),teclado) - 1).getAbsolutePath();
				return result;
			}
			else
			{
				url = moverEntreCarpetas(teclado, url);
				return seleccionarFichero(teclado, url);
			}
		}
		
		return result;
	}
	
	/**
	 * Metodo que devuelve el nombre de cada fichero o directorio de una ruta.
	 * @param url
	 * @param tipo
	 * @return
	 */
	public static String ficheros(String url, String tipo)
	{
		tipo = tipo.toLowerCase();
		String result = "";
		File f = new File(url);
        File[] files;
        if(f.listFiles() != null)
		{
			files = f.listFiles();
		}
		else
		{
			result = "";
			return result;
		}
        
        try {
        	int contador = 1;
            for(int i = 0;i < files.length;i++) {
            	if(tipo.equals("ficheros") && files[i].isFile() && (Character.isLetter(files[i].getName().charAt(0)) || Character.isDigit(files[i].getName().charAt(0))))
        		{
	        		result += (contador) + ") " + files[i].getName() + "\n";
	        		contador++;
        		}
            	else if(tipo.equals("carpetas") && files[i].isDirectory() && (Character.isLetter(files[i].getName().charAt(0)) || Character.isDigit(files[i].getName().charAt(0))))
            	{
	        		result += (contador) + ") " + files[i].getName() + "\n";
	        		contador++;
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(result.equals(""))
        	return "";
        else
        	return result;
	}

	/**
	 * Metodo que devuelve un array con todos los ficheros o directorios de la ruta insertada.
	 * @param url
	 * @param tipo
	 * @return
	 */
	public static ArrayList<File> devolucion(String url, String tipo)
	{
		ArrayList<File> array = new ArrayList<File>();
		File f = new File(url);
        File[] files;
        if(f.listFiles() != null)
		{
			files = f.listFiles();
		}
		else
		{
			return null;
		}
        
        try {
        	for(int i = 0;i < files.length;i++) {
            	if(tipo.equals("ficheros") && files[i].isFile() && (Character.isLetter(files[i].getName().charAt(0)) || Character.isDigit(files[i].getName().charAt(0))))
        		{
            		array.add(files[i]);
        		}
            	else if(tipo.equals("carpetas") && files[i].isDirectory() && (Character.isLetter(files[i].getName().charAt(0)) || Character.isDigit(files[i].getName().charAt(0))))
            	{
            		array.add(files[i]);
        		}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
	}
	
	/**
	 * Metodo para comprobar si el dato insertado es del mismo tipo.
	 */
	public static long comprobacionDatoInt(Scanner teclado) {
		long parametro = 0;
		boolean repetir = true;
		do {
			try {
				parametro = teclado.nextLong();
				teclado.nextLine();
				return parametro;
			} catch (Exception a) {
				System.out.println("Dato incorrecto");
				System.out.println("Vuelve ha insertarlo: ");
				teclado.nextLine();
			}
		} while (repetir);
		return parametro;
	}
	
	/**
	 * Metodo para comprobar si el dato insertado es del mismo tipo.
	 */
	public static double comprobacionDatoDouble(Scanner teclado) {
		double parametro = 0;
		boolean repetir = true;

		do {
			try {
				parametro = teclado.nextDouble();
				teclado.nextLine();
				return parametro;
			} catch (Exception a) {
				System.out.println("Dato incorrecto");
				System.out.println("Vuelve ha insertarlo: ");
				teclado.nextLine();
			}
		} while (repetir);
		return parametro;
	}
	
	/**
	 * Metodo para preguntar al cliente si quiere seguir o no.
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean confirmacionSN(Scanner teclado) {
		String result;

		do {
			result = teclado.next();
			if (result.length() > 1 || result.length() < 1) {
				System.out.println("Dato incorrecto, Vuelve ha insertarlo.");
			} else {
				if (result.toUpperCase().equals("S")) {
					teclado.nextLine();
					return true;
				} else if (result.toUpperCase().equals("N")) {
					teclado.nextLine();
					return false;
				}
				System.out.println("Tiene que insertar S o N.");
			}

		} while (!result.toUpperCase().equals("N") && !result.toUpperCase().equals("S"));

		return false;
	}
	
	/**
	 * Metodo para validar la entrada de numeros por teclado y controlar las
	 * exceptciones.
	 * @param min
	 * @param max
	 * @param teclado
	 * @return int 
	 */
	public static int entradaInt(int min, int max, Scanner teclado) {
		int result = 0;
		do {
			try {
				result = teclado.nextInt();
				if (result < min || result > max) {
					System.out.println("Tiene que insertar un numero entre " + min + " y " + max);
					teclado.nextLine();
				}
			} catch (InputMismatchException a) {
				System.out.println("Tiene que insertar un numero:");
				teclado.nextLine();
			}
		} while (result < min || result > max);
		teclado.nextLine();
		return result;
	}

}