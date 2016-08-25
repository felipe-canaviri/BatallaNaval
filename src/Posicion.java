
public class Posicion {
	
	public int X;
	public int Y;
	private boolean ocupado = false;
	
	private boolean atacado = false;
	
	public Posicion(int x, int y) {
		
		if(x < 0 || x > 9 || y < 0 || y > 9) {
			throw new RuntimeException("Valores invalidos para X: " + x + "o Y: " + y);
		}
		
		X = x;
		Y = y;
		this.ocupado = false;
	}

	public Posicion(int x, int y, boolean ocupado) {
		
		if(x < 0 || x > 9 || y < 0 || y > 9) {
			throw new RuntimeException("Valores invalidos para X: " + x + "o Y: " + y);
		}
		
		X = x;
		Y = y;
		this.ocupado = ocupado;
	}
	
	public boolean estaOcupado(){
		return ocupado;
	}
	
	public void ocupar(){
		this.ocupado = true;
	}
	
	public void desocupar(){
		this.ocupado = false;
	}
	
	public void recibirDisparo() {
		atacado = true;
	}
	
	public boolean estaAtacado(){
		return atacado;
	}
	
	@Override
	public boolean equals(Object that) {
		if (that == this) {
			return true;
		}
		
		if (that == null){
			return false;
		}
		
		if (that.getClass() != Posicion.class) {
			return false;
		}
		
		Posicion thatObj = (Posicion) that;
		
		boolean resultado = true;
		resultado = resultado && thatObj.X == X;
		resultado = resultado && thatObj.Y == Y;
		
		return resultado;
	}
}
