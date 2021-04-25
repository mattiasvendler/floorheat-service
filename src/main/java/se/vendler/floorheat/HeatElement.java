package se.vendler.floorheat;

public class HeatElement {
    private int id;
    private HeatElementState state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HeatElementState getState() {
        return state;
    }

    public void setState(HeatElementState state) {
        this.state = state;
    }
}
