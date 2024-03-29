package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException("&cOnly players can run this command!");
        }

        Player player = (Player) sender;
        Player target = getPlayer(args, 1);

        player.openInventory(target.getInventory());
        Util.send(player, "&fOpened inventory of &e" + player.getName() + "!");
    }

    @Override
    public String getDescription() {
        return "Mess around with a player's inventory";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.invsee";
    }

    @Override
    public String getUsage() {
        return "invsee <player>";
    }
}
