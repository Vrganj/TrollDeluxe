package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class CreeperSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            target.playSound(target.getLocation(), Util.getSound("ENTITY_CREEPER_PRIMED", "CREEPER_HISS"), 1.0f, 1.0f);
        }

        Util.sendLocalized(sender, "subcommand.creeper.creeped", targets.size());
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.creeper";
    }

    @Override
    public String getUsage() {
        return "creeper <players>";
    }
}
