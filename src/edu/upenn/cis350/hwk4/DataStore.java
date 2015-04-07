package edu.upenn.cis350.hwk4;

import java.util.Set;

/**
 * Calls Readers for Tweets and States and stores the data
 * 
 * @author Douglas Dolitsky
 */
public abstract class DataStore {

  private Set<Tweet> tweets;
  private Set<State> states;
  
  public DataStore() {
    TweetReader tweetReader = makeTweetReader(); // call factory method
    tweets = tweetReader.read();
    
    StateReader stateReader = new StateReader();
    states = stateReader.read();
  }
  
  protected abstract TweetReader makeTweetReader(); // factory method
  
  public Set<Tweet> getTweets() {
    return tweets;
  }
  
  public Set<State> getStates() {
    return states;
  }
  
}
