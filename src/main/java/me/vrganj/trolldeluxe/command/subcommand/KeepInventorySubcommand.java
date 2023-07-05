package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class KeepInventorySubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            Util.sendRaw(target, "&7&o[Server: Gamerule keepInventory is now set to: true]");
        }

        Util.send(sender, "Sent fake keepInventory message to &e" + args[1] + "!");
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
        return "keepinventory <players>";
    }
}
