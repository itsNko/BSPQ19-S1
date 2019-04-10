package es.deusto.client.data;

public class Articulo {

	private String nombre;
	private String caratula;
	private double precio;

	public Articulo(String nombre, double precio) {
		this.nombre = nombre;
		this.caratula = null;
		this.precio = precio;
	}
	
	public Articulo(String nombre, String caratula, double precio) {
		this.nombre = nombre;
		this.caratula = caratula;
		this.precio = precio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
