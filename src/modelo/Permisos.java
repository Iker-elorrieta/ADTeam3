package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Permisos {

	public static void main(String[] args) {

		Scanner teclado = new java.util.Scanner(System.in);
		int opcion;
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
		}
	}

	private static void quitarPermiso(Scanner teclado) {
		try {
			String usuario;
			String nombreFichero;
			String nomFichero;

			System.out.println("ingrese el nombre del usuario al cual le dara permisos");
			usuario = teclado.next();

			System.out.println("ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
			nomFichero = teclado.next();

			nombreFichero = modelo.Metodos.buscarFichero(nomFichero);

			Process process = Runtime.getRuntime().exec("cmd /c ICACLS " + nombreFichero + " /remove " + usuario);
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
			int permisoUnix;
			Runtime builder = Runtime.getRuntime();
			String permisoLetras = null;

			if (modelo.Metodos.isWindows()) {

				System.out.println("ingrese el nombre del usuario al cual le dara permisos");
				usuario = teclado.next();

				System.out.println("ingrese el nombre del archivo y la extension(xml,txt,csv....) ");
				nomFichero = teclado.next();
				nombreFichero = modelo.Metodos.buscarFichero(nomFichero);
				System.out.println("que permiso desea agregar");
				System.out.println("F - acceso total");
				System.out.println("M - acceso de modificación");
				System.out.println("RX - acceso de lectura y ejecución");
				System.out.println("R - acceso de solo lectura");
				System.out.println("W - acceso de solo escritura");
				System.out.println("D - acceso de eliminación");
				permiso = teclado.next();

				Process process = Runtime.getRuntime()
						.exec("cmd /c ICACLS " + nombreFichero + " /grant " + usuario + ":(" + permiso + ")");
				BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String resultOfExecution = null;
				while ((resultOfExecution = br.readLine()) != null) {
					System.out.println(resultOfExecution);
				}

			} else if (modelo.Metodos.isUnix()) {

				System.out.println("ingrese el nombre del archivo");
				nomFichero = teclado.next();

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
				permisoUnix = teclado.nextInt();

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
				System.out.println("cambio realizado ");
			}

		} catch (Exception e) {
			System.out.println(" no se realiazo el cambio de permiso");
		}

	}

}
