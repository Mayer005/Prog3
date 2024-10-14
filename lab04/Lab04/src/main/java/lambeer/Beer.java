package lambeer;

import java.io.Serializable;

public class Beer implements Serializable{
    private String name;
    private String style;
    private double strength;

    Beer(String name, String style, double strength) {
        this.name = name;
        this.style = style;
        this.strength = strength;
    }

    @Override
    public String toString() {
        return "Név: "+name + ", jelleg: " + style + ", alkoholfok: " + strength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }


}
