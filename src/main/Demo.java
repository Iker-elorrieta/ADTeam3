package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import modelo.Libro;
import modelo.Metodos;
import modelo.Utilidades;
import modelo.Variables;
import modelo.ficheroCsv;

public class Demo {

	public static Scanner teclado = new Scanner(System.in);

	/*
	 * La clase main donde se empieza el programa.
	 */
	public static void main(String[] args) throws IOException {
		if (Variables.ficheroTxt.createNewFile())
			System.out.println("Se creo un archivo nuevo.");
		else
			Metodos.cargarLista(Variables.ficheroTxt);

		System.out.println("eliga el sistema operativo");
		System.out.println("1- windows");
		System.out.println("2- Linux");
		int opcion = entradaInt(1, 2);
 
		if (opcion == 1) {

			Variables.urlTxt = ".\\Ficheros\\Fichero1.txt";
			Variables.urlXml = ".\\Ficheros\\libreria.xml";
			Variables.urlCsv = ".\\Ficheros\\Fichero3.csv";
		} else {
			Variables.urlTxt = "./Ficheros/Fichero1.txt";
			Variables.urlXml = "./Ficheros/libreria.xml";
			Variables.urlCsv = "./Ficheros/Fichero3.csv";

		}
		do {
			menu();
			System.out.println("¿Quiere hacer otras operaciones? s/n");
		} while (confirmacionSN());
	}

	/*
	 * Menu donde se encuentra las acciones que quiere realizar el cliente.
	 */
	public static boolean menu() {

		boolean correcto = false;
		int respuestaOpcionesTipo;
		int respuestaOpcionesAccion;
		boolean confirmacionEscribir;

		System.out.println("Elige alguna de las opciones: ");
		System.out.println("1) Xml");
		System.out.println("2) txt");
		System.out.println("3) Csv"); 
		respuestaOpcionesTipo = entradaInt(1, 3);

		if (respuestaOpcionesTipo == 1) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. Crear Xml");
			System.out.println("2. leer Xml");
			System.out.println("3. modificar Xml");
			System.out.println("4. eliminar Xml");
			respuestaOpcionesAccion = entradaInt(1, 4);

			menuXml(respuestaOpcionesAccion);
			correcto = true;
		} else if (respuestaOpcionesTipo == 2) {

			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer");
			System.out.println("2. modificar");
			System.out.println("3. eliminar");
			respuestaOpcionesAccion = entradaInt(1, 3);

			menuTxt(respuestaOpcionesAccion);
			correcto = true;
		} else if (respuestaOpcionesTipo == 3) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer");
			System.out.println("2. crear");
			System.out.println("3. modificar");
			System.out.println("4. eliminar");
			respuestaOpcionesAccion = entradaInt(1, 4);

			menuCsv(respuestaOpcionesAccion);
			correcto = true;
		}
		return correcto;
	}

	/*
	 * Metodo para validar la entrada de numeros por teclado y controlar las
	 * exceptciones.
	 */
	public static int entradaInt(int min, int max) {
		int result = 0;
		do {
			try {
				result = teclado.nextInt();
				if (result < min || result > max) {
					System.out.println("Tiene que insertar un numero entre " + min + " y " + max);
					teclado.nextLine();
				}
			} catch (InputMismatchException a) {
				// a.printStackTrace();
				System.out.println("Tiene que insertar un numero:");
				teclado.nextLine();
			}
		} while (result < min || result > max);
		teclado.nextLine();
		return result;
	}

	/*
	 * Metodo para preguntar al cliente si quiere seguir o no.
	 */
	public static boolean confirmacionSN() {
		String result;

		do {
			result = teclado.next();
			if (result.length() > 1 || result.length() < 1) {
				System.out.println("Dato incorrecto, Vuelve ha insertarlo.");
			} else {
				if (result.toUpperCase().equals("S")) {
					return true;
				} else if (result.toUpperCase().equals("N")) {
					return false;
				}
				System.out.println("Tiene que insertar S o N.");
			}

		} while (!result.toUpperCase().equals("N") && !result.toUpperCase().equals("S"));

		return false;
	}

	/*
	 * Metodo para la creacion de un nuevo objeto libro.
	 */
	public static void crearLibro() throws IOException {
		String titulo;
		String editorial;
		int paginas;
		Double altura;
		String notas;
		int isbn;
		String materias;

		System.out.println("Inserte el titulo: ");
		titulo = teclado.nextLine();

		System.out.println("Inserte el editorial: ");
		editorial = teclado.nextLine();

		System.out.println("Inserte las paginas: ");
		paginas = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Inserte al altura: ");
		altura = teclado.nextDouble();
		teclado.nextLine();

		System.out.println("Inserte las notas: ");
		notas = teclado.nextLine();

		System.out.println("Inserte el isbn: ");
		isbn = teclado.nextInt();
		teclado.nextLine();

		System.out.println("Inserte la materia: ");
		materias = teclado.nextLine();

		Libro libro = new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);

		System.out.println(
				"¿Quiere confirmar los datos del libro? Si elige n el libro no se guardara y tendra que volver ha insertarlo.");
		System.out.println(libro.mostrar());
		if (confirmacionSN()) {
			Variables.listaLibros.add(libro);
			Metodos.escribir(Variables.listaLibros);
		}

	}

	/*
	 * Menu para listar y operar las opciones de los ficheros de extension xml.
	 */
	public static boolean menuXml(int opcion) {
		boolean correcto = false;
		try {
			int paginas, isbn;
			double altura;
			String titulo, editorial, materias, notas;
			char letra;
			Libro libros = new Libro();
			ArrayList<Libro> lisataLibro = new ArrayList<>();

			switch (opcion) {

			case 1:

				do {
					System.out.println("ingrese el titulo");
					titulo = teclado.nextLine();
					System.out.println("ingrese la editorial");
					editorial = teclado.nextLine();
					System.out.println("ingrese las paginas");
					paginas = teclado.nextInt();
					teclado.nextLine();
					System.out.println("ingrese la altura");
					altura = teclado.nextDouble();
					teclado.nextLine();
					System.out.println("ingrese las notas");
					notas = teclado.nextLine();
					System.out.println("ingrese el isbn");
					isbn = teclado.nextInt();
					teclado.nextLine();
					System.out.println("ingrese la materia");
					materias = teclado.nextLine();

					libros = new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);

					lisataLibro.add(libros);
					System.out.println(libros.mostrar());

					System.out.println("Desea crear mas libros S/N");
					letra = teclado.next().charAt(0);
					teclado.nextLine();

				} while (letra == 's' || letra == 'S');

				modelo.crearXml.crearXml(lisataLibro);
				correcto = true;
				break;

			case 2:

				modelo.leerPrincipalXml.leerPrincipal();
				correcto = true;
				break;
			case 3:
				modelo.modificarXml.modXml();
				break;

			case 4:

				File fichero = new File(Variables.urlXml);

				if (fichero.delete())
					System.out.println("El fichero ha sido borrado satisfactoriamente");
				else
					System.out.println("El fichero no puede ser borrado");
				correcto = true;
				break;

			default:
				System.out.println("opcion incorrecta");
				correcto = true;
			}

		} catch (Exception e) {
			System.out.println("datos incorrectos");
		}
		return correcto;

	}

	/*
	 * Menu para listar y operar las opciones de los ficheros de extension txt.
	 */
	public static boolean menuTxt(int opcion) {
		boolean correcto = false;
		try {
			if (opcion == 1) {
				Metodos.listar(Variables.listaLibros);
				correcto = true;
			} else if (opcion == 2) {
				System.out.println("¿Quiere escribir un nuevo libro? s/n");
				boolean confirmacionEscribir = confirmacionSN();
				if (confirmacionEscribir) {
					teclado.nextLine();
					crearLibro();
					correcto = true;
				}
			}
			if (opcion == 3) {
				if (Variables.ficheroTxt.delete()) {
					System.out.println("Fichero de texto borrado.");
				} else {
					System.out.println("No se ha borrado el fichero.");
				}
				correcto = true;
			}
		} catch (Exception e) {
			System.out.println("error menuTxt");
			correcto = false;
		}

		return correcto;
	}

	/*
	 * Menu para listar y operar las opciones de los ficheros de extension csv.
	 */
	public static void menuCsv(int opcion) {
		switch (opcion) {

		case 1:

			ficheroCsv.leerCsv();
			break;

		case 2:

			ficheroCsv.crearArchivoCSV();

			break;
		case 3:

			ficheroCsv.ModificarFichero();

			break;
		case 4:
			ficheroCsv.EliminarficheroCsv();

			break;
		}

	}



}