package com.lezv;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.lezvinskyi
 * Date: 9/11/12
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Restaurant {
    private String name = "";
    private String address = "";
    private String type = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return(type);
    }
    public void setType(String type) {
        this.type=type;
    }
    public String toString() {
        return(getName());
    }

}
