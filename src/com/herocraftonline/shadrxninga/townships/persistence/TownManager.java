package com.herocraftonline.shadrxninga.townships.persistence;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.util.config.Configuration;

import com.herocraftonline.shadrxninga.townships.TownShips;

public class TownManager {

    private TownShips plugin;
    private Set<Town> towns;
    private File townFolder;

    public TownManager(TownShips plugin) {
        this.plugin = plugin;
        this.towns = new HashSet<Town>();
        this.townFolder = new File(plugin.getDataFolder(), "towns"); // Setup our Player Data Folder
        this.townFolder.mkdirs(); // Create the folder if it doesn't exist.
    }

    public void loadTownFile(String town) {
        File playerFile = new File(this.townFolder, town + ".yml");
        Configuration townConfig = new Configuration(playerFile);
        townConfig.load(); // Load the Config File
        Town townShip = new Town(this.plugin, town);
        this.loadMembers(townShip, townConfig);
        int x = townConfig.getInt("location.X", 0);
        int y = townConfig.getInt("location.Y", 0);
        int z = townConfig.getInt("location.Z", 0);
        Location loc = new Location(null, x, y, z);
        townShip.townLoc = loc;
        townShip.townName = townConfig.getString("name");
        townShip.mayor = townConfig.getString("mayor");
        townShip.greeting = townConfig.getString("greeting");
        townShip.farewell = townConfig.getString("farewell");
        System.out.print("Town Loaded:" + townShip.townName);
        System.out.print("Town Loaded:" + townShip.getRadius());

        this.addTown(townShip);
    }

    public boolean addTown(Town town) {
        return this.towns.add(town);
    }

    public void createNewTown(String town) {
        Town township = new Town(this.plugin, town);
        this.loadTownFile(town);
    }

    public Town getTown(String townname) {
        for (Town town : this.towns) {
            if (town.getTownName() == null) {
                this.towns.remove(town); // Seeing as it's null we might as well remove it.
                continue;
            }
            if (townname.equalsIgnoreCase(town.getTownName())) {
                return town;
            }
        }
        // If it gets to this stage then clearly the TownManager doesn't have it so we create it...
        this.loadTownFile(townname);
        for (Town town : this.towns) {
            if (townname.equalsIgnoreCase(town.getTownName())) {
                return town;
            }
        }
        return null;
    }

    public Town[] getTowns() {
        return this.towns.toArray(new Town[0]);
    }

    public Set<Town> getTownSet() {
        return this.towns;
    }

    public void saveTownFile(String town) {
        File playerFile = new File(this.townFolder, town + ".yml");
        Configuration townConfig = new Configuration(playerFile);
        Town township = this.getTown(town);
        this.saveMembers(township, townConfig);
        townConfig.setProperty("name", township.getTownName());
        townConfig.setProperty("mayor", township.getMayor());
        townConfig.setProperty("radius", township.getRadius());
        townConfig.setProperty("greeting", township.getGreeting());
        townConfig.setProperty("farewell", township.getFarewell());
        townConfig.setProperty("location.X", township.getLocation().getX());
        townConfig.setProperty("location.Y", township.getLocation().getY());
        townConfig.setProperty("location.Z", township.getLocation().getZ());
        townConfig.save();
    }

    private void loadMembers(Town town, Configuration config) {
        Map<String, Integer> member = new HashMap<String, Integer>();
        List<String> memberKeys = config.getKeys("members");
        if ((memberKeys != null) && (memberKeys.size() > 0)) {
            for (String members : memberKeys) {
                String bind = config.getString("." + members, "");
                if (bind.length() > 0) {
                    town.members = member;
                }
            }
        }

    }

    private void saveMembers(Town town, Configuration config) {
        Map<String, Integer> members = town.getCitizins();
        for (String player : members.keySet()) {
            config.setProperty("members." + player, 1);
        }
    }

}
