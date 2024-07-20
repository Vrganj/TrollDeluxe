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
            Util.sendLocalized(sender, "subcommand.help.subcommands");

            for (Map.Entry<String, Subcommand> entry : subcommands.entrySet()) {
                String name = entry.getKey();
                Subcommand subcommand = entry.getValue();
                Util.sendRawLocalized(sender, "subcommand.help.message.subcommand", subcommand.getUsage(), Util.getLocalized("subcommand." + name + ".description"));
            }

            return;
        }

        Subcommand subcommand = subcommands.get(args[1].toLowerCase());

        if (subcommand == null) {
            throw new CommandException(Util.getLocalized("command.invalid-subcommand"));
        }

        Util.sendLocalized(sender, "subcommand.help.message.help", args[1].toLowerCase());
        Util.sendRawLocalized(sender, "subcommand.help.message.description", Util.getLocalized("subcommand." + args[1].toLowerCase() + ".description"));
        Util.sendRawLocalized(sender, "subcommand.help.message.usage", subcommand.getUsage());
        Util.sendRawLocalized(sender, "subcommand.help.message.permission", subcommand.getPermission());
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
