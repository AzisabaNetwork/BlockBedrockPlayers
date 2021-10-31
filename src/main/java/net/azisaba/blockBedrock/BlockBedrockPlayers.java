package net.azisaba.blockBedrock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockBedrockPlayers extends JavaPlugin {
    private String kickMessage = "";

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new TheGreatEventListener(), this);
        getConfig().options().copyDefaults();
        kickMessage = getConfig().getString("kickMessage", "&cBedrock Edition (統合版)では参加できません。");
        getLogger().info("Enabled " + this.getName());
    }

    public class TheGreatEventListener implements Listener {
        @EventHandler
        public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
            if (e.getName().startsWith(".")) {
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.translateAlternateColorCodes('&', kickMessage));
            }
        }
    }
}
