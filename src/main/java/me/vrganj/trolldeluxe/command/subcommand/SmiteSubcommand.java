package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.Collection;

public class SmiteSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Entity> targets = consumeEntities(sender, args, 1);

        for (Entity target : targets) {
            target.getWorld().strikeLightningEffect(target.getLocation());
        }

        Util.sendLocalized(sender, "subcommand.smite.struck", targets.size());
    }

    @Override
    public String getName() {
        return "smite";
    }
}
