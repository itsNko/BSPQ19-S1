package es.deusto.data;

public class Socio {

	private String nombre;
	private String password;
	private int monedero;
	private String imagen;
	
	public Socio(String nombre, String password, int monedero) {
		this.nombre = nombre;
		this.password = password;
		this.monedero = monedero;
		this.imagen = null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getMonedero() {
		return monedero;
	}
	
	public void setMonedero(int monedero) {
		this.monedero = monedero;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getImagen() {
		return imagen;
	}
	
	
	
	
	
}
