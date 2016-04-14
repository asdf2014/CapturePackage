package com.capture.packages.model;

/**
 * Created by Benedict Jin on 2016/4/14.
 */
public class NetworkCardInfos {

    private String name;
    private String description;
    private String addresses;

    public NetworkCardInfos() {
    }

    public NetworkCardInfos(String name, String description, String addresses) {
        this.name = name;
        this.description = description;
        this.addresses = addresses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }
}
