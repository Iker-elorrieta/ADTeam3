package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Metodos {
	
	public static void cargarLista(File fichero)
	{
		try {
			BufferedReader ficheroR = new BufferedReader(new FileReader(fichero));
			Libro libro = new Libro();
			ArrayList<String[]> contenido = new ArrayList<String[]>();
			String linea = "";
			while((linea = ficheroR.readLine())!=null)
			{
				contenido.add(linea.split(";"));
			}

			for(int i = 0; i<contenido.size();i++)
			{
				try 
				{
					for(int y = 0; y < contenido.get(i).length ; y++)
					{
						System.out.println();
						if(y == 0)
						{
							libro.setTitulo(contenido.get(i)[y]);
						}
						else if(y == 1)
						{
							libro.setEditorial(contenido.get(i)[y]);
						}
						else if(y == 2)
						{
							libro.setPaginas(Integer.parseInt(contenido.get(i)[y]));
						}
						else if(y == 3)
						{
							libro.setAltura(Double.parseDouble(contenido.get(i)[y]));
						}
						else if(y == 4)
						{
							libro.setNotas(contenido.get(i)[y]);
						}
						else if(y == 5)
						{
							libro.setIsbn(Integer.parseInt(contenido.get(i)[y]));
						}
						else if(y == 6)
						{
							libro.setMaterias(contenido.get(i)[y]);
						}
					}
					Variables.listaLibros.add(libro);
				}
				catch(Exception a)
				{
//					a.printStackTrace();
				}
			}
			
			ficheroR.close();
			
		} 
		catch (FileNotFoundException fn)
		{
			System.out.println("No se encuentra el fichero");
		} 
		catch (IOException io) 
		{
			System.out.println("Error de E/S ");
		}
		catch (Exception idk)
		{
			System.out.println("Error no especificado: ");
			idk.printStackTrace();
		}
	}
	
	public static void escribir(ArrayList<Libro> listaLibros)
	{
		try
		{
			BufferedWriter fichero = new BufferedWriter(new FileWriter(Variables.url,true));
			for (int i=0; i < listaLibros.size(); i++)
			{
				fichero.write(listaLibros.get(i).getTitulo() + ";" + 
				  	 	      listaLibros.get(i).getEditorial() + ";" + 
						 	  listaLibros.get(i).getPaginas() + ";" + 
						 	  listaLibros.get(i).getAltura() + ";" + 
						 	  listaLibros.get(i).getNotas() + ";" + 
						 	  listaLibros.get(i).getIsbn() + ";" + 
						 	  listaLibros.get(i).getMaterias() + ";");
				fichero.newLine();
			}
		fichero.close();
		}
		catch (FileNotFoundException fn )
		{
			System.out.println("No se encuentra el fichero");
		}
		catch (IOException io) 
		{
			System.out.println("Error de E/S ");
		}
	}

	public static void listar()
	{
		
	}
	
}
