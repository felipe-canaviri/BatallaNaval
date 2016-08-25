import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestTanque {

	@Test
	public void testRecibirDisparo() {
		Tablero tablero = new Tablero();
		Posicion posicionBase = new Posicion(2, 3);
		Posicion misil = new Posicion(2, 3);
		tablero.agregarTanque(posicionBase, DireccionEnum.UP, 0);
		
		EfectoDisparoEnum efecto = tablero.recibirDisparo(misil);
		
		assertEquals(EfectoDisparoEnum.HERIDO, efecto);
		
	}
	
	@Test
	public void mover(){
		Tablero tablero = new Tablero();
		Posicion posicionBase = new Posicion(2, 3);
		
		tablero.agregarTanque(posicionBase, DireccionEnum.UP, 0);
		
//		tablero.tanques[0].mover(3, 3, 0);
		tablero.piezas.get(0).mover(3, 3, 0);
		
		assertTrue(tablero.posicionEn(2, 3).estaOcupado());
		assertTrue(tablero.posicionEn(3, 2).estaOcupado());
		assertTrue(tablero.posicionEn(3, 3).estaOcupado());
		assertTrue(tablero.posicionEn(4, 3).estaOcupado());
	}
	
}
