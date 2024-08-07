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
            "freeze",
            "gmc",
            "invsee",
            "keepinventory",
            "launch",
            "op",
            "potato",
            "ride",
            "shuffle",
            "smite",
            "starve",
            "tnt",
            "unpotato",
            "wolf"
    ));

    public BindSubcommand(TrollDeluxe plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final Map<UUID, Map<Material, String>> bindings = new HashMap<>();

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        if (!(sender instanceof Player)) {
            throw new CommandException(Util.getLocalized("command.players-only"));
        }

        Player player = (Player) sender;
        Material material = player.getInventory().getItemInHand().getType();

        if (material == Material.AIR) {
            throw new CommandException(Util.getLocalized("subcommand.bind.invalid-item"));
        }

        if (args.length == 1) {
            Map<Material, String> binding = bindings.get(player.getUniqueId());

            if (binding == null || binding.get(material) == null) {
                throw new CommandException(Util.getLocalized("subcommand.bind.no-binding"));
            }

            binding.remove(material);

            if (binding.isEmpty()) {
                bindings.remove(player.getUniqueId());
            }

            Util.sendLocalized(player, "subcommand.bind.binding-removed");

            return;
        }

        String subcommand = getString(args, 1).toLowerCase();

        if (!SUBCOMMANDS.contains(subcommand)) {
            throw new CommandException(Util.getLocalized("command.invalid-subcommand"));
        }

        Map<Material, String> binding = bindings.computeIfAbsent(player.getUniqueId(), uuid -> new HashMap<>());
        binding.put(material, subcommand);

        Util.sendLocalized(player, "subcommand.bind.binding-created", material, subcommand);
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Player) {
            Player player = event.getPlayer();
            ItemStack tool;

            if (event.getHand() == EquipmentSlot.HAND) {
                tool = player.getInventory().getItemInHand();
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
    public String getName() {
        return "bind";
    }
}
