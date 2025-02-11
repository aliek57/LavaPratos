
public class Prato {
	final int idPrato;
	final NivelSujeira nivel;
	
	public Prato(int idPrato, NivelSujeira nivel) {
		super();
		this.idPrato = idPrato;
		this.nivel = nivel;
	}

	public int getIdPrato() {
		return idPrato;
	}

	public NivelSujeira getNivel() {
		return nivel;
	}
	
	public enum NivelSujeira {
		BAIXO, MEDIO, ENGORDURADO;
	}	
}