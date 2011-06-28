package com.herocraftonline.shadrxninga.townships.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;

import org.bukkit.util.config.Configuration;

import com.herocraftonline.shadrxninga.townships.TownShips;
import com.herocraftonline.shadrxninga.townships.types.TypesManager;

public class ConfigManager {
	 	protected TownShips plugin;
	    protected File primaryConfigFile;
	    protected File typesConfigFile;
	    protected Properties properties = new Properties();

	 
	public ConfigManager(TownShips plugin) {
	        this.plugin = plugin;
	        this.primaryConfigFile = new File(plugin.getDataFolder(), "config.yml");
	        this.typesConfigFile = new File(plugin.getDataFolder(), "types.yml");
	    }
	
	public void reload() throws Exception {
        load();
        plugin.log(Level.INFO, "Reloaded Configuration");
    }
	
	public void load() {
        try {
            checkForConfig(primaryConfigFile);
            checkForConfig(typesConfigFile);
            Configuration primaryConfig = new Configuration(primaryConfigFile);
            primaryConfig.load();
            TypesManager typesManager = new TypesManager(plugin);
            typesManager.loadTypes(typesConfigFile);
            plugin.setTypesManager(typesManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void checkForConfig(File config) {
        if (!config.exists()) {
            try {
                plugin.log(Level.WARNING, "File " + config.getName() + " not found - generating defaults.");
                config.getParentFile().mkdir();
                config.createNewFile();
                OutputStream output = new FileOutputStream(config, false);
                InputStream input = ConfigManager.class.getResourceAsStream("/defaults/" + config.getName());
                byte[] buf = new byte[8192];
                while (true) {
                    int length = input.read(buf);
                    if (length < 0) {
                        break;
                    }
                    output.write(buf, 0, length);
                }
                input.close();
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




}
