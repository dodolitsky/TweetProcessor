package edu.upenn.cis350.hwk4;

import java.util.Set;

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
