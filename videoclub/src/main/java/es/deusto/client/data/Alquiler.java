package es.deusto.client.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Alquiler {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	int id;
	private Articulo alquilado;
	private double coste;
	private String fecha_inicio;
	private String fecha_fin;
	private boolean enCurso;
	
	public Alquiler(Articulo alquilado, double coste, String fecha_inicio, String fecha_fin, boolean enCurso) {
		this.alquilado = alquilado;
		this.coste = coste;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.enCurso = enCurso;
	}
	
	public Articulo getAlquilado() {
		return alquilado;
	}

	public void setAlquilado(Articulo alquilado) {
		this.alquilado = alquilado;
	}


	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public boolean isEnCurso() {
		return enCurso;
	}

	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}
	
}
