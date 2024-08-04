package me.vrganj.trolldeluxe.command.subcommand;

import me.vrganj.trolldeluxe.Util;
import me.vrganj.trolldeluxe.command.CommandException;
import me.vrganj.trolldeluxe.command.Subcommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class FreezeSubcommand extends Subcommand {

    @Override
    public void execute(CommandSender sender, String[] args) throws CommandException {
        Collection<Player> targets = getPlayers(sender, args, 1);

        for (Player target : targets) {
            target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60*20, 200));
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 60*20, 200));
        }

        Util.sendLocalized(sender, "subcommand.freeze.froze", targets.size());
    }

    @Override
    public String getName() {
        return "freeze";
    }
}
