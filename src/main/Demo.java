package main;

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
			System.out.println("Se creo un archivo nuevo.");;
		
		menu();
		
		
	}
	
	public static void menu()
	{
		int respuestaOpciones;
		boolean confirmacionEscribir;
		
		System.out.println("Elige alguna de las opciones: ");
		System.out.println("1) Leer archivo.");
		System.out.println("2) Escribir archivo.");
		respuestaOpciones = entradaInt(1,2);
		
		if(respuestaOpciones == 1)
		{
			Metodos.leer(Variables.fichero);
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
		String paginas;
		String altura;
		String notas;
		String isbn;
		String materias;
		
		System.out.println("Inserte el titulo: ");
//		Metodos.validar(Utilidades.enum.titulo = teclado.nextLine());
		titulo = teclado.nextLine();
		System.out.println("Inserte el editorial: ");
		editorial = teclado.nextLine();
		System.out.println("Inserte el paginas: ");
		paginas = teclado.nextLine();
		System.out.println("Inserte el altura: ");
		altura = teclado.nextLine();
		System.out.println("Inserte el notas: ");
		notas = teclado.nextLine();
		System.out.println("Inserte el isbn: ");
		isbn = teclado.nextLine();
		System.out.println("Inserte el materias: ");
		materias = teclado.nextLine();
		
		Libro libro = new Libro(titulo,editorial,paginas,altura,notas,isbn,materias);
		Variables.listaLibros.add(libro);
		Metodos.escribir(Variables.listaLibros);
		Metodos.leer(Variables.fichero);
		
	}
}
