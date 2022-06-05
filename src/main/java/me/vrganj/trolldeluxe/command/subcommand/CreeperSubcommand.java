package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreeperSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.playSound(target.getLocation(), Sound.ENTITY_CREEPER_PRIMED, 1.0f, 1.0f);
        Util.send(sender, "&e" + target.getName() + " &7has been scared!");
    }

    @Override
    public String getDescription() {
        return "Play a creeper sound to a player";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.creeper";
    }

    @Override
    public String getUsage() {
        return "creeper <player>";
    }
}
