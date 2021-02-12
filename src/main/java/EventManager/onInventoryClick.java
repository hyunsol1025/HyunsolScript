package EventManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class onInventoryClick implements Listener {
    @EventHandler
    void onIventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null) return;

        Bukkit.broadcastMessage(""+e.getRawSlot());

        // 스크립트 목록 GUI
        if(e.getView().getTitle().contains("스크립트 목록")) {
        }
    }
}
