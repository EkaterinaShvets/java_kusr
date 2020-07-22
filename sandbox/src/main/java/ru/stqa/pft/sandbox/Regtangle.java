package ru.stqa.pft.sandbox;

public class Regtangle {
  public double a;
  public double b;

  public Regtangle(double a, double b) {
    this.a=a;
    this.b=b;
  }

  public double area() {
    return this.a*this.b;
  }
}
