package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        Util.sendRaw(target, "&7&o[Server: Made " + target.getName() + " a server operator]");
        Util.send(sender, "Sent a fake op message to &e" + target.getName() + "!");
    }

    @Override
    public String getDescription() {
        return "Send a fake op message";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.op";
    }

    @Override
    public String getUsage() {
        return "op <player>";
    }
}
