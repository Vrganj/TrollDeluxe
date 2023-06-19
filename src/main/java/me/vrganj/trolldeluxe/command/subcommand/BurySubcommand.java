package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BurySubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            player.teleport(player.getLocation().subtract(0, 3, 0));

            Block block = player.getLocation().getBlock();
            block.setType(Material.AIR);
            block.getRelative(BlockFace.UP).setType(Material.AIR);
        }

        Util.send(sender, "&e" + args[1] + " &fhas been buried!");
    }

    @Override
    public String getDescription() {
        return "Bury players 3 blocks underground";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.bury";
    }

    @Override
    public String getUsage() {
        return "bury <players>";
    }
}
