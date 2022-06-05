package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathbedSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.setHealth(1);
        Util.send(sender, "&e" + target.getName() + " &fhas been damaged to half a heart!");
    }

    @Override
    public String getDescription() {
        return "Set the player's health to half a heart";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.deathbed";
    }

    @Override
    public String getUsage() {
        return "deathbed <player>";
    }
}
