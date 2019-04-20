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

public class MySQL_DB implements IDAO {

	PersistenceManagerFactory persistentManagerFactory;
	PersistenceManager persistentManager;			
	Transaction transaction;


	public MySQL_DB() {
		persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		persistentManager = null;			
		transaction = null;
	}

	@Override
	public boolean insertarSocio(Socio socio) {
		try {
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();
			transaction.begin();


			persistentManager.makePersistent(socio);

			System.out.println("- Inserted into db: " + socio.getNombre());

			transaction.commit();

			return true;
		} catch(Exception ex) {
			System.err.println("* Exception inserting data into db: " + ex.getMessage());
			return false;
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); // Deshace los cambios en caso de que ocurra algún error
			}

			persistentManager.close();
		}
	}

	@Override
	public boolean existeSocio(String nombreSocio) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		persistentManager.getFetchPlan().setMaxFetchDepth(1);

		transaction = persistentManager.currentTransaction();

		boolean existe = false;
		try {
			System.out.println("* Retrieving an Extent for Socios.");

			transaction.begin();
			Extent<Socio> extent = persistentManager.getExtent(Socio.class, true);

			for (Socio socio : extent) {
				if(socio.getNombre().equals(nombreSocio)) {
					existe = true;
					break;
				}
			}


			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error retrieving an extent: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

		return existe;
	}
	
	@Override
	public Socio inicioSesion(String nombreSocio, String password) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		
		transaction = persistentManager.currentTransaction();
		
		Socio s = null;
		try {
			System.out.println("* Retrieving an Extent for Socios.");

			transaction.begin();
			Extent<Socio> extent = persistentManager.getExtent(Socio.class, true);

			for (Socio socio : extent) {
				if(socio.getNombre().equals(nombreSocio)) {
					if(socio.getPassword().equals(password)) {
						s = socio;
						break;
					}
				}
			}

			transaction.commit();
		} catch (Exception ex) {
			System.out.println("$ Error retrieving an extent: " + ex.getMessage());
		} finally {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}
		
		if(s == null) {
			s = new Socio();
		}
	
		return s;
	}
	
	@Override
	public boolean insertarAlquiler(Alquiler alquiler, String nombreUsuario)
	{
		
		try {
			transaction.begin();

			Extent<Articulo> a = persistentManager.getExtent(Articulo.class, true);
			Iterator<Articulo> iter2 = a.iterator();

			while (iter2.hasNext()) {
				Articulo art = (Articulo) iter2.next();
				if (art.getNombre().equals(alquiler.getAlquilado().getNombre())) {
					alquiler.setAlquilado(art);
				}
			}
			transaction.commit();
			
			transaction.begin();
			Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
			Iterator<Socio> iter = e.iterator();

			while (iter.hasNext()) {
				Socio s = (Socio) iter.next();
				if (s.getNombre().equals(nombreUsuario)) {
					System.out.println("- Añadida alquileres a socio: " + s.getNombre());
					s.getAlquileres().add(alquiler);
				}
			}

			transaction.commit();
			
			

			return true;
		} catch(Exception ex) {
			System.err.println("* Exception inserting data into db: " + ex.getMessage());
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
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();
			@SuppressWarnings("unchecked")
			Query<Socio> query = persistentManager.newQuery("javax.jdo.query.SQL",
					"SELECT * FROM SOCIO WHERE NOMBRE = '" + nombreUsuario + "'");
			query.setClass(Socio.class);
			query.setUnique(true);
			Socio socio = (Socio) query.execute();
			return socio;
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
			return new Socio();
		}

	}
	
	@Override
	public boolean updateMonedero(String nombreUsuario, Double monedero) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();

			Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
			Iterator<Socio> iter = e.iterator();
			while (iter.hasNext()) 
			{
				Socio s = (Socio)iter.next();
				if (s.getNombre().equals(nombreUsuario))
				{
					System.out.println("- Data modified: " + s.getMonedero() +" -> "+monedero);
					s.setMonedero(monedero);
					return true;
				}
			}

			transaction.commit();
		} catch(Exception ex) {
			System.err.println("* Exception executing a query: " + ex.getMessage());
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
	public List<Alquiler> selectAlquilerPorSocio(String nombreUsuario) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();
			@SuppressWarnings("unchecked")
			Query<Alquiler> query = persistentManager.newQuery("javax.jdo.query.SQL",
					"SELECT * FROM ALQUILER WHERE ALQUILERES_NOMBRE_OWN = '" + nombreUsuario + "'");
			query.setClass(Alquiler.class);
			List<Alquiler> alquileres = (List<Alquiler>) new ArrayList<Alquiler>();
			return alquileres;
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
			return null;
		}

	}

	@Override
	public List<Articulo> listadoArticulos() {
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

			return articulos;
			
		} catch(Exception ex) {
			System.err.println("* Exception retrieving articulos: " + ex.getMessage());
			return null;
			
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); 
			}

			persistentManager.close();
		}
	}

}
