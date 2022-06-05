package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CageSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            Location location = player.getEyeLocation();

            for (int dx = -2; dx <= 2; ++dx) {
                for (int dy = -2; dy <= 2; ++dy) {
                    for (int dz = -2; dz <= 2; ++dz) {
                        if (Math.abs(dx) == 2 || Math.abs(dy) == 2 || Math.abs(dz) == 2) {
                            location.clone().add(dx, dy, dz).getBlock().setType(Material.BEDROCK);
                        }
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
