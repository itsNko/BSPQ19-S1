package es.deusto.paypal.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import es.deusto.paypal.data.UsuarioPayPal;

public class PayPalServer extends UnicastRemoteObject implements IPayPalServer {

	private static final long serialVersionUID = 1L;
	private int cont = 1;
	private List<UsuarioPayPal> registro = new ArrayList<UsuarioPayPal>();

	protected PayPalServer() throws RemoteException {
		this.registro.add(new UsuarioPayPal("pago", "1234", 500.0));
		this.registro.add(new UsuarioPayPal("dinero", "money", 9999.0));
		this.registro.add(new UsuarioPayPal("pocoDinero", "1234", 25.0));
	}

	public List<UsuarioPayPal> getRegistro() {
		return registro;
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {		
			IPayPalServer paypalServer = new PayPalServer();
			Naming.rebind(name, paypalServer);
			VentanaServerPaypal vsp = new VentanaServerPaypal();
			System.out.println("* '" + name + "' active and waiting...");
			vsp.setVisible(true);
		} catch (Exception e) {
			System.err.println("- Exception running the PayPal server: " + e.getMessage());
			e.printStackTrace();
		}
	}


	@Override
	public boolean pagar(String nombre, String password, double cantidad) throws RemoteException {
		boolean pago = false;

		for (int i = 0; i < this.registro.size(); i++) {
			if(this.registro.get(i).getEmail().equals(nombre) && this.registro.get(i).getPassword().equals(password)) {
				System.out.println("###PayPal server: Cliente " + cont + " ha iniciado sesion correctamente###");
				System.out.println("###Paypal server: Iniciando pago...###");
				if(this.registro.get(i).getSaldo() >= cantidad) {
					System.out.println("###Paypal server: Realizando pago ->" + this.registro.get(i).getSaldo() + " - " + cantidad + "###");
					this.registro.get(i).setSaldo(this.registro.get(i).getSaldo() - cantidad);
					System.out.println("###PayPal server: Cantidad restante: " + this.registro.get(i).getSaldo() + "###");
					System.out.println("###PayPal server: Cliente " + cont + " ha realizado el pago correctamente###");
					cont++;
					pago = true;
				} else {
					System.out.println("###PayPal server: Cliente " + cont + " no ha podido realizar el pago correctamente###");
					cont++;
					throw new RemoteException("###PayPal server: No se ha podido realizar el pago, cantidad de dinero insuficiente###");
				}
			} else if (i == this.registro.size()) {
				System.out.println("###PayPal server: Cliente " + cont + " no ha iniciado sesion correctamente###");
				cont++;
				throw new RemoteException("###PayPal server: No se ha podido iniciar sesion, datos incorrectos###");
			}

		}

		return pago;
	}

}