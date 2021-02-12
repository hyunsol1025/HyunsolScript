package ScriptControl;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import script.script.Hyunsolapi;
import script.script.PlayerGlobal;
import script.script.Script;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GUI {

    public void GUI_ScriptMenu(Player p) {
        Hyunsolapi.openGUI(p,27,"스크립트 메뉴");

        Hyunsolapi.putItem(p,12,Hyunsolapi.getItemStack(Material.BOOKSHELF,1, "§f§l스크립트 목록보기",null,0));
        Hyunsolapi.putItem(p,14,Hyunsolapi.getItemStack(Material.WOODEN_AXE,1, "§f§l개발자 정보!",null,0));
    }

    private void UPDATE_ScriptList(Player p,String scriptDir) {
        Bukkit.broadcastMessage("scriptDir: "+scriptDir);

        PlayerGlobal.GUI_myPage.putIfAbsent(p.getUniqueId(),1);
        PlayerGlobal.Script_mySelectScript.putIfAbsent(p.getUniqueId(),"");
        Hyunsolapi.clearGUI(p,54);

        int ArrayIndex = 53*(PlayerGlobal.GUI_myPage.get(p.getUniqueId()) - 1);

        File standF = new File(""+Script.main.getDataFolder()+scriptDir);
        File[] standFList = standF.listFiles();

        for(int i=0;i<54;i++) {
            Bukkit.broadcastMessage("ArratyIndex: "+ArrayIndex);

            if(ArrayIndex == standFList.length - 1) break;

            File f = standFList[ArrayIndex];

            ItemStack item = null;

            // 스크립트 그룹일때
            if(f.isDirectory()) {

                String itemName = "§f§l스크립트 그룹: "+f.getName();

                item = Hyunsolapi.getItemStack(Material.CHEST,1,itemName, new ArrayList<>(Arrays.asList("카테고리 | §f§l"+f.getName())),0);

                // 선택된 대본 표시
                if(PlayerGlobal.Script_mySelectScript.get(p.getUniqueId()).contains(itemName)) {
                    itemName = "§f§l스크립트 그룹: "+f.getName()+" §7(선택됨!)";

                    ItemMeta meta = item.getItemMeta();

                    meta.addEnchant(Enchantment.MENDING,1,true);
                    meta.setDisplayName(itemName);
                    item.setItemMeta(meta);
                }

                Hyunsolapi.putItem(p,i,item);

            } else {
                String itemName = "§f§l"+f.getName();

                item = Hyunsolapi.getItemStack(Material.PAPER,1,itemName, new ArrayList<>(Arrays.asList("일반 대본입니다.")),0);

                // 선택된 대본 표시
                if(PlayerGlobal.Script_mySelectScript.get(p.getUniqueId()).contains(itemName)) {
                    itemName = "§f§l"+itemName+" §7(선택됨!)";

                    ItemMeta meta = item.getItemMeta();

                    meta.setDisplayName(itemName);
                    meta.addEnchant(Enchantment.MENDING,1,true);
                    item.setItemMeta(meta);
                }

                Hyunsolapi.putItem(p,i,item);
            }
            
            ArrayIndex++;
        }
    }

    public void GUI_ScriptList(Player p) {
        Hyunsolapi.openGUI(p,54,"스크립트 목록");

        UPDATE_ScriptList(p,"");

        // 페이지 컨트롤 아이템 배치
        Hyunsolapi.putItem(p,48,Hyunsolapi.getItemStack(Material.PAPER,1,"§f§l이전으로",null,0));
        Hyunsolapi.putItem(p,50,Hyunsolapi.getItemStack(Material.PAPER,1,"§f§l다음으로",null,0));
    }

    public void GUI_ScriptSelect(Player p, String currentItemName) {

        // 이미 선택된 스크립트일시 선택해제
        if(PlayerGlobal.Script_mySelectScript.get(p.getUniqueId()).contains(currentItemName)) {
            PlayerGlobal.Script_mySelectScript.put(p.getUniqueId(),"");
            UPDATE_ScriptList(p,"");
        } else {
            PlayerGlobal.Script_mySelectScript.put(p.getUniqueId(),currentItemName);
            UPDATE_ScriptList(p,"");
        }
    }
}
