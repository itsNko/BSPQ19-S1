package es.deusto.server.services;

import java.rmi.RemoteException;

import es.deusto.client.data.Socio;
import es.deusto.server.db.MySQL_DB;
import es.deusto.server.db.IDAO;

public class AppServiceDB {

	private IDAO db;

	public AppServiceDB() throws RemoteException {
		db = new MySQL_DB();
	}

	public boolean insertarSocio(String email, String pass, double monedero) {
		Socio s = new Socio(email, pass, monedero);
		return db.insertarSocio(s);
	}

}
