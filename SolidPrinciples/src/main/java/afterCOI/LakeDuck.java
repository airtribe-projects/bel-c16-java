package afterCOI;

public class LakeDuck extends BaseDuck {

  public String lakeType;

  private SwimBehaviour _swimBehaviour;



  public LakeDuck(int hands, int legs, int beak, String lakeType,
                  SwimBehaviour swimBehaviour,
                  WalkBehaviour walkBehaviour,
                  SqueakBehaviour squeakBehaviour) {
    super(hands, legs, beak);
    this.lakeType = lakeType;
    this._swimBehaviour = swimBehaviour;
    this._walkBehaviour = walkBehaviour;
    this._squeakBehaviour = squeakBehaviour;
  }

  public void swim() {
    _swimBehaviour.swim();
  }

  public void walk() {
    _walkBehaviour.walk();
  }

  public void squeak() {
    _squeakBehaviour.squeak();
  }

}
