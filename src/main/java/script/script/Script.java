package script.script;

import EventManager.onInventoryClick;
import ScriptControl.ScriptControl;
import ScriptControl.ScriptPrint;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public final class Script extends JavaPlugin {
    public static Script main;

    @Override
    public void onEnable() {

        main = this;
        getDataFolder().mkdir();

        getServer().getPluginManager().registerEvents(new onInventoryClick(),this);

    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        if(label.contains("hyunsol-script")) {

        }

        else if(label.contains("script-test")){
            ScriptControl.importScript("sex2","1");

            ScriptPrint sc = new ScriptPrint();
            sc.scriptStart("1", new ArrayList<>(Bukkit.getOnlinePlayers()));

            // Bukkit.broadcastMessage("스크립트 갯수: "+ScriptControl.getScriptAmount("all"));
        }

        return false;
    }
}
