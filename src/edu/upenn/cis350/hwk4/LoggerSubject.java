package edu.upenn.cis350.hwk4;

import java.util.Set;
import java.util.HashSet;

public class LoggerSubject {
  
  private static Set<Logger> loggers = new HashSet<Logger>();
  
  public static void addLogger(Logger logger) {
    loggers.add(logger);
  }
  
  public static void removeLogger(Logger logger) {
    loggers.remove(logger);
  }
  
  public static void log(String message) {
    for (Logger logger : loggers) {
      logger.log(message, logger);
    }
  }
  
  public static void close() {
    for (Logger logger : loggers) {
      logger.close();
    }
  }

}
