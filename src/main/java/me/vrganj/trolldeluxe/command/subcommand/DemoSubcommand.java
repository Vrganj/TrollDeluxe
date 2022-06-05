package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class DemoSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        List<Player> target = getPlayers(sender, args, 1);

        // TODO: hack something up so this works on lower versions
        // Player#showDemoScreen exists only since 1.18+

        try {
            Method method = Player.class.getMethod("showDemoScreen");

            for (Player player : target) {
                // InventoryCloseEvent doesn't get called
                // when the demo screen is showed to a player,
                // so it must be done manually.
                player.closeInventory();

                method.invoke(player);
            }

            Util.send(sender, "Displayed demo screen to &e" + args[1] + "!");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            Util.send(sender, "&cThis feature only works on 1.18+");
        }
    }

    @Override
    public String getDescription() {
        return "Make players think they're playing the demo version of Minecraft";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.demo";
    }

    @Override
    public String getUsage() {
        return "demo <players>";
    }
}
