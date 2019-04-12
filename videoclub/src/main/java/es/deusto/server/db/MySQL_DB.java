package es.deusto.server.db;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import es.deusto.client.data.Socio;

public class MySQL_DB implements IDAO {

	PersistenceManagerFactory persistentManagerFactory;
	PersistenceManager persistentManager;			
	Transaction transaction;
	
	
	public MySQL_DB() {
		persistentManagerFactory = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		persistentManager = null;			
		transaction = null;
	}


	// Devolver true si se ha guardado correctamente el socio en la DB. En caso contrario devolver false.
	@Override
	public boolean insertarSocio(Socio usuario) {
		// TODO 
		return true;
	}
	
}
