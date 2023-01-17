package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BurnSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            player.setFireTicks(10 * 20);
        }

        Util.send(sender, "Set &e" + args[1] + " &fon fire!");
    }

    @Override
    public String getDescription() {
        return "Set players on fire";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.burn";
    }

    @Override
    public String getUsage() {
        return "burn <players>";
    }
}
