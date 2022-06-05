package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HelpSubcommand extends Subcommand {
    private final Map<String, Subcommand> subcommands;

    public HelpSubcommand(Map<String, Subcommand> subcommands) {
        this.subcommands = subcommands;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (args.length < 2) {
            Util.send(sender, "&f/trolldeluxe subcommands:");

            for (Subcommand subcommand : subcommands.values()) {
                Util.sendRaw(sender, "&8\u00bb &e" + subcommand.getUsage() + " &f" + subcommand.getDescription());
            }

            return;
        }

        Subcommand subcommand = subcommands.get(args[1].toLowerCase());

        if (subcommand == null) {
            Util.send(sender, "&cUnknown subcommand. Run /trolldeluxe help");
            return;
        }

        Util.send(sender, "&e/trolldeluxe " + args[1].toLowerCase() + " &fhelp:");
        Util.sendRaw(sender, "&8\u00bb &eDescription: &f" + subcommand.getDescription());
        Util.sendRaw(sender, "&8\u00bb &eUsage: &f/td " + subcommand.getUsage());
        Util.sendRaw(sender, "&8\u00bb &ePermission: &f" + subcommand.getPermission());
    }

    @Override
    public String getDescription() {
        return "List all of the subcommands or show info about a specific one";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.help";
    }

    @Override
    public String getUsage() {
        return "help [subcommand]";
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 2) {
            return subcommands.keySet().stream()
                    .filter(arg -> arg.startsWith(args[1].toLowerCase()))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
