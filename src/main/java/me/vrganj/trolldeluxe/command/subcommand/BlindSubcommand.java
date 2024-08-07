package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class BlindSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = getPlayers(sender, args, 1);

        for (Player target : targets) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60*20, 1));
        }

        Util.sendLocalized(sender, "subcommand.blind.blinded", targets.size());
    }

    @Override
    public String getName() {
        return "blind";
    }
}
