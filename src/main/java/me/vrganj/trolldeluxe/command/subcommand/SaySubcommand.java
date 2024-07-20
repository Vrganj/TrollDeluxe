package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class SaySubcommand extends Subcommand {
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = getPlayers(sender, args, 1);
        String message = consume(args, 2);

        for (Player target : targets) {
            target.chat(message);
        }

        Util.sendLocalized(sender, "subcommand.say.said", targets.size(), message);
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
