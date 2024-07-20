package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class GmcSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            Util.sendRawLocalized(target, "subcommand.gmc.message", target.getName());
        }

        Util.sendLocalized(sender, "subcommand.gmc.sent", targets.size());
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.gmc";
    }

    @Override
    public String getUsage() {
        return "gmc <players>";
    }
}
