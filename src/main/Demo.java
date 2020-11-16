package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Libro;
import modelo.Metodos;
import modelo.Patrones;
import modelo.Variables;

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
				Metodos.generateXml(Variables.urlXml);
			if (!Variables.ficheroTxt.exists())
				Variables.ficheroTxt.createNewFile();
			do {
				menu(teclado);
				System.out.println("¿Quiere hacer otras operaciones? s/n");
			} while (Metodos.confirmacionSN(teclado));
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
		System.out.println("¿Desea modificar/crear/eliminar un fichero?");
		
		if(Metodos.confirmacionSN(teclado))
		{
			System.out.println("Elige alguna de las opciones: ");
			System.out.println("1) Xml");
			System.out.println("2) txt");
			System.out.println("3) Csv");
			respuestaOpcionesTipo = Metodos.entradaInt(1, 3, teclado);
	
			System.out.println("¿Que desea hacer? ");
			System.out.println("1. Leer");
			System.out.println("2. Crear libro");
			System.out.println("3. Eliminar archivo");
			System.out.println("4. Crear archivo / Seleccionar archivo");
			if (respuestaOpcionesTipo == 1) {
				respuestaOpcionesAccion = Metodos.entradaInt(1, 4, teclado);
	
				menuXml(respuestaOpcionesAccion, teclado);
				correcto = true;
			} else if (respuestaOpcionesTipo == 2) {
				respuestaOpcionesAccion = Metodos.entradaInt(1, 4, teclado);
	
				menuTxt(respuestaOpcionesAccion, teclado);
				correcto = true;
			} else if (respuestaOpcionesTipo == 3) {
				respuestaOpcionesAccion = Metodos.entradaInt(1, 4, teclado);
	
				menuCsv(respuestaOpcionesAccion, teclado);
				correcto = true;
			}
			
		}
		else
		{
			System.out.println("Elige alguna de las siguientes opciones: ");
			System.out.println("1) Cambiar permisos.");
			System.out.println("2) Mover fichero.");
			respuestaOpcionesTipo = Metodos.entradaInt(1, 2, teclado);
			
			if (respuestaOpcionesTipo == 1) {
				
				Metodos.cambioPermiso(teclado);
				
			} else if (respuestaOpcionesTipo == 2) {
				moverFichero(teclado);
				correcto = true;
			}
		}
	return correcto;
	}
	
	/**
	 * metodo para crearLibro
	 * @param teclado
	 * @return objeto Libro
	 */
	public static Libro crearLibro(Scanner teclado) {
		String titulo, editorial, notas, materias, isbn;
		double altura = 0.0;
		int paginas = 0;
	
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
		altura = Metodos.comprobacionDatoDouble(teclado);
		while(!Metodos.validacion(Patrones.altura.getNombre(), altura+""))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			altura = Metodos.comprobacionDatoDouble(teclado);
		}
		
		System.out.print("Introduce las paginas: ");
		paginas = Integer.parseInt(Metodos.comprobacionDatoInt(teclado)+"");
		while(!Metodos.validacion(Patrones.paginas.getNombre(), paginas+""))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			paginas = Integer.parseInt(Metodos.comprobacionDatoInt(teclado)+"");
		}
		
		System.out.print("Introduce el isbn: ");
		isbn = teclado.nextLine();
		while(!Metodos.validacion(Patrones.isbn.getNombre(), isbn+""))
		{
			System.out.print("dato insertado incorrecto, vuelve ha intentarlo: ");
			isbn = teclado.nextLine();
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
						Metodos.generateXml(Variables.urlXml);
						System.out.println("libro base creado");
					}
				} else {
					listaLibro = Metodos.leerXml(Variables.urlXml);
					Metodos.listar(listaLibro);
					correcto = true;
				}
				break;

			case 2:

				if (!Variables.ficheroXml.exists()) {
					Metodos.generateXml(Variables.urlXml);
				}
				Metodos.crearLibro(teclado, Variables.urlXml);
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
					Metodos.generateXml(url);
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
					System.out.println("ï¿½Quiere escribir otro libro? s/n");
					confirmacionEscribir = Metodos.confirmacionSN(teclado);
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
					correcto = true;
				}
				else
				{
					System.out.println("El archivo ya existe.");
					Variables.urlTxt = url;
					Variables.ficheroTxt = archivo;
					correcto = true;
				}
			}
		} catch (Exception e) {
			System.out.println("error menuTxt");
//			e.printStackTrace();
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
		final String extension = ".csv";
		switch (opcion) {

		case 1:

			Metodos.listar(Metodos.cargarCsv(teclado));
			correcto = true;
			break;

		case 2:

			Metodos.crearArchivoCSV(teclado);
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
			String url = prefix+nombre+extension;
			
			File archivo = new File(url);
			
			if(!archivo.exists())
			{
				try {
					archivo.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Variables.urlCsv = url;
				Variables.ficheroCsv = archivo;
				correcto=true;
			}
			else
			{
				System.out.println("Archivo ya existe.");
				Variables.urlCsv = url;
				Variables.ficheroCsv = archivo;
				correcto=false;
			}
		
			break;
			
		}
		return correcto;
	}

	/**
	 * Metodo para mover fichero cuando el metodo consigue 2 rutas
	 * 1 del fichero y otro de la carpeta este metodo ejecutara
	 * un comando en cmd para mover el archivo.
	 * @param teclado
	 */
	public static boolean moverFichero(Scanner teclado)
	{
		//D:\basura
		//D:\Steff\Reto\ADTeam3\Ficheros\abcdef.csv
		boolean control = true;
		String urlDefecto = "";
		if(Metodos.isWindows())
			urlDefecto= ".\\Ficheros";
		else if(Metodos.isUnix())
			urlDefecto= "./Ficheros";
		File archivoParaMover;
		File lugarMovida;
		
		archivoParaMover = Metodos.encontrarFichero(teclado, urlDefecto);
		lugarMovida = Metodos.encontrarDirectorio(teclado, urlDefecto);
		
		System.out.println("El fichero " + archivoParaMover.getAbsolutePath() + " se movera en el directorio " + lugarMovida.getAbsolutePath() + " ¿Desea confirmar la accion?");
		if(Metodos.confirmacionSN(teclado))
		{
			ProcessBuilder test = new ProcessBuilder(); 
			test = test.command("CMD", "/C", "move " + archivoParaMover.getAbsolutePath() + " " + lugarMovida.getAbsolutePath());
			try 
			{
				Process p = test.start();
				InputStream is = p.getInputStream(); 
				is.close(); 
				System.out.println("Fichero se movio correctamente.");
				control = true;
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("No se ha hecho ningun cambio.");
			archivoParaMover = null;
			lugarMovida = null;
			control = false;
		}
		return control;
	}
	
	

}
