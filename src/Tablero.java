import java.util.ArrayList;
import java.util.List;

public class Tablero {
	Posicion[] posiciones = new Posicion[100];
	
//	PiezaJugable [] tanques = new Tanque[3];
	
//	PiezaJugable [] soldados = new Soldado[2];
	
	List<PiezaJugable> piezas = new ArrayList<PiezaJugable>();
	
	private Integer cantidadTanques = 0;
	private Integer cantidadSoldados = 0;

	public Tablero() {
		inicializarTablero();
	}

	public Posicion posicionEn(int x, int y) {
		return posiciones[x * 10 + y];
	}

	public void inicializarTablero() {
		int contadorFila = 0;
		int contadorColumna = 0;
        
        for (int contadorPosicion = 0; contadorPosicion < 100; contadorPosicion++, contadorFila++) {
        	if (contadorFila == 10) {
				contadorColumna ++;
                contadorFila = 0;
			}
        	posiciones[contadorPosicion] = new Posicion(contadorColumna, contadorFila);        	
        }
	}

	public void agregarTanque(Posicion posicionBase, DireccionEnum direccion, int indice){
		if (cantidadTanques == 3) {
			return;
		}
		
//		tanques[indice] = new Tanque(this, direccion, posicionBase);
		piezas.add(new Tanque(this, direccion, posicionBase));
		cantidadTanques ++;
	}
	
	public void agregarSoldado(Posicion posicion, int i) {
		
		if (cantidadSoldados == 2) {
			return;
		}
//		soldados[i] = new Soldado(this, posicion);
		piezas.add(new Soldado(this, posicion));
		cantidadSoldados++;
	}
	
	public EfectoDisparoEnum recibirDisparo(Posicion misil) {
		if (posicionEn(misil.X, misil.Y).estaOcupado() == false) {
			return EfectoDisparoEnum.SANO;
		}
		
		posicionEn(misil.X, misil.Y).recibirDisparo();
		
		for (PiezaJugable pieza : piezas) {
			EfectoDisparoEnum efecto = pieza.recibirDisparo(misil);
			if (efecto.equals(EfectoDisparoEnum.HERIDO) || efecto.equals(EfectoDisparoEnum.MUERTO)) {
				return pieza.calcularEstado();
			}
		}
		
		return EfectoDisparoEnum.SANO;
	}

	
}
