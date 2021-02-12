package script.script;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class PlayerGlobal {
    public static HashMap<UUID, Inventory> inv = new HashMap<>();

    // 스크립트 GUI 관련
    public static HashMap<UUID, Integer> GUI_myPage = new HashMap<>();
    public static HashMap<UUID, String> Script_mySelectScript = new HashMap<>();
}
