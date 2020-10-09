package modelo;

public class Libro {
	
	private String titulo;
	private String editorial;
	private int paginas;
	private Double altura;
	private String notas;
	private int isbn;
	private String materias;
	
	public Libro()
	{
		
	}
	
	public Libro(String titulo, String editorial, int paginas, Double altura, String notas, int isbn, String materias)
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
	public int getPaginas() {
		return paginas;
	}
	public Double getAltura() {
		return altura;
	}
	public String getNotas() {
		return notas;
	}
	public int getIsbn() {
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
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public void setMaterias(String materias) {
		this.materias = materias;
	}
	
}
