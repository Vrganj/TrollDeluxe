package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PotatoSubcommand extends Subcommand {
    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        ItemStack potato = new ItemStack(Material.POTATO, 64);
        target.getInventory().setArmorContents(new ItemStack[] { potato, potato, potato, potato });

        for (int i = 0; i < 4 * 9; ++i) {
            target.getInventory().setItem(i, potato);
        }

        Util.send(sender, "&e" + target.getName() + " &7has been potatoed!");
    }

    @Override
    public String getDescription() {
        return "Fill a player up with potatoes!";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.potato";
    }

    @Override
    public String getUsage() {
        return "potato <player>";
    }
}
