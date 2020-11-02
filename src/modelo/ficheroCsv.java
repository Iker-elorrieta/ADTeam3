<<<<<<< HEAD
package Modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class ficheroCsv {
	public static final String SEPARATOR=";";
	   public static final String QUOTE="\"";
	public void leerCsv(String rutaFichero) {
	   //public static void main(String[] args) { 
		BufferedReader br = null;
	      
	      try {
	         
	         //br =new BufferedReader(new FileReader("C:\\Users\\in2dam-b\\Desktop\\prueba.csv"));
	    	  br =new BufferedReader(new FileReader(rutaFichero));
	         String line = br.readLine();
	         while (null!=line) {
	            String [] fields = line.split(SEPARATOR);
	            System.out.println(Arrays.toString(fields));
	            
	            
	            line = br.readLine();
	         }
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.out.println("Error al leer fichero");
	      } finally {
	         if (null!=br) {
	            try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	         }
	}
	}
	/*public static void main(String[] args) {
		final String nombreDeArchivo = "./files/archivo.csv";
		crearArchivoCSV(nombreDeArchivo);
	}

	private static void crearArchivoCSV(String nombreDeArchivo) {
		// this also can be "\t" for tab delimitador
		crearArchivoCSV(nombreDeArchivo, ",");
	}

	private static void crearArchivoCSV(String file, String delim) {
		final String NEXT_LINE = "\n";
		try {
			FileWriter fw = new FileWriter(file);

			fw.append("testing").append(delim)
			fw.append("123").append(NEXT_LINE);

			fw.append("value1");
			fw.append(delim);
			fw.append("312");
			fw.append(NEXT_LINE);

			fw.append("anotherthing,888\n");

			fw.flush();
			fw.close();
		} catch (IOException e) {
			// Error al crear el archivo, por ejemplo, el archivo 
			// está actualmente abierto.
			e.printStackTrace();
		}
	}*/
	}

=======
package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

import main.Demo;

public class ficheroCsv {

	public static final String SEPARATOR = ";";

	public static ArrayList<Libro> cargarCsv(Scanner sc) {

		BufferedReader br = null;
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();

		StringTokenizer fields = null;
		
		

		try {
			String ruta = Variables.urlCsv;
			File archivo = new File(ruta);
			if (!archivo.exists()) {
				System.out.println("El fichero no existe");
				
			} else {
			
				
				br = new BufferedReader(new FileReader(ruta));
				String line = br.readLine();
				StringTokenizer tokens;
				while (null != line) {
					tokens = new StringTokenizer(line, ";");

					Libro libro = new Libro();

					String titulo = tokens.nextToken();
					libro.setTitulo(titulo);
					String editorial = tokens.nextToken();
					libro.setEditorial(editorial);
					String paginas = tokens.nextToken();
					libro.setPaginas(Integer.parseInt(paginas));
					String altura = tokens.nextToken();
					libro.setAltura(Double.parseDouble(altura));
					String notas = tokens.nextToken();
					libro.setNotas(notas);
					String isbn = tokens.nextToken();
					libro.setIsbn(Integer.parseInt(isbn));
					String materias = tokens.nextToken();
					libro.setMaterias(materias);

					listaLibro.add(libro);
					line = br.readLine();
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

	public static boolean crearArchivoCSV(Scanner teclado) {

		boolean seguir = false;
		try {
			System.out.println("Introduce nombre del archivo");
			String nombreArchivo = teclado.nextLine();
			String ruta = ".\\Ficheros\\"+nombreArchivo+".csv";
			FileWriter fw = new FileWriter(ruta, true);
			File archivo = new File(ruta);

			if (archivo.exists()) {
				System.out.println("El fichero ya existe");
				
			} else {
				
			
			do {

				Libro libro = main.Demo.crearLibro(teclado);

				fw.append(libro.getTitulo()).append(SEPARATOR);

				fw.append(libro.getEditorial()).append(SEPARATOR);
				fw.append(String.valueOf(libro.getPaginas())).append(SEPARATOR);
				fw.append(String.valueOf(libro.getAltura())).append(SEPARATOR);
				fw.append(libro.getNotas()).append(SEPARATOR);
				fw.append(String.valueOf(libro.getIsbn())).append(SEPARATOR);
				fw.append(libro.getMaterias()).append(SEPARATOR + "\n");

				fw.flush();

				System.out.println("Fichero creado con exito");
				System.out.println("desea crear otro libro?");

				seguir = Demo.confirmacionSN(teclado);

			} while (seguir);
			fw.close();

		} }catch (IOException e) {
			System.out.println("Error al crear fichero ");
			e.printStackTrace();
			seguir = true;
		}
		return seguir;
	}

}
>>>>>>> branch 'Antonio' of https://github.com/Iker-elorrieta/ADTeam3.git
