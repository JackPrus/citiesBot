package by.prus.citybot.model;

import javax.persistence.Column;
import java.io.Serializable;

public class CityDTO implements Serializable {

    private String name;
    private String info;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getInfo() { return info; }
    public void setInfo(String info) { this.info = info; }
}
