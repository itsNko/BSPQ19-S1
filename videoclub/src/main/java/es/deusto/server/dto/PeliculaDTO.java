package es.deusto.server.dto;

import java.io.Serializable;

public class PeliculaDTO extends ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String sinopsis;
	private String genero;
	private String fecha_estr;
	private double puntuacion;
	
	public PeliculaDTO(String nombre, double precio, String sinopsis, String genero, String fecha_estr, double puntuacion, String caratula, double descuento) {
		super(nombre, caratula, precio, descuento);
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

	@Override
	public String getClassName() {
		return "PeliculaDTO";
	}
}
