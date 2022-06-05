package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KeepInventorySubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        Util.sendRaw(target, "&7&o[Server: Gamerule keepInventory is now set to: true]");
        Util.send(sender, "Sent fake keepInventory message to &e" + target.getName() + "!");
    }

    @Override
    public String getDescription() {
        return "Send a fake keepInventory message";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.keepinventory";
    }

    @Override
    public String getUsage() {
        return "keepinventory <player>";
    }
}
