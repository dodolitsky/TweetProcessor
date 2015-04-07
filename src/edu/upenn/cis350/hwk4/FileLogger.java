package edu.upenn.cis350.hwk4;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogger extends Logger{
  
  private static Logger instance = new FileLogger();
  private FileWriter logWriter = Main.logWriter;
  
  private FileLogger() {
  }
  
  public static Logger getInstance() {
    return instance;
  }
    
  @Override
  public void write(String toWrite) {
    try {
      logWriter.write(toWrite);
    } catch (IOException e) {
      System.err.println("ERROR: Cannot write to log file");
      System.exit(0);
    }    
  }
  
  public void close() {
    try {
      logWriter.close();
    } catch (IOException e) {}
  }
  
}
