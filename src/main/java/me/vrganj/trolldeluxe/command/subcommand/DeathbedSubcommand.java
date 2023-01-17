package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DeathbedSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            player.setHealth(1);
        }

        Util.send(sender, "&e" + args[1] + " &fhas been damaged to half a heart!");
    }

    @Override
    public String getDescription() {
        return "Set players' health to half a heart";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.deathbed";
    }

    @Override
    public String getUsage() {
        return "deathbed <players>";
    }
}
