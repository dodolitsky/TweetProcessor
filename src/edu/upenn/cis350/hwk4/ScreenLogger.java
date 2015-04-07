package edu.upenn.cis350.hwk4;

/**
 * Logs to Stdout
 * 
 * @author Douglas Dolitsky
 */
public class ScreenLogger extends Logger {

  private static Logger instance = new ScreenLogger();
  
  private ScreenLogger() {
  }
  
  public static Logger getInstance() {
    return instance;
  }
    
  @Override
  public void write(String toWrite) {
    System.out.println(toWrite);
  }
  
  public void close() {
  }
  
}
