package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ShuffleSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Entity> targets = consumeEntities(sender, args, 1);
        List<Entity> list = new ArrayList<>(targets);
        List<Location> shuffled = list.stream().map(Entity::getLocation).collect(Collectors.toList());
        Location first = shuffled.get(0);

        for (int i = 0; i < shuffled.size() - 1; ++i) {
            shuffled.set(i, shuffled.get(i + 1));
        }

        shuffled.set(shuffled.size() - 1, first);

        for (int i = 0; i < list.size(); ++i) {
            list.get(i).teleport(shuffled.get(i));
        }

        Util.send(sender, "Shuffled &e" + targets.size() + " entities!");
    }

    @Override
    public String getDescription() {
        return "Shuffle the positions of entities!";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.shuffle";
    }

    @Override
    public String getUsage() {
        return "shuffle <entities>";
    }
}
