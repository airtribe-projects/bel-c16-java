package preferCompositionOverInheritance;

public class Duck {

  private int hands;
  private int legs;
  private int beak;

  public Duck(int hands, int legs, int beak) {
    this.hands = hands;
    this.legs = legs;
    this.beak = beak;
  }

  public int getHands() {
    return hands;
  }

  public int getLegs() {
    return legs;
  }

  public int getBeak() {
    return beak;
  }

  public void squeak() {
    System.out.println("Squeaking");
  }

  public void swim() {
    System.out.println("Swimming with hands + wings");
  }

  public void walk() {
    System.out.println("Walking");
  }
}
