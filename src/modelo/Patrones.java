package modelo;


public enum Patrones {
		
		notas("[a-z0-9\\.\\s]{1,100}"),
		titulo("[a-z0-9\\s]{1,50}"),
		editorial("[a-z0-9]{1,50}"),
		paginas("[0-9]{1,4}"),
		altura("[0-9\\.]{1,4}"),
		isbn("[0-9]{1,13}"),
		materias("[a-z0-9\\s]{1,100}");
		
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
