package es.deusto.client.data;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * clase Alquiler
 *
 */
@PersistenceCapable
public class Alquiler {

	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
	private int id;
	private Articulo alquilado;
	private double coste;
	private String fecha_inicio;
	private String fecha_fin;
	private boolean enCurso;
	private String nombreArticulo;
	private double valoracion;
	
	/**
	 * Getter del nombre de Articulo
	 * @return String con el nombre del articulo
	 */
	public String getNombreArticulo() {
		return nombreArticulo;
	}

	/**
	 * Setter del nombre del articulo
	 * @param nombreArticulo
	 */
	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	/**
	 * Constructor Alquiler
	 * @param alquilado
	 * @param coste
	 * @param fecha_inicio
	 * @param fecha_fin
	 * @param enCurso
	 * @param nombreArticulo
	 */
	public Alquiler(Articulo alquilado, double coste, String fecha_inicio, String fecha_fin, boolean enCurso, String nombreArticulo) {
		this.alquilado = alquilado;
		this.coste = coste;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.enCurso = enCurso;
		this.nombreArticulo = nombreArticulo;
		this.valoracion = 0.0;
	}
	
	/**
	 * Getter de valoracion del articulo dentro del alquiler
	 * @return double valoracion
	 */
	public double getValoracion() {
		return valoracion;
	}
	
	/**
	 * Setter valoracion del articulo dentro del alquiler
	 * @param valoracion
	 */
	public void setValoracion(double valoracion) {
		this.valoracion = valoracion;
	}
	/**
	 * Getter del articulo alquilado
	 * @return Articulo
	 */
	public Articulo getAlquilado() {
		return alquilado;
	}

	/**
	 * Setter del articulo alquilado
	 * @param alquilado
	 */
	public void setAlquilado(Articulo alquilado) {
		this.alquilado = alquilado;
	}

	/**
	 * Getter coste del alquiler
	 * @return double coste
	 */
	public double getCoste() {
		return coste;
	}

	/**
	 * Setter coste del alquiler
	 * @param coste
	 */
	public void setCoste(double coste) {
		this.coste = coste;
	}

	/**
	 * Getter fecha inicio del alquiler
	 * @return String con la fecha de inicio
	 */
	public String getFecha_inicio() {
		return fecha_inicio;
	}

	/**
	 * Setter fecha inicio del alquiler
	 * @param fecha_inicio
	 */
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	/**
	 * Getter fecha fin del alquiler
	 * @return String con la fecha de devolucion del alquiler
	 */
	public String getFecha_fin() {
		return fecha_fin;
	}

	/** Setter fecha fin del alquiler
	 * @param fecha_fin
	 */
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	/**
	 * Getter del estado del alquiler si esta en curso
	 * @return true si esta en curso \n false si no esta en curso
	 */
	public boolean isEnCurso() {
		return enCurso;
	}

	/**
	 * Setter del estado del alquiler si esta en curso
	 * @param enCurso
	 */
	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}
	
}
