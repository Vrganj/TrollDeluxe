package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class AnvilSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Entity> targets = consumeEntities(sender, args, 1);

        for (Entity target : targets) {
            Block block = target.getLocation().getBlock().getRelative(0, 5, 0);
            block.setType(Util.getMaterial("DAMAGED_ANVIL", "ANVIL"));
        }

        Util.sendLocalized(sender, "subcommand.anvil.dropped", targets.size());
    }

    @Override
    public String getName() {
        return "anvil";
    }
}
