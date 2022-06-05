package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SmiteSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.getWorld().strikeLightningEffect(target.getLocation());
        Util.send(sender, "&e" + target.getName() + " &fhas been struck!");
    }

    @Override
    public String getDescription() {
        return "Strike a player with no damage";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.smite";
    }

    @Override
    public String getUsage() {
        return "smite <player>";
    }
}
