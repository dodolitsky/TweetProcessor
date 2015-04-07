package edu.upenn.cis350.hwk4;


public class DateHour implements Comparable<DateHour> {
  String date;
  int hour;
  int frequency;
  
  public DateHour (String date, int hour) {
    this.date = date;
    this.hour = hour;
    this.frequency = 1;
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
  
  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }
  
  public int getFrequency() {
    return this.frequency;
  }

  @Override
  public int compareTo(DateHour dh) {
    if (this.date.compareTo(dh.date) != 0) {
      return this.date.compareTo(dh.date);
    }
    if (this.hour > dh.hour) {
      return 1;
    }
    if (this.hour < dh.hour) {
      return -1;
    }
    return 0;
  }
  
  @Override
  public boolean equals(Object dh) {
    if (!(dh instanceof DateHour)) {
      return false;
    }
    return this.date.equals(((DateHour) dh).date)
        && this.hour == ((DateHour) dh).hour;
  }
  
  
}
