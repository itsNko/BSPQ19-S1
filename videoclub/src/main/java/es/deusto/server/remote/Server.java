package es.deusto.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements IServer {

	private static Server INSTANCE;

	private static final long serialVersionUID = 1L;

	private Server() throws RemoteException {
		super();
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

}
