package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.ItemBuilder;
import me.vrganj.trolldeluxe.TrollDeluxe;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GuiSubcommand extends Subcommand implements Listener {
    private final Map<UUID, String> targetMap = new HashMap<>();
    private final String[] trolls = new String[36];
    private final Inventory inventory;

    public GuiSubcommand(TrollDeluxe plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        inventory = Bukkit.createInventory(null, 2 * 9, "Troll Deluxe");

        addTroll(0, Material.POTATO, "potato", "&6&lPOTATO");
        addTroll(1, Material.BEDROCK, "cage", "&6&lCAGE");
        addTroll(2, Material.SLIME_BALL, "launch", "&6&lLAUNCH");
        addTroll(3, Material.CREEPER_HEAD, "creeper", "&6&lCREEPER");
        addTroll(4, Material.FLINT_AND_STEEL, "burn", "&6&lBURN");
        addTroll(5, Material.GOLD_INGOT, "gmc", "&6&lFAKE GMC");
        addTroll(6, Material.DIAMOND, "op", "&6&lFAKE OP");
        addTroll(7, Material.BLAZE_ROD, "smite", "&6&lSMITE");
        addTroll(8, Material.RED_BED, "deathbed", "&6&lDEATHBED");

        addTroll(12, Material.KNOWLEDGE_BOOK, "demo", "&6&lDEMO");
        addTroll(13, Material.CHEST, "keepinventory", "&6&lFAKE KEEP INVENTORY");
        addTroll(14, Material.COOKED_CHICKEN, "starve", "&6&lSTARVE");
    }

    private void addTroll(int slot, Material material, String command, String name) {
        ItemStack item = new ItemBuilder(material).setName(name).setLore("", "&r&7(Right-click for help)", "").build();
        inventory.setItem(slot, item);
        trolls[slot] = command;
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException("&cOnly players can run this command!");
        }

        Player player = (Player) sender;
        Player target = getPlayer(args, 1);

        targetMap.put(player.getUniqueId(), target.getName());

        player.openInventory(inventory);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory() == inventory) {
            event.setCancelled(true);
        }

        if (event.getClickedInventory() == inventory) {
            String troll = trolls[event.getSlot()];

            if (troll != null) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 100f, 2f);

                if (event.getClick() != ClickType.RIGHT) {
                    String target = targetMap.get(player.getUniqueId());
                    player.performCommand("trolldeluxe " + troll + " " + target);
                } else {
                    player.performCommand("trolldeluxe help " + troll);
                    player.closeInventory();
                }
            }
        }
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onQuit(PlayerQuitEvent event) {
        // Not using InventoryCloseEvent because it
        // is not called when a player is showed
        // the demo screen.

        targetMap.remove(event.getPlayer().getUniqueId());
    }

    @Override
    public String getDescription() {
        return "Open a simple troll gui";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.gui";
    }

    @Override
    public String getUsage() {
        return "gui <player>";
    }
}
