package me.vrganj.trolldeluxe;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

    public ItemStack build() {
        item.setItemMeta(meta);
        return item.clone();
    }
}
