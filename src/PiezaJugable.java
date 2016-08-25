
public interface PiezaJugable {

	EfectoDisparoEnum recibirDisparo(Posicion misil);
	
	EfectoDisparoEnum calcularEstado();
	
	void mover(int x, int y, int direccion);
}
