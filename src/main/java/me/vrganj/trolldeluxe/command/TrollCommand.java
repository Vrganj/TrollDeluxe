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

    private void register(Subcommand subcommand) {
        // Add aliases here in the future
        subcommands.put(subcommand.getName(), subcommand);
    }

    public TrollCommand(TrollDeluxe plugin) {
        register(new AnvilSubcommand());
        register(new BlindSubcommand());
        register(new BurnSubcommand());
        register(new BurySubcommand());
        register(new CageSubcommand());
        register(new CarrySubcommand());
        register(new CreeperSubcommand());
        register(new DeathbedSubcommand());
        register(new DemoSubcommand());
        register(new EcoSubcommand());
        register(new FlipSubcommand());
        register(new FreezeSubcommand());
        register(new GmcSubcommand());
        register(new InvseeSubcommand());
        register(new JoinSubcommand());
        register(new KeepInventorySubcommand());
        register(new LaunchSubcommand(plugin));
        register(new OpSubcommand());
        register(new PotatoSubcommand(plugin));
        register(new ReloadSubcommand(plugin));
        register(new RideSubcommand());
        register(new SaySubcommand());
        register(new ShuffleSubcommand());
        register(new SmiteSubcommand());
        register(new StarveSubcommand());
        register(new TntSubcommand(plugin));
        register(new UnpotatoSubcommand(plugin));
        register(new WolfSubcommand(plugin));

        register(new BindSubcommand(plugin));
        register(new GuiSubcommand(plugin));
        register(new HelpSubcommand(subcommands));
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
            return subcommands.entrySet().stream()
                    .filter(entry -> sender.hasPermission(entry.getValue().getPermission()))
                    .map(Map.Entry::getKey)
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
