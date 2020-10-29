package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/*
 * Clase donde se apuntan los metodos que se usan en el programa.
 */
public class Metodos {

	/*
	 * Metodo para rellenar la lista del probrama con los libros apuntados en el
	 * fichero txt.
	 */
	public static ArrayList<Libro> cargarLista(File fichero, ArrayList<Libro> lista)  {
		boolean correcto=false;
		try (BufferedReader ficheroR = new BufferedReader(new FileReader(fichero));){
			ArrayList<String[]> contenido = new ArrayList<String[]>();
			String linea = "";
			while ((linea = ficheroR.readLine()) != null) {
				contenido.add(linea.split(";"));
			}
			Variables.posicionNumero=0;
			for (int i = 0; i < contenido.size(); i++) {
				Libro libro = new Libro();
					for (int y = 0; y < contenido.get(i).length; y++) {
						if (y == 0) {
							libro.setTitulo(contenido.get(i)[y]);
						} else if (y == 1) {
							libro.setEditorial(contenido.get(i)[y]);
						} else if (y == 2) {
							libro.setPaginas(Integer.parseInt(contenido.get(i)[y]));
						} else if (y == 3) {
							libro.setAltura(Double.parseDouble(contenido.get(i)[y]));
						} else if (y == 4) {
							libro.setNotas(contenido.get(i)[y]);
						} else if (y == 5) {
							libro.setIsbn(Integer.parseInt(contenido.get(i)[y]));
						} else if (y == 6) {
							libro.setMaterias(contenido.get(i)[y]);
						}
					}
					lista.add(libro);
					correcto=true;
					Variables.posicionNumero++;
			}
			correcto=true;
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
			correcto=false;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lista;
	}

	/*
	 * Metodo para insertar en el txt un libro.
	 */
	public static boolean escribir(ArrayList<Libro> listaLibros ) {
		boolean correcto = false;
		try (BufferedWriter fichero = new BufferedWriter(new FileWriter(Variables.urlTxt, true));)
		{
			for (int i = Variables.posicionNumero; i < listaLibros.size(); i++) {
				fichero.write(listaLibros.get(i).getTitulo() + ";" + listaLibros.get(i).getEditorial() + ";"
							+ listaLibros.get(i).getPaginas() + ";" + listaLibros.get(i).getAltura() + ";"
							+ listaLibros.get(i).getNotas() + ";" + listaLibros.get(i).getIsbn() + ";"
							+ listaLibros.get(i).getMaterias() + ";");
				fichero.newLine();
			}
			correcto=true;
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
			correcto=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return correcto;
	}
	
	/*
	 * Metodo para mostrar los libros actualmente en el fichero y el programa.
	 */
	public static boolean listar(ArrayList<Libro> listaLibros) {

		boolean correcto = false; 
		String titulo;
		String editorial;
		String paginas;
		String altura;
		String notas;
		String isbn;
		String materias;
		
		try {
			System.out.println("   Titulo" + "\t\t" + "Editorial" + "\t" + "Paginas" + "\t\t" + "Altura" + "\t\t" + "Notas"
					+ "\t\t" + "Isbn" + "\t\t" + "Materias");
			for (int i = 0; i < listaLibros.size(); i++) {
				titulo = listaLibros.get(i).getTitulo() + "        ";
				editorial = listaLibros.get(i).getEditorial() + "       ";
				paginas = listaLibros.get(i).getPaginas() + "        ";
				altura = listaLibros.get(i).getAltura() + "       ";
				notas = listaLibros.get(i).getNotas() + "       ";
				isbn = listaLibros.get(i).getIsbn() + "       ";
				materias = listaLibros.get(i).getMaterias() + "      ";

				System.out.println((i+1)+"   "+titulo.substring(0, 5) + "\t\t" + editorial.substring(0, 5) + "\t\t"
						+ paginas.substring(0, 2) + "\t\t" + altura.substring(0, 4) + "\t\t" + notas.substring(0, 5)
						+ "\t\t" + isbn.substring(0, 5) + "\t\t" + materias.substring(0, 5));
			}
			correcto=true;
		} catch (Exception e) {
			System.out.println("error listar libros");
		}

		return correcto;
	}
	
	public static boolean isWindows() 
	{
		if(Variables.OS.indexOf("win") >= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean isUnix() 
	{
		if(Variables.OS.indexOf("nix") >= 0 || Variables.OS.indexOf("nux") >= 0 || Variables.OS.indexOf("aix") > 0 )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}