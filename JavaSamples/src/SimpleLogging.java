import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SimpleLogging {

	static Logger logger = Logger.getLogger("MyLog");

	public static void main(String[] args) {
		try {
			// This block configure the logger with handler and formatter
			FileHandler fh = new FileHandler("./MyLogFile.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			e.printStackTrace();
			// can't use logger here
		} catch (IOException e) {
			// can't use logger here
			e.printStackTrace();
		}

		final long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			Logger.getAnonymousLogger().log(Level.INFO, "Message" + i);
		}
		final long endTime = System.currentTimeMillis();

		System.out.println("Total execution time: " + (endTime - startTime) + " ms");
	}

}
