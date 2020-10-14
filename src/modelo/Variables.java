package modelo;

import java.io.File;
import java.util.ArrayList;

/*
 * Clase donde se apuntan las variables que se usan en todo el programa.
 *
 */
public class Variables {
	
	public static String urlTxt = ".\\Ficheros\\Fichero1.txt";
	public static String urlXml = ".\\Ficheros\\libreria.xml";
	public static String urlCsv = ".\\Ficheros\\prueba.csv";
	public static File ficheroTxt = new File(urlTxt);
	public static File ficheroXml = new File(urlXml);
	public static File ficheroCsv = new File(urlCsv);
	public static ArrayList<Libro> listaLibros = new ArrayList<Libro>();
	/*
	 * Variable para mantener la cuenta desde donde empezar ha insertar libros en el fichero.
	 */
	public static int posicionNumero;
	
}