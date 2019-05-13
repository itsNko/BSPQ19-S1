package es.deusto.paypal.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPayPalServer extends Remote {
	boolean pagar(String nombre, String password, double cantidad) throws RemoteException;
}

