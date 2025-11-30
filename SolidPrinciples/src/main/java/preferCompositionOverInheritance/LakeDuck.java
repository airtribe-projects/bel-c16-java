package preferCompositionOverInheritance;

public class LakeDuck extends Duck {

  public String lakeType;

  public LakeDuck(int hands, int legs, int beak, String lakeType) {
    super(hands, legs, beak);
    this.lakeType = lakeType;
  }

  public void display() {
    System.out.println("I am a Lake Duck named " + this.lakeType + " from " + lakeType + " lake.");
  }

  public void swim() {
    System.out.println("Lake Duck is swimming in " + lakeType + " lake.");
  }

}
