package modelo;

import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ficheroCsv {
	public static final String SEPARATOR = ";";
	public static final String QUOTE = "\"";

	
	

	public static ArrayList cargarCsv() {

		BufferedReader br = null;
		String nombreFichero;
		Scanner sc = new Scanner(System.in);
		ArrayList<Libro> listaLibro = new ArrayList<Libro>();
		
		String[] fields = null ;
		ArrayList<String[]> lista = new ArrayList<String[]>();
		boolean seguir;
		try {
			System.out.println("introduzca nombre del fichero");
			nombreFichero = sc.nextLine();
			
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
					//System.out.println(line);
					//System.out.println(Arrays.toString(fields));
					 lista.add(fields);
					 
					line = br.readLine();
				}
				for(int linea = 0;linea<lista.size();linea++) {
					
				
					Libro libro = new Libro();
					
					libro.setTitulo(fields[0]);
					libro.setEditorial(fields[1]);
					libro.setPaginas(Integer.parseInt(fields[2]));
					libro.setAltura(Double.parseDouble(fields[3]));
					libro.setNotas(fields[4]);
					libro.setIsbn(Integer.parseInt(fields[5]));
					libro.setMaterias(fields[6]);
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

	public static void crearArchivoCSV() {
		final String NEXT_LINE = ";";
		Scanner sc = new Scanner(System.in);
		String titulo;
		String editorial,strPaginas;
		int paginas,isbn;
		double altura = 0;
		String strAltura;
		String notas;
		String strIsbn;
		String materias;
		boolean seguir;
		try {
			System.out.println("introduzca nombre del fichero");
			String nombreFichero = sc.nextLine();
			
			String ruta = ".\\Ficheros\\"+ nombreFichero + ".csv";
			
			File archivo = new File(ruta);
			if (archivo.exists()) {
				System.out.println("El fichero ya existe");
				
			} else {
				
			FileWriter fw = new FileWriter(ruta);

			do {
				System.out.println("Inserte el titulo: ");
				titulo = sc.nextLine();
				seguir = true;
			} while (!seguir);

			do {
				System.out.println("Inserte el editorial: ");
				editorial = sc.nextLine();
				seguir= true;
			} while (!seguir);

			//do {
				System.out.println("Inserte las paginas: ");
				paginas = sc.nextInt();
				//strPaginas = String.valueOf(paginas);
				sc.nextLine();
				
			//} while (!seguir);

			//do {
				System.out.println("Inserte la altura: ");
				
				altura = sc.nextDouble();
				sc.nextLine();
					/*if (Utilidades.validar(Patrones.altura.getNombre(),strAltura)) {
						
					} else {
						System.out.println("Datos no validos");
						
					}*/
//				}else {
//					seguir = false;
//					System.out.println("introducir '.' si es decimal, no se admiten caracteres");
//				}
//				
//				
//			} while (!seguir);

			//do {
				System.out.println("Inserte las notas: ");
				notas = sc.nextLine();
//					if (Utilidades.validar(Patrones.notas.getNombre(), )) {
//					seguir = true;
//				} else {
//					System.out.println("Datos no validos");
//					seguir = false;
//				}
//			} while (!seguir);

//			do {
				System.out.println("Inserte el isbn: ");
				isbn = sc.nextInt();
				//strIsbn = String.valueOf(isbn);
				sc.nextLine();
				//seguir = true;
				/*if (Utilidades.validar(Patrones.isbn.getNombre(), strIsbn)) {
					
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}*/
			//} while (!seguir);

			//do {
				System.out.println("Inserte las materias: ");
				materias = sc.nextLine();
//				if (Utilidades.validar(Patrones.materias.getNombre(), )) {
//
//					seguir = true;
//				} else {
//					System.out.println("Datos no validos");
//					seguir = false;
//				}
//			} while (!seguir);
			Libro libro = new Libro(titulo,editorial,paginas,altura,notas,isbn,materias);
			
			
			fw/*.append("Titulo: ")*/.append(libro.getTitulo()).append(SEPARATOR);

			fw./*append("Editorial: ")*/append(libro.getEditorial()).append(SEPARATOR);
			fw./*append("Paginas: ")*/append(String.valueOf(libro.getPaginas())).append(SEPARATOR);
			fw./*append("Altura: ")*/append(String.valueOf(libro.getAltura())).append(SEPARATOR);
			fw./*append("Notas: ").*/append(libro.getNotas()).append(SEPARATOR);
			fw./*append("Isbn: ").*/append(String.valueOf(libro.getIsbn())).append(SEPARATOR);
			fw./*append("Materias: ").*/append(libro.getMaterias()).append(SEPARATOR);

			fw.flush();
			fw.close();
			System.out.println("Fichero creado con exito");
			}
		} catch (IOException e) {
			// Error al crear el archivo, por ejemplo, el archivo
			// está actualmente abierto.
			System.out.println("Error al crear fichero ");
			e.printStackTrace();
		}

		

	}

	public static void ModificarFichero() {
		Scanner sc = new Scanner(System.in);
		/*try {
			FileWriter fw = new FileWriter(rutaFichero);

			String titulo;
			System.out.println("Inserte titulo");
			titulo = sc.nextLine();
			if (titulo.equals("")) {
				titulo = libro.getTitulo();
			} else {
				fw.append(titulo);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		final String NEXT_LINE = "\n";
		
		String titulo;
		String editorial, paginas;
		String altura;
		String notas;
		String isbn;
		String materias;
		boolean seguir;
		try {
			System.out.println("introduzca nombre del fichero");
			String nombreFichero = sc.nextLine();
			
			String ruta = ".\\Ficheros\\"+ nombreFichero + ".csv";
			
			File archivo = new File(ruta);
			
				
			FileWriter fw = new FileWriter(ruta);

			do {
				System.out.println("Inserte el titulo: ");
				titulo = sc.nextLine();
						if(titulo.equals("")) {
							seguir = true;
						}else if (Utilidades.validar(Patrones.titulo.getNombre(), titulo)) {
					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			do {
				System.out.println("Inserte el editorial: ");

				if (Utilidades.validar(Patrones.editorial.getNombre(), editorial = sc.nextLine())) {
					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			do {
				System.out.println("Inserte las paginas: ");
				if (Utilidades.validar(Patrones.paginas.getNombre(), paginas = sc.nextLine())) {
					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			do {
				System.out.println("Inserte la altura: ");
				if (Utilidades.validar(Patrones.altura.getNombre(), altura = sc.nextLine())) {
					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			do {
				System.out.println("Inserte las notas: ");
				if (Utilidades.validar(Patrones.notas.getNombre(), notas = sc.nextLine())) {
					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			do {
				System.out.println("Inserte el isbn: ");
				if (Utilidades.validar(Patrones.isbn.getNombre(), isbn = sc.nextLine())) {
					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			do {
				System.out.println("Inserte las materias: ");
				if (Utilidades.validar(Patrones.materias.getNombre(), materias = sc.nextLine())) {

					seguir = true;
				} else {
					System.out.println("Datos no validos");
					seguir = false;
				}
			} while (!seguir);

			//Libro libro = new Libro(titulo, editorial, paginas, altura, notas, isbn, materias);
			fw.append(titulo).append(NEXT_LINE);

			fw.append(editorial).append(NEXT_LINE);
			fw.append(paginas).append(NEXT_LINE);
			fw.append(altura).append(NEXT_LINE);
			fw.append(notas).append(NEXT_LINE);
			fw.append(isbn).append(NEXT_LINE);
			fw.append(materias).append(NEXT_LINE);

			fw.flush();
			fw.close();
			System.out.println("Fichero creado con exito");
			
		} catch (IOException e) {
			// Error al crear el archivo, por ejemplo, el archivo
			// está actualmente abierto.
			System.out.println("Error al crear fichero ");
			e.printStackTrace();
		}

	

	

	}

	public static void EliminarficheroCsv() {
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("introduzca nombre del fichero");
			String nombreFichero = sc.nextLine();
			
			String ruta = ".\\Ficheros\\"+ nombreFichero + ".csv";
			
			File fichero = new File(ruta);
			if (fichero.exists() && fichero.delete()) { 
				 
				System.out.println("El fichero ha sido borrado satisfactoriamente");
				}else {
				System.out.println("El fichero no puede ser borrado o no existe");
				
			
			
			
		}} catch (Exception e) {
		
			System.out.println("Error al eliminar fichero");
		}}
	}


