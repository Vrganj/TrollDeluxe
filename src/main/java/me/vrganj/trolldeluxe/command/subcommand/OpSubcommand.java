package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class OpSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        for (Player player : target) {
            Util.sendRaw(player, "&7&o[Server: Made " + player.getName() + " a server operator]");
        }

        Util.send(sender, "Sent a fake op message to &e" + args[1] + "!");
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
