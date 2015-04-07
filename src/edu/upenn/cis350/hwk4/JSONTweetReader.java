package edu.upenn.cis350.hwk4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Set;
import java.util.TreeSet;

import com.google.gson.stream.JsonReader;

/**
 * Reads and processes a .json tweet file
 * 
 * @author Douglas Dolitsky
 */
public class JSONTweetReader implements TweetReader {
  
  private Set<Tweet> tweets = new TreeSet<Tweet>();

  @Override
  public Set<Tweet> read() {
    LoggerSubject.addLogger(ScreenLogger.getInstance());
    LoggerSubject.addLogger(FileLogger.getInstance());
    
    try {
      BufferedReader bf = new BufferedReader(new
          FileReader(Main.tweetFileName));
      while (bf.ready()) {
        String line = bf.readLine();
        
        JsonReader tweetReader = new JsonReader(new StringReader(line));
        
        tweetReader.beginObject();
        tweetReader.nextName();
        String text = tweetReader.nextString();
        tweetReader.nextName();
        String time = tweetReader.nextString();
        tweetReader.nextName();
        tweetReader.beginArray();
        double latitude = tweetReader.nextDouble();
        double longitude = tweetReader.nextDouble();
        tweetReader.endArray();
        String date = time.substring(0, 10);
        int hour = Integer.parseInt(time.substring(11, 13));
        tweetReader.endObject();
        
        tweets.add(new Tweet(latitude, longitude, date, hour, text));
      
        tweetReader.close();
      }
      bf.close();
      
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
