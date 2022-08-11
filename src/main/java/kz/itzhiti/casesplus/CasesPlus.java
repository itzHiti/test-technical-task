package kz.itzhiti.casesplus;

import kz.itzhiti.casesplus.Listeners.DailyGiver;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.util.HashMap;

public final class CasesPlus extends JavaPlugin {

    private static CasesPlus plugin;
    public static FileConfiguration cfg;
    public HashMap<InetAddress, Long> ips = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        cfg = Configurations.getFile("config.yml");
        Bukkit.getPluginManager().registerEvents(new DailyGiver(), plugin);
    }

    @Override
    public void onDisable() {
        ips.clear(); // При отключении плагина список айпишников очистится
        Bukkit.getScheduler().cancelTasks(plugin);
    }

    public static CasesPlus getInstance() {
        return plugin;
    }

    public String getMSG(final String path) {
        return ChatColor.translateAlternateColorCodes('&', cfg.getString("MESSAGES." + path).replace("{new}", "\n"));
    }
}
