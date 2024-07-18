package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class RideSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException(Util.getLocalized("command.players-only"));
        }

        Player player = (Player) sender;
        Entity target = getEntity(sender, args, 1);

        player.leaveVehicle();

        if (player != target) {
            target.addPassenger(player);

            Util.sendLocalized(player, "troll.ride.riding", target.getName());
        } else {
            throw new CommandException(Util.getLocalized("troll.ride.self"));
        }
    }

    @Override
    public String getDescription() {
        return "Ride on top of an entity";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.ride";
    }

    @Override
    public String getUsage() {
        return "ride <entity>";
    }
}
