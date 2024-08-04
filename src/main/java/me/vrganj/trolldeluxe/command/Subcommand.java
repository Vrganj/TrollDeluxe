package me.vrganj.trolldeluxe.command;

import me.vrganj.trolldeluxe.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class Subcommand {

    public abstract void execute(CommandSender sender, String[] args) throws CommandException;
    public abstract String getName();

    public String getPermission() {
        return "trolldeluxe." + getName();
    }

    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }

    private List<Entity> selectEntities(CommandSender sender, String selector) {
        try {
            return Bukkit.selectEntities(sender, selector);
        } catch (NoSuchMethodError ignore) {
            return Collections.singletonList(Bukkit.getPlayer(selector));
        }
    }

    protected Set<Entity> consumeEntities(CommandSender sender, String[] args, int from) throws CommandException {
        Set<Entity> result = new HashSet<>();

        try {
            for (int i = from; i < args.length; ++i) {
                result.addAll(selectEntities(sender, getString(args, i)));
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        if (result.isEmpty()) {
            throw new CommandException("&cNo entity was found!");
        }

        return result;
    }

    protected List<Player> consumePlayers(CommandSender sender, String[] args, int from) throws CommandException {
        List<Player> result = new ArrayList<>();

        try {
            for (int i = from; i < args.length; ++i) {
                for (Entity entity : selectEntities(sender, getString(args, i))) {
                    if (entity instanceof Player) {
                        result.add((Player) entity);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        if (result.isEmpty()) {
            throw new CommandException("&cNo player was found!");
        }

        return result;
    }

    protected List<Entity> getEntities(CommandSender sender, String[] args, int index) throws CommandException {
        List<Entity> result;

        try {
            result = selectEntities(sender, getString(args, index));
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        if (result.isEmpty()) {
            throw new CommandException("&cNo entity was found!");
        }

        return result;
    }

    protected Entity getEntity(CommandSender sender, String[] args, int index) throws CommandException {
        List<Entity> entities = getEntities(sender, args, index);

        if (entities.size() != 1) {
            throw new CommandException("&cMore than one entity found");
        }

        return entities.get(0);
    }

    protected List<Player> getPlayers(CommandSender sender, String[] args, int index) throws CommandException {
        List<Player> result = new ArrayList<>();

        try {
            for (Entity entity : selectEntities(sender, getString(args, index))) {
                if (entity instanceof Player) {
                    result.add((Player) entity);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }

        if (result.isEmpty()) {
            throw new CommandException("&cNo player was found!");
        }

        return result;
    }

    protected Player getPlayer(String[] args, int index) throws CommandException {
        Player player = Bukkit.getPlayer(getString(args, index));

        if (player == null) {
            throw new CommandException("&cTarget player is not online");
        }

        return player;
    }

    protected String getString(String[] args, int index) throws CommandException {
        if (index < args.length) {
            return args[index];
        }

        throw new CommandException("&c/trolldeluxe " + Util.getLocalized("subcommand." + getName() + ".usage"));
    }

    protected String consume(String[] args, int from) throws CommandException {
        if (from < args.length) {
            return Stream.of(args).skip(from).collect(Collectors.joining(" "));
        }

        throw new CommandException("&c/trolldeluxe " + Util.getLocalized("subcommand." + getName() + ".usage"));
    }
}
