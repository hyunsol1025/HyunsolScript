package ScriptControl;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import script.script.Hyunsolapi;
import script.script.Script;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class GUI {

    public void GUI_ScriptMenu(Player p) {
        Hyunsolapi.openGUI(p,27,"스크립트 메뉴");

        Hyunsolapi.putItem(p,12,Hyunsolapi.getItemStack(Material.BOOKSHELF,1, "§f§l스크립트 목록보기",null,0));
        Hyunsolapi.putItem(p,14,Hyunsolapi.getItemStack(Material.WOODEN_AXE,1, "§f§l개발자 정보!",null,0));
    }

    public void GUI_ScriptList(Player p) {
        Hyunsolapi.openGUI(p,54,"스크립트 목록");

        int i=0;
        File standF = new File(""+Script.main.getDataFolder());

        for(File f : standF.listFiles()) {

            // 스크립트 그룹일때
            if(f.isDirectory()) {
                String dirName = f.getName().split("_")[1];
                Hyunsolapi.putItem(p,i,
                        Hyunsolapi.getItemStack(Material.CHEST,1,"§f§l스크립트 그룹: "+dirName, new ArrayList<>(Arrays.asList("카테고리 | §f§l"+dirName)),0)
                );
            } else {
                Hyunsolapi.putItem(p,i,
                        Hyunsolapi.getItemStack(Material.CHEST,1,"§f§l"+f.getName(), new ArrayList<>(Arrays.asList("일반 대본입니다.")),0)
                );
            }

            i++;
        }

        // 페이지 컨트롤 아이템 배치
        Hyunsolapi.putItem(p,48,Hyunsolapi.getItemStack(Material.PAPER,1,"§f§l이전으로",null,0));
        Hyunsolapi.putItem(p,50,Hyunsolapi.getItemStack(Material.PAPER,1,"§f§l다음으로",null,0));
    }
}
