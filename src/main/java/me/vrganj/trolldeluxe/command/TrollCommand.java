package me.vrganj.trolldeluxe.command;

import me.vrganj.trolldeluxe.TrollDeluxe;
import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.subcommand.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TrollCommand implements TabExecutor {
    private final Map<String, Subcommand> subcommands = new HashMap<>();

    public TrollCommand(TrollDeluxe plugin) {
        subcommands.put("burn", new BurnSubcommand());
        subcommands.put("cage", new CageSubcommand());
        subcommands.put("creeper", new CreeperSubcommand());
        subcommands.put("deathbed", new DeathbedSubcommand());
        subcommands.put("demo", new DemoSubcommand());
        subcommands.put("gmc", new GmcSubcommand());
        subcommands.put("gui", new GuiSubcommand(plugin));
        subcommands.put("join", new JoinSubcommand(plugin.getConfig()));
        subcommands.put("launch", new LaunchSubcommand());
        subcommands.put("op", new OpSubcommand());
        subcommands.put("potato", new PotatoSubcommand());
        subcommands.put("say", new SaySubcommand());
        subcommands.put("smite", new SmiteSubcommand());
        subcommands.put("starve", new StarveSubcommand());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            Util.send(sender, "&f/trolldeluxe command help");

            for (Subcommand subcommand : subcommands.values()) {
                sender.sendMessage(
                        Util.translate("&8\u00bb &e" + subcommand.getUsage() + " &f" + subcommand.getDescription())
                );
            }

            return true;
        }

        Subcommand subcommand = subcommands.get(args[0].toLowerCase());

        if (subcommand == null) {
            Util.send(sender, "&cUnknown subcommand. Run /trolldeluxe help");
            return true;
        }

        if (!sender.hasPermission(subcommand.getPermission())) {
            Util.send(sender, "&cYou have insufficient permissions!");
            return true;
        }

        try {
            subcommand.execute(sender, args);
        } catch (CommandException e) {
            Util.send(sender, Util.translate(e.getMessage()));
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return subcommands.keySet().stream()
                    .filter(arg -> arg.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        }

        return null;
    }
}
