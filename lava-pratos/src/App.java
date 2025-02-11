import java.util.logging.Logger;

public class App {
	static final int tempoTrabalho = 2 * 60 * 1000;
	
	private Escorredor escorredor;
	private Lavador lavador;
	private Enxugador enxugador;
	
	private static Logger logger = Logger.getLogger(Class.class.getSimpleName());
	
	public void work() {
		escorredor = new Escorredor(5);
		lavador = new Lavador(escorredor);
		enxugador = new Enxugador(escorredor);
		
		Thread lavThread = new Thread(lavador, "Lavador");
		Thread enxThread = new Thread(enxugador, "Enxugador");
		
		lavThread.start();
		enxThread.start();
		
		for(int i = 0; i < 10; i++) {
			Prato prato = PratosSujosFactory.getPrato();
			try {
				escorredor.addSujo(prato);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.severe(LogColor.getColor("ERRO", "Erro ao inserir prato" + e.getMessage()));
			}
		}
	}
	
	public void stop() {
		lavador.finishTask();
		enxugador.finishTask();
	}

	public static void main(String[] args) throws InterruptedException {
		App app = new App();
		app.work();
		
		Thread.sleep(tempoTrabalho);
			
		app.stop();
		logger.info(LogColor.getColor("DONE", "Todos os pratos foram limpos e enxugados!"));
	}

}