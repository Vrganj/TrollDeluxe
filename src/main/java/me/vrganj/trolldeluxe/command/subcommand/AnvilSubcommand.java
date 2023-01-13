package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class AnvilSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            Block block = player.getLocation().getBlock().getRelative(0, 5, 0);
            block.setType(Material.DAMAGED_ANVIL);
        }

        Util.send(sender, "&fDropped an anvil on &e" + args[1] + "&f!");
    }

    @Override
    public String getDescription() {
        return "Drop an anvil on someone";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.anvil";
    }

    @Override
    public String getUsage() {
        return "anvil <players>";
    }
}
