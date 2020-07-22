package ru.stqa.pft.sandbox;

public class DistancePoint {

  public static void main(String[] args){
    Point p1=new Point(3,4);
    Point p2=new Point(6,7);

    System.out.println("Расстояние между двумя точками с координатами ("+p1.getPoint()+" ) и ("+p2.getPoint()+") = "+p2.distance(p1));

  }
}
