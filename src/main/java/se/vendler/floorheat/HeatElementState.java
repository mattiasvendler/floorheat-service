package se.vendler.floorheat;

public enum HeatElementState {
ON(1),OFF(0);
    private int id;
    private HeatElementState(int id){
        this.id=id;
    }

    public HeatElementState get(int id){
        for(HeatElementState s : HeatElementState.values()){
            if(s.id == id){
                return s;
            }
        }
        return OFF;
    }
}
