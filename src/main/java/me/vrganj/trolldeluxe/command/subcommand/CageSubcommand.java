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

            for (int dx = -2; dx <= 2; ++dx) {
                for (int dy = -2; dy <= 2; ++dy) {
                    for (int dz = -2; dz <= 2; ++dz) {
                        if (Math.abs(dx) == 2 || Math.abs(dy) == 2 || Math.abs(dz) == 2) {
                            origin.getRelative(dx, dy, dz).setType(Material.BEDROCK);
                        }
                    }
                }
            }
        }

        Util.sendLocalized(sender, "subcommand.cage.caged", targets.size());
    }

    @Override
    public String getName() {
        return "cage";
    }
}
