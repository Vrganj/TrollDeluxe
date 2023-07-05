package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.TrollDeluxe;
import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import java.util.stream.Collectors;

public class BindSubcommand extends Subcommand implements Listener {
    private static final Set<String> SUBCOMMANDS = new HashSet<>(Arrays.asList(
            "anvil",
            "blind",
            "burn",
            "bury",
            "cage",
            "carry",
            "creeper",
            "deathbed",
            "demo",
            "eco",
            "flip",
            "gmc",
            "invsee",
            "keepinventory",
            "launch",
            "op",
            "potato",
            "ride",
            "smite",
            "starve",
            "tnt",
            "wolf"
    ));

    public BindSubcommand(TrollDeluxe plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final Map<UUID, Map<Material, String>> bindings = new HashMap<>();

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException("&cOnly players can run this command!");
        }

        Player player = (Player) sender;
        Material material = player.getInventory().getItemInMainHand().getType();

        if (material == Material.AIR) {
            throw new CommandException("&cYou can't bind air");
        }

        if (args.length == 1) {
            Map<Material, String> binding = bindings.get(player.getUniqueId());

            if (binding == null || binding.get(material) == null) {
                throw new CommandException("&cUse /trolldeluxe bind <troll> while holding an item");
            }

            binding.remove(material);

            if (binding.size() == 0) {
                bindings.remove(player.getUniqueId());
            }

            Util.send(player, "&aRemoved binding from your item");

            return;
        }

        String subcommand = getString(args, 1).toLowerCase();

        if (!SUBCOMMANDS.contains(subcommand)) {
            throw new CommandException("&cInvalid troll name");
        }

        Map<Material, String> binding = bindings.computeIfAbsent(player.getUniqueId(), uuid -> new HashMap<>());
        binding.put(material, subcommand);

        Util.send(player, "&aBound " + material + " to " + subcommand);
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player player = event.getPlayer();
            ItemStack tool;

            if (event.getHand() == EquipmentSlot.HAND) {
                tool = player.getInventory().getItemInMainHand();
            } else {
                tool = player.getInventory().getItemInOffHand();
            }

            Map<Material, String> binding = bindings.get(player.getUniqueId());

            if (binding == null) {
                return;
            }

            String subcommand = binding.get(tool.getType());

            if (subcommand == null) {
                return;
            }

            player.performCommand("trolldeluxe " + subcommand + " " + event.getRightClicked().getName());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        if (args.length == 2) {
            return SUBCOMMANDS.stream().filter(arg -> arg.toLowerCase().startsWith(args[1].toLowerCase())).collect(Collectors.toList());
        }

        return null;
    }

    @Override
    public String getDescription() {
        return "Bind an item to a troll";
    }

    @Override
    public String getPermission() {
        return "trolldeluxe.bind";
    }

    @Override
    public String getUsage() {
        return "bind <troll>";
    }
}
