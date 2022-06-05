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
    private final String[] trolls = new String[6*9];
    private final Inventory inventory;

    public GuiSubcommand(TrollDeluxe plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);

        inventory = Bukkit.createInventory(null, 6 * 9, plugin.toString());

        ItemStack border = new ItemBuilder(Material.YELLOW_STAINED_GLASS_PANE).setName("&r").build();

        for (int i = 0; i < 9; ++i) {
            inventory.setItem(i, border);
            inventory.setItem(i + 5 * 9, border);
        }

        ItemStack note = new ItemBuilder(Material.WRITABLE_BOOK)
                .setName("&eAnd that's not all!")
                .setLore(
                        "",
                        "&r&fThere are more trolls you",
                        "&r&fcan use, but which are not",
                        "&r&fin the troll GUI currently!",
                        "",
                        "&r&e/td help",
                        ""
                )
                .build();

        inventory.setItem(49, note);
        trolls[49] = "help";

        addTroll(19, Material.BEDROCK, "cage", "&6&lCAGE");
        addTroll(20, Material.SLIME_BALL, "launch", "&6&lLAUNCH");
        addTroll(21, Material.CREEPER_HEAD, "creeper", "&6&lCREEPER");
        addTroll(22, Material.FLINT_AND_STEEL, "burn", "&6&lBURN");
        addTroll(23, Material.GOLD_INGOT, "gmc", "&6&lFAKE GMC");
        addTroll(24, Material.DIAMOND, "op", "&6&lFAKE OP");
        addTroll(25, Material.BLAZE_ROD, "smite", "&6&lSMITE");

        addTroll(29, Material.POTATO, "potato", "&6&lPOTATO");
        addTroll(30, Material.KNOWLEDGE_BOOK, "demo", "&6&lDEMO");
        addTroll(31, Material.CHEST, "keepinventory", "&6&lFAKE KEEP INVENTORY");
        addTroll(32, Material.COOKED_CHICKEN, "starve", "&6&lSTARVE");
        addTroll(33, Material.RED_BED, "deathbed", "&6&lDEATHBED");
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
        String target = getString(args, 1);

        targetMap.put(player.getUniqueId(), target);
        player.openInventory(inventory);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getInventory() == inventory) {
            event.setCancelled(true);
        }

        int slot = event.getSlot();

        if (event.getClickedInventory() == inventory && slot >= 0 && slot < 6*9) {
            String troll = trolls[slot];

            if (troll != null) {
                player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 100f, 2f);

                if (troll.equals("help")) {
                    player.performCommand("trolldeluxe help");
                    player.closeInventory();
                } else if (event.getClick() != ClickType.RIGHT) {
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
        return "gui <players>";
    }
}
