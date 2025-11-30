package ocp;

public class AreaCalculator {

  public double calculateArea(Object object) {
    if (object instanceof ShapeRectangle) {

      ShapeRectangle rectangle = (ShapeRectangle) object;
      //rectangle.getArea();
      return rectangle.getLength() * rectangle.getWidth();
    } else if (object instanceof ShapeSquare) {
      ShapeSquare square = (ShapeSquare) object;
      return square.getSideLength() * square.getSideLength();
    } else if (object instanceof ShapeCircle) {
      ShapeCircle circle = (ShapeCircle) object;
      return Math.PI * circle.getRadius() * circle.getRadius();
    }
    else {
      throw new IllegalArgumentException("Unsupported shape type");
    }

  }
}
