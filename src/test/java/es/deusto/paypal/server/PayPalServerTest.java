package es.deusto.paypal.server;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.deusto.paypal.data.UsuarioPayPal;

public class PayPalServerTest {

	private static List<UsuarioPayPal> registro = new ArrayList<UsuarioPayPal>();
	private PayPalServer pps;

	@BeforeClass
	public static void init() {
		registro.add(new UsuarioPayPal("pago", "1234", 500.0));
		registro.add(new UsuarioPayPal("dinero", "money", 9999.0));
		registro.add(new UsuarioPayPal("pocoDinero", "1234", 25.0));
	}

	@Before
	public void setUp() {
		try {
			pps = new PayPalServer();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void constructorTest() {
		assertNotNull(pps);
		assertNotNull(pps.getRegistro());
	}

	@Test
	public void getRegistroTest() {
		boolean igual = false;
		List<UsuarioPayPal> reg = pps.getRegistro();

		for (int i = 0; i < reg.size(); i++) {
			if(reg.get(i).getEmail().equals(registro.get(i).getEmail()) &&
					reg.get(i).getPassword().equals(registro.get(i).getPassword()) &&
					reg.get(i).getSaldo() == registro.get(i).getSaldo()) {
				igual = true;
			}
		}
		assertTrue(igual);
	}

	@Test
	public void pagarBienTest() {
		boolean result = false;
		try {
			result = pps.pagar("pago", "1234", 50.0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertTrue(result);
		assertEquals(450.0, pps.getRegistro().get(0).getSaldo(), 0);
	}

	@Test
	public void pagarMalTest1() {
		boolean result = false;
		try {
			result = pps.pagar("pago", "1", 50.0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}

	@Test
	public void pagarMalTest2() {
		boolean result = false;
		try {
			result = pps.pagar("pocoDinero", "1234", 50.0);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		assertFalse(result);
	}

}
