package afterCOI;

public class BaseDuck {

  private int hands;
  private int legs;
  private int beak;

  public SwimBehaviour _swimBehaviour;

  public WalkBehaviour _walkBehaviour;

  public SqueakBehaviour _squeakBehaviour;

  public BaseDuck(int hands, int legs, int beak,
                  SwimBehaviour swimBehaviour,
                  WalkBehaviour walkBehaviour,
                  SqueakBehaviour squeakBehaviour) {
    this.hands = hands;
    this.legs = legs;
    this.beak = beak;
    this._squeakBehaviour = squeakBehaviour;
    this._swimBehaviour = swimBehaviour;
    this._walkBehaviour = walkBehaviour;

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
    _squeakBehaviour.squeak();
  }

  public void swim() {
    _swimBehaviour.swim();
  }


  public void walk() {
    _walkBehaviour.walk();
  }

}
