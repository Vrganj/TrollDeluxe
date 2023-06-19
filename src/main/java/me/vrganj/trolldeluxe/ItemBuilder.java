package me.vrganj.trolldeluxe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ItemBuilder {
    private final ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(Material material, int count) {
        item = new ItemStack(material, count);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(material, 1);
    }

    public ItemBuilder setName(String name) {
        meta.setDisplayName(Util.translate(name));
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        meta.setLore(Stream.of(lore).map(Util::translate).collect(Collectors.toList()));
        return this;
    }

    public ItemBuilder hideAttributes() {
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        return this;
    }

    public ItemStack build() {
        item.setItemMeta(meta);
        return item.clone();
    }
}
