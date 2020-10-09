package modelo;

import java.io.File;
import java.util.ArrayList;

public class Variables {
	public static String urlTxt = ".\\Ficheros\\Fichero1.txt";
	public static String urlXml = ".\\Ficheros\\Fichero2.xml";
	public static String urlCsv = ".\\Ficheros\\Fichero3.csv";
	public static File ficheroTxt = new File(urlTxt);
	public static File ficheroXml = new File(urlXml);
	public static File ficheroCsv = new File(urlCsv);
	public static ArrayList<Libro> listaLibros = new ArrayList<Libro>();
	
	
}