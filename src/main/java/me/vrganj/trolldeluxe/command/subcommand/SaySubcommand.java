package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SaySubcommand extends Subcommand {
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        String message = consume(args, 2);

        target.chat(message);
        Util.send(sender, "Made &e" + target.getName() + " &7say &e" + message + "&e!");
    }

    @Override
    public String getDescription() {
        return "Make a player say or execute something";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.say";
    }

    @Override
    public String getUsage() {
        return "say <player> <message>";
    }
}
