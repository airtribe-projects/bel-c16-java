package afterOCP;

public class ShapeSquare implements Shape {
  private double sideLength;

  public ShapeSquare(double sideLength) {
    this.sideLength = sideLength;
  }

  public double getSideLength() {
    return sideLength;
  }

  public void setSideLength(double sideLength) {
    this.sideLength = sideLength;
  }

  @Override
  public double getArea() {
    return sideLength * sideLength;
  }
}
