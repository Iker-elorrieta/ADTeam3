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

