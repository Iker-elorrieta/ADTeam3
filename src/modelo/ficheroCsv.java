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

	/**
	 * Metodo que carga datos Csv
	 * @param sc
	 * @return ArrayList<Libro>
	 */
	public static ArrayList<Libro> cargarCsv(Scanner sc) {

		BufferedReader br = null;
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
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
					e.printStackTrace();
				}
			}
		}
		return listaLibro;
	}

	/**
	 * Metodo que crea Archivos Csv
	 * @param teclado
	 * @return boolean 
	 */
	public static boolean crearArchivoCSV(Scanner teclado) {

		boolean seguir = false;
		try {
			FileWriter fw = new FileWriter(Variables.urlCsv, true);
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
				System.out.println("desea crear otro libro?  S/N");

				seguir = Demo.confirmacionSN(teclado);

			} while (seguir);
			fw.close();

		} catch (IOException e) {
			System.out.println("Error al crear fichero ");
			e.printStackTrace();
			seguir = true;
		}
		return seguir;
	}

}
