package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class StarveSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            target.setFoodLevel(0);
        }

        Util.send(sender, "Starved &e" + targets.size() + " players!");
    }

    @Override
    public String getDescription() {
        return "Clear players' hunger bar";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.starve";
    }

    @Override
    public String getUsage() {
        return "starve <players>";
    }
}
