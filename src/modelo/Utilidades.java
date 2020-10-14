package modelo;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

	public static boolean validar(String titulo, String texto) {
		boolean validado = false;
		Pattern patron = Pattern.compile(titulo);
		Matcher mat = patron.matcher(texto);
		if (mat.matches()) {
			validado = true;
		}
		return validado;
	}
	public static boolean continuar() {

		boolean error = true;
		boolean continuar = true;
		String letraS = "";
		char letraC = ' ';

		Scanner sc = new Scanner(System.in);

		do {
			do {
				System.out.print("\n\n ¿Desea continuar? (S/N):");
				letraS = sc.next();
				if (letraS.length() > 1) {
					System.out.println("\nLa respuesta introducida no es valida\n");
				}
			} while (letraS.length() > 1);

			letraC = letraS.charAt(0);

			if (Character.isDigit(letraC)) {
				System.out.println("\n ERROR. Debe introducir una letra\n");
			} else if (letraC == 'S' || letraC == 's') {
				error = false;
				continuar = true;
			} else if (letraC == 'N' || letraC == 'n') {
				error = false;
				continuar = false;
				System.out.println("Ha salido del programa");
			} else {
				System.out.println("\n ERROR. La letra introducida no es valida.\n");
			}
		} while (error);

		return continuar;
	}

}
