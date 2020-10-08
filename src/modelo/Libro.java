package modelo;

public class Libro {
	
	private String titulo;
	private String editorial;
	private String paginas;
	private String altura;
	private String notas;
	private String isbn;
	private String materias;
	
	public Libro()
	{
		
	}
	
	public Libro(String titulo, String editorial, String paginas, String altura, String notas, String isbn, String materias)
	{
		this.titulo = titulo;
		this.editorial = editorial;
		this.paginas = paginas;
		this.altura = altura;
		this.notas = notas;
		this.isbn = isbn;
		this.materias = materias;
	}

	public String getTitulo() {
		return titulo;
	}
	public String getEditorial() {
		return editorial;
	}
	public String getPaginas() {
		return paginas;
	}
	public String getAltura() {
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
	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}
	public void setAltura(String altura) {
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
