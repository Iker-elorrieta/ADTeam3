package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Libro;
import modelo.Metodos;
import modelo.Variables;
import modelo.ficheroCsv;

public class Demo {

	/* 
	 * La clase main donde se empieza el programa.
	 */
	public static void main(String[] args) {
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

		System.out.println("Elige alguna de las opciones: ");
		System.out.println("1) Xml");
		System.out.println("2) txt");
		System.out.println("3) Csv"); 
		respuestaOpcionesTipo = entradaInt(1, 3,teclado);

		if (respuestaOpcionesTipo == 1) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer Xml");
			respuestaOpcionesAccion = entradaInt(1, 1,teclado);

			menuXml(respuestaOpcionesAccion,teclado);
			correcto = true;
		} else if (respuestaOpcionesTipo == 2) {

			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer");
			respuestaOpcionesAccion = entradaInt(1, 1,teclado);

			menuTxt(respuestaOpcionesAccion,teclado);
			correcto = true;
		} else if (respuestaOpcionesTipo == 3) {
			System.out.println("¿Que desea hacer?");
			System.out.println("1. leer");
			respuestaOpcionesAccion = entradaInt(1, 1,teclado);

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
	 * Menu para listar y operar las opciones de los ficheros de extension xml.
	 */
	public static boolean menuXml(int opcion,Scanner teclado) {
		boolean correcto = false;
		try {
			ArrayList<Libro> listaLibro = new ArrayList<Libro>();
			listaLibro = modelo.leerPrincipalXml.leerPrincipal(listaLibro, Variables.urlXml);
			switch (opcion) {

			case 1:
				
				Metodos.listar(listaLibro);
				correcto = true;
				break;
				
			default:
				System.out.println("opcion incorrecta");
				correcto = false;
			}
 
		} catch (Exception e) {
			System.out.println("datos incorrectos");
			correcto=false;
		}
		return correcto;

	}

	/*
	 * Menu para listar y operar las opciones de los ficheros de extension txt.
	 */
	public static boolean menuTxt(int opcion,Scanner teclado) {
		boolean confirmacionEscribir = true;
		boolean correcto = false;
		ArrayList<Libro> lista = new ArrayList<Libro>();
		lista = Metodos.cargarLista(Variables.ficheroTxt, lista);
		
		try {
		
			if (opcion == 1) {
				Metodos.listar(lista);
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

			Metodos.listar(ficheroCsv.cargarCsv(teclado));
			break;

		}

	}

}