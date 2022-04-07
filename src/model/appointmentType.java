package model;

public class appointmentType {

    private String Type;

    public appointmentType(String Type){
        this.Type = Type;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        Type = Type;
    }

    @Override
    public String toString(){return Type;}

}
