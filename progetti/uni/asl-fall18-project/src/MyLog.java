import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import java.util.logging.*;

public class MyLog {

	public static final String log_name = "middlewareLogger";
	public static final String log_file = "middlewareLogger.log";
	public static Logger logger;
	private static ConsoleHandler console_handler;
	private static FileHandler file_handler;

	public static Logger getLogger () {
		if (logger == null) initializeLogger();
		return logger;
	}

	public static void initializeLogger () {
		// see https://stackoverflow.com/questions/13825403/java-how-to-get-logger-to-work-in-shutdown-hook
		logger = Logger.getAnonymousLogger(); // TODO 2018/10/09 19:17 -  anonymous logger to work in shutdown hook
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);

		console_handler = new ConsoleHandler();
		console_handler.setLevel(Level.ALL);
		console_handler.setFormatter(new LogFormatter());
		logger.addHandler(console_handler);
		try {
			file_handler = new FileHandler (log_file, true);
			file_handler.setLevel(Level.ALL);
			//file_handler.setFormatter(new SimpleFormatter()); // TODO 2018/10/09 18:16 - implement your own formatter
			logger.addHandler(file_handler);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void shutdown() {
		console_handler.flush();
		console_handler.close();
		file_handler.flush();
		file_handler.close();
	}

}

class LogFormatter extends Formatter {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	private static final DateFormat df = new SimpleDateFormat("dd/MM/YY HH:mm:ss.SSS");

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder();

		sb.append("\033[32m[");
        sb.append(df.format(new Date(record.getMillis())));
		sb.append("] \033[0m");

		if (record.getLevel() == Level.SEVERE) {
			sb.append("[\033[31;1m!\033[0m]\033[31m");
		} else if (record.getLevel() == Level.WARNING) {
			sb.append("[\033[33;1m*\033[0m]\033[33m");
		} else if (record.getLevel() == Level.INFO) {
			sb.append("[\033[32m+\033[0m]\033[1m");
		} else if (record.getLevel() == Level.FINE) {
			sb.append("[\033[32m+\033[0m]\033[0m");
		} else {
			sb.append("-> ");
		}


		sb.append(" ")
			.append(formatMessage(record))
			.append("\033[0m")
            .append(LINE_SEPARATOR);

        if (record.getThrown() != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch (Exception ex) {
                // ignore
            }
        }

		return sb.toString();
    }

}
