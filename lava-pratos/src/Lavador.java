import java.util.logging.Logger;

public class Lavador implements Runnable {
	final Escorredor escorredor;
	boolean done = false;
	
	private static Logger logger = Logger.getLogger(Class.class.getSimpleName());
	
	public Lavador(Escorredor escorredor) {
		super();
		this.escorredor = escorredor;
	}
		
	@Override
	public void run() {
		try {
			while(!done) {
				Prato prato = escorredor.getSujo();
				
				if(prato != null) {
					logger.info(LogColor.getColor("INFO", "Lavando prato " + prato.getIdPrato()));
					switch(prato.getNivel()) {
						case BAIXO:
							Thread.sleep(3);
							break;	
						case MEDIO:
							Thread.sleep(5);
							break;
						case ENGORDURADO:
							Thread.sleep(10);
							break;
					}
					
					logger.info(LogColor.getColor("INFO", "Prato " + prato.getIdPrato() + " lavado!"));
					escorredor.addLimpo(prato);
				}else {
					logger.info(LogColor.getColor("INFO", "Lavador está aguardando pratos sujos"));
					synchronized(escorredor) {
						escorredor.wait();
					}
				}
			}
		} catch(InterruptedException e ) {
			e.printStackTrace();
			logger.severe(LogColor.getColor("ERRO", "Erro ao lavar prato: " + e.getMessage()));
		}
	}
	
	public void finishTask() {
		done = true;
	}
}