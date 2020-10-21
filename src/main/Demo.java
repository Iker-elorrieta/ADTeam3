package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import modelo.Libro;
import modelo.Metodos;
import modelo.Patrones;
import modelo.Utilidades;
import modelo.Variables;
import modelo.ficheroCsv;
import modelo.leerPrincipalXml;

public class Demo {

//	public static Scanner teclado = new Scanner(System.in);
 
	/* 
	 * La clase main donde se empieza el programa.
	 */
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		try {
	  
			if (Metodos.isWindows()) {
 
				Variables.urlTxt = ".\\Ficheros\\Fichero1.txt";
				Variables.urlXml = ".\\Ficheros\\libreria.xml";
				Variables.urlCsv = ".\\Ficheros\\Fichero3.csv";
				 
				Variables.ficheroTxt = new File(Variables.urlTxt);
				Variables.ficheroXml = new File(Variables.urlXml);
				Variables.ficheroCsv = new File(Variables.urlCsv);
			} else if (Metodos.isUnix()){ 
				Variables.urlTxt = "./Ficheros/Fichero1.txt";  
				Variables.urlXml = "./Ficheros/libreria.xml";
				Variables.urlCsv = "./Ficheros/Fichero3.csv";
				
				Variables.ficheroTxt = new File(Variables.urlTxt);
				Variables.ficheroXml = new File(Variables.urlXml);
				Variables.ficheroCsv = new File(Variables.urlCsv);
			}	
			
			do {
				menu(teclado);
				System.out.println("¿Quiere hacer otras operaciones? s/n");
			} while (confirmacionSN(teclado));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} 
		
	}

	/*
	 * Menu donde se encuentra las acciones que quiere realizar el cliente.
	 */
	public static boolean menu(Scanner teclado) {
 
		boolean correcto = false;
		int respuestaOpcionesTipo;
		int respuestaOpcionesAccion;
		boolean confirmacionEscribir;

		System.out.println("Elige alguna de las opciones: ");
		System.out.println("1) Xml");
		System.out.println("2) txt");
		System.out.println("3) Csv"); 
		respuestaOpcionesTipo = entradaInt(1, 3,teclado);

		if (respuestaOpcionesTipo == 1) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. Crear Xml");
			System.out.println("2. leer Xml");
			System.out.println("3. modificar Xml");
			System.out.println("4. eliminar Xml");
			respuestaOpcionesAccion = entradaInt(1, 4,teclado);

			menuXml(respuestaOpcionesAccion,teclado);
			correcto = true;
		} else if (respuestaOpcionesTipo == 2) {

			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer");
			System.out.println("2. añadir");
			System.out.println("3. modificar");
			System.out.println("4. eliminar");
			respuestaOpcionesAccion = entradaInt(1, 4,teclado);

			menuTxt(respuestaOpcionesAccion,teclado);
			correcto = true;
		} else if (respuestaOpcionesTipo == 3) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer");
			System.out.println("2. crear");
			System.out.println("3. modificar");
			System.out.println("4. eliminar");
			respuestaOpcionesAccion = entradaInt(1, 4,teclado);

			menuCsv(respuestaOpcionesAccion,teclado);
			correcto = true;
		}
		return correcto;
	}

	/*
	 * Metodo para validar la entrada de numeros por teclado y controlar las
	 * exceptciones.
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
	public static boolean confirmacionSN(Scanner teclado) {
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
	public static void crearLibro(Scanner teclado) throws IOException {
		final String NEXT_LINE = "\n";
		String titulo;
		String editorial, paginas;
		String altura;
		String notas;
		String isbn;
		String materias;
		boolean seguir;
		
		do {
			System.out.println("Inserte el titulo: ");
			if (Utilidades.validar(Patrones.titulo.getNombre(), titulo = teclado.nextLine())) {
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
	
		do {
			System.out.println("Inserte el editorial: ");
	
			if (Utilidades.validar(Patrones.editorial.getNombre(), editorial = teclado.nextLine())) {
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
	
		do {
			System.out.println("Inserte las paginas: ");
			if (Utilidades.validar(Patrones.paginas.getNombre(), paginas = teclado.nextLine())) {
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
	
		do {
			System.out.println("Inserte la altura: ");
			if (Utilidades.validar(Patrones.altura.getNombre(), altura = teclado.nextLine())) {
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
	
		do {
			System.out.println("Inserte las notas: ");
			if (Utilidades.validar(Patrones.notas.getNombre(), notas = teclado.nextLine())) {
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
	
		do {
			System.out.println("Inserte el isbn: ");
			if (Utilidades.validar(Patrones.isbn.getNombre(), isbn = teclado.nextLine())) {
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
	
		do {
			System.out.println("Inserte las materias: ");
			if (Utilidades.validar(Patrones.materias.getNombre(), materias = teclado.nextLine())) {
	
				seguir = true;
			} else {
				System.out.println("Datos no validos");
				seguir = false;
			}
		} while (!seguir);
//		Libro libro = new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);
	}

	/*
	 * Menu para listar y operar las opciones de los ficheros de extension xml.
	 */
	public static boolean menuXml(int opcion,Scanner teclado) {
		boolean correcto = false;
		try {
			int paginas, isbn;
			double altura;
			String titulo, editorial, materias, notas;
			char letra;
			Libro libros = new Libro();
			ArrayList<Libro> listaLibro = new ArrayList<Libro>();
			listaLibro = modelo.leerPrincipalXml.leerPrincipal(listaLibro, Variables.urlTxt);
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

					listaLibro.add(libros);
					System.out.println(libros.mostrar());

					System.out.println("Desea crear mas libros S/N");
					letra = teclado.next().charAt(0);
					teclado.nextLine();

				} while (letra == 's' || letra == 'S');

				modelo.crearXml.crearXml(listaLibro);
				correcto = true;
				break;

			case 2:

				
				correcto = true;
				Metodos.listar(listaLibro);
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
	public static boolean menuTxt(int opcion,Scanner teclado) {
		boolean correcto = false;
		boolean confirmacionEscribir = true;
		ArrayList<Libro> lista = new ArrayList<Libro>();
		lista = Metodos.cargarLista(Variables.ficheroTxt, lista);
		try {
			if (opcion == 1) {
				Metodos.listar(lista);
				correcto = true;
			} else if (opcion == 2) {
				while(confirmacionEscribir) {
					teclado.nextLine();
					crearLibro(teclado);
					System.out.println("¿Quiere escribir un otro libro? s/n");
					confirmacionEscribir = confirmacionSN(teclado);
					correcto = true;
				}
			}
			else if (opcion == 3) 
			{
				System.out.println("¿Que libro quiere modificar?");
				Metodos.listar(lista);
				System.out.println("Escriba el numero: ");
				Libro libro = lista.get((entradaInt(1,lista.size(),teclado))-1);
				System.out.println(libro.mostrar());
				System.out.println("¿Que campo quiere modificar? escriba el numero del campo: ");
				int respuesta = entradaInt(1,7,teclado);
				switch (respuesta) {
				case 1:
					System.out.println("Escriba el nuevo titulo: ");
					libro.setTitulo(teclado.nextLine());
					break;
				case 2:
					System.out.println("Escriba el nuevo editorial: ");
					libro.setEditorial(teclado.nextLine());
					break;
				case 3:
					System.out.println("Escriba el nuevo numero de paginas: ");
					libro.setPaginas(teclado.nextInt());
					teclado.nextLine();
					break;
				case 4:
					System.out.println("Escriba la nueva altura: ");
					libro.setAltura(teclado.nextDouble());
					teclado.nextLine();
					break;
				case 5:
					System.out.println("Escriba las nuevas notas: ");
					libro.setNotas(teclado.nextLine());
					break;
				case 6:
					System.out.println("Escriba el nuevo isbn: ");
					libro.setIsbn(teclado.nextInt());
					teclado.nextLine();
					break;
				case 7:
					System.out.println("Escriba la nueva materia: ");
					libro.setMaterias(teclado.nextLine());
					break;
				default:
					break;
				}
				Variables.posicionNumero = 0;
				Metodos.escribir(lista, false);
				
			}
			else if(opcion == 4)
			{
				if (Variables.ficheroTxt.delete()) {
					System.out.println("Fichero de texto borrado.");
				} else {
					System.out.println("No se ha borrado el fichero.");
				}
				correcto = true;
			}
		} catch (Exception e) {
			System.out.println("error menuTxt");
			e.printStackTrace();
			correcto = false;
		}

		return correcto;
	}

	/*
	 * Menu para listar y operar las opciones de los ficheros de extension csv.
	 */
	public static void menuCsv(int opcion,Scanner teclado) {
		switch (opcion) {

		case 1:

			ficheroCsv.leerCsv(teclado);
			break;

		case 2:

			ficheroCsv.crearArchivoCSV(teclado);

			break;
		case 3:

			ficheroCsv.ModificarFichero(teclado);

			break;
		case 4:
			ficheroCsv.EliminarficheroCsv();

			break;
		}

	}



}