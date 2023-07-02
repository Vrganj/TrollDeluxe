package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class TntSubcommand extends Subcommand {
    private final Plugin plugin;

    public TntSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Entity> targets = getEntities(sender, args, 1);

        for (Entity target : targets) {
            target.getWorld().spawn(target.getLocation(), TNTPrimed.class, entity -> {
                entity.setFuseTicks(plugin.getConfig().getInt("tnt fuse ticks", 40));
            });
        }

        Util.send(sender, "Spawned TNT next to &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Spawn TNT next to entities";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.tnt";
    }

    @Override
    public String getUsage() {
        return "tnt <entities>";
    }
}
