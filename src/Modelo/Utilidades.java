package Modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

	public static boolean validar(String pat, String texto) {
		boolean validado = false;
		Pattern patron = Pattern.compile(pat);
		Matcher mat = patron.matcher(texto);
		if (mat.matches()) {
			validado = true;
		}
		return validado;
	}

}
