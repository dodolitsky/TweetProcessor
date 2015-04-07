package edu.upenn.cis350.hwk4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class TextTweetReader implements TweetReader {
  
  private Set<Tweet> tweets = new TreeSet<Tweet>();

  @Override
  public Set<Tweet> read() {
    LoggerSubject.addLogger(ScreenLogger.getInstance());
    LoggerSubject.addLogger(FileLogger.getInstance());

    try {
      FileReader tweetReader = new FileReader(Main.tweetFileName);
      
      while (tweetReader.ready()) {        
        String latString = "";
        char c = (char) tweetReader.read();
        c = (char) tweetReader.read();
        while (c != ',') {
          latString += c;
          c = (char) tweetReader.read();
        }
        double latitude = Double.parseDouble(latString);
        
        String longString = "";
        c = (char) tweetReader.read();
        c = (char) tweetReader.read(); 
        while (Character.isDigit(c) || c == '.' || c == '-') {        
          longString += c;
          c = (char) tweetReader.read();            
        }
        double longitude = Double.parseDouble(longString);
        
        c = (char) tweetReader.read();
        while (Character.isWhitespace(c)) {
          c = (char) tweetReader.read();
        }
        while (!Character.isWhitespace(c)) {
          c = (char) tweetReader.read();
        }
        while (Character.isWhitespace(c)) {
          c = (char) tweetReader.read();
        }
        
        String date = "";
        while (!Character.isWhitespace(c)) {
          date += c;
          c = (char) tweetReader.read();
        }
        
        String hourString = "";
        while (c != ':') {
          if (!Character.isWhitespace(c)) {
            hourString += c;         
          }
          c = (char) tweetReader.read();
        }
        int hour = Integer.parseInt(hourString);
        
        while (!Character.isWhitespace(c)) {
          c = (char) tweetReader.read();
        }
        while (Character.isWhitespace(c)) {
          c = (char) tweetReader.read();
        }
        
        String text = "";
        while (c != '\n') {
          text += c;
          if (!tweetReader.ready()) {
            break;
          }
          c = (char) tweetReader.read();
        }
        
        tweets.add(new Tweet(latitude, longitude, date, hour, text));
      }
      tweetReader.close();
      
    } catch (FileNotFoundException e) {
      LoggerSubject.log("ERROR: Tweet file not found");
      System.exit(0);
    } catch (IOException e) {
      LoggerSubject.log("ERROR: Tweet file cannot be opened for reading");      
      System.exit(0);
    }
    return tweets;
  }

}
