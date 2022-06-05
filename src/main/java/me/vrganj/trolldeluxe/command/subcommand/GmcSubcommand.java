package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GmcSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);
        target.forEach(player -> Util.sendRaw(player, "&7&o[Server: Set " + player.getName() + "'s game mode to Creative Mode]"));
        Util.send(sender, "Sent a fake gmc message to &e" + args[1] + "!");
    }

    @Override
    public String getDescription() {
        return "Send a fake gamemode change message";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.gmc";
    }

    @Override
    public String getUsage() {
        return "gmc <players>";
    }
}
