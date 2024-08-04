package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Collection;

public class UnpotatoSubcommand extends Subcommand {

    private final Plugin plugin;

    public UnpotatoSubcommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = consumePlayers(sender, args, 1);

        for (Player target : targets) {
            for (MetadataValue value : target.getMetadata("pre-potato")) {
                if (plugin.equals(value.getOwningPlugin())) {
                    ItemStack[] contents = (ItemStack[]) value.value();
                    target.getInventory().setContents(contents);
                    break;
                }
            }
        }

        Util.sendLocalized(sender, "subcommand.unpotato.unpotatoed", args[1]);
    }

    @Override
    public String getName() {
        return "unpotato";
    }
}