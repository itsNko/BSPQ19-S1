package es.deusto.server.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import es.deusto.client.data.Alquiler;
import es.deusto.client.data.Articulo;

import es.deusto.client.data.Pelicula;
import es.deusto.client.data.Socio;
import es.deusto.client.data.Videojuego;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase MySQL_DB.
 */
public class MySQL_DB implements IDAO {

	PersistenceManagerFactory persistentManagerFactory;
	PersistenceManager persistentManager;			
	Transaction transaction;

	Logger logger = LoggerFactory.getLogger("ServerLog");

	/**
	 * Constructor de la clase MySQL_DB.
	 */
	public MySQL_DB() {
		persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		persistentManager = null;			
		transaction = null;
		logger.info("MySQL_DB - Constructor");
	}

	@Override
	public boolean insertarSocio(Socio socio) {
		logger.info("MySQL_DB - insertarSocio()");
		boolean result = false;
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			transaction.begin();

			persistentManager.makePersistent(socio);

			System.out.println("- Inserted into db: " + socio.getNombre());

			transaction.commit();

			result = true;
		} catch(Exception ex) {
			logger.error("MySQL_DB - insertarSocio() : Error -> " + ex.getMessage());
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
			}


			persistentManager.close();
		}

		return result;
	}

	//	@Override
	//	public boolean deleteSocio(Socio socio) {
	//		boolean result = false;
	//		try {
	//			persistentManager = persistentManagerFactory.getPersistenceManager();
	//			transaction = persistentManager.currentTransaction();
	//			transaction.begin();
	//
	//			persistentManager.deletePersistent(socio);
	//
	//			System.out.println("- Deleted from db: " + socio.getNombre());
	//
	//			transaction.commit();
	//
	//			result = true;
	//		} catch(Exception ex) {
	//			System.err.println("* Exception deleting data from db: " + ex.getMessage());
	//		} finally {		    
	//			if (transaction.isActive()) {
	//				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
	//			}
	//
	//			persistentManager.close();
	//		}
	//		return result;
	//	}

	@Override
	public boolean existeSocio(String nombreSocio) {
		logger.info("MySQL_DB - existeSocio()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		//persistentManager.getFetchPlan().setMaxFetchDepth(1);

		boolean existe = false;
		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreSocio);
			if(socio.getNombre().equals(nombreSocio)) {
				existe = true;
			}
			logger.info("MySQL_DB - existeSocio() : OK");
		} catch (Exception ex) {
			logger.error("MySQL_DB - existeSocio() : Error -> " + ex.getMessage());
		} finally {
			persistentManager.close();
		}

		return existe;
	}

	@Override
	public Socio inicioSesion(String nombreSocio, String password) {
		logger.info("MySQL_DB - inicioSesion()");
		persistentManager = persistentManagerFactory.getPersistenceManager();

		Socio s = null;
		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreSocio);
			if(socio.getPassword().equals(password)) {
				s = socio;
			}

			logger.info("MySQL_DB - inicioSesion() : OK");
		} catch (Exception ex) {
			logger.error("MySQL_DB - inicioSesion() : Error -> " + ex.getMessage());
		} finally {
			persistentManager.close();
		}

		if(s == null) {
			s = new Socio();
		}

		return s;
	}

	@Override
	public boolean insertarAlquiler(Alquiler alquiler, String nombreUsuario) {
		logger.info("MySQL_DB - insertarAlquiler()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();
			Articulo articulo = persistentManager.getObjectById(Articulo.class, alquiler.getAlquilado().getNombre());
			alquiler.setAlquilado(articulo);
			Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
			Iterator<Socio> iter = e.iterator();
			while (iter.hasNext()) {
				Socio s = (Socio)iter.next();
				if (s.getNombre().equals(nombreUsuario)) {
					if(s.getAlquileres() == null || s.getAlquileres().isEmpty()) {
						List<Alquiler> a2 = new ArrayList<Alquiler>();
						a2.add(alquiler);
						s.setAlquileres(a2);
					} else {
						List<Alquiler> a =s.getAlquileres();
						a.add(alquiler);

						s.setAlquileres(a);
					}
				}

			}	
			transaction.commit();
			logger.info("MySQL_DB - insertarAlquiler() : OK");
			return true;
		} catch(Exception ex) {
			logger.error("MySQL_DB - insertarAlquiler() : Error -> " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
			}

			persistentManager.close();
		}
	}

	@Override
	public Socio selectSocio(String nombreUsuario) {
		logger.info("MySQL_DB - selectSocio()");
		persistentManager = persistentManagerFactory.getPersistenceManager();

		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreUsuario);

			logger.info("MySQL_DB - selectSocio() : OK");
			return socio;
		} catch(Exception ex) {
			logger.error("MySQL_DB - selectSocio() : Error -> " + ex.getMessage());
			return new Socio();
		} finally {
			persistentManager.close();
		}

	}

	@Override
	public boolean updateMonedero(String nombreUsuario, Double monedero) {
		logger.info("MySQL_DB - updateMonedero()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
			Iterator<Socio> iter = e.iterator();
			while (iter.hasNext()) {
				Socio s = (Socio)iter.next();
				if (s.getNombre().equals(nombreUsuario)) {
					System.out.println("- Data modified: " + s.getMonedero() +" -> "+monedero);
					s.setMonedero(monedero);
					transaction.commit();
					logger.info("MySQL_DB - updateMonedero() : OK");
					return true;
				}
			}
			
		} catch(Exception ex) {
			logger.error("MySQL_DB - updateMonedero() : Error -> " + ex.getMessage());
			return false;
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return false;
	}

	public boolean updateDescuento(String nombreArticulo, double descuento) {
		logger.info("MySQL_DB - updateDescuento()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<Articulo> e = persistentManager.getExtent(Articulo.class, true);
			Iterator<Articulo> iter = e.iterator();
			while (iter.hasNext()) {
				Articulo s = (Articulo)iter.next();
				if (s.getNombre().equals(nombreArticulo)) {
					System.out.println("- Data modified: " + s.getDescuento() +" -> "+descuento);
					s.setDescuento(descuento);
					transaction.commit();
					logger.info("MySQL_DB - updateDescuento() : OK");
					return true;
				}
			}


		} catch(Exception ex) {
			logger.error("MySQL_DB - updateDescuento() : Error -> " + ex.getMessage());
			return false;
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return false;
	}

	public boolean updateDatosSocio(String nombreSocio, String datosNuevos) {
		logger.info("MySQL_DB - updateDatosSocio()");
		persistentManager = persistentManagerFactory.getPersistenceManager();

		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreSocio);

			String[] partes = datosNuevos.split(";");
			if(!partes[0].equals(" ")) {
				socio.setNombreCompleto(partes[0]);
			}

			if(!partes[1].equals(" ")) {
				socio.setApellidos(partes[1]);
			}

			if(!partes[2].equals(" ")) {
				socio.setPassword(partes[2]);
			}

			if(!partes[3].equals(" ")) {
				socio.setDireccion(partes[3]);
			}

			logger.info("MySQL_DB - updateDatosSocio() : OK");
			return true;

		} catch(Exception ex) {
			logger.error("MySQL_DB - updateDatosSocio() : Error -> " + ex.getMessage());
			return false;
		} finally {
			persistentManager.close();
		}

	}

	@Override
	public List<Alquiler> selectAlquilerPorSocio(String nombreUsuario) {
		logger.info("MySQL_DB - selectAlquilerPorSocio()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();
			@SuppressWarnings("unchecked")
			Query<Alquiler> query = persistentManager.newQuery("javax.jdo.query.SQL",
					"SELECT * FROM ALQUILER WHERE ALQUILERES_NOMBRE_OWN = '" + nombreUsuario + "'");
			query.setClass(Alquiler.class);
			List<Alquiler> alquileres = (List<Alquiler>) new ArrayList<Alquiler>();
			logger.info("MySQL_DB - selectAlquilerPorSocio() : OK");
			return alquileres;
		} catch (Exception ex) {
			logger.error("MySQL_DB - selectAlquilerPorSocio() : Error -> " + ex.getMessage());
			return null;
		}

	}

	@Override
	public List<Articulo> listadoArticulos() {
		logger.info("MySQL_DB - listadoArticulos()");
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();	
			transaction.begin();

			Extent<Pelicula> extentPeliculas = persistentManager.getExtent(Pelicula.class, true);
			Extent<Videojuego> extentVideojuegos = persistentManager.getExtent(Videojuego.class, true);

			List<Articulo> articulos = new ArrayList<Articulo>();

			for (Pelicula pelicula : extentPeliculas) {
				articulos.add(pelicula);
			}

			for (Videojuego juego : extentVideojuegos) {
				articulos.add(juego);
			}

			transaction.commit();
			for(Articulo a: articulos)
			{
				System.out.println(a.getPrecio());
			}

			logger.info("MySQL_DB - listadoArticulos() : OK");
			return articulos;

		} catch(Exception ex) {
			logger.error("MySQL_DB - listadoArticulos() : Error -> " + ex.getMessage());
			return null;

		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); 
			}

			persistentManager.close();
		}
	}

	@Override
	public List<Alquiler> historialAlquileres(String nombreSocio) {
		logger.info("MySQL_DB - historialAlquileres()");
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();

			Socio socio = persistentManager.getObjectById(Socio.class, nombreSocio);
			String[] partes;
			Pelicula peli;
			Videojuego videojuego;

			List<Alquiler> alquileres = new ArrayList<Alquiler>();
			alquileres = socio.getAlquileres();

			if(!(alquileres.isEmpty() || alquileres == null)) {
				for(Alquiler alq : alquileres) {
					partes = alq.getNombreArticulo().split("-");

					if(partes[1].equals("Pelicula")) {
						peli = persistentManager.getObjectById(Pelicula.class, partes[0]);
						alq.setAlquilado(peli);
					} else {
						videojuego = persistentManager.getObjectById(Videojuego.class, partes[0]);
						alq.setAlquilado(videojuego);
					}

				}

				for(Alquiler a : alquileres) {
					System.out.println(a.getAlquilado().getNombre());
					System.out.println(a.getCoste());
				}
			}

			logger.info("MySQL_DB - historialAlquileres() : OK");
			return alquileres;
		} catch(Exception ex) {
			logger.error("MySQL_DB - historialAlquileres() : Error -> " + ex.getMessage());
			return null;
		} finally {		    
			persistentManager.close();
		}

	}

	@Override
	public boolean insertarPelicula(Pelicula pelicula) {
		logger.info("MySQL_DB - insertarPelicula()");
		System.out.println(pelicula.getNombre());
		boolean result = false;
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			transaction.begin();

			persistentManager.makePersistent(pelicula);

			System.out.println("- Inserted into db: " + pelicula.getNombre());

			transaction.commit();

			result = true;
			logger.info("MySQL_DB - insertarPelicula() : OK");
		} catch(Exception ex) {
			logger.error("MySQL_DB - insertarPelicula() : Error -> " + ex.getMessage());
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
			}


			persistentManager.close();
		}

		return result;

	}

	@Override
	public boolean insertarVideojuego(Videojuego videojuego) {
		logger.info("MySQL_DB - insertarVideojuego()");
		System.out.println(videojuego.getNombre());
		boolean result = false;
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			transaction.begin();

			persistentManager.makePersistent(videojuego);

			System.out.println("- Inserted into db: " + videojuego.getNombre());

			transaction.commit();

			result = true;
			logger.info("MySQL_DB - insertarVideojuego() : OK");
		} catch(Exception ex) {
			logger.error("MySQL_DB - insertarVideojuego() : Error -> " + ex.getMessage());
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
			}


			persistentManager.close();
		}

		return result;

	}

	@Override
	public boolean devolverAlquiler(String nombreUsuario, String nombreArticulo, int valoracion) {
		logger.info("MySQL_DB - devolverAlquiler()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();

			Extent<Socio> extentSocios = persistentManager.getExtent(Socio.class, true);
			for(Socio s: extentSocios) {
				if(s.getNombre().equals(nombreUsuario))	{
					for(Alquiler a: s.getAlquileres()) {
						if(a.getAlquilado().getNombre().equals(nombreArticulo)) {
							a.setEnCurso(false);
							a.setValoracion(valoracion);
							transaction.commit();
							logger.info("MySQL_DB - devolverAlquiler() : OK");
							return true;
						}
					}
				}

			}


		} catch (Exception ex) {
			logger.error("MySQL_DB - devolverAlquiler() : Error -> " + ex.getMessage());
			return false;
		}
		return false;

	}
	@Override
	public boolean updatePrecio(String nombreArticulo, double precio) {
		logger.info("MySQL_DB - updatePrecio()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<Articulo> e = persistentManager.getExtent(Articulo.class, true);
			Iterator<Articulo> iter = e.iterator();
			while (iter.hasNext()) 
			{
				Articulo s = (Articulo)iter.next();
				if (s.getNombre().equals(nombreArticulo))
				{
					System.out.println("- Data modified: " + s.getPrecio()+" -> "+ precio);
					s.setPrecio(precio);
					transaction.commit();
					logger.info("MySQL_DB - updatePrecio() : OK");
					return true;
				}
			}

		} catch(Exception ex) {
			logger.error("MySQL_DB - updatePrecio() : Error -> " + ex.getMessage());
			return false;
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		return false;
	}

	@Override
	public boolean bloquearMaquina(String nombreAdmin) {
		logger.info("MySQL_DB - bloquearMaquina()");
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreAdmin);
			if(socio.isBloquearMaquina()) {
				socio.setBloquearMaquina(false);
			} else {
				socio.setBloquearMaquina(true);
			}

			logger.info("MySQL_DB - bloquearMaquina() : OK");
			return true;
		} catch(Exception ex) {
			logger.error("MySQL_DB - bloquearMaquina() : Error -> " + ex.getMessage());
			return false;
		} finally {
			persistentManager.close();
		}

	}

}
