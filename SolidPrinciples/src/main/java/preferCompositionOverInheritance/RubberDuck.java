package preferCompositionOverInheritance;

public class RubberDuck extends Duck {
  private String rubbberType;

  public RubberDuck(int hands, int legs, int beak, String rubbberType) {
    super(hands, legs, beak);
    this.rubbberType = rubbberType;
  }

  @Override
  public void squeak() {
    System.out.println("Rubber duck makes noises");
  }

  @Override
  public void swim() {
    throw  new UnsupportedOperationException("Rubber ducks cannot swim!");
  }

  @Override
  public void walk() {
    throw new UnsupportedOperationException("Rubber ducks cannot walk!");
  }
}

