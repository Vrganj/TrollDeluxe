package me.vrganj.trolldeluxe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.vrganj.trolldeluxe.command.TrollCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class TrollDeluxe extends JavaPlugin implements Listener {
    private volatile String latest = null;

    @Override
    public void onEnable() {
        new Metrics(this, 15358);

        saveDefaultConfig();

        if (!getConfig().getBoolean("disable update check")) {
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> checkVersion(getDescription().getVersion()));
        }

        Objects.requireNonNull(getCommand("trolldeluxe")).setExecutor(new TrollCommand(this));
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (player.isOp() && latest != null) {
            String current = getDescription().getVersion();
            Util.send(player, "Update available: &e" + current + " &8\u00bb &e" + latest);
        }
    }

    private void checkVersion(String current) {
        try {
            URL url = new URL("https://api.spiget.org/v2/resources/102324/versions/latest");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setUseCaches(false);

            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            JsonObject object = new Gson().fromJson(new InputStreamReader(connection.getInputStream()), JsonObject.class);

            if (connection.getResponseCode() == 200) {
                String latest = object.get("name").getAsString();

                if (!object.get("name").getAsString().equals(current)) {
                    this.latest = latest;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
