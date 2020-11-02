
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
		boolean seguir;
		
		
		try {
			do {
		
				System.out.println("Introduce nombre del archivo");
				String nombreArchivo = sc.nextLine();
				String ruta = ".\\Ficheros\\"+nombreArchivo+".csv";
				File archivo = new File(ruta);
			if (!archivo.exists()) {
				System.out.println("El fichero no existe");
				seguir = true;
			} else {
				seguir = false;
				
				br = new BufferedReader(new FileReader(ruta));
				String line = br.readLine();
				StringTokenizer tokens;
				while (null != line) {
					tokens = new StringTokenizer(line,SEPARATOR );

					Libro libro = new Libro();

					
					libro.setTitulo(tokens.nextToken());
					
					libro.setEditorial(tokens.nextToken());
					
					libro.setPaginas(Integer.parseInt(tokens.nextToken()));
					
					libro.setAltura(Double.parseDouble(tokens.nextToken()));
					
					libro.setNotas(tokens.nextToken());
				
					libro.setIsbn(Integer.parseInt(tokens.nextToken()));
					
					libro.setMaterias(tokens.nextToken());

					listaLibro.add(libro);
					line = br.readLine();
				}
			}
			}while(seguir);

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
		String ruta;
		boolean seguir = false,repetir;
		try {
			do {
			System.out.println("Introduce nombre del archivo");
			String nombreArchivo = teclado.nextLine();
			if (Metodos.isWindows()) {
			ruta = ".\\Ficheros\\"+nombreArchivo+".csv";
			}else if (Metodos.isUnix()){
				ruta = "./Ficheros/"+nombreArchivo+".csv";
			}
		
			FileWriter fw = new FileWriter(ruta, true);
			File archivo = new File(ruta);

			if (archivo.exists()) {
				System.out.println("El fichero ya existe");
				repetir =  true;
			} else {
				
			repetir = false;
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
			}while(repetir);
		} }catch (IOException e) {
			System.out.println("Error al crear fichero ");
			e.printStackTrace();
			seguir = true;
		}
		return seguir;
	}

}

