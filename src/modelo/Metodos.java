package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase donde se apuntan los metodos que se usan en el programa.
 *
 */
public class Metodos {

	/**
	 *  Metodo para rellenar la lista del probrama con los libros apuntados en el
	 * fichero txt.
	 * @param fichero
	 * @param lista
	 * @return ArrayList<Libro>
	 */
	public static ArrayList<Libro> cargarLista(File fichero, ArrayList<Libro> lista)  {
		
		try {
			BufferedReader ficheroR = new BufferedReader(new FileReader(fichero));
			ArrayList<String[]> contenido = new ArrayList<String[]>();
			StringTokenizer token;
			String linea = "";
			while ((linea = ficheroR.readLine()) != null) {
				token = new StringTokenizer(linea,";");
				String[] tokens = new String[token.countTokens()];
				for(int i = 0; token.hasMoreTokens();i++)
				{
					tokens[i] = token.nextToken();
				}
				contenido.add(tokens);
			}

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
			}
			if(contenido.size()!=0)
			{
				Variables.posicionNumero = contenido.size() - 1;
			}
			ficheroR.close();
		} catch (FileNotFoundException fn) {
			System.out.println("No se encuentra el fichero");
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lista;
	}

	/**
	 * Metodo para insertar en el txt un libro.
	 * @param listaLibros
	 * @return boolean 
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
			e.printStackTrace();
		}
		return correcto;
	}
	
	/**
	 * Metodo para mostrar los libros actualmente en el fichero y el programa.
	 * @param ArrayList listaLibros
	 * @return boolean 
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

	/**
	 *  Metodo que elimina el fichero introducido por el usuario 
	 * @param Scanne teclado
	 * @param String extension
	 * @return boolean 
	 */
	public static boolean eliminarFichero(Scanner teclado,String extension) {
		boolean correcto=false;
			
		String nombreFichero;
		System.out.println("introduzca nombre del fichero");
		nombreFichero = teclado.nextLine();
		String ruta = ".\\Ficheros\\"+ nombreFichero + extension;
		File archivo = new File(ruta);
		
		if (archivo.exists()) {
			if (archivo.delete()) {
				System.out.println("El fichero ha sido borrado satisfactoriamente");
				correcto=true;
			} else {
				System.out.println("El fichero no pudó ser borrado");
				correcto=false;
			}
		}else {
			System.out.println("no existe el fichero");
			correcto=false;
		}

		return correcto;
	}
	
	/**
	 * @param el patron puede tener [] y {}, dentro de [] se tendra que meter tipo de dato y de donde hasta donde
	 * y en {} se tendra que poner la longitud de esta separado el numero de minimo con , del maximo.
	 * @param en el dato se tendra que mandar el string para validar.
	 * 
	 * Posibles opciones: 
	 * insertar solo letras [a-z]
	 * insertar letras y espacios [a-z\\s] 
	 * insertar solo numeros [0-9]
	 * insertar numeros y espacios [0-9\\s]
	 * insertar letras y numeros sin caracteres especiales [a-z0-9]
	 * insertar letras y numeros con espacios [a-z0-9\\s]
	 * longitud de la palabra {1,10} 1 = longitud minima || 10 = longitud maxima
	 * 
	 * @return retorna true o false si el dato para validar esta correcto
	 * 
	 * caracteres como ÑÇöü se considera caracteres especiales
	 * con lo cual este metodo devolvera false en cuanto tenga alguno es estos caracteres.
	 */
	public static boolean validacion(String patron,String dato)
	{
		String rango = patron.substring(patron.indexOf("[")+1,patron.indexOf("[")+4); 
		String segundoRango = patron.substring(patron.indexOf(rango)+rango.length(),patron.indexOf(rango)+rango.length()+3);
		String principioSegundoRango = "";
		String finalSegundoRango = "";
		boolean espacios;
		boolean comas;
		
		if(patron.substring(patron.indexOf("]")-2,patron.indexOf("]")).equals("\\s"))
			espacios = true;
		else
			espacios = false;
		
		if(patron.contains("."))
		{
			if(patron.substring(patron.indexOf("\\"),patron.indexOf("\\")+2).equals("\\."))
				comas = true;
			else
				comas = false;
		}
		else
			comas = false;
		
		//comprobar que el rango es una letra o un digito para prevenir la insercion de caracteres especiales
		if(Character.isLetter(segundoRango.charAt(0)) || Character.isDigit(segundoRango.charAt(0)))
		{
			principioSegundoRango = segundoRango.substring(0,1);
			finalSegundoRango = segundoRango.substring(2);
		}
		else
		{
			segundoRango = "";
		}
		
		String principioRango = rango.substring(0,1);
		String finalRango = rango.substring(2);
		
		String longitud = patron.substring(patron.indexOf("{")+1,patron.lastIndexOf("}"));
		String principioLongitud = longitud.substring(0,longitud.indexOf(","));
		String finalLongitud = longitud.substring(longitud.indexOf(",")+1);

		//comprobar que el rango es una letra o un digito para prevenir la insercion de caracteres especiales
		if(!Character.isLetter(principioRango.toUpperCase().charAt(0)) && !Character.isDigit(principioRango.toUpperCase().charAt(0)))
		{
			System.out.println("entro caracter special");
			return false;
		}
		else if(!rango.contains("-"))
		{
			return false;
		}
		else if(!segundoRango.equals("") && !segundoRango.contains("-"))
		{
			return false;
		}
		
		
		//comprobar que no se pueda poner un rango minimo mas pequeño que el maximo
		if(Integer.parseInt(finalLongitud) < Integer.parseInt(principioLongitud))
		{
			int cambio = Integer.parseInt(finalLongitud);
			finalLongitud = principioLongitud;
			principioLongitud = cambio + "";
		}
		
		//comprobar que el dato insertado esta dentro del rango
		if(dato.length() > Integer.parseInt(finalLongitud) || dato.length() < Integer.parseInt(principioLongitud))
		{
			return false;
		}
		
		if(dato.contains("."))
		if(dato.substring(dato.indexOf(".")+1).contains("."))
			return false;
		
		//comprobar caracteres especiales
		for(int i = 0; i < dato.length(); i++)
		{
			if((!Character.isLetter(dato.charAt(i)) && !Character.isDigit(dato.charAt(i))))
			{
				if((dato.charAt(i) == '\s' && !espacios))
				{
					return false;
				}
				else if (dato.charAt(i) == '.' && !comas)
					return false;
				else if (dato.charAt(i) != '\s' && dato.charAt(i) != '.')
					return false;
			}
		}
				
		//si el rango es una letra entrara en el if
		if(Character.isLetter(principioRango.toUpperCase().charAt(0)) && segundoRango.equals(""))
		{
			//si el rango inicial es mayor que el final se cambian de posiciones y empieza la busqueda
			if((int)finalRango.toUpperCase().charAt(0) < (int)principioRango.toUpperCase().charAt(0))
			{
				String cambio = principioRango;
				principioRango = finalRango;
				finalRango = cambio;
			}
			
			//comprobar que cada posicion del dato es una letra
			for(int i = 0 ; i < dato.length(); i++)
			{
				
				//comprobar si tiene digito
				if (Character.isDigit(dato.charAt(i)))
				{
					return false;
				}
				else if (((int)dato.toUpperCase().charAt(i) > (int)finalRango.toUpperCase().charAt(0) || 
				   (int)dato.toUpperCase().charAt(i) < (int)principioRango.toUpperCase().charAt(0)))
				{
					//comprobar que tiene espacio
					if(dato.toUpperCase().charAt(i) == '\s' && !espacios)
					{
						return false;
					}
					else if(dato.toUpperCase().charAt(i) == '.' && !comas)
					{
						return false;
					}
					else if (dato.charAt(i) != '\s' && dato.charAt(i) != '.')
						return false;
				}
			}
		}
		else if(Character.isDigit(principioRango.toUpperCase().charAt(0)) && segundoRango.equals(""))
		{
			//comprobar si el rango de numeros inicial no es mayor que el final y acmbiarlos si es true
			if(Integer.parseInt(finalRango.toUpperCase().charAt(0)+"") < Integer.parseInt(principioRango.toUpperCase().charAt(0)+""))
			{
				String cambio = principioRango;
				principioRango = finalRango;
				finalRango = cambio;
			}
			
			for(int i = 0 ; i < dato.length(); i++)
			{
				//comprobar si es un digito y que no sea un espacio
				if((Character.isDigit((char)dato.toUpperCase().charAt(i))))
				{	
					if(Integer.parseInt(dato.toUpperCase().charAt(i)+"") < Integer.parseInt(principioRango) ||
					   Integer.parseInt(dato.toUpperCase().charAt(i)+"") > Integer.parseInt(finalRango))
					{
						return false;
					}	
				}
				else if((dato.toUpperCase().charAt(i) == '\s' && !espacios))
				{
					return false;
				}
				else if((dato.toUpperCase().charAt(i) == '.' && !comas))
				{
					return false;
				}
				else if(dato.toUpperCase().charAt(i) != '\s' && dato.toUpperCase().charAt(i) != '.')
					return false;
				
			}
		}
		else 
		{
			for(int i = 0 ; i < dato.length(); i++)
			{
				if(Character.isLetter(dato.toUpperCase().charAt(i)))
				{
					if((int)finalRango.toUpperCase().charAt(0) < (int)principioRango.toUpperCase().charAt(0))
					{
						String cambio = principioRango;
						principioRango = finalRango;
						finalRango = cambio;
					}
					
					//comprobar que tiene espacio
					if(dato.toUpperCase().charAt(i) == '\s' && !espacios)
					{
						return false;
					}
					else if(dato.charAt(i) == '.' && !comas)
						return false;
					else if(((int)dato.toUpperCase().charAt(i) > (int)finalRango.toUpperCase().charAt(0) || 
						(int)dato.toUpperCase().charAt(i) < (int)principioRango.toUpperCase().charAt(0)))
						return false;
					
				}
				else
				{
					if((Character.isDigit(dato.toUpperCase().charAt(i))))
					{
						//comprobar si el rango de numeros inicial no es mayor que el final y acmbiarlos si es true
						if(Integer.parseInt(principioSegundoRango.toUpperCase().charAt(0)+"") < Integer.parseInt(finalSegundoRango.toUpperCase().charAt(0)+""))
						{
							String cambio = principioRango;
							principioRango = finalRango;
							finalRango = cambio;
						}
						
						if(Integer.parseInt(dato.charAt(i)+"") < Integer.parseInt(principioSegundoRango) ||
						   Integer.parseInt(dato.charAt(i)+"") > Integer.parseInt(finalSegundoRango))
						{
							return false;
						}
					}
					else if((dato.toUpperCase().charAt(i) == '\s' && !espacios))
					{
						return false;
					}
					else if(dato.charAt(i) == '.' && !comas)
						return false;
				}
			}
		}
		
		return true;
	}

}