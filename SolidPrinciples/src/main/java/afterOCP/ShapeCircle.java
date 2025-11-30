package afterOCP;

public class ShapeCircle implements Shape {
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

  @Override
  public double getArea() {
    return Math.PI * radius * radius;
  }

  public void hello() {
    System.out.println("Hello from Circle");
  }
}
