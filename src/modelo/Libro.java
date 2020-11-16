package modelo;

/**
 * Clase libro para rellenar la libreria.
 *
 */
public class Libro {
	
	private String titulo;
	private String editorial;
	private int paginas;
	private double altura;
	private String notas;
	private String isbn;
	private String materias;
	
	public Libro()
	{
		
	}
	
	/**
	 * Los parametros que se necesitan para crear un libro con este constructor son
	 * (titulo,editorial,paginas,altura,notas,isbn,materias)
	 * paginas y isbn tienen que ser enteros y altura tiene que ser un numero con decimales.
	 * @param titulo
	 * @param editorial
	 * @param paginas
	 * @param altura
	 * @param notas
	 * @param isbn
	 * @param materias
	 */
	public Libro(String titulo, String editorial, int paginas, double altura, String notas, String isbn, String materias)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.paginas = paginas;
		this.altura = altura;
		this.notas = notas;
		this.isbn = isbn;
		this.materias = materias;
	}

	/*
	 * Metodo para mostrar los datos del libro actual.
	 */
	public String mostrar()
	{
		String result = "Titulo: " + titulo + "\n" +
						"Editorial: " + editorial + "\n" +
						"Paginas: " + paginas + "\n" +
						"Altura: " + altura + "\n" +
						"Notas: " + notas + "\n" + 
						"Isbn: " + isbn + "\n" +
						"Materias: " + materias;
		return result;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public String getEditorial() {
		return editorial;
	}
	public int getPaginas() {
		return paginas;
	}
	public Double getAltura() {
		return altura;
	}
	public String getNotas() {
		return notas;
	}
	public String getIsbn() {
		return isbn;
	}
	public String getMaterias() {
		return materias;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public void setAltura(Double altura) {
		this.altura = altura;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public void setMaterias(String materias) {
		this.materias = materias;
	}
	
}
