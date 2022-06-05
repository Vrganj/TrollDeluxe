package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmcSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.sendMessage(Util.translate("&7&o[Server: Set " + target.getName() + "'s game mode to Creative Mode]"));
        Util.send(sender, "Sent a fake gmc message to &e" + target.getName() + "!");
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
        return "gmc <player>";
    }
}
