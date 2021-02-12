package EventManager;

import ScriptControl.GUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import script.script.Hyunsolapi;

public class onInventoryClick implements Listener {

    @EventHandler
    void onIventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem() == null) return;

        Bukkit.broadcastMessage(""+e.getRawSlot());

        GUI scGUI = new GUI();

        // 스크립트 메뉴 GUI
        if(e.getView().getTitle().contains("스크립트 메뉴")) {
            e.setCancelled(true);

            // 스크립트 목록
            if(e.getView().getTitle().contains("스크립트 목록보기")) {
                scGUI.GUI_ScriptList(p);
            }

            // 개발자 정보
            else if(e.getView().getTitle().contains("개발자 정보!")) {

            }
        }

        // 스크립트 목록 GUI
        else if(e.getView().getTitle().contains("스크립트 목록")) {
            e.setCancelled(true);

            if(Hyunsolapi.isBetween(e.getRawSlot(),0,53)) {
                scGUI.GUI_ScriptSelect(p,e.getCurrentItem().getItemMeta().getDisplayName());
            }
        }
    }
}
