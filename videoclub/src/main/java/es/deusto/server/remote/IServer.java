package es.deusto.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {

	String sayHello() throws RemoteException;
	public boolean registro(String nombre, String pass, double monedero) throws RemoteException;
	public boolean existeSocio(String nombreSocio) throws RemoteException;

}
