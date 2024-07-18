package me.vrganj.trolldeluxe;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    private static Configuration configuration = YamlConfiguration.loadConfiguration(new InputStreamReader(TrollDeluxe.class.getClassLoader().getResourceAsStream("locale/en_US.yml")));

    public static void sendLocalized(CommandSender target, String key, Object... values) {
        send(target, getLocalized(key, values));
    }

    public static void sendRawLocalized(CommandSender target, String key, Object... values) {
        sendRaw(target, getLocalized(key, values));
    }

    public static String getLocalized(String key, Object... values) {
        return MessageFormat.format(configuration.getString(key), values);
    }

    public static List<String> getLocalizedList(String key, Object... values) {
        return configuration.getStringList(key).stream().map(single -> MessageFormat.format(single, values)).collect(Collectors.toList());
    }

    public static void send(CommandSender target, String text) {
        sendRaw(target, getLocalized("prefix") + text);
    }

    public static void sendRaw(CommandSender target, String text) {
        target.sendMessage(translate(text));
    }
}
