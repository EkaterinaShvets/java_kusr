package ru.stqa.pft.sandbox;

public class DistancePoint {

  public static void main(String[] args){
    Point p1=new Point(3,4);
    Point p2=new Point(6,7);

    System.out.println("Расстояние между двумя точками с координатами ("+p1.x+", "+p2.y+") и ("+p2.x+", "+p2.y+") = "+p2.distance(p1));

  }
}
