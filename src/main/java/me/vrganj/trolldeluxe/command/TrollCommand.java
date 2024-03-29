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
        subcommands.put("anvil", new AnvilSubcommand());
        subcommands.put("blind", new BlindSubcommand());
        subcommands.put("burn", new BurnSubcommand());
        subcommands.put("bury", new BurySubcommand());
        subcommands.put("cage", new CageSubcommand());
        subcommands.put("carry", new CarrySubcommand());
        subcommands.put("creeper", new CreeperSubcommand());
        subcommands.put("deathbed", new DeathbedSubcommand());
        subcommands.put("demo", new DemoSubcommand());
        subcommands.put("eco", new EcoSubcommand());
        subcommands.put("flip", new FlipSubcommand());
        subcommands.put("freeze", new FreezeSubcommand());
        subcommands.put("gmc", new GmcSubcommand());
        subcommands.put("invsee", new InvseeSubcommand());
        subcommands.put("join", new JoinSubcommand(plugin));
        subcommands.put("keepinventory", new KeepInventorySubcommand());
        subcommands.put("launch", new LaunchSubcommand(plugin));
        subcommands.put("op", new OpSubcommand());
        subcommands.put("potato", new PotatoSubcommand());
        subcommands.put("reload", new ReloadSubcommand(plugin));
        subcommands.put("ride", new RideSubcommand());
        subcommands.put("say", new SaySubcommand());
        subcommands.put("shuffle", new ShuffleSubcommand());
        subcommands.put("smite", new SmiteSubcommand());
        subcommands.put("starve", new StarveSubcommand());
        subcommands.put("tnt", new TntSubcommand(plugin));
        subcommands.put("wolf", new WolfSubcommand(plugin));

        subcommands.put("bind", new BindSubcommand(plugin));
        subcommands.put("gui", new GuiSubcommand(plugin, subcommands));
        subcommands.put("help", new HelpSubcommand(subcommands));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Subcommand subcommand = subcommands.get(args.length != 0 ? args[0].toLowerCase() : "help");

        try {
            if (subcommand == null) {
                throw new CommandException("&cUnknown command. Run /trolldeluxe help");
            }

            if (!sender.hasPermission(subcommand.getPermission())) {
                throw new CommandException("&cYou have insufficient permissions!");
            }

            subcommand.execute(sender, args);
        } catch (CommandException e) {
            Util.send(sender, e.getMessage());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return subcommands.keySet().stream()
                    .filter(arg -> arg.toLowerCase().startsWith(args[0].toLowerCase()))
                    .collect(Collectors.toList());
        } else if (args.length >= 2) {
            Subcommand subcommand = subcommands.get(args[0].toLowerCase());

            if (subcommand != null) {
                return subcommand.onTabComplete(sender, args);
            }
        }

        return null;
    }
}
