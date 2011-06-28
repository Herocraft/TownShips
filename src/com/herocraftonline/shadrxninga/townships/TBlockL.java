package com.herocraftonline.shadrxninga.townships;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class TBlockL extends BlockListener {

    private TownShips plugin;

    public TBlockL(TownShips plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onBlockBreak(BlockBreakEvent e) {
        Location townloc = this.plugin.getTownManager().getTown("super").getLocation();
        Block block = e.getBlock();
        double x2 = block.getLocation().getX();
        double x1 = townloc.getX();
        double z2 = block.getLocation().getZ();
        double z1 = townloc.getZ();
        double radius = this.plugin.getTownManager().getTown("super").getRadius(); // config.getDouble(radius);
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        if (distance <= radius) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You don't belong to " + this.plugin.getTownManager().getTown("super").getTownName());
        } else {
            e.setCancelled(false);
        }

    }

    @Override
    public void onBlockPlace(BlockPlaceEvent e) {
        Location townloc = this.plugin.getTownManager().getTown("super").getLocation();
        Block block = e.getBlock();
        double x2 = block.getLocation().getX();
        double x1 = townloc.getX();
        double z2 = block.getLocation().getZ();
        double z1 = townloc.getZ();
        double radius = this.plugin.getTownManager().getTown("super").getRadius(); // config.getDouble(radius);
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(z2 - z1, 2));
        if (distance <= radius) {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "You don't belong to " + this.plugin.getTownManager().getTown("super").getTownName());
        } else {
            e.setCancelled(false);
        }

    }
}
