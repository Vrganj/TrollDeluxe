package me.vrganj.trolldeluxe.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Subcommand {
    public abstract void execute(CommandSender sender, String[] args) throws CommandException;
    public abstract String getDescription();
    public abstract String getPermission();
    public abstract String getUsage();

    protected List<Player> getPlayers(String[] args, int index) throws CommandException {
        if (index < args.length) {
            if (args[index].equals("*")) {
                return new ArrayList<>(Bukkit.getOnlinePlayers());
            }

            return Collections.singletonList(getPlayer(args, index));
        }

        throw new CommandException("&c/trolldeluxe " + getUsage());
    }

    protected Player getPlayer(String[] args, int index) throws CommandException {
        if (index < args.length) {
            Player player = Bukkit.getPlayer(args[index]);

            if (player == null) {
                throw new CommandException("&cTarget player is not online");
            }

            return player;
        }

        throw new CommandException("&c/trolldeluxe " + getUsage());
    }

    protected String getString(String[] args, int index) throws CommandException {
        if (index < args.length) {
            return args[index];
        }

        throw new CommandException("&c/trolldeluxe " + getUsage());
    }

    protected String consume(String[] args, int from) throws CommandException {
        if (from < args.length) {
            return Stream.of(args).skip(from).collect(Collectors.joining(" "));
        }

        throw new CommandException("&c/trolldeluxe " + getUsage());
    }
}
