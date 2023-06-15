package com.mobile;

// MobileModel entity class
public class MobileModel {
    private String name;

    private MobileModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Factory method to create instances of Model
    public static MobileModel createModel(String name) {
        return new MobileModel(name);
    }

}
