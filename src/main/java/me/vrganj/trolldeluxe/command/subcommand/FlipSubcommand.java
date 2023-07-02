package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.util.List;

public class FlipSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Entity> targets = getEntities(sender, args, 1);

        for (Entity target : targets) {
            Location location = target.getLocation();
            Vector direction = location.getDirection();
            location.setDirection(direction.multiply(-1));
            target.teleport(location);
        }

        Util.send(sender, "Flipped &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Flip a player to the opposite direction!";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.flip";
    }

    @Override
    public String getUsage() {
        return "flip <players>";
    }
}
