package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.List;

public class LaunchSubcommand extends Subcommand {
    private final Configuration config;

    public LaunchSubcommand(Configuration config) {
        this.config = config;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            player.setVelocity(new Vector(0.0, config.getDouble("launch velocity", 100), 0.0));
        }

        Util.send(sender, "Launched &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Launch players far up into the sky";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.launch";
    }

    @Override
    public String getUsage() {
        return "launch <players>";
    }
}
