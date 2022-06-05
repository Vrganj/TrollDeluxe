package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StarveSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);
        target.setFoodLevel(0);
        Util.send(sender, "Starved &e" + target.getName() + "!");
    }

    @Override
    public String getDescription() {
        return "Clear a player's hunger bar";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.starve";
    }

    @Override
    public String getUsage() {
        return "starve <player>";
    }
}
