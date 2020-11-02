package modelo;

import java.io.File;
import java.util.ArrayList;

/*
 * Clase donde se apuntan las variables que se usan en todo el programa.
 *
 */
<<<<<<< HEAD
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
	public static String OS = System.getProperty("os.name").toLowerCase();
=======
public class Variables {
	
	public static String urlTxt = ".\\Ficheros\\Fichero1.txt";
	public static String urlXml = ".\\Ficheros\\libreria.xml";
	public static String urlCsv = ".\\Ficheros\\prueba.csv";
	public static File ficheroTxt = new File(urlTxt);
	public static File ficheroXml = new File(urlXml);
	public static File ficheroCsv = new File(urlCsv);
	public static ArrayList<Libro> listaLibros = new ArrayList<Libro>();
>>>>>>> branch 'Antonio' of https://github.com/Iker-elorrieta/ADTeam3.git
	/*
	 * Variable para mantener la cuenta desde donde empezar ha insertar libros en el fichero.
	 */
	public static int posicionNumero;
	
}