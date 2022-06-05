package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;

import java.util.List;

public class TntSubcommand extends Subcommand {
    private final Configuration config;

    public TntSubcommand(Configuration config) {
        this.config = config;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            player.getWorld().spawn(player.getLocation(), TNTPrimed.class, entity -> {
                entity.setFuseTicks(config.getInt("tnt fuse ticks", 40));
            });
        }

        Util.send(sender, "Spawned TNT next to &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Spawn TNT next to players";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.tnt";
    }

    @Override
    public String getUsage() {
        return "tnt <players>";
    }
}
