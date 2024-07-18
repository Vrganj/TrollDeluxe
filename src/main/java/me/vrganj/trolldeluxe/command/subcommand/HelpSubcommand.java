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
            Util.sendLocalized(sender, "troll.help.subcommands");

            for (Subcommand subcommand : subcommands.values()) {
                Util.sendRawLocalized(sender, "troll.help.subcommand", subcommand.getUsage(), subcommand.getDescription());
            }

            return;
        }

        Subcommand subcommand = subcommands.get(args[1].toLowerCase());

        if (subcommand == null) {
            throw new CommandException(Util.getLocalized("command.invalid-subcommand"));
        }

        Util.sendLocalized(sender, "troll.help.help", args[1].toLowerCase());
        Util.sendRawLocalized(sender, "troll.help.description", subcommand.getDescription());
        Util.sendRawLocalized(sender, "troll.help.usage", subcommand.getUsage());
        Util.sendRawLocalized(sender, "troll.help.permission", subcommand.getPermission());
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
        return "help <subcommand>";
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
