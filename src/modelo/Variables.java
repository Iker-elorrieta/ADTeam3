package modelo;

import java.io.File;
import java.util.ArrayList;

/**
 * Clase donde se apuntan las variables que se usan en todo el programa.
 */
public class Variables { 
	
	public static String urlTxt= "";
	public static String urlXml= "";
	public static String urlCsv= "";
	public static File ficheroTxt = new File(urlTxt);
	public static File ficheroXml= new File(urlXml);
	public static File ficheroCsv= new File(urlCsv);
	static ArrayList<Libro> listaLibrosTxt = new ArrayList<Libro>();
	static ArrayList<Libro> listaLibrosXml= new ArrayList<Libro>();
	static ArrayList<Libro> listaLibrosCsv= new ArrayList<Libro>();
	public static String OS = System.getProperty("os.name").toLowerCase();
	/*
	 * Variable para mantener la cuenta desde donde empezar ha insertar libros en el fichero.
	 */
	public static int posicionNumero;
	final public static String versionXml = "1.0";
}