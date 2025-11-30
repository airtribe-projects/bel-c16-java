package ocp;

public class Main {
  public static void main(String[] args) {
    ShapeRectangle rectangle = new ShapeRectangle(5, 10);
    ShapeSquare square = new ShapeSquare(4);

    AreaCalculator areaCalculator = new AreaCalculator();
    double rectangleArea = areaCalculator.calculateArea(rectangle);
    double squareArea = areaCalculator.calculateArea(square);
    System.out.println("Rectangle Area: " + rectangleArea);
    System.out.println("Square Area: " + squareArea);

  }
}
