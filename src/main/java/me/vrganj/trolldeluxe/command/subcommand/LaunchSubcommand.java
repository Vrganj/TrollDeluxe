package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import java.util.Collection;

public class LaunchSubcommand extends Subcommand {
    private final Plugin plugin;

    public LaunchSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Entity> targets = consumeEntities(sender, args, 1);

        for (Entity target : targets) {
            target.setVelocity(new Vector(0.0, plugin.getConfig().getDouble("launch velocity", 20), 0.0));
        }

        Util.sendLocalized(sender, "subcommand.launch.launched", targets.size());
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.launch";
    }

    @Override
    public String getUsage() {
        return "launch <entities>";
    }
}
