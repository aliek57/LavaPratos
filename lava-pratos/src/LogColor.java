public class LogColor {
	public static String RESET = "\u001B[0m";
	public static String RED = "\u001B[31m";
	public static String YELLOW = "\u001B[33m";
	public static String BLUE = "\u001B[34m";
	public static String GREEN = "\u001B[32m";
	
	public static String getColor(String nivel, String mensagem) {
		switch(nivel.toUpperCase()) {
			case "ERRO":
				return RED + mensagem + RESET;
			case "WARNING":
				return YELLOW + mensagem + RESET;
			case "INFO":
				return BLUE + mensagem + RESET;
			case "DONE":
				return GREEN + mensagem + RESET;
			default:
				return mensagem;
		}
	}
}