import java.util.logging.Logger;

public class Escorredor {
	final Prato[] pratosSujos;
	final Prato[] pratosLimpos;
	int comecoS = 0, fimS = 0, qtdS = 0;
	int comecoL = 0, fimL = 0, qtdL = 0;
	final int limite;
	
	private static Logger logger = Logger.getLogger(Class.class.getSimpleName());
	
	public Escorredor(int limite) {
		super();
		this.limite = limite;
		this.pratosSujos = new Prato[limite];
		this.pratosLimpos = new Prato[limite];
	}
	
	public synchronized void addSujo(Prato prato) throws InterruptedException {
		while(qtdS == limite) {
			logger.warning(LogColor.getColor("WARNING", "Escorredor cheio (PRATOS SUJOS)"));
			wait();
		}
		pratosSujos[fimS] = prato;
		fimS = (fimS+1) % limite;
		qtdS++;
		logger.info(LogColor.getColor("INFO", "Prato " + prato.getIdPrato() + " entrou no escorredor como SUJO (nível " + prato.getNivel() + ")"));
		notifyAll();
	}
	
	public synchronized Prato getSujo() throws InterruptedException {
		while(qtdS == 0) {
			logger.warning(LogColor.getColor("WARNING", "Lavador aguardando pratos sujos"));
			wait();
		}
		
		Prato prato = pratosSujos[comecoS];
		comecoS = (comecoS+1) % limite;
		qtdS--;
		notifyAll();
		
		return prato;
	}
	
	public synchronized void addLimpo(Prato prato) throws InterruptedException {
		while(qtdL == limite) {
			logger.warning(LogColor.getColor("WARNING", "Escorredor cheio (PRATOS LIMPOS)"));
			wait();
		}
		pratosLimpos[fimL] = prato;
		fimL = (fimL+1) % limite;
		qtdL++;
		logger.info(LogColor.getColor("INFO", "Prato " + prato.getIdPrato() + " voltou no escorredor como LIMPO"));
		notifyAll();
	}
	
	public synchronized Prato getLimpo() throws InterruptedException {
		while(qtdL == 0) {
			logger.warning(LogColor.getColor("WARNING", "Enxugador aguardando pratos limpos"));
			wait();
		}
		
		Prato prato = pratosLimpos[comecoL];
		comecoL = (comecoL+1) % limite;
		qtdL--;
		notifyAll();
		
		return prato;
	}
}