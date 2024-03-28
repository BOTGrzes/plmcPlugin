package pl.polskaliga.plmcplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import pl.polskaliga.plmcplugin.cmd.FlyCMD;
import pl.polskaliga.plmcplugin.cmd.GmCMD;

public final class PlmcPlugin extends JavaPlugin {
    public void onEnable() {
        this.saveDefaultConfig();
        new GmCMD(this);
        new FlyCMD(this);
        this.getLogger().info("Załadowano plugin");
    }

    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        HandlerList.unregisterAll(this);
        this.getLogger().info("Wyłączono plugin");
    }
}
    