
public class Soldado implements PiezaJugable {

	private Tablero tablero;
	private Posicion posicion;
	public Soldado(Tablero tablero, Posicion posicion) {
		this.tablero = tablero;
		
		this.construir(posicion);	
	}
	
	public void construir(Posicion posicion) {
		
		if (tablero.posicionEn(posicion.X, posicion.Y).estaOcupado()) {
			throw new RuntimeException("No se puede crear soldado en posicion ocupada.");
		}
		
		this.posicion = posicion;
		tablero.posicionEn(posicion.X, posicion.Y).ocupar();
	}
	
	@Override
	public EfectoDisparoEnum recibirDisparo(Posicion misil) {
		return calcularEstado();
	}

	@Override
	public EfectoDisparoEnum calcularEstado() {
		if (tablero.posicionEn(posicion.X, posicion.Y).estaAtacado()) {
			return EfectoDisparoEnum.MUERTO;
		}
		
		return EfectoDisparoEnum.SANO;
	}

	@Override
	public void mover(int x, int y, int direccion) {
		
		Posicion nuevaPosicion = tablero.posicionEn(posicion.X, posicion.Y + (direccion * -1));
		
		tablero.posicionEn(posicion.X, posicion.Y).desocupar();
		construir(nuevaPosicion);
	}

	public Posicion posicionActual() {
		return posicion;
	}
}
