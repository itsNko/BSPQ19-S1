package es.deusto.data;

public class Articulo {

	private String nombre;
	private String caratula;

	public Articulo(String nombre) {
		this.nombre = nombre;
		this.caratula = null;
	}
	
	public Articulo(String nombre, String caratula) {
		this.nombre = nombre;
		this.caratula = caratula;
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
	
}
