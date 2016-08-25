import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class TestSoldado {
	
	@Test
	public void testConstruir(){
		Tablero tablero = new Tablero();
		Posicion base = new Posicion(3, 3);
		Soldado soldado = new Soldado(tablero, base);
		
		assertNotNull(soldado);
	}
	
	@Test(expected = RuntimeException.class)
	public void testConstruirEnPosicionOcupada(){
		Tablero tablero = new Tablero();
		tablero.posicionEn(3, 3).ocupar();
		Posicion base = new Posicion(3, 3);
		Soldado soldado = new Soldado(tablero, base);
		
		assertNull(soldado);
	}
	
	@Test
	public void testRecibirDisparo(){
		Tablero tablero = new Tablero();
		Posicion base = new Posicion(3, 3);
		Soldado soldado = new Soldado(tablero, base);
		
		tablero.posicionEn(base.X, base.Y).recibirDisparo();
		
		EfectoDisparoEnum efecto = soldado.recibirDisparo(base);
		
		assertEquals(EfectoDisparoEnum.MUERTO, efecto);
	}
	
	@Test
	public void testMover() {
		Tablero tablero = new Tablero();
		Posicion base = new Posicion(3, 3);
		Soldado soldado = new Soldado(tablero, base);
		
		soldado.mover(3, 3, 1);
		
		assertEquals(2, soldado.posicionActual().Y);
	}
}
