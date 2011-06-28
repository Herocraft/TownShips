/**
 * Copyright (C) 2011 DThielke <dave.thielke@gmail.com>
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/ or send a letter to
 * Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 **/

package com.herocraftonline.shadrxninga.townships.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.herocraftonline.shadrxninga.townships.TownShips;

public class CommandManager {

    protected LinkedHashMap<String, BaseCommand> commands;

    public CommandManager() {
        this.commands = new LinkedHashMap<String, BaseCommand>();
    }

    public boolean dispatch(CommandSender sender, Command command, String label, String[] args) {
        String input = label + " ";
        for (String s : args) {
            input += s + " ";
        }

        BaseCommand match = null;
        String[] trimmedArgs = null;
        StringBuilder identifier = new StringBuilder();

        for (BaseCommand cmd : this.commands.values()) {
            StringBuilder tmpIdentifier = new StringBuilder();
            String[] tmpArgs = cmd.validate(input, tmpIdentifier);
            if (tmpIdentifier.length() > identifier.length()) {
                identifier = tmpIdentifier;
                match = cmd;
                trimmedArgs = tmpArgs;
            }
        }

        if (match != null) {
            if ((trimmedArgs == null) || ((trimmedArgs.length > 0) && trimmedArgs[0].equals("?"))) {
                sender.sendMessage("§cCommand:§e " + match.getName());
                sender.sendMessage("§cDescription:§e " + match.getDescription());
                sender.sendMessage("§cUsage:§e " + match.getUsage());
                List<String> notes = match.getNotes();
                for (String note : notes) {
                    sender.sendMessage("§e" + note);
                }
            } else {
                // If there is no Permission Node we allow the Command, otherwise we check against the Permissions.
                if ((match.permissionNode.length() == 0) || (this.hasPermission(sender, match.permissionNode))) {
                    match.execute(sender, trimmedArgs);
                } else {
                    sender.sendMessage("You do not have permission to use this command.");
                }
            }
        }
        return true;
    }

    public void addCommand(BaseCommand command) {
        this.commands.put(command.name.toLowerCase(), command);
    }

    public void removeCommand(BaseCommand command) {
        this.commands.remove(command);
    }

    public List<BaseCommand> getCommands() {
        return new ArrayList<BaseCommand>(this.commands.values());
    }

    public BaseCommand getCommand(String name) {
        return this.commands.get(name.toLowerCase());
    }

    public boolean hasPermission(CommandSender sender, String node) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;
        try {
            if (player.isOp()) {
                // If Player is Op we always let them use it.
                return true;
            } else if ((TownShips.Permissions != null) && TownShips.Permissions.safeGetUser(player.getWorld().getName(), player.getName()).hasPermission(node)) {
                // If Permissions is enabled we check against them.
                return true;
            } else {
                // If the Player doesn't have Permissions and isn't an Op then
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
