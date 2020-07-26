package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistanceEqual() {
    Point p1 = new Point(3, 3);
    Point p2 = new Point(3, 3);
    Assert.assertEquals(p2.distance(p1),0);
  }

  @Test
  public void testDistance() {
    Point p1 = new Point(0, 0);
    Point p2 = new Point(6, 8);
    Assert.assertEquals(p2.distance(p1),10.0);
  }

  @Test
  public void testDistanceSubzero() {
    Point p1 = new Point(7, 7);
    Point p2 = new Point(-7, -7);
    double d = p2.distance(p1);
    d=Math.round(d * 100);
    d=d/100;
    Assert.assertEquals(d,19.80);
  }

  @Test
  public void testDistancePlus() {
    Point p1 = new Point(5, 15);
    Point p2 = new Point(14, 2);
    double d = p2.distance(p1);
    d=Math.round(d * 100);
    d=d/100;
    Assert.assertEquals(d,15.81);
  }

}
