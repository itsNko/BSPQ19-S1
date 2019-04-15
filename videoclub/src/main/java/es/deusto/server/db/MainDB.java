package es.deusto.server.db;

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

public class MainDB {

	public static void main(String[] args) {
		try {
			PersistenceManagerFactory persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");

			//Insert data in the DB
			PersistenceManager persistentManager = persistentManagerFactory.getPersistenceManager();				
			Transaction transaction = persistentManager.currentTransaction();

			Socio s1 = new Socio("Marcos20", "1111111A", 20.75, "default-profile.png");
			Socio s2 = new Socio("Isabel1123", "2222222B", 10, "default-profile.png");
			Socio s3 = new Socio("Fran", "3333333C", 0, "default-profile.png");

			Articulo art1 = new Pelicula("Los Vengadores", 5.5, "Sinopsis Los Vengadores", "Acción", "30/11/2010", 2, "vengadores.jpg");
			Articulo art2 = new Videojuego("Mario Bros", 6, "Juego de la Wii", "Plataformas", "20/06/2005", 8.5, "mario.jpg");

			Alquiler alquiler1 = new Alquiler(art1, art1.getPrecio(), "15/04/2019", "20/04/2019", true);
			Alquiler alquiler2 = new Alquiler(art1, art1.getPrecio(), "15/04/2019", "20/04/2019", true);

			try {
				transaction.begin();

				persistentManager.makePersistent(s1);
				persistentManager.makePersistent(s2);
				persistentManager.makePersistent(s3);

				System.out.println("- Inserted into db: " + s1.getNombre());
				System.out.println("- Inserted into db: " + s2.getNombre());
				System.out.println("- Inserted into db: " + s3.getNombre());

				persistentManager.makePersistent(art1);
				persistentManager.makePersistent(art2);

				System.out.println("- Inserted into db: " + art1.getNombre());
				System.out.println("- Inserted into db: " + art2.getNombre());

				transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception inserting data into db: " + ex.getMessage());
			} finally {		    
				if (transaction.isActive()) {
					transaction.rollback(); // Deshace los cambios en caso de que de algun error
				}

				persistentManager.close();
			}

			//Select data using a Query and delete data
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();

			try {
				transaction.begin();

				@SuppressWarnings("unchecked")
				Query<Socio> usuariosQuery = persistentManager.newQuery("SELECT FROM " + Socio.class.getName());
				for (Socio socio : usuariosQuery.executeList()) {
					System.out.println("- Selected from db: " + socio.getNombre());
					if(socio.getNombre().equals("Fran")) {
						// Borra el socio con nombre 'Fran'
						persistentManager.deletePersistent(socio);
						System.out.println("- Deleted from db: " + socio.getNombre());
					}

				}

				transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
					transaction.rollback();
				}

				persistentManager.close();
			}

			// Update de un dato de la DB
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();

			try {
				transaction.begin();

				//				@SuppressWarnings("unchecked")
				//				Query<Aerolinea> aerolineaQuery = persistentManager.newQuery("UPDATE " +  Usuario.class.getName() + " SET email = 'aaaa' WHERE id = 1;");

				Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
				Iterator<Socio> iter = e.iterator();
				while (iter.hasNext()) {
					Socio s = (Socio) iter.next();
					if (s.getNombre().equals("Isabel1123")) {
						System.out.println("- Data modified: " + s.getPassword() + " ---> " + s.getPassword() + "M");
						s.setPassword(s.getPassword() + "M");
					}
				}

				transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
					transaction.rollback();
				}

				persistentManager.close();
			}

			// Añadir alquiler a socio
			persistentManager = persistentManagerFactory.getPersistenceManager();
			transaction = persistentManager.currentTransaction();

			try {
				transaction.begin();

				Extent<Articulo> a = persistentManager.getExtent(Articulo.class, true);
				Iterator<Articulo> iter2 = a.iterator();

				while (iter2.hasNext()) {
					Articulo art = (Articulo) iter2.next();
					if (art.getNombre().equals(alquiler1.getAlquilado().getNombre())) {
						alquiler1.setAlquilado(art);
					}
				}
				transaction.commit();

				transaction.begin();

				Extent<Articulo> a2 = persistentManager.getExtent(Articulo.class, true);
				Iterator<Articulo> iter3 = a2.iterator();

				while (iter3.hasNext()) {
					Articulo art = (Articulo) iter3.next();
					if (art.getNombre().equals(alquiler2.getAlquilado().getNombre())) {
						alquiler2.setAlquilado(art);
					}
				}
				transaction.commit();

				transaction.begin();
				Extent<Socio> e = persistentManager.getExtent(Socio.class, true);
				Iterator<Socio> iter = e.iterator();

				while (iter.hasNext()) {
					Socio s = (Socio) iter.next();
					if (s.getNombre().equals("Marcos20")) {
						System.out.println("- Añadida alquileres a socio: " + s.getNombre());
						s.getAlquileres().add(alquiler1);
						s.getAlquileres().add(alquiler2);
					}
				}

				transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
					transaction.rollback();
				}

				persistentManager.close();
			}


		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}

	}

}
