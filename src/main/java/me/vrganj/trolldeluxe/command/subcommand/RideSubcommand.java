package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RideSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException("&cOnly players can run this command!");
        }

        Player player = (Player) sender;
        Player target = getPlayer(args, 1);

        if (player != target) {
            target.addPassenger(player);

            Util.send(player, "&fRiding &e" + target.getName() + "!");
        } else {
            throw new CommandException("&cYou can't ride yourself");
        }
    }

    @Override
    public String getDescription() {
        return "Ride on top of a player";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.ride";
    }

    @Override
    public String getUsage() {
        return "ride <player>";
    }
}
