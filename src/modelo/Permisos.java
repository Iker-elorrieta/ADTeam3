package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import main.Demo;

public class Permisos {
		
	public static boolean cambioPermiso(Scanner teclado) {

		int opcion;
		boolean correcto=false;
		try {

			do {
				System.out.println("¿Que desea hacer?");
				System.out.println("1) Agregar permisos");
				System.out.println("2) Quitar permisos");
				System.out.println("3) Salir");
				opcion = Demo.entradaInt(1, 3, teclado);

				if (opcion == 1) {
					agregarPermiso(teclado);
					correcto = true;
				} else if (opcion == 2) {

					if (modelo.Metodos.isUnix()) {
						agregarPermiso(teclado);
					} else {
						quitarPermiso(teclado);
					}
					correcto = true;
				} else if (opcion == 3) {
					correcto = false;
				}
			} while (correcto = true);

		} catch (Exception e) {
			e.getMessage();
			correcto = false;
		}
		return correcto;
	}

	private static boolean quitarPermiso(Scanner teclado) {
		String usuario;
		String nombreFichero;
		String nomFichero;
		boolean correcto=false;
		
		try {
			System.out.println("Ingrese el nombre del usuario al cual le dara permisos");
			usuario = teclado.next();
			teclado.nextLine();
			
			System.out.println("Ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
			nomFichero = teclado.next();
			teclado.nextLine();

			nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

			Process process = Runtime.getRuntime().exec("cmd /c ICACLS " + nombreFichero + " /remove " + usuario);
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String resultOfExecution = null;
			while ((resultOfExecution = br.readLine()) != null) {
				System.out.println(resultOfExecution);
			}
			correcto=true;
		} catch (Exception e) {
			e.getMessage();
			correcto=false;
		}
		return correcto;

	}

	private static boolean agregarPermiso(Scanner teclado) {
		String usuario;
		String nombreFichero;
		String permiso;
		String nomFichero;
		int permisoUnix;
		Runtime builder = Runtime.getRuntime();
		String permisoLetras = null;
		boolean correcto=false;
		
		try {
			if (modelo.Metodos.isWindows()) {

				System.out.println("Ingrese el nombre del usuario al cual le dara permisos");
				usuario = teclado.next();
				teclado.nextLine();

				System.out.println("Ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
				nomFichero = teclado.next();
				teclado.nextLine();
				
				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);
				System.out.println("que permiso desea agregar");
				System.out.println("F - acceso total");
				System.out.println("M - acceso de modificación");
				System.out.println("RX - acceso de lectura y ejecución");
				System.out.println("R - acceso de solo lectura");
				System.out.println("W - acceso de solo escritura");
				System.out.println("D - acceso de eliminación");
				permiso = teclado.next();
				teclado.nextLine();

				Process process = Runtime.getRuntime()
						.exec("cmd /c ICACLS " + nombreFichero + " /grant " + usuario + ":(" + permiso + ")");
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String resultOfExecution = null;
				while ((resultOfExecution = br.readLine()) != null) {
					System.out.println(resultOfExecution);
				}
				correcto=true;
			} else if (modelo.Metodos.isUnix()) {

				System.out.println("Ingrese el nombre del archivo");
				nomFichero = teclado.next();
				teclado.nextLine();

				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

				System.out.println("que permiso desea cambiar");
				System.out.println("0 = sin acceso");
				System.out.println("1 = ejecución");
				System.out.println("2 = escritura");
				System.out.println("3 = escritura y ejecución");
				System.out.println("4 = lectura");
				System.out.println("5 = lectura y ejecución");
				System.out.println("6 = lectura y escritura");
				System.out.println("7 = lectura, escritura y ejecución");
				permisoUnix = Demo.entradaInt(0, 7, teclado);

				if (permisoUnix == 0) {
					permisoLetras = "---";
				} else if (permisoUnix == 1) {
					permisoLetras = "--x";
				} else if (permisoUnix == 2) {
					permisoLetras = "-w-";
				} else if (permisoUnix == 3) {
					permisoLetras = "-wr";
				} else if (permisoUnix == 4) {
					permisoLetras = "r--";
				} else if (permisoUnix == 5) {
					permisoLetras = "r-x";
				} else if (permisoUnix == 6) {
					permisoLetras = "rw-";
				} else if (permisoUnix == 7) {
					permisoLetras = "rwx";
				}

				String cmd = "chmod g=" + permisoLetras + "," + "o=" + permisoLetras + " " + nombreFichero;
				Process out = builder.exec(cmd);

				out.getInputStream();
				System.out.println("Cambio realizado");
				correcto=true;
			}

		} catch (Exception e) {
			System.out.println("No se realiazo el cambio de permiso");
			correcto=false;
		}
		return correcto;

	}

}
