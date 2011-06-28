package com.herocraftonline.shadrxninga.townships.types;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.util.config.Configuration;

import com.herocraftonline.shadrxninga.townships.TownShips;

public class TypesManager {

    private final TownShips plugin;
    private Set<TownType> towntypes;
    private TownType defaultClass;

    public TypesManager(TownShips plugin) {
        this.plugin = plugin;
        this.towntypes = new HashSet<TownType>();
    }

    public TownType getType(String name) {
        for (TownType c : this.towntypes) {
            if (name.equalsIgnoreCase(c.getName())) {
                return c;
            }
        }
        return null;
    }

    public boolean addTownType(TownType c) {
        return this.towntypes.add(c);
    }

    public boolean removeTownType(TownType c) {
        return this.towntypes.remove(c);
    }

    public Set<TownType> getTownTypes() {
        return this.towntypes;
    }

    public void loadTypes(File file) {
        Configuration config = new Configuration(file);
        config.load();
        List<String> towntypeNames = config.getKeys("TownTypes");
        if (towntypeNames == null) {
            this.plugin.log(Level.WARNING, "You have no TownTypes defined in your setup!");
            return;
        }
        for (String townName : towntypeNames) {
            TownType newTown = new TownType(townName.substring(0, 1).toUpperCase() + townName.substring(1).toLowerCase());

            newTown.setName(config.getString("TownTypes." + townName + ".name", ""));
            newTown.setRadius(config.getInt("TownTypes." + townName + ".order", 0));
            newTown.setDescription(config.getString("TownTypes." + townName + ".description", ""));
            newTown.setTitle(config.getString("TownTypes." + townName + ".title", ""));
            newTown.setCost(config.getDouble("TownTypes." + townName + ".cost", 0));
            newTown.setUpkeep(config.getDouble("TownTypes." + townName + ".upkeep", 0));
            newTown.setRadius(config.getInt("TownTypes." + townName + ".radius", 0));
            newTown.setMinSigs(config.getInt("TownTypes." + townName + ".minSigs", 0));
            newTown.setMaxSigs(config.getInt("TownTypes." + townName + ".maxsigs", 0));
            boolean added = addTownType(newTown);
            if (!added) {
                plugin.log(Level.WARNING, "Duplicate class (" + townName + ") found. Skipping this class.");
            } else {
                plugin.log(Level.INFO, "Loaded class: " + townName);
                if (config.getBoolean("classes." + townName + ".default", false)) {
                    plugin.log(Level.INFO, "Default class found: " + townName);
                    defaultClass = newTown;
                }
            if (defaultClass == null) {
                plugin.log(Level.SEVERE, "You are missing a Default Town, this will cause ALOT of issues!");
            }
            // Save the Configuration setup to file, we do this so that any defaults values loaded are saved to file.
            config.save();

        
            }
    
        }
    }
}


