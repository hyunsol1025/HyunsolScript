package script.script;

import ScriptControl.ScriptControl;
import ScriptControl.ScriptPrint;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public final class Script extends JavaPlugin {
    public static Script main;

    @Override
    public void onEnable() {

        main = this;
        getDataFolder().mkdir();

    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.contains("script-test")){
            ScriptControl.importScript("sex2","1");

            ScriptPrint sc = new ScriptPrint();
            sc.scriptStart("1", new ArrayList<>(Bukkit.getOnlinePlayers()));

            // Bukkit.broadcastMessage("스크립트 갯수: "+ScriptControl.getScriptAmount("all"));
        }

        return false;
    }
}
