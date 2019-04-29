package es.deusto.client.data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public abstract class Articulo {

	@PrimaryKey
	private String nombre;
	private String caratula;
	private double precio;
	private int unidades;
	private double porcentajeDescuento;

	public Articulo(String nombre, String caratula, double precio, double porcentajeDescuento) {
		this.nombre = nombre;
		this.caratula = caratula;
		this.precio = precio;
		this.unidades = 1;
		this.porcentajeDescuento = porcentajeDescuento; 
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
	
	public double getDescuento() {
		return porcentajeDescuento;
	}

	public void setDescuento(double porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	public abstract String getClassName();
	
}
