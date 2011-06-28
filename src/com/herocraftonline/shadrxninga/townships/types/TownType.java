package com.herocraftonline.shadrxninga.townships.types;

public class TownType {

    private String name;
    private String title;
    private String description;
    private double cost;
    private double upkeep;
    private int minSigs;
    private int maxSigs;
    private int material;
    private int radius;
    private TownType parent;

    public TownType() {
        this.name = new String();
        this.title = new String();
        this.description = new String();
        this.cost = 10;
        this.upkeep = 5;
        this.radius = 25;
    }

    public TownType(String name) {
        this();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getCost() {
        return this.cost;
    }

    public void setUpkeep(double upkeep) {
        this.upkeep = upkeep;
    }

    public double getUpkeep() {
        return this.upkeep;
    }

    public void setMinSigs(int minSigs) {
        this.minSigs = minSigs;
    }

    public int getMinSigs() {
        return this.minSigs;
    }

    public void setMaxSigs(int maxSigs) {
        this.maxSigs = maxSigs;
    }

    public int getMaxSigs() {
        return this.maxSigs;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return this.radius;
    }

    public TownType getParent() {
        return this.parent == null ? null : this.parent;
    }

    public void setParent(TownType parent) {
        this.parent = parent;
    }

}
