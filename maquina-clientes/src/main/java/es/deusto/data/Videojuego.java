package es.deusto.data;

public class Videojuego {

	private String nombre;
	private String descripcion;
	private String categoria;
	private String fecha_lan;
	private double puntuacion;
	
	public Videojuego(String nombre, String descripcion, String categoria, String fecha_lan, double puntuacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fecha_lan = fecha_lan;
		this.puntuacion = puntuacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getFecha_lan() {
		return fecha_lan;
	}

	public void setFecha_lan(String fecha_lan) {
		this.fecha_lan = fecha_lan;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}
