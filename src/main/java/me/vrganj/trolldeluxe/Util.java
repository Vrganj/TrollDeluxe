package me.vrganj.trolldeluxe;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Util {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void send(CommandSender target, String text) {
        sendRaw(target, "&8[&6TrollDeluxe&8] &f" + text);
    }

    public static void sendRaw(CommandSender target, String text) {
        target.sendMessage(translate(text));
    }
}
