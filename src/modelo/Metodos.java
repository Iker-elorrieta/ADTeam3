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
	
	public static void leer(File fichero)
	{
		try {
			BufferedReader ficheroR = new BufferedReader(new FileReader(fichero));

			ArrayList<String[]> contenido = new ArrayList<String[]>();
			String linea = "";
			while((linea = ficheroR.readLine())!=null)
			{
				contenido.add(linea.split(";"));
			}

			for(int i = 0; i<contenido.size();i++)
			{
				String txt = "";
				String []libro;
				try 
				{
					for(int y = 0; y < contenido.get(i).length ; y++)
					{
						System.out.println(contenido.get(i)[y]);
					}
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
	
	
	public enum menu
	{
		
		titulo("Agua" , 1.6),
		editorial("Coca-cola" , (double) 2),
		paginas("Ref de naranja" , (double) 2),
		altura("Ref de limón" , (double) 2),
		notas("Nestea" , 1.80),
		isbn("Kit-kat" , 1.50),
		materias("Toblerone" , (double) 2);
		
		
		// private obligatorio.
		private String nombre;
		private Double precio;
		
		private menu(String pNombre, Double pPrecio)
		{
			this.nombre = pNombre;
			this.precio = pPrecio;
		}
		
		
		public Double getPrecio()
		{
			return precio;
		}
		
		public String getNombre()
		{
			return nombre;
		}
	}
}
