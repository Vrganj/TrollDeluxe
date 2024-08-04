package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CarrySubcommand extends Subcommand {

    @Override
    @SuppressWarnings("deprecation")
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException(Util.getLocalized("command.players-only"));
        }

        Player player = (Player) sender;
        Collection<Entity> targets = consumeEntities(sender, args, 1);

        Set<Entity> stack = new HashSet<>();

        Entity top = player;
        stack.add(top);

        while (top != null && top.getPassenger() != null) {
            top = top.getPassenger();

            if (top != null) {
                stack.add(top);
            }
        }

        int count = 0;

        for (Entity target : targets) {
            if (stack.contains(target)) {
                continue;
            }

            ++count;

            target.leaveVehicle();
            target.eject();

            top.addPassenger(target);
            top = target;
        }

        Util.sendLocalized(player, "subcommand.carry.carrying", count);
    }

    @Override
    public String getName() {
        return "carry";
    }
}
