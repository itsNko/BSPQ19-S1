package es.deusto.server.dto;

import java.io.Serializable;

public class VideojuegoDTO extends ArticuloDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String descripcion;
	private String categoria;
	private String fecha_lan;
	private double puntuacion;
	
	public VideojuegoDTO(String nombre, double precio, String descripcion, String categoria, String fecha_lan, double puntuacion, String caratula, double descuento) {
		super(nombre, caratula, precio, descuento);
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.fecha_lan = fecha_lan;
		this.puntuacion = puntuacion;
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

	@Override
	public String getClassName() {
		return "VideojuegoDTO";
	}

}
