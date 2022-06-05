package me.vrganj.trolldeluxe;

import me.vrganj.trolldeluxe.command.TrollCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class TrollDeluxe extends JavaPlugin {

    @Override
    public void onEnable() {
        new Metrics(this, 15358);

        saveDefaultConfig();

        getCommand("trolldeluxe").setExecutor(new TrollCommand(this));
    }
}
