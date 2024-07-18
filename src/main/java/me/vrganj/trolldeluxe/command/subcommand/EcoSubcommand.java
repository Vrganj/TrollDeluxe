package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class EcoSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            Util.sendRawLocalized(target, "troll.eco.message");
        }

        Util.sendLocalized(sender, "troll.eco.showed", targets.size());
    }

    @Override
    public String getDescription() {
        return "Show a fake balance change to players";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.eco";
    }

    @Override
    public String getUsage() {
        return "eco <players>";
    }
}
