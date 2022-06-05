package me.vrganj.trolldeluxe;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;

public class Util {
    private static final String OBC_PREFIX = Bukkit.getServer().getClass().getPackage().getName();
    private static final String NMS_PREFIX = OBC_PREFIX.replace("org.bukkit.craftbukkit", "net.minecraft.server");

    public static Class<?> getClass(String s) throws ClassNotFoundException {
        s = s.replace("{obc}", OBC_PREFIX).replace("{nms}", NMS_PREFIX);
        return Class.forName(s);
    }

    public static Field getField(Class<?> clazz, Class<?> type, int index) {
        for (Field field : clazz.getDeclaredFields()) {
            if (type.isAssignableFrom(field.getType()) && index-- == 0) {
                field.setAccessible(true);
                return field;
            }
        }

        return null;
    }

    public static String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void send(CommandSender target, String text) {
        target.sendMessage(translate("&8[&6TrollDeluxe&8] &7" + text));
    }


}
