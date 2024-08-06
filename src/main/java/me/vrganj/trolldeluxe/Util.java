package me.vrganj.trolldeluxe;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Util {

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    // TODO: move localization to its own class
    private static Configuration configuration = null;

    public static void loadLocalization(String locale, Logger logger) {
        try (InputStream stream = TrollDeluxe.class.getClassLoader().getResourceAsStream("locale/" + locale + ".yml")) {
            if (stream == null) {
                logger.severe("Locale " + locale + " not found!");
                return;
            }

            configuration = YamlConfiguration.loadConfiguration(new InputStreamReader(stream));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendLocalized(CommandSender target, String key, Object... values) {
        send(target, getLocalized(key, values));
    }

    public static void sendRawLocalized(CommandSender target, String key, Object... values) {
        sendRaw(target, getLocalized(key, values));
    }

    public static String getLocalized(String key, Object... values) {
        return MessageFormat.format(configuration.getString(key, "&cMissing localization (" + key + ")"), values);
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

    public static Material getMaterial(String name, String fallback) {
        try {
            return Material.valueOf(name);
        } catch (IllegalArgumentException ignore) {
            return Material.valueOf(fallback);
        }
    }

    public static Sound getSound(String name, String fallback) {
        try {
            return Sound.valueOf(name);
        } catch (IllegalArgumentException ignore) {
            return Sound.valueOf(fallback);
        }
    }
}
