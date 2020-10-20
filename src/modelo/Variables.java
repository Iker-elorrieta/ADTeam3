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
	static ArrayList<Libro> listaLibrosTxt = new ArrayList<Libro>();
	static ArrayList<Libro> listaLibrosXml= new ArrayList<Libro>();
	static ArrayList<Libro> listaLibrosCsv= new ArrayList<Libro>();
	public static ArrayList []listaLibrerias = {listaLibrosTxt,listaLibrosXml,listaLibrosCsv};
	/*
	 * Variable para mantener la cuenta desde donde empezar ha insertar libros en el fichero.
	 */
	public static int posicionNumero;
	
}