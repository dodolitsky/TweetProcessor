package edu.upenn.cis350.hwk4;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  static FileWriter logWriter;
  static String tweetFileName;
  static String stateFileName;
  private static DataStore store;
  private static DataProcessor processor;

  /**
   * Takes in 4 arguments from the command-line:
   * - "JSON"/"TEXT" - the type of reader
   * - the name of the tweet file (.txt or .json) to be read
   * - the name of the state file (.txt) to be read
   * - the name of the log file to be written to
   */
  public static void main(String[] args) {
    if (args.length != 4) {
      System.err.println("ERROR: Illegal number of arguments");
      System.exit(0);
    }
    
    try {
      logWriter = new FileWriter(args[3], true);      
    } catch (FileNotFoundException e) {
      System.err.println("ERROR: Log file not found");
      System.exit(0);
    } catch (IOException e) {
      System.err.println("ERROR: Log file cannot be opened for writing");
      System.exit(0);
    }
    
    // LoggerSubject.addLogger(ScreenLogger.getInstance());
    LoggerSubject.addLogger(FileLogger.getInstance());

    LoggerSubject.log(args[0] + "\t" + args[1] + "\t" + args[2] + "\t"
        + args[3]);
    
    tweetFileName = args[1];
    stateFileName = args[2];
    
    if (args[0].equals("TEXT")) {
      store = new TextDataStore();
    }
    else if (args[0].equals("JSON")) {
      store = new JSONDataStore();
    }
    else {
      LoggerSubject.log("ERROR: Invalid TweetReader type");
      System.exit(0);
    }
    
    processor = new DataProcessor(store);
    
    mainMenu();
  }
  
  public static void mainMenu() {
    String input = null;
    do {
      View.output("Main Menu:\n"
          + "Press \"1\" to rank the states by the number of tweets.\n"
          + "Press \"2\" to show the most popular hashtags in a given state.\n"
          + "Press \"3\" to show the number of tweets per hour containing a "
            + "given term.\n"
          + "Press \"Q\" to quit the program");
      
      Scanner in = new Scanner(System.in);
      input = in.next();
      LoggerSubject.log("\"" + input + "\"");
      
      if (input.equals("1")) {
        View.output("--------------------");
        String s = processor.rankStatesByTweets();
        View.output(s);
      }
      
      else if (input.equals("2")) {
        View.output("--------------------");
        boolean isState = false;
        input = in.nextLine();
        while (!isState) {
          View.output("Enter the name of a state");
          input = in.nextLine();
          LoggerSubject.log("\"" + input + "\"");
          
          for (State state : store.getStates()) {
            if (state.getName().equals(input)) {
              isState = true;
              String s = processor.popularHashtagsByState(state);
              View.output(s);
              break;
            }
          }
          if (!isState) {
            View.output("Invalid state name");
          }
        }
      }
      
      else if (input.equals("3")) {
        View.output("--------------------");
        input = in.nextLine();
        View.output("Enter a word or phrase");
        input = in.nextLine();
        LoggerSubject.log("\"" + input + "\"");
        
        String s = processor.tweetsPerHourWithTerm(input);
        View.output(s);
      }
      
      else if (!input.equals("Q")) {
        View.output("Invalid input");
      }
      View.output("--------------------");
    } while (!input.equals("Q"));
    
    LoggerSubject.log("program ended\n");
    LoggerSubject.close();
  }
  
}
