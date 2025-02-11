public class PratosSujosFactory {
	static int count = 0;
	
	public static Prato getPrato() {
		count++;
		Prato.NivelSujeira nivel = randomNivel();
		return new Prato(count, nivel);
	}
	
	private static Prato.NivelSujeira randomNivel() {
		int n = (int) (Math.random() * 100);
		
		if (n < 30) {
			return Prato.NivelSujeira.BAIXO;
		} else if (n < 90) {
			return Prato.NivelSujeira.MEDIO;
		} else {
			return Prato.NivelSujeira.ENGORDURADO;
		}
	}
}