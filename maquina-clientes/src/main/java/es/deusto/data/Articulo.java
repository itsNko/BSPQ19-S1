package es.deusto.data;

public class Articulo {

	private String nombre;
	private String caratula;
	private boolean disponible;

	public Articulo(String nombre, boolean disponible) {
		this.nombre = nombre;
		this.disponible = disponible;
		this.caratula = null;
	}
	
	public Articulo(String nombre,boolean disponible, String caratula) {
		this.nombre = nombre;
		this.disponible = disponible;
		this.caratula = caratula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getCaratula() {
		return caratula;
	}

	public void setCaratula(String caratula) {
		this.caratula = caratula;
	}
	
}
