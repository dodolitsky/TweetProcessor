package edu.upenn.cis350.hwk4;

public class JSONDataStore extends DataStore {
  
  public JSONDataStore() {
    super();
  }

  @Override
  protected TweetReader makeTweetReader() {
    return new JSONTweetReader();
  }

}
