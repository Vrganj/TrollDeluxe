package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class OpSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            Util.sendRaw(target, "&7&o[Server: Made " + target.getName() + " a server operator]");
        }

        Util.send(sender, "Sent a fake op message to &e" + targets.size() + " players!");
    }

    @Override
    public String getDescription() {
        return "Send a fake op message";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.op";
    }

    @Override
    public String getUsage() {
        return "op <players>";
    }
}
