package main;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import modelo.Libro;
import modelo.Metodos;
import modelo.Variables;

public class Demo {
	
	public static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) throws IOException 
	{
		if(Variables.fichero.createNewFile())
			System.out.println("Se creo un archivo nuevo.");
		else
			Metodos.cargarLista(Variables.fichero);	
		
		menu();		
	}
	
	public static void menu()
	{
		int respuestaOpciones;
		boolean confirmacionEscribir;
		System.out.println("que fichero desea usar");
		System.out.println("1) Xml");
		System.out.println("2) Txt");
		System.out.println("3) Scv");
		respuestaOpciones = entradaInt(1,3);
		if(respuestaOpciones == 1)
		{
			System.out.println("¿Que desea hacaer?");
			System.out.println("1. Crear Xml");
			System.out.println("2. leer Xml");
			System.out.println("3. modificar Xml");
			System.out.println("4. eliminar Xml");
			respuestaOpciones = entradaInt(1,4);
			
			if(respuestaOpciones == 1)
			{
				modelo.crearXml.crearXml(Variables.listaLibros);
			}else if(respuestaOpciones == 2)
			{
				modelo.leerPrincipalXml.leerPrincipal();
			}else if(respuestaOpciones == 3)
			{
				//sin hacer
			}else if(respuestaOpciones == 2)
			{
				File fichero = new File(".\\Ficheros\\libreria.xml");
				
				if (fichero.delete())
					System.out.println("El fichero ha sido borrado satisfactoriamente");
				else
					System.out.println("El fichero no puede ser borrado");
			}
					
		}else if(respuestaOpciones == 2)
		{
			System.out.println("Elige alguna de las opciones: ");
			System.out.println("1) Leer archivo.");
			System.out.println("2) Escribir archivo.");
			respuestaOpciones = entradaInt(1,2);
			
			if(respuestaOpciones == 1)
			{
				Metodos.listar();
			}
			else if(respuestaOpciones == 2)
			{
				System.out.println("¿Quiere escribir un nuevo libro?");
				confirmacionEscribir = entradaChar();
				if(confirmacionEscribir)
				{
					teclado.nextLine();
					crearLibro();
				}
			}
		}
		
	}
//	Metodos.escribir(Variables.listaLibros);
	public static int entradaInt(int min, int max)
	{
		int result = 0;
		do
		{
			try
			{
				result = teclado.nextInt();
				if(result < min || result > max)
				{
					System.out.println("Tiene que insertar un numero entre " + min + " y " + max);
					teclado.nextLine();
				}
			}
			catch(InputMismatchException a)
			{
	//			a.printStackTrace();
				System.out.println("Tiene que insertar un numero:");
				teclado.nextLine();
			}
		}while (result < min || result > max);
		teclado.nextLine();
		return result;
	}
	
	public static boolean entradaChar()
	{
		char result = 'a';
		
		do
		{
			result = teclado.next().charAt(0);
			
			if(result != 'n' || result != 'N')
			{
				return true;
			}
			else if(result != 's' || result != 'S')
			{
				return false;
			}
			
		}while((result != 'n' || result != 'N') && (result != 's' || result != 'S'));
		
		return false;
	}

	public static void crearLibro()
	{
		String titulo;
		String editorial;
		int paginas;
		Double altura;
		String notas;
		int isbn;
		String materias;
		
		System.out.println("Inserte el titulo: ");
//		Metodos.validar(Utilidades.enum.titulo = teclado.nextLine());
		titulo = teclado.nextLine();
		System.out.println("Inserte el editorial: ");
		editorial = teclado.nextLine();
		System.out.println("Inserte el paginas: ");
		paginas = teclado.nextInt();
		teclado.nextLine();
		System.out.println("Inserte el altura: ");
		altura = teclado.nextDouble();
		teclado.nextLine();
		System.out.println("Inserte el notas: ");
		notas = teclado.nextLine();
		System.out.println("Inserte el isbn: ");
		isbn = teclado.nextInt();
		teclado.nextLine();
		System.out.println("Inserte el materias: ");
		materias = teclado.nextLine();
		
		Libro libro = new Libro(titulo,editorial,paginas,altura,notas,isbn,materias);
		Variables.listaLibros.add(libro);
		Metodos.escribir(Variables.listaLibros);
		Metodos.listar();
		
	}
}
