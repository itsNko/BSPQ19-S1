package es.deusto.server.server;

import java.rmi.Naming;
import java.util.Scanner;

import es.deusto.server.remote.IServer;
import es.deusto.server.remote.Server;

public class ProgramaPrincipal {

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
				IServer server = Server.getInstance();
				Naming.rebind(name, server);
				Scanner scanner = new Scanner(System.in);
				System.out.println("* Server '" + name + "' active and waiting...");
				scanner.nextLine();
				scanner.close();
			} catch (Exception e) {
				System.err.println("- Exception running the server: " + e.getMessage());
				e.printStackTrace();
			}
		}
}
