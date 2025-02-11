import java.util.logging.Logger;

public class Enxugador implements Runnable{
	final Escorredor escorredor;
	boolean done = false;
	
	private static Logger logger = Logger.getLogger(Class.class.getSimpleName());

	public Enxugador(Escorredor escorredor) {
		super();
		this.escorredor = escorredor;
	}
		
	@Override
	public void run() {
		try {
			while(!done) {
				Prato prato = escorredor.getLimpo();
				
				logger.info(LogColor.getColor("INFO", "Enxugando prato " + prato.getIdPrato()));
				int enxugando = 3 + (int) (Math.random() * 8);
				Thread.sleep(enxugando);
				logger.info(LogColor.getColor("INFO", "Prato " + prato.getIdPrato() + " enxugado!"));
			}
		} catch(InterruptedException e ) {
			e.printStackTrace();
			logger.severe(LogColor.getColor("ERRO", "Erro ao enxugar prato: " + e.getMessage()));
		}
	}
	
	public void finishTask() {
		done = true;
	}
}