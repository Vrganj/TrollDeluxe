package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class ReloadSubcommand extends Subcommand {
    private final Plugin plugin;

    public ReloadSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        plugin.reloadConfig();
        Util.send(sender, "Reloaded the configuration!");
    }

    @Override
    public String getDescription() {
        return "Reload the configuration";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.reload";
    }

    @Override
    public String getUsage() {
        return "reload";
    }
}
