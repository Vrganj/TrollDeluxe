package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class CarrySubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException("&cOnly players can run this command!");
        }

        Player player = (Player) sender;
        Entity target = getEntity(sender, args, 1);

        if (player != target) {
            if (!player.getPassengers().contains(target)) {
                Util.send(player, "&fCarrying &e" + target.getName() + "!");
                player.addPassenger(target);
            } else {
                Util.send(player, "&fNo longer carrying &e" + target.getName() + "!");
                player.removePassenger(target);
            }

        } else {
            throw new CommandException("&cYou can't carry yourself");
        }
    }

    @Override
    public String getDescription() {
        return "Carry an entity";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.carry";
    }

    @Override
    public String getUsage() {
        return "carry <entity>";
    }
}
