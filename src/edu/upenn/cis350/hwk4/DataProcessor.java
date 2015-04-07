package edu.upenn.cis350.hwk4;

import java.util.PriorityQueue;
import java.util.Set;

public class DataProcessor {
  
private DataStore store;
private Set<Tweet> tweets;
private Set<State> states;
private PriorityQueue<State> statePQ = new PriorityQueue<State>();
  
  public DataProcessor(DataStore dataStore) {
    store = dataStore;
    this.tweets = store.getTweets();
    this.states = store.getStates();
    assignTweetsToStates();
  }
  
  private void assignTweetsToStates() {
    for (Tweet tweet : tweets) {
      State closest = null;
      double closestDistance = Double.POSITIVE_INFINITY;
      for (State state : states) {
        double latDiff = tweet.getLatitude() - state.getLatitude();
        double longDiff = tweet.getLongitude() - state.getLongitude();
        double distance = Math.sqrt(latDiff * latDiff + longDiff * longDiff);
        if (distance < closestDistance) {
          closestDistance = distance;
          closest = state;
        }
      }
      closest.addTweet(tweet);
      if (statePQ.contains(closest)) {
        statePQ.remove(closest);
      }
      statePQ.add(closest);
    }
  }
  
  public String rankStatesByTweets() {
    String result = "";
    int i = 1;
    while (!statePQ.isEmpty()) {
      State state = statePQ.remove();
      result += i + ". " + state.getName() + ": " + state.getTweets().size() 
          + " tweets\n";
      i++;
    }
    return result;
  }
  
  public String popularHashtagsByState(State state) {  
    String result = "";
    for (Tweet tweet : state.getTweets()) {
      String text = tweet.getText();
      String hashtag = "";
      boolean hashSign = false;
      for (int i = 0; i < text.length(); i++) {
        if (text.charAt(i) == '#' && !hashSign) {
          hashSign = true;
        }
        if (Character.isWhitespace(text.charAt(i)) && hashSign) {
          hashSign = false;
          state.addHashTag(new HashTag(hashtag));
          hashtag = "";
        }
        if (hashSign) {
          hashtag += text.charAt(i);
        }
      }
      if (hashSign) {
        state.addHashTag(new HashTag(hashtag));
      }
    }
    int i = 1;
    while (!state.getHashTags().isEmpty() && i <= 10) {
      result += i + ". " + state.getHashTags().remove().getText() + "\n";
      i++;
    }
    return result;
  }
  
  public String tweetsPerHourWithTerm(String term) {
    String result = "";
    PriorityQueue<DateHour> dhs = new PriorityQueue<DateHour>();
    for (Tweet tweet : tweets) {
      if (tweet.getText().contains(term)) {
        DateHour dh = new DateHour(tweet.getDate(), tweet.getHour());
        for (DateHour datehour : dhs) {
          if (datehour.equals(dh)) {
            dhs.remove(datehour);
            dh.setFrequency(datehour.getFrequency() + 1);
            break;
          }
        }
        dhs.add(dh);
      }
    }
    while (!dhs.isEmpty()) {
      DateHour dh = dhs.remove();
      result += dh.getDate() + " " + dh.getHour() + ":00 " + dh.getFrequency()
          + " times\n";
    }
    return result;
  }

}
