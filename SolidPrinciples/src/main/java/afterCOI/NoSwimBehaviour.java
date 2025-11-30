package afterCOI;

public class NoSwimBehaviour implements SwimBehaviour {
    @Override
    public void swim() {
        System.out.println("I cannot swim!");
    }
}
