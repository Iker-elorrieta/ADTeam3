package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class permisos {
	
	public static void main(String[] args) {

		Scanner teclado = new java.util.Scanner(System.in);
		int opcion;
		String usuario = "";
		String nombreFichero = "";
		String permiso = "";
		boolean correcto;
		try {
			
			do {
				System.out.println("¿Que desea hacer?");
				System.out.println("1) Agregar permisos");
				System.out.println("2) quitar permisos");
				System.out.println("3) salir");
				opcion = teclado.nextInt();

				if (opcion == 1) {

					agregarPermiso(teclado);
					
					correcto=true;
				} else if (opcion == 2) {

					quitarPermiso(teclado);
					
					correcto=true;
				} else  if (opcion == 3){
					correcto=false;
				}
			} while (correcto=true);
			
		} catch (Exception e) {
			e.getMessage();
		}

	}

	private static void quitarPermiso(Scanner teclado)  {
		try {
			String usuario;
			String nombreFichero;
			String nomFichero;
			
			System.out.println("ingrese el nombre del usuario al cual le dara permisos");
			usuario = teclado.next();
			
			System.out.println("ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
			nomFichero = teclado.next();
			
			nombreFichero=modelo.Metodos.buscarFichero(nomFichero);
			
			Process process = Runtime.getRuntime()
					.exec("cmd /c ICACLS "+ nombreFichero + " /remove " + usuario);
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String resultOfExecution = null;
			while ((resultOfExecution = br.readLine()) != null) {
				System.out.println(resultOfExecution);
			}
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	private static void agregarPermiso(Scanner teclado) {
		
		try {
			String usuario;
			String nombreFichero;
			String permiso;
			String nomFichero;
			
			System.out.println("ingrese el nombre del usuario al cual le dara permisos");
			usuario = teclado.next();
			
			System.out.println("ingrese el nombre del archivo");
			nomFichero = teclado.next();
			
			nombreFichero=modelo.Metodos.buscarFichero(nomFichero);
			System.out.println("que permiso desea agregar");
			System.out.println("F - acceso total");
			System.out.println("M - acceso de modificación");
			System.out.println("RX - acceso de lectura y ejecución");
			System.out.println("R - acceso de solo lectura");
			System.out.println("W - acceso de solo escritura");
			System.out.println("D - acceso de eliminación");
			permiso = teclado.next();

			Process process = Runtime.getRuntime().exec("cmd /c ICACLS "+ nombreFichero + " /grant " + usuario + ":(" + permiso + ")");
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String resultOfExecution = null;
			while ((resultOfExecution = br.readLine()) != null) {
				System.out.println(resultOfExecution);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	
	}

	
}
