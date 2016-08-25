
public class Tanque implements PiezaJugable {

	private Posicion[] posiciones = new Posicion[4];
	private Tablero tablero;
	
	private DireccionEnum direccionActual;
	
	public Tanque(Posicion[] posiciones) {
		if (sonPosicionesValidas(posiciones) == false) {
			throw new RuntimeException("Posiciones invalidas");
		}

		this.posiciones = posiciones;
	}
	
	public Tanque(Tablero tablero, DireccionEnum direccion, Posicion base) {
		this.tablero = tablero;
		this.construir(base, direccion);
	}

	private void construir(Posicion base, DireccionEnum direccion) {
		if (this.tablero == null) {
			throw new RuntimeException("Imposible crear tanque sobre un tablero nulo");
		}
		
		this.direccionActual = direccion;
		
		switch (direccion) {

		case UP: {// direccion al norte
			posiciones[0] = tablero.posicionEn(base.X, base.Y);
			posiciones[1] = tablero.posicionEn(base.X, base.Y - 1);
			posiciones[2] = tablero.posicionEn(base.X - 1, base.Y);
			posiciones[3] = tablero.posicionEn(base.X + 1, base.Y); 
		} break;
		case DOWN: {// direccion al sur
			posiciones[0] = tablero.posicionEn(base.X, base.Y);
			posiciones[1] = tablero.posicionEn(base.X, base.Y + 1);
			posiciones[2] = tablero.posicionEn(base.X - 1, base.Y);
			posiciones[3] = tablero.posicionEn(base.X + 1, base.Y); 
		} break;
		case RIGHT: {// direccion al este
			posiciones[0] = tablero.posicionEn(base.X, base.Y);
			posiciones[1] = tablero.posicionEn(base.X + 1, base.Y);
			posiciones[2] = tablero.posicionEn(base.X, base.Y - 1);
			posiciones[3] = tablero.posicionEn(base.X, base.Y + 1);
		} break;
		case LEFT: {// direccion al oeste
			posiciones[0] = tablero.posicionEn(base.X, base.Y);
			posiciones[1] = tablero.posicionEn(base.X - 1, base.Y);
			posiciones[2] = tablero.posicionEn(base.X, base.Y - 1);
			posiciones[3] = tablero.posicionEn(base.X, base.Y + 1);
		} break;

		}
		
		if (posiciones[0].estaOcupado() || posiciones[1].estaOcupado() || posiciones[2].estaOcupado() || posiciones[3].estaOcupado()) {
			throw new RuntimeException("No es posible crear tanque en posiciones ocupadas");
		}
		
		tablero.posicionEn(posiciones[0].X, posiciones[0].Y).ocupar();
		tablero.posicionEn(posiciones[1].X, posiciones[1].Y).ocupar();
		tablero.posicionEn(posiciones[2].X, posiciones[2].Y).ocupar();
		tablero.posicionEn(posiciones[3].X, posiciones[3].Y).ocupar();
	}

	public boolean sonPosicionesValidas(Posicion[] posiciones) {
		if (posiciones.length != 4) {
			return false;
		}

		int k = 0;
		for (int i = 0; i < posiciones.length; i++) {

			if (compararPosicionesEnIndice(k, posiciones) == false) {
				return false;
			}

			if (posiciones[k].estaOcupado()) {
				return false;
			}

			k++;
		}

		return true;
	}

	public boolean compararPosicionesEnIndice(int k, Posicion[] posiciones) {

		boolean resultado = false;
		for (int i = 0; i < posiciones.length; i++) {
			// TODO: Mejorar codigo para no romper regla de anidacion.
			if (k == i) {
				continue;
			}

			resultado = posiciones[i].equals(posiciones[k]);
		}

		return resultado == false;
	}

	public EfectoDisparoEnum recibirDisparo(Posicion misil) {
		return calcularEstado();
	}
	
	public EfectoDisparoEnum calcularEstado(){

        boolean estaMuerto = true;
        for (int i = 0; i < 4; i++) {
            estaMuerto = estaMuerto && tablero.posicionEn(posiciones[i].X, posiciones[i].Y).estaAtacado();
        }

        if (estaMuerto) {
            return EfectoDisparoEnum.MUERTO;
        }

        boolean estaHerido = false;
        for (int i = 0; i < 4; i++) {
            estaHerido = estaHerido || tablero.posicionEn(posiciones[i].X, posiciones[i].Y).estaAtacado();
        }

        if (estaHerido) {
            return EfectoDisparoEnum.HERIDO;
        }
		
		return EfectoDisparoEnum.SANO;
	}

	@Override
	public void mover(int x, int y, int direccion) {
		
		if (calcularEstado().equals(EfectoDisparoEnum.HERIDO) ||
				calcularEstado().equals(EfectoDisparoEnum.MUERTO)) {
			return;
		}

		if (estoyAlBorde()) {
			return;
		}		
		
		Posicion nuevaPosicion = tablero.posicionEn(x, y);
		
		tablero.posicionEn(posiciones[0].X, posiciones[0].Y).desocupar();
		tablero.posicionEn(posiciones[1].X, posiciones[1].Y).desocupar();
		tablero.posicionEn(posiciones[2].X, posiciones[2].Y).desocupar();
		tablero.posicionEn(posiciones[3].X, posiciones[3].Y).desocupar();
		
		construir(nuevaPosicion, direccionActual);
	}
	
	public boolean estoyAlBorde() {
		return direccionActual == DireccionEnum.UP && posiciones[1].Y == 0 ||
			direccionActual == DireccionEnum.DOWN && posiciones[1].Y == 9 ||
			direccionActual == DireccionEnum.LEFT && posiciones[1].X == 0 ||
			direccionActual == DireccionEnum.RIGHT && posiciones[1].X == 9;
	}
	
	public boolean esMiPosicion(Posicion posicion) {
		boolean esParte = false;
		for (int i = 0; i < posiciones.length; i++) {
			esParte = esParte || posiciones[i].equals(posicion);
		}
		
		return esParte;
	}
}
