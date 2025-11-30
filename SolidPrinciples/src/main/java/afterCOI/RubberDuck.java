package afterCOI;

public class RubberDuck extends BaseDuck {

  private SqueakBehaviour _squeakBehaviour;

  public RubberDuck(int hands, int legs, int beak,
                     SqueakBehaviour squeakBehaviour) {
    super(hands, legs, beak, null, null, squeakBehaviour);
    this._squeakBehaviour = squeakBehaviour;
  }

  public void squeak() {
    _squeakBehaviour.squeak();
  }
}
