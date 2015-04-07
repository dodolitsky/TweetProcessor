package edu.upenn.cis350.hwk4;


import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class State implements Comparable<State> {
  String name;
  double latitude;
  double longitude;
  Set<Tweet> tweets;
  PriorityQueue<HashTag> hashtags;
  
  public State(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
    tweets = new TreeSet<Tweet>();
    hashtags = new PriorityQueue<HashTag>();
  }
  
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }
  
  public double getLatitude() {
    return this.latitude;
  }
  
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
  
  public double getLongitude() {
    return this.longitude;
  }
  
  public void addTweet(Tweet tweet) {
    this.tweets.add(tweet);
  }
  
  public Set<Tweet> getTweets() {
    return this.tweets;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public void addHashTag(HashTag hashtag) {
    boolean contains = false;
    for (HashTag ht : hashtags) {
      if (ht.getText().equals(hashtag.getText())) {
        contains = true;
        hashtags.remove(ht);
        ht.setPopularity(ht.getPopularity() + 1);
        hashtags.add(ht);
        break;
      }
    }
    if (!contains) {
      hashtags.add(hashtag);
    }
  }
  
  public PriorityQueue<HashTag> getHashTags() {
    return hashtags;
  }

  @Override
  public int compareTo(State state) {
    if (this.tweets.size() > state.tweets.size()) {
      return -1;
    }
    if (this.tweets.size() < state.tweets.size()) {
      return 1;
    }
    return this.name.compareTo(state.name) * -1;
  }
}
