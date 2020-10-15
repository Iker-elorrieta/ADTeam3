package modelo;

import java.io.File;
import java.util.ArrayList;

/*
 * Clase donde se apuntan las variables que se usan en todo el programa.
 *
 */
public class Variables { 
	
	public static String urlTxt;
	public static String urlXml;
	public static String urlCsv;
	public static File ficheroTxt;
	public static File ficheroXml;
	public static File ficheroCsv;
	public static ArrayList<Libro> listaLibros = new ArrayList<Libro>();
	/*
	 * Variable para mantener la cuenta desde donde empezar ha insertar libros en el fichero.
	 */
	public static int posicionNumero;
	
}