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

public class BurySubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Entity> targets = consumeEntities(sender, args, 1);

        for (Entity target : targets) {
            target.teleport(target.getLocation().subtract(0, target.getHeight() + 1, 0));

            Block block = target.getLocation().getBlock();
            block.setType(Material.AIR);
            block.getRelative(BlockFace.UP).setType(Material.AIR);
        }

        Util.send(sender, "&e" + targets.size() + " entities &fhave been buried!");
    }

    @Override
    public String getDescription() {
        return "Bury entities underground";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.bury";
    }

    @Override
    public String getUsage() {
        return "bury <entities>";
    }
}
