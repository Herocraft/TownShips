package com.herocraftonline.shadrxninga.townships.command.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



import com.herocraftonline.shadrxninga.townships.TownShips;
import com.herocraftonline.shadrxninga.townships.command.BaseCommand;

public class CreateCommand extends BaseCommand {

    public CreateCommand(TownShips plugin) {
        super(plugin);
        name = "Create";
        description = "Creates a township";
        usage = "/township create";
        minArgs = 0;
        maxArgs = 0;
        identifiers.add("township create");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
      if(sender instanceof Player) {
          sender.sendMessage("Derp");
      }
    }
}
