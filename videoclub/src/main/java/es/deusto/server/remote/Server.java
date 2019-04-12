package es.deusto.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.server.services.AppServiceDB;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	
	private static Server INSTANCE;
	private AppServiceDB appService;
	

	private Server() throws RemoteException {
		super();
		appService = new AppServiceDB();
	}

	public static Server getInstance() {
		synchronized(Server.class) {
			if (INSTANCE == null) {
				try {
					INSTANCE = new Server();
				} catch (Exception ex) {
					System.err.println("# Error creating Server: " + ex);
				}
			}

			return INSTANCE;
		}

	}

	public String sayHello() throws RemoteException {
		return "Hello World!";
	}

	@Override
	public boolean registro(String email, String pass, double monedero) throws RemoteException {
		try {
			System.out.println("###Server: AppServiceDB.insertarSocio###");
			return appService.insertarSocio(email, pass, monedero);
		} catch (Exception e) {
			System.err.println("$ Error al registrarse " + e.getMessage());
			return false;
		}
	}

}
