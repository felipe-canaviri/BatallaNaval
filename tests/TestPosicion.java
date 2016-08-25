import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class TestPosicion {

	@Test
	public void testCrearPosicion() {
		Posicion posicion = new Posicion(1, 5);
		
		assertNotNull(posicion);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCrearPosicionErronea() {
		Posicion posicion = new Posicion(-1, 8);
		
		assertNull(posicion);
	}
	
	@Test(expected = RuntimeException.class)
	public void testCrearPosicionFueraDeRango() {
		Posicion posicion = new Posicion(-1, 80);
		
		assertNull(posicion);
	}
	@Test 
	public void CompararPosicionesIguales(){
		Posicion posicionA = new Posicion(3,4);
		Posicion posicionB = new Posicion(3,4);
		
		boolean Resultado=  posicionA.equals(posicionB);
		assertTrue(Resultado);
		
	}
	@Test 
	public void CompararPosicionesDistintas(){
		Posicion posicionA = new Posicion(3,4);
		Posicion posicionB = new Posicion(4,4);
		
		boolean Resultado=  posicionA.equals(posicionB);
		assertFalse(Resultado);
	}
	
}
