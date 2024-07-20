package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class JoinSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        String fakePlayer = getString(args, 1);
        Collection<Player> targets = consumePlayers(sender, args, 2);

        for (Player target : targets) {
            Util.sendRawLocalized(target, "subcommand.join.message", fakePlayer);
        }

        Util.sendLocalized(sender, "subcommand.join.sent", targets.size());
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.join";
    }

    @Override
    public String getUsage() {
        return "join <fake player> <players>";
    }
}
