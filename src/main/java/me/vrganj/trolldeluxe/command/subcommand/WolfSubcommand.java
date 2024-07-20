package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

public class WolfSubcommand extends Subcommand {
    private final Plugin plugin;

    public WolfSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        int wolves = plugin.getConfig().getInt("wolves", 3);

        for (Player target : targets) {
            for (int i = 0; i < wolves; ++i) {
                Wolf wolf = target.getWorld().spawn(target.getLocation(), Wolf.class);
                wolf.setAngry(true);
                wolf.setTarget(target);
            }
        }

        Util.sendLocalized(sender, "subcommand.wolf.spawned", targets.size());
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.wolf";
    }

    @Override
    public String getUsage() {
        return "wolf <players>";
    }
}
