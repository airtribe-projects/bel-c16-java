package afterOCP;

public class Main {
  public static void main(String[] args) {
    Shape rectangle = new ShapeRectangle(5, 10);
    Shape square = new ShapeSquare(4);
    Shape circle = new ShapeCircle(3); // Assuming ShapeCircle is defined similarly
    Shape point = new ShapePoint();

    AreaCalculator areaCalculator = new AreaCalculator();
    double area = areaCalculator.calculateArea(circle);
    double areaPoint = areaCalculator.calculateArea(point);
    //circle.hello();
  }
}
