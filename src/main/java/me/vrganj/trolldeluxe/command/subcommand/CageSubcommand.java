package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CageSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            Block origin = player.getEyeLocation().getBlock();

            for (int dx = -2; dx <= 2; dx += 4) {
                for (int dy = -2; dy <= 2; dy += 4) {
                    for (int dz = -2; dz <= 2; dz += 4) {
                        origin.getRelative(dx, dy, dz).setType(Material.BEDROCK);
                    }
                }
            }
        }

        Util.send(sender, "&e" + args[1] + " &fhas been caged!");
    }

    @Override
    public String getDescription() {
        return "Spawn a bedrock cage around players";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.cage";
    }

    @Override
    public String getUsage() {
        return "cage <players>";
    }
}
