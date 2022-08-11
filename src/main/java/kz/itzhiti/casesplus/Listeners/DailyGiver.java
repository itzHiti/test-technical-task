package kz.itzhiti.casesplus.Listeners;

import kz.itzhiti.casesplus.CasesPlus;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.net.InetAddress;

public class DailyGiver implements Listener {

    @EventHandler
    public void onJoin (PlayerJoinEvent e) {
        long secondsLeft = 0;
        Player p = e.getPlayer();
        InetAddress pip = p.getAddress().getAddress();
        Long time = System.currentTimeMillis();
        long seconds = 86399*1000;
        try {
            secondsLeft = ((CasesPlus.getInstance().ips.get(pip) / 1000) + seconds) - (time / 1000);
        }
        catch (Exception ex) {
        }
        try {
        if (time - CasesPlus.getInstance().ips.get(pip) < seconds) {
            p.sendMessage(CasesPlus.getInstance().getMSG("ALREADY_TOOK_CASES") + secondsLeft + CasesPlus.getInstance().getMSG("SECONDS"));
        }
    }
        catch (Exception ex) {
    }
        if (CasesPlus.getInstance().ips.get(pip) == null){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cases give " + e.getPlayer().getName() + " skins_daily 1");
            p.sendMessage(CasesPlus.getInstance().getMSG("GIVE_CASE"));
            CasesPlus.getInstance().ips.put(pip, time);
        }
    }
}
