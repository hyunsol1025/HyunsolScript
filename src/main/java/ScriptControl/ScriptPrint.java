package ScriptControl;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import script.script.Script;
import script.script.ServerGlobal;

import java.util.ArrayList;
import java.util.Arrays;

import static script.script.Script.main;

public class ScriptPrint {
    public int delayTick = 0;
    public int beforeDelayTick = 0;
    public int autoDelayTick = 0;

    public boolean isLooping = false;

    public int loopStartIndex = 0;
    public int loopTime = 0;
    public int targetLoopTime = 0;

    public int readIndex = 0;

    public String subtitle_type = "chat";

    private String[] getScriptOption(String option, String e) {

        // 해당 옵션이 맞는지 확인
        String[] eSplit = e.replace(" ","").split("=");

        if(eSplit[0].equals("#"+option)) {
            return eSplit;
        }

        return null;
    }


    // 스크립트 시작
    public void scriptStart(String scriptName, ArrayList<Player> targetPlayer) {

        ArrayList<String> scriptContent = ServerGlobal.scripts.get(scriptName);

        while(readIndex < scriptContent.size()) {
            String e = scriptContent.get(readIndex);
            Bukkit.broadcastMessage("readIndex: "+readIndex);
            Bukkit.broadcastMessage("현재 e: "+e);
            readIndex++;

            // LOOP: 현재 루프중일때
            if(isLooping && e.equals("#END_LOOP")) {
                loopTime++;
                readIndex = loopStartIndex;

                if(targetLoopTime <= loopTime) { isLooping = false; }
            }

            // 스크립트 규칙 읽기
            if(e.toCharArray()[0] == '#') {

                // 게임모드 설정
                if(getScriptOption("GAMEMODE",e) != null) {
                    Bukkit.getScheduler().runTaskLater(main, () -> {

                        switch(getScriptOption("GAMEMODE",e)[1]) {
                            case "0":
                                for(Player p : targetPlayer) p.setGameMode(GameMode.SURVIVAL);
                                break;

                            case "1":
                                for(Player p : targetPlayer) p.setGameMode(GameMode.CREATIVE);
                                break;

                            case "2":
                                for(Player p : targetPlayer) p.setGameMode(GameMode.ADVENTURE);
                                break;

                            case "3":
                                for(Player p : targetPlayer) p.setGameMode(GameMode.SPECTATOR);
                                break;
                        }

                    },beforeDelayTick+delayTick);
                }

                // 자막 타입
                else if(getScriptOption("SUBTITLE_TYPE",e) != null) {
                    Bukkit.getScheduler().runTaskLater(main, () -> {

                        subtitle_type = getScriptOption("SUBTITLE_TYPE",e)[1];

                    },beforeDelayTick+delayTick);
                }

                // 딜레이
                else if(getScriptOption("WAIT", e) != null) {
                    delayTick = Integer.parseInt(getScriptOption("WAIT",e)[1]);
                }

                // 딜레이
                else if(getScriptOption("AUTO_WAIT", e) != null) {
                    autoDelayTick = Integer.parseInt(getScriptOption("AUTO_WAIT",e)[1]);
                }

                // LOOP
                else if(getScriptOption("LOOP", e) != null) {
                    loopStartIndex = readIndex;
                    isLooping = true;
                    targetLoopTime = Integer.parseInt(getScriptOption("LOOP", e)[1]);
                }

                // BREAK
                else if(getScriptOption("BREAK", e) != null) {
                    autoDelayTick = 0;

                    isLooping = false;
                }

            }
            else {

                Bukkit.getScheduler().runTaskLater(main, () -> {

                    Bukkit.broadcastMessage(e);

                    },beforeDelayTick+delayTick);

                beforeDelayTick += (delayTick + e.length() * autoDelayTick);
                delayTick = 0;
            }

        }
    }
}
