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

import main.Demo;

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
							libro.setIsbn(Long.parseLong(contenido.get(i)[y]));
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
	 * caracteres como ÑÇÖÔÜÛ etc. se considera caracteres especiales
	 * con lo cual este metodo devolvera false en cuanto tenga alguno es estos caracteres.
	 */
	public static boolean validacion(String patron,String dato)
	{	// https://www.isbn-international.org/content/what-isbn#:~:text=An%20ISBN%20is%20an%20International,digit%20to%20validate%20the%20number.
		String rango = patron.substring(patron.indexOf("[")+1,patron.indexOf("[")+4); 
		String segundoRango = patron.substring(patron.indexOf(rango)+rango.length(),patron.indexOf(rango)+rango.length()+3);
		String principioSegundoRango = "";
		String finalSegundoRango = "";
		boolean espacios = false;
		boolean comas = false;
		boolean barras = false;
		
		if(patron.contains("\\s"))
			espacios = true;

		if(patron.contains("\\."))
			comas = true;

		if(patron.contains("\\-"))
			barras = true;
		
		//comprobar que el rango es una letra o un digito para prevenir la insercion de caracteres especiales
		if(Character.isLetter(segundoRango.charAt(0)) || Character.isDigit(segundoRango.charAt(0)))
		{
			principioSegundoRango = segundoRango.substring(0,1);
			finalSegundoRango = segundoRango.substring(2);
		}
		else
		{
			segundoRango = "";
		}
		
		String principioRango = rango.substring(0,1);
		String finalRango = rango.substring(2);
		
		String longitud = patron.substring(patron.indexOf("{")+1,patron.lastIndexOf("}"));
		String principioLongitud = longitud.substring(0,longitud.indexOf(","));
		String finalLongitud = longitud.substring(longitud.indexOf(",")+1);

		//comprobar que el rango es una letra o un digito para prevenir la insercion de caracteres especiales
		if(!Character.isLetter(principioRango.toUpperCase().charAt(0)) && !Character.isDigit(principioRango.toUpperCase().charAt(0)))
		{
			System.out.println("entro caracter special");
			return false;
		}
		else if(!rango.contains("-"))
		{
			return false;
		}
		else if(!segundoRango.equals("") && !segundoRango.contains("-"))
		{
			return false;
		}
		
		
		//comprobar que no se pueda poner un rango minimo mas pequeï¿½o que el maximo
		if(Integer.parseInt(finalLongitud) < Integer.parseInt(principioLongitud))
		{
			int cambio = Integer.parseInt(finalLongitud);
			finalLongitud = principioLongitud;
			principioLongitud = cambio + "";
		}
		
		//comprobar que el dato insertado esta dentro del rango
		if(dato.length() > Integer.parseInt(finalLongitud) || dato.length() < Integer.parseInt(principioLongitud))
		{
			return false;
		}
		
		if(dato.contains("."))
		if(dato.substring(dato.indexOf(".")+1).contains("."))
			return false;
		
		//comprobar caracteres especiales
		for(int i = 0; i < dato.length(); i++)
		{
			if((!Character.isLetter(dato.charAt(i)) && !Character.isDigit(dato.charAt(i))))
			{
				if((dato.charAt(i) == '\s' && !espacios))
				{
					return false;
				}
				else if (dato.charAt(i) == '.' && !comas)
					return false;
				else if (dato.charAt(i) != '\s' && dato.charAt(i) != '.')
					return false;
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
					return false;
				}
				else if (((int)dato.toUpperCase().charAt(i) > (int)finalRango.toUpperCase().charAt(0) || 
				   (int)dato.toUpperCase().charAt(i) < (int)principioRango.toUpperCase().charAt(0)))
				{
				 if (dato.charAt(i) != '\s' && dato.charAt(i) != '.')
						return false;
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
					 if (dato.charAt(i) != '\s' && dato.charAt(i) != '.')
							return false;
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
				System.out.println("¿Desea crear otro libro?  S/N");
				
				correcto=Demo.confirmacionSN(teclado);
				
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
			lLibro.setIsbn(Long.parseLong(resetaerNum+""));

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
			parametroIsbn.setTextContent("" + lLibro.getIsbn());
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
					
					libro.setIsbn(Long.parseLong(tokens.nextToken()));
					
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
				System.out.println("¿Desea crear otro libro?  S/N");

				seguir = Demo.confirmacionSN(teclado);

			} while (seguir);
			fw.close();

		} catch (IOException e) {
			System.out.println("Error al crear fichero ");
			e.printStackTrace();
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
				System.out.println("¿Que desea hacer?");
				System.out.println("1) Agregar permisos");
				System.out.println("2) Quitar permisos");
				System.out.println("3) Salir");
				opcion = Demo.entradaInt(1, 3, teclado);

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
			} while (correcto = true);

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
		
		try {
			System.out.println("Ingrese el nombre del usuario al cual le dara permisos");
			usuario = teclado.next();
			teclado.nextLine();
			
			System.out.println("Ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
			nomFichero = teclado.next();
			teclado.nextLine();

			nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

			Process process = Runtime.getRuntime().exec("cmd /c ICACLS " + nombreFichero + " /remove " + usuario);
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String resultOfExecution = null;
			while ((resultOfExecution = br.readLine()) != null) {
				System.out.println(resultOfExecution);
			}
			correcto=true;
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
		
		try {
			if (isWindows()) {

				System.out.println("Ingrese el nombre del usuario al cual le dara permisos");
				usuario = teclado.next();
				teclado.nextLine();

				System.out.println("Ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
				nomFichero = teclado.next();
				teclado.nextLine();
				
				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);
				System.out.println("que permiso desea agregar");
				System.out.println("F - acceso total");
				System.out.println("M - acceso de modificación");
				System.out.println("RX - acceso de lectura y ejecución");
				System.out.println("R - acceso de solo lectura");
				System.out.println("W - acceso de solo escritura");
				System.out.println("D - acceso de eliminación");
				permiso = teclado.next();
				teclado.nextLine();

				Process process = Runtime.getRuntime().exec("cmd /c ICACLS " + nombreFichero + " /grant " + usuario + ":(" + permiso + ")");
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String resultOfExecution = null;
				while ((resultOfExecution = br.readLine()) != null) {
					System.out.println(resultOfExecution);
				}
				correcto=true;
			} else if (isUnix()) {

				System.out.println("Ingrese el nombre del archivo");
				nomFichero = teclado.next();
				teclado.nextLine();

				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

				System.out.println("que permiso desea cambiar");
				System.out.println("0 = sin acceso");
				System.out.println("1 = ejecución");
				System.out.println("2 = escritura");
				System.out.println("3 = escritura y ejecución");
				System.out.println("4 = lectura");
				System.out.println("5 = lectura y ejecución");
				System.out.println("6 = lectura y escritura");
				System.out.println("7 = lectura, escritura y ejecución");
				permisoUnix = Demo.entradaInt(0, 7, teclado);

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

}