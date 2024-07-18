package me.vrganj.trolldeluxe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker implements Listener {

    private final Plugin plugin;
    private volatile String latestVersion = null;

    public UpdateChecker(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String currentVersion = plugin.getDescription().getVersion();

        if (player.isOp() && latestVersion != null && !latestVersion.equals(currentVersion)) {
            Util.sendLocalized(player, "update.available", currentVersion, latestVersion);
        }
    }

    public void fetchVersion() {
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
                this.latestVersion = object.get("name").getAsString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
