package edu.upenn.cis350.hwk4;


public class HashTag implements Comparable<HashTag> {
  String text;
  int popularity;
  
  public HashTag(String text) {
    this.text = text.toLowerCase();
    popularity = 1;
  }
  
  public void setText(String text) {
    this.text = text.toLowerCase();
  }
  
  public String getText() {
    return this.text;
  }
  
  public void setPopularity(int popularity) {
    this.popularity = popularity;
  }
  
  public int getPopularity() {
    return this.popularity;
  }

  @Override
  public int compareTo(HashTag hashtag) {
    if (this.popularity > hashtag.popularity) {
      return -1;
    }
    if (this.popularity < hashtag.popularity) {
      return 1;
    }
    return this.text.compareTo(hashtag.text) * -1;
  }
  
  
}
