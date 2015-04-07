package edu.upenn.cis350.hwk4;

public class TextDataStore extends DataStore {
  
  public TextDataStore() {
    super();
  }

  @Override
  protected TweetReader makeTweetReader() {
    return new TextTweetReader();
  }
  
}