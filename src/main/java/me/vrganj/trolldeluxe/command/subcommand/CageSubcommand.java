package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class CageSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Entity> targets = consumeEntities(sender, args, 1);

        for (Entity target : targets) {
            Block origin = target.getLocation().getBlock().getRelative(BlockFace.UP);

            for (int dx = -2; dx <= 2; dx += 4) {
                for (int dy = -2; dy <= 2; dy += 4) {
                    for (int dz = -2; dz <= 2; dz += 4) {
                        origin.getRelative(dx, dy, dz).setType(Material.BEDROCK);
                    }
                }
            }
        }

        Util.send(sender, "&e" + targets.size() + " entities &fhave been caged!");
    }

    @Override
    public String getDescription() {
        return "Spawn a bedrock cage around entities";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.cage";
    }

    @Override
    public String getUsage() {
        return "cage <entities>";
    }
}
