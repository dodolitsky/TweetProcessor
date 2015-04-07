package edu.upenn.cis350.hwk4;


public class Tweet implements Comparable<Tweet> {
  double latitude;
  double longitude;
  String date;
  int hour;
  String text;
  
  public Tweet(double latitude, double longitude, String date, int hour,
      String text) {
    this.latitude = latitude;
    this.longitude = longitude;
    this.date = date;
    this.hour = hour;
    this.text = text;
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
  
  public void setDate(String date) {
    this.date = date;
  }
  
  public String getDate() {
    return this.date;
  }
  
  public void setHour(int hour) {
    this.hour = hour;
  }
  
  public int getHour() {
    return this.hour;
  }
  
  public void setText(String text) {
    this.text = text;
  }
  
  public String getText() {
    return this.text;
  }

  @Override
  public int compareTo(Tweet tweet) {
    if (!this.date.equals(tweet.date)) {
      return this.date.compareTo(tweet.date);
    }
    if (this.hour < tweet.hour) {
      return -1;
    }
    if (this.hour > tweet.hour) {
      return 1;
    } 
    return this.text.compareTo(tweet.text);
  }
}
