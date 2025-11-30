package afterOCP;

public class ShapePoint implements Shape {

  @Override
  public double getArea() {
    throw new UnsupportedOperationException("Point does not have an area");
  }
}
