package ocp;

public class ShapeCircle {

  private double radius;

  public ShapeCircle(double radius) {
    this.radius = radius;
  }

  public double getRadius() {
    return radius;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public double calculateArea() {
    return Math.PI * radius * radius;
  }
}
