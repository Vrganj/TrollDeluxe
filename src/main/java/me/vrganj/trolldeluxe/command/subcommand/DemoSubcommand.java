package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DemoSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Player target = getPlayer(args, 1);

        // TODO: hack something up so this works on lower versions
        // Player#showDemoScreen exists only since 1.18+

        try {
            Method method = target.getClass().getMethod("showDemoScreen");
            method.invoke(target);
            Util.send(sender, "Displayed demo screen to &e" + target.getName() + "!");

            // InventoryCloseEvent doesn't get called
            // when the demo screen is showed to a player,
            // so it must be done manually.
            target.closeInventory();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            Util.send(sender, "&cThis feature only works on 1.18+");
        }
    }

    @Override
    public String getDescription() {
        return "Make a player think they're playing the demo version of Minecraft";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.demo";
    }

    @Override
    public String getUsage() {
        return "demo <player>";
    }
}
