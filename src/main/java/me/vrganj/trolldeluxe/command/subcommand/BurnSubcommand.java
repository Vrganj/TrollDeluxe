package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BurnSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.setFireTicks(10 * 20);
        Util.send(sender, "Set &e" + target.getName() + " &7on fire!");
    }

    @Override
    public String getDescription() {
        return "Set a player on fire";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.burn";
    }

    @Override
    public String getUsage() {
        return "burn <player>";
    }
}
