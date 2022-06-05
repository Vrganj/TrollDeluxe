package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class JoinSubcommand extends Subcommand {
    private final Plugin plugin;

    public JoinSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);
        String fakePlayer = getString(args, 2);

        String message = plugin.getConfig().getString("join message");

        if (message == null) {
            throw new CommandException("&c'join message' field in config not found");
        }

        String joinMessage = String.format(message, fakePlayer);
        target.forEach(player -> Util.sendRaw(player, joinMessage));

        Util.send(sender, "Sent a fake join message to &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Send a fake player join message";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.join";
    }

    @Override
    public String getUsage() {
        return "join <players> <fake player>";
    }
}
