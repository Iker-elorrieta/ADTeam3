package modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilidades {

	public static boolean validar(Pattern pat, String cadena) {

		boolean correcto = false;
		Matcher mat = pat.matcher(cadena);
		if (mat.matches()) {
			correcto = true;
		}
		return correcto;
	}

}
