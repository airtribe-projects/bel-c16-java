package preferCompositionOverInheritance;

public class Main {
  public static void main(String[] args) {
    LakeDuck lakeDuck = new LakeDuck(2, 2, 1, "Crystal Lake");
    lakeDuck.display();
    lakeDuck.swim();
    lakeDuck.walk();
    RubberDuck rubberDuck = new RubberDuck(2, 2, 1, "Yellow");
    rubberDuck.squeak();
    rubberDuck.swim();

  }
}
