package modelo;

public enum Patrones {
		
		notas("[a-z A-Z]{1,100}"),
		titulo("[a-z A-Z]{1,50}"),
		editorial("[a-z A-Z]{1,50}"),
		paginas("[0-9]{1,4}"),
		altura("[0-9]{1,2}" ),
		isbn("[0-9]{13}" ),
		materias("[a-z A-Z]{1,100}" ),
		frutos("Frutos Secos" );
		
		
		// private obligatorio.
		private String nombre;
		
		private Patrones(String pNombre)
		{
			this.nombre = pNombre;
		}
		
		public String getNombre()
		{
			return nombre;
		}
}
