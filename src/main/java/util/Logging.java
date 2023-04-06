package util;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
public class Logging {

    public Logger start(String className) {
        return LoggerFactory.getLogger(Logging.class);
       // Logger logger = LoggerFactory.getLogger(className);
    }

    public void setFileName(String username){
        String fileName = username + ".txt";
        File logFile = new File( fileName);
        try{

            FileHandler handler = new FileHandler(fileName);
            //Logger logger = Logger.getLogger("MyLog");
            //logger.addHandler(handler);
            //logger.removeHandler(handler);
            handler.close();
            
        } catch (IOException e){
            e.printStackTrace();
        }

    }
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

        public void run(String username){
        // Load the configuration file
        context.reset();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(context);
        try {
            configurator.doConfigure("logback.xml");
        } catch (JoranException ex) {
            throw new RuntimeException(ex);
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(context);

        // Get the file appender
        FileAppender fileAppender = (FileAppender) context.getLogger("com.example").getAppender("FILE");

        // Generate a new log file name
        String logFileName = "myapp-" + username + ".log";

        // Set the new file name
        fileAppender.setFile(logFileName);
        fileAppender.start();

        // Log a message to verify that the new file is used
        Logger logger = LoggerFactory.getLogger(Logging.class);
        logger.info("Logging to file: {}", logFileName);

    }

}


