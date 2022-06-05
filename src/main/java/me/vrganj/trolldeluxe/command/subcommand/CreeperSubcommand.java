package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreeperSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);
        target.forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1.0f, 1.0f));
        Util.send(sender, "&e" + args[1] + " &fhas been scared!");
    }

    @Override
    public String getDescription() {
        return "Play a creeper sound to players";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.creeper";
    }

    @Override
    public String getUsage() {
        return "creeper <players>";
    }
}
