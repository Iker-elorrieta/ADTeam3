package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Libro;
import modelo.Metodos;
import modelo.Patrones;
import modelo.Variables;
import modelo.crearLibroXml;
import modelo.ficheroCsv;

public class Demo {

	/**
	 * La clase main donde se empieza el programa.
	 * @param args
	 */
	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);

		inicioPrograma(teclado);

	}

	/**
	 * metodo de inicio del programa
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean inicioPrograma(Scanner teclado) {

		boolean correcto = false;

		try {

			if (Metodos.isWindows()) {

				Variables.urlTxt = ".\\Ficheros\\fichero.txt";
				Variables.urlXml = ".\\Ficheros\\libreria.xml";
				Variables.urlCsv = ".\\Ficheros\\fichero.csv";

				Variables.ficheroTxt = new File(Variables.urlTxt);
				Variables.ficheroXml = new File(Variables.urlXml);
				Variables.ficheroCsv = new File(Variables.urlCsv);
				correcto = true;
			} else if (Metodos.isUnix()) {
				Variables.urlTxt = "./Ficheros/fichero.txt";
				Variables.urlXml = "./Ficheros/libreria.xml";
				Variables.urlCsv = "./Ficheros/fichero.csv";

				Variables.ficheroTxt = new File(Variables.urlTxt);
				Variables.ficheroXml = new File(Variables.urlXml);
				Variables.ficheroCsv = new File(Variables.urlCsv);
				correcto = true;
			}

			if (!Variables.ficheroCsv.exists())
				Variables.ficheroCsv.createNewFile();
			if (!Variables.ficheroXml.exists())
				modelo.crearXml.generateXml(Variables.urlXml);
			if (!Variables.ficheroTxt.exists())
				Variables.ficheroTxt.createNewFile();
			do {
				menu(teclado);
				System.out.println("�Quiere hacer otras operaciones? s/n");
			} while (confirmacionSN(teclado));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			correcto = false;
		}
		return correcto;
	}

	/**
	 * Menu donde se encuentra las acciones que quiere realizar el cliente.
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean menu(Scanner teclado) {

		boolean correcto = false;
		int respuestaOpcionesTipo;
		int respuestaOpcionesAccion;

		System.out.println("Elige alguna de las opciones: ");
		System.out.println("1) Xml");
		System.out.println("2) txt");
		System.out.println("3) Csv");
		respuestaOpcionesTipo = entradaInt(1, 3, teclado);

		System.out.println("�Que desea hacer? ");
		System.out.println("1. Leer");
		System.out.println("2. Crear libro");
		System.out.println("3. Eliminar archivo");
		System.out.println("4. Crear archivo / Seleccionar archivo");
		if (respuestaOpcionesTipo == 1) {
			respuestaOpcionesAccion = entradaInt(1, 4, teclado);

			menuXml(respuestaOpcionesAccion, teclado);
			correcto = true;
		} else if (respuestaOpcionesTipo == 2) {
			respuestaOpcionesAccion = entradaInt(1, 4, teclado);

			menuTxt(respuestaOpcionesAccion, teclado);
			correcto = true;
		} else if (respuestaOpcionesTipo == 3) {
			respuestaOpcionesAccion = entradaInt(1, 4, teclado);

			menuCsv(respuestaOpcionesAccion, teclado);
			correcto = true;
		}
		return correcto;
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
	 * metodo para crearLibro
	 * @param teclado
	 * @return objeto Libro
	 */
	public static Libro crearLibro(Scanner teclado) {
		String titulo, editorial, notas, materias;
		double altura = 0.0;
		int paginas = 0, isbn = 0;
	
		System.out.print("Introduce el titulo: ");
		titulo = teclado.nextLine();
		while(!Metodos.validacion(Patrones.titulo.getNombre(), titulo))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			titulo = teclado.nextLine();
		}
	
		System.out.print("Introduce el editorial: ");
		editorial = teclado.nextLine();
		while(!Metodos.validacion(Patrones.editorial.getNombre(), editorial))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			editorial = teclado.nextLine();
		}
	
		System.out.print("Introduce las notas: ");
		notas = teclado.nextLine();
		while(!Metodos.validacion(Patrones.notas.getNombre(), notas))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			notas = teclado.nextLine();
		}
		
		System.out.print("Introduce las materias: ");
		materias = teclado.nextLine();
		while(!Metodos.validacion(Patrones.materias.getNombre(), materias))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			materias = teclado.nextLine();
		}
		
		System.out.print("Introduce la altura: ");
		altura = comprobacionDatoDouble(teclado);
		while(!Metodos.validacion(Patrones.altura.getNombre(), altura+""))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			altura = comprobacionDatoDouble(teclado);
		}
		
		System.out.print("Introduce las paginas: ");
		paginas = comprobacionDatoInt(teclado);
		while(!Metodos.validacion(Patrones.paginas.getNombre(), paginas+""))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			paginas = comprobacionDatoInt(teclado);
		}
		
		System.out.print("Introduce el isbn: ");
		isbn = comprobacionDatoInt(teclado);
		while(!Metodos.validacion(Patrones.isbn.getNombre(), isbn+""))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			isbn = comprobacionDatoInt(teclado);
		}
		
		Libro libro = new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);
	
		return libro;
	}

	/**
	 * Menu para listar y operar las opciones de los ficheros de extension xml.
	 * @param opcion
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean menuXml(int opcion, Scanner teclado) {
		boolean correcto = false;
		char letra;
		try {
			ArrayList<Libro> listaLibro = new ArrayList<Libro>();
			switch (opcion) {

			case 1:
				if (Variables.ficheroXml.exists() == false) {

					System.out.println("el fichero no existe");

					System.out.println("desea crear un fichero base  S/N");
					letra = teclado.next().charAt(0);

					if (letra == 's') {
						modelo.crearXml.generateXml(Variables.urlXml);
						System.out.println("libro base creado");
					}
				} else {
					listaLibro = modelo.leerPrincipalXml.leerPrincipal(listaLibro, Variables.urlXml);
					Metodos.listar(listaLibro);
					correcto = true;
				}
				break;

			case 2:

				if (!Variables.ficheroXml.exists()) {
					modelo.crearXml.generateXml(Variables.urlXml);
				}
				crearLibroXml.crearLibro(teclado, Variables.urlXml);
				correcto = true;
				break;

			case 3:
				Metodos.eliminarFichero(teclado, ".xml");
				correcto = true;
				break;
			case 4:
				System.out.print("Escriba el nombre del archivo: ");
				String nombre = teclado.next();
				String prefix = "";
				if (Metodos.isWindows())
					prefix = ".\\Ficheros\\";
				else if(Metodos.isUnix())
					prefix = "./Ficheros/";
				String url = prefix+nombre+".xml";
				
				File archivo = new File(url);
				
				if (!archivo.exists()) {
					modelo.crearXml.generateXml(url);
					Variables.urlXml = url;
					Variables.ficheroXml = archivo;
				}
				else
				{
					System.out.println("Archivo ya existe.");
					Variables.urlXml = url;
					Variables.ficheroXml = archivo;
				}
				break;
			default:
				System.out.println("opcion incorrecta");
				correcto = false;
			}

		} catch (Exception e) {
			System.out.println("datos incorrectos");
			correcto = false;
		}
		return correcto;

	}

	/**
	 * Menu para listar y operar las opciones de los ficheros de extension txt.
	 * @param opcion
	 * @param teclado
	 * @return boolena 
	 */
	public static boolean menuTxt(int opcion, Scanner teclado) {
		boolean confirmacionEscribir = true;
		boolean correcto = false;
		ArrayList<Libro> lista = new ArrayList<Libro>();
		lista = Metodos.cargarLista(Variables.ficheroTxt, lista);

		try {
			if (opcion == 1) {
				Metodos.listar(lista);
				correcto = true;
			} else if (opcion == 2) {
				while (confirmacionEscribir) {
					lista.add(crearLibro(teclado));
					System.out.println("�Quiere escribir otro libro? s/n");
					confirmacionEscribir = confirmacionSN(teclado);
					correcto = true;
				}

				Metodos.escribir(lista);
			}else if (opcion == 3) {
				Metodos.eliminarFichero(teclado, ".txt");
				correcto = true;
			}
			else if(opcion == 4)
			{
				System.out.print("Escriba el nombre del archivo: ");
				String nombre = teclado.next();
				String prefix = "";
				if (Metodos.isWindows())
					prefix = ".\\Ficheros\\";
				else if(Metodos.isUnix())
					prefix = "./Ficheros/";
				String url = prefix+nombre+".txt";
				
				File archivo = new File(url);
				
				if(!archivo.exists())
				{
					archivo.createNewFile();
					Variables.urlTxt = url;
					Variables.ficheroTxt = archivo;
				}
				else
				{
					System.out.println("El archivo ya existe.");
					Variables.urlTxt = url;
					Variables.ficheroTxt = archivo;
				}
			}
		} catch (Exception e) {
			System.out.println("error menuTxt");
			e.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	/**
	 * Menu para listar y operar las opciones de los ficheros de extension csv.
	 * @param opcion
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean menuCsv(int opcion, Scanner teclado) {
		boolean correcto = false;
		switch (opcion) {

		case 1:

			Metodos.listar(ficheroCsv.cargarCsv(teclado));
			correcto = true;
			break;

		case 2:

			ficheroCsv.crearArchivoCSV(teclado);
			correcto = true;
			break;
			
		case 3:
			
			Metodos.eliminarFichero(teclado, ".csv");
			correcto = true;
			
			break;
			
		case 4:

			System.out.print("Escriba el nombre del archivo: ");
			String nombre = teclado.next();
			String prefix = "";
			if (Metodos.isWindows())
				prefix = ".\\Ficheros\\";
			else if(Metodos.isUnix())
				prefix = "./Ficheros/";
			String url = prefix+nombre+".csv";
			
			File archivo = new File(url);
			
			if(!archivo.exists())
			{
				try {
					archivo.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Variables.urlCsv = url;
				Variables.ficheroCsv = archivo;
			}
			else
			{
				System.out.println("Archivo ya existe.");
				Variables.urlCsv = url;
				Variables.ficheroCsv = archivo;
			}
		
			break;
			
		}
		return correcto;
	}

	/**
	 * Metodo temporal
	 */
	public static int comprobacionDatoInt(Scanner teclado) {
		int parametro = 0;
		boolean repetir = true;

		do {
			try {
				parametro = teclado.nextInt();
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
	 * Metodo temporal
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
}