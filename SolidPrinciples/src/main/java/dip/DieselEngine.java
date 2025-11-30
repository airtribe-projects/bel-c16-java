package dip;

public class DieselEngine implements Engine {
    private String type;
    private int horsepower;

  public DieselEngine(String type, int horsepower) {
    this.type = type;
    this.horsepower = horsepower;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getHorsepower() {
    return horsepower;
  }

  public void setHorsepower(int horsepower) {
    this.horsepower = horsepower;
  }

  @Override
  public String start() {
    return "Starting the engine " + type + " with horsepower: " + horsepower;
  }
}
