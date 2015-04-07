package edu.upenn.cis350.hwk4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class StateReader {
  
  private Set<State> states = new TreeSet<State>();
  
  public Set<State> read() {
    LoggerSubject.addLogger(ScreenLogger.getInstance());
    LoggerSubject.addLogger(FileLogger.getInstance());

    try {
      FileReader stateReader = new FileReader(Main.stateFileName);
      while (stateReader.ready()) {
        String name = "";
        char c = (char) stateReader.read();
        while (c != ',') {
          if (c != '\n') {
            name += c;
          }
          c = (char) stateReader.read();
        }
        
        String latString = "";
        c = (char) stateReader.read();
        while (c != ',') {
          latString += c;
          c = (char) stateReader.read();
        }
        double latitude = Double.parseDouble(latString);
        
        String longString = "";
        c = (char) stateReader.read();
        while (Character.isDigit(c) || c == '.' || c == '-') {
          longString += c;
          c = (char) stateReader.read();
        }
        double longitude = Double.parseDouble(longString);
        
        states.add(new State(name, latitude, longitude));
      }
      stateReader.close();

    } catch (FileNotFoundException e) {
      LoggerSubject.log("ERROR: State file not found");
      System.exit(0);
    } catch (IOException e) {
      LoggerSubject.log("ERROR: State file cannot be opened for reading");      
      System.exit(0);
    }
    return states;
  }

}
