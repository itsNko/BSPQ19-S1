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
			System.err.println("* Exception inserting data into db: " + ex.getMessage());
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
		persistentManager = persistentManagerFactory.getPersistenceManager();
		//persistentManager.getFetchPlan().setMaxFetchDepth(1);

		boolean existe = false;
		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreSocio);
			if(socio.getNombre().equals(nombreSocio)) {
				existe = true;
			}
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

		Socio s = null;
		try {
			Socio socio = persistentManager.getObjectById(Socio.class, nombreSocio);
			if(socio.getPassword().equals(password)) {
				s = socio;
			}
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
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

		try {
			transaction.begin();
			Articulo articulo = persistentManager.getObjectById(Articulo.class, alquiler.getAlquilado().getNombre());
			alquiler.setAlquilado(articulo);
			Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
			Iterator<Socio> iter = e.iterator();
			while (iter.hasNext()) 
			{
				Socio s = (Socio)iter.next();
				if (s.getNombre().equals(nombreUsuario))
				{
					if(s.getAlquileres() == null || s.getAlquileres().isEmpty())
					{
						List<Alquiler> a2 = new ArrayList<Alquiler>();
						a2.add(alquiler);
						s.setAlquileres(a2);
					}else
					{
						List<Alquiler> a =s.getAlquileres();
						a.add(alquiler);

						s.setAlquileres(a);
					}
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
			Socio socio = persistentManager.getObjectById(Socio.class, nombreUsuario);
			
			return socio;
		} catch(Exception ex) {
			System.err.println("* Exception executing a query: " + ex.getMessage());
			return new Socio();
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
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
					transaction.commit();
					return true;
				}
			}


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

	public boolean updateDescuento(String nombreArticulo, double descuento) {
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
					System.out.println("- Data modified: " + s.getDescuento() +" -> "+descuento);
					s.setDescuento(descuento);
					transaction.commit();
					return true;
				}
			}


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

	public boolean updateDatosSocio(String nombreSocio, String datosNuevos) {
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();

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
			
			return true;

		} catch(Exception ex) {
			System.err.println("* Exception executing a query: " + ex.getMessage());
			return false;
		} finally {
			if (transaction.isActive()) {
				transaction.rollback();
			}

			persistentManager.close();
		}

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
			for(Articulo a: articulos)
			{
				System.out.println(a.getPrecio());
			}
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

	@Override
	public List<Alquiler> historialAlquileres(String nombreSocio) {

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

			return alquileres;
		} catch(Exception ex) {
			System.err.println("* Exception retrieving alquileres: " + ex.getMessage());
			return null;
		} finally {		    
			if (transaction.isActive()) {
				transaction.rollback(); 
			}

			persistentManager.close();
		}

	}

	@Override
	public boolean insertarPelicula(Pelicula pelicula) {
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
		} catch(Exception ex) {
			System.err.println("* Exception inserting data into db: " + ex.getMessage());
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
		} catch(Exception ex) {
			System.err.println("* Exception inserting data into db: " + ex.getMessage());
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
		persistentManager = persistentManagerFactory.getPersistenceManager();
		transaction = persistentManager.currentTransaction();
		try {
			transaction.begin();

			Extent<Socio> extentSocios = persistentManager.getExtent(Socio.class, true);
			for(Socio s: extentSocios)
			{
				if(s.getNombre().equals(nombreUsuario))
				{
					for(Alquiler a: s.getAlquileres())
					{
						if(a.getAlquilado().getNombre().equals(nombreArticulo))
						{
							a.setEnCurso(false);
							a.setValoracion(valoracion);
							transaction.commit();
							return true;
						}
					}
				}

			}


		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
			return false;
		}
		return false;

	}
	@Override
	public boolean updatePrecio(String nombreArticulo, double precio) {

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
					return true;
				}
			}


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

}
