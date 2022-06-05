package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class LaunchSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.setVelocity(new Vector(0, 100, 0));
        Util.send(sender, "&e" + target.getName() + " &7has been launched!");
    }

    @Override
    public String getDescription() {
        return "Launch a player far up";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.launch";
    }

    @Override
    public String getUsage() {
        return "launch <player>";
    }
}
