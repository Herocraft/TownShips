package com.herocraftonline.shadrxninga.townships.persistence;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;

import com.herocraftonline.shadrxninga.townships.TownShips;
import com.herocraftonline.shadrxninga.townships.types.TownType;

public class Town {

    protected final TownShips plugin;
    protected TownType townType;

    protected Location townLoc;
    protected String townName;
    protected String greeting;
    protected String farewell;
    protected String mayor;
    protected int radius;
    protected Map<String, Integer> members = new HashMap<String, Integer>();

    public Town(TownShips plugin, String townName, TownType townType) {
        this.plugin = plugin;
        this.townName = townName;
        this.townType = townType;
    }

    public Town(TownShips plugin, String townName) {
        this.plugin = plugin;
        this.townName = townName;
    }

    public Location getLocation() {
        return this.townLoc;
    }

    public void setLocation(Location loc) {
        this.townLoc = loc;
    }

    public String getMayor() {
        return this.mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public String getTownName() {
        return this.townName;
    }

    public String getGreeting() {
        return this.greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getFarewell() {
        return this.farewell;
    }

    public void setFarewell(String farewell) {
        this.farewell = farewell;
    }

    public Map<String, Integer> getCitizins() {
        return this.members;

    }

    public void addCitizen(String player, int rank) {
        this.members.put(player, rank);
    }

    public boolean isCitizen(String player) {
        if (this.members.containsKey(player)) {
            return true;
        } else {
            return false;
        }
    }

    public TownType getTownType() {
        return this.townType;
    }

    public void setTownType(TownType townType) {
        this.townType = townType;

    }

    public void changeTownType(TownType townType) {
        this.setTownType(townType);
    }

    public int getRadius() {
    	return townType.getRadius();
    }

}
