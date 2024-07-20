package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;
import java.util.Collection;

public class PotatoSubcommand extends Subcommand {

    private final Plugin plugin;

    public PotatoSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);
        ItemStack potato = new ItemStack(Util.getMaterial("POTATO_ITEM", "POTATO"), 64);

        for (Player target : targets) {
            ItemStack[] contents = target.getInventory().getContents();
            ItemStack[] snapshot = clone(contents);

            Arrays.fill(contents, potato);
            target.getInventory().setContents(contents);

            // Avoid saving potato inventory
            if (!Arrays.equals(contents, snapshot)) {
                target.setMetadata("pre-potato", new FixedMetadataValue(plugin, snapshot));
            }
        }

        Util.sendLocalized(sender, "subcommand.potato.potatoed", args[1]);
    }

    private ItemStack[] clone(ItemStack[] itemStacks) {
        ItemStack[] result = new ItemStack[itemStacks.length];

        for (int i = 0; i < itemStacks.length; i++) {
            if (itemStacks[i] != null) {
                result[i] = itemStacks[i].clone();
            }
        }

        return result;
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.potato";
    }

    @Override
    public String getUsage() {
        return "potato <players>";
    }
}
