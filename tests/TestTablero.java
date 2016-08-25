import static org.junit.Assert.*;

import org.junit.Test;


public class TestTablero {

	@Test
	public void testInicializarTablero() {
		Tablero tablero = new Tablero ();
		assertNotNull(tablero);	
	}
	
	@Test
	public void testVerificarPosicionesTablero() {
		Tablero tablero = new Tablero ();
		Posicion posicion = tablero.posicionEn (5,4); 
		assertNotNull(tablero);
		assertNotNull(posicion);
		assertTrue(posicion.X==5);
		assertTrue(posicion.Y==4);
	}
	
	
	@Test
	public void testCrearTanqueConDireccionYBase(){
		Tablero tablero = new Tablero();
		Posicion posicionBase = new Posicion(2, 3);
		tablero.agregarTanque(posicionBase, DireccionEnum.UP, 0);
		
//		assertNotNull(tablero.tanques[0]);
		assertNotNull(tablero.piezas.get(0));
		assertTrue(tablero.posicionEn(2, 3).estaOcupado());
		assertTrue(tablero.posicionEn(2, 2).estaOcupado());
		assertTrue(tablero.posicionEn(1, 3).estaOcupado());
		assertTrue(tablero.posicionEn(3, 3).estaOcupado());
	}
	
	@Test(expected = RuntimeException.class)
	public void testCrearTanqueEnPosicionOcupada(){
		Tablero tablero = new Tablero();
		Posicion posicionBase = new Posicion(2, 3);
		tablero.posicionEn(2, 3).ocupar();
		tablero.agregarTanque(posicionBase, DireccionEnum.UP, 0);
		
//		assertNull(tablero.tanques[0]);
		assertNull(tablero.piezas.get(0));
	}
	
	@Test
	public void testRecibirDisparoComoHerido() {
		Tablero tablero = new Tablero();
		Posicion posicionBase = new Posicion(2, 3);
		Posicion misil = new Posicion(2, 3);
		tablero.agregarTanque(posicionBase, DireccionEnum.UP, 0);
		
		EfectoDisparoEnum efecto = tablero.recibirDisparo(misil);
		
		assertEquals(EfectoDisparoEnum.HERIDO, efecto);
	}
	
	@Test
	public void testRecibirDisparoComoMuerto() {
		Tablero tablero = new Tablero();
		Posicion posicionBase = new Posicion(2, 3);
		tablero.agregarTanque(posicionBase, DireccionEnum.UP, 0);
		
		Posicion misil = new Posicion(2, 3);
		tablero.recibirDisparo(misil);
		
		misil = new Posicion(2, 2);
		tablero.recibirDisparo(misil);
		
		misil = new Posicion(1, 3);
		tablero.recibirDisparo(misil);
		
		misil = new Posicion(3, 3);
		EfectoDisparoEnum efecto = tablero.recibirDisparo(misil);
		
		assertEquals(EfectoDisparoEnum.MUERTO, efecto);
	}
	
	@Test
	public void testAgregarSoldado(){
		Tablero tablero = new Tablero();
		tablero.agregarSoldado(new Posicion(5,5), 0);
		
//		assertNotNull(tablero.soldados[0]);
		assertNotNull(tablero.piezas.get(0));
	}
	
	@Test(expected = RuntimeException.class)
	public void testAgregarSoldadoEnPosicionRepetida(){
		Tablero tablero = new Tablero();
		tablero.posicionEn(5, 5).ocupar();
		tablero.agregarSoldado(new Posicion(5,5), 0);
		
//		assertNull(tablero.soldados[0]);
		assertNull(tablero.piezas.get(0));
	}
}