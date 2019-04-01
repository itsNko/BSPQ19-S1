package es.deusto.data;

public class Pelicula extends Articulo {

	private String sinopsis;
	private String genero;
	private String fecha_estr;
	private double puntuacion;
	
	public Pelicula(String nombre, String sinopsis, String genero, String fecha_estr, double puntuacion, boolean disponible) {
		super(nombre, disponible);
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.fecha_estr = fecha_estr;
		this.puntuacion = puntuacion;
	}
	
	public Pelicula(String nombre, String sinopsis, String genero, String fecha_estr, double puntuacion, boolean disponible, String caratula) {
		super(nombre, disponible, caratula);
		this.sinopsis = sinopsis;
		this.genero = genero;
		this.fecha_estr = fecha_estr;
		this.puntuacion = puntuacion;
	}
	

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFecha_estr() {
		return fecha_estr;
	}

	public void setFecha_estr(String fecha_estr) {
		this.fecha_estr = fecha_estr;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}
	
}
