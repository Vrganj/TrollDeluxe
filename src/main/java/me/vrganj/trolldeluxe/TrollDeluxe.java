package me.vrganj.trolldeluxe;

import me.vrganj.trolldeluxe.command.TrollCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class TrollDeluxe extends JavaPlugin {

    @Override
    public void onEnable() {
        new Metrics(this, 15358);

        saveDefaultConfig();

        Util.loadLocalization(getConfig().getString("locale", "en_US"), getLogger());

        if (!getConfig().getBoolean("disable update check")) {
            UpdateChecker updateChecker = new UpdateChecker(this);
            Bukkit.getScheduler().runTaskAsynchronously(this, updateChecker::fetchVersion);
            Bukkit.getPluginManager().registerEvents(updateChecker, this);
        }

        Objects.requireNonNull(getCommand("trolldeluxe")).setExecutor(new TrollCommand(this));
    }
}
