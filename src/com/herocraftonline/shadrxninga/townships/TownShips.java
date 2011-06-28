package com.herocraftonline.shadrxninga.townships;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.herocraftonline.shadrxninga.townships.commands.CommandManager;
import com.herocraftonline.shadrxninga.townships.persistence.Town;
import com.herocraftonline.shadrxninga.townships.persistence.TownManager;
import com.herocraftonline.shadrxninga.townships.types.TypesManager;
import com.herocraftonline.shadrxninga.townships.util.ConfigManager;
import com.nijiko.permissions.PermissionHandler;

public class TownShips extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    // Variable for the Permissions plugin handler.
    public static PermissionHandler Permissions;

    // Listeners
    private final TBlockL blockListener = new TBlockL(this);

    private CommandManager commandManager = new CommandManager();

    // Various data managers
    private TownManager townManager;
    private TypesManager typesManager;
    private ConfigManager configManager;


    @Override
    public void onLoad() {
        System.out.print("TownShips Loading...");
        getDataFolder().mkdirs();
        this.townManager = new TownManager(this);
        this.configManager = new ConfigManager(this);

    }

    @Override
    public void onDisable() {
        System.out.print("TownShips Disabled");
    }

    @Override
    public void onEnable() {
        System.out.print("TownShips Enabled");
        loadTowns();
        try {
            configManager.load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        registerEvents();
        registerCommands();
    }

    private void registerEvents() {
        PluginManager pluginManager = this.getServer().getPluginManager();

        pluginManager.registerEvent(Type.BLOCK_BREAK, this.blockListener, Priority.Monitor, this);
        pluginManager.registerEvent(Type.BLOCK_PLACE, this.blockListener, Priority.Monitor, this);
    }

    private void registerCommands() {
        // Page 1
     //   this.commandManager.addCommand(new CreateCommand(this));
        log.info("Commands Enabled");
    }

    public void loadTowns() {
        //System.out.print("Where's this NPE coming from...");
     //this.townManager.loadTownFile("config");
        /*Town town = this.townManager.getTown("config");
        town.setFarewell("Cya");
        town.addCitizen("shadrxninga", 1);
        town.addCitizen("n", 1);
        town.setGreeting("Welcome");
        Location loc = new Location(null, 3, 4, 2);
        town.setLocation(loc);
        if (town.isCitizen("Edvarc")) {
            System.out.println("Edvarc is a Citizen");
        } else {
            System.out.println("Edvarc is not a Citizen");
        }

        this.townManager.saveTownFile("config");
    }
    */
    }
    public TownManager getTownManager() {
        return this.townManager;
    }

    public TypesManager getTypesManager() {
        return this.typesManager;
    }

    public void setTypesManager(TypesManager typesManager) {
        this.typesManager = typesManager;
    }
    
    public ConfigManager getConfigManager() {
        return configManager;
    }


    public void log(Level level, String msg) {
        log.log(level, "[TownShips] " + msg);
    }

}
