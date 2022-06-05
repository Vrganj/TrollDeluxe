package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SaySubcommand extends Subcommand {
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);
        String message = consume(args, 2);

        for (Player player : target) {
            player.chat(message);
        }

        Util.send(sender, "Made &e" + args[1] + " &fsay &e" + message + "&e!");
    }

    @Override
    public String getDescription() {
        return "Make players say or execute something";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.say";
    }

    @Override
    public String getUsage() {
        return "say <players> <message>";
    }
}
