package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.Subcommand;
import me.vrganj.trolldeluxe.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class JoinSubcommand extends Subcommand {
    private final Configuration config;

    public JoinSubcommand(Configuration config) {
        this.config = config;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        String fakePlayer = getString(args, 2);

        String message = config.getString("join message");

        if (message == null) {
            throw new CommandException("&c'join message' field in config not found");
        }

        target.sendMessage(Util.translate(String.format(message, fakePlayer)));
        Util.send(sender, "Sent a fake join message to &e" + target.getName() + "!");
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
        return "join <player> <fake player>";
    }
}
