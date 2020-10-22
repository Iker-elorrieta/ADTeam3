package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ficheroCsv {
	public static final String SEPARATOR = ";";

	public static ArrayList cargarCsv(Scanner teclado) {

		BufferedReader br = null;
		String nombreFichero;
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		
		String[] fields = null ;
		ArrayList<String[]> lista = new ArrayList<String[]>();
		boolean seguir;
		try {
			System.out.println("introduzca nombre del fichero");
			nombreFichero = teclado.nextLine();
			
			String ruta = ".\\Ficheros\\"+ nombreFichero + ".csv";
			File archivo = new File(ruta);
			if (!archivo.exists()) {
				System.out.println("El fichero no existe");
				seguir = true;
			} else {
				seguir = false;
				br = new BufferedReader(new FileReader(ruta));
				String line = br.readLine();
				while (null != line) {
					 fields = line.split(SEPARATOR);
					
					 lista.add(fields);
					 
					line = br.readLine();
				}
				for(int linea = 0;linea<lista.size();linea++) {
								
					Libro libro = new Libro();
					
					libro.setTitulo(lista.get(linea)[0]);
					libro.setEditorial(lista.get(linea)[1]);
					libro.setPaginas(Integer.parseInt(lista.get(linea)[2]));
					libro.setAltura(Double.parseDouble(lista.get(linea)[3]));
					libro.setNotas(lista.get(linea)[4]);
					libro.setIsbn(Integer.parseInt(lista.get(linea)[5]));
					libro.setMaterias(lista.get(linea)[6]);
					listaLibro.add(libro);				
				}
		
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error al leer fichero");
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return listaLibro;
	}

}
