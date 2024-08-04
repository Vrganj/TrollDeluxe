package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class DeathbedSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            target.setHealth(1);
        }

        Util.sendLocalized(sender, "subcommand.deathbed.damaged", targets.size());
    }

    @Override
    public String getName() {
        return "deathbed";
    }
}
