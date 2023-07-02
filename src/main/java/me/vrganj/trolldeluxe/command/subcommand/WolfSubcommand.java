package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class WolfSubcommand extends Subcommand {
    private final Plugin plugin;

    public WolfSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> targets = getPlayers(sender, args, 1);

        int wolves = plugin.getConfig().getInt("wolves", 3);

        for (Player target : targets) {
            for (int i = 0; i < wolves; ++i) {
                target.getWorld().spawn(target.getLocation(), Wolf.class, entity -> {
                    entity.setAngry(true);
                    entity.setTarget(target);
                });
            }
        }

        Util.send(sender, "Spawned wolves around &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Summon angry wolves";
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
