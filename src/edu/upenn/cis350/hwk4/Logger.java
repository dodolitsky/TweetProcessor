package edu.upenn.cis350.hwk4;

public abstract class Logger implements Writer {
  
  public void log(String message, Writer writer) {
    long time = System.currentTimeMillis();
    writer.write(time + ":\t" + message + "\n");
  }
    
  public abstract void write(String toWrite);
  
  @Override
  public boolean equals(Object o) {
    return this == o;
  }
  
  public abstract void close();

}
