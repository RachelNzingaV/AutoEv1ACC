package Principal;

public class Anuncio {
	private int id;
	private String titulo;
	private String categoria;
	private double precio;
	private String estado;
	private String barrio;
	private String fechaPublicacion;
	private int visitas;
	private boolean vendido;

	public Anuncio() {}

	public Anuncio(int id, String titulo, String categoria, double precio, String estado, String barrio, String fechaPublicacion, int visitas, boolean vendido) {
		this.id = id;
		this.titulo = titulo;
		this.categoria = categoria;
		this.precio = precio;
		this.estado = estado;
		this.barrio = barrio;
		this.fechaPublicacion = fechaPublicacion;
		this.visitas = visitas;
		this.vendido = vendido;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }

	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }

	public String getCategoria() { return categoria; }
	public void setCategoria(String categoria) { this.categoria = categoria; }

	public double getPrecio() { return precio; }
	public void setPrecio(double precio) { this.precio = precio; }

	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }

	public String getBarrio() { return barrio; }
	public void setBarrio(String barrio) { this.barrio = barrio; }

	public String getFechaPublicacion() { return fechaPublicacion; }
	public void setFechaPublicacion(String fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

	public int getVisitas() { return visitas; }
	public void setVisitas(int visitas) { this.visitas = visitas; }

	public boolean isVendido() { return vendido; }
	public void setVendido(boolean vendido) { this.vendido = vendido; }
}
