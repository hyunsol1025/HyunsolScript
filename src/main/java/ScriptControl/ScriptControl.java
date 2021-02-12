package ScriptControl;

import org.bukkit.Bukkit;
import script.script.Script;
import script.script.ServerGlobal;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;

public class ScriptControl {

    public static int getScriptAmount(String pluginName) {
        if(Script.main.getDataFolder().listFiles() == null) return 0;

        // 전체 스크립트 확인
        if(pluginName.equals("all")){
            int totalAmount = 0;

            for(File f : Script.main.getDataFolder().listFiles()) {
                Bukkit.broadcastMessage("f: "+f);
                totalAmount += new File(Script.main.getDataFolder()+"/"+f.getName()).listFiles().length;
            }

            return totalAmount;
        }


        // 특정 플러그인의 스크립트 확인
        File f = new File(Script.main.getDataFolder()+"/"+pluginName);
        if(f.listFiles() == null) return 0;

        return f.listFiles().length;
    }

    public static ArrayList<File> getAllScript() {
        File f = new File(Script.main.getDataFolder()+"");
        ArrayList<File> result = new ArrayList<>();

        for(File f2 : f.listFiles()) {
            for(File f3 : f2.listFiles()) {
                result.add(f3);
            }
        }

        return result;
    }

    public static boolean importScript(String pluginName, String scriptName) {
        File targetFile = null;

        // 스크립트 경로 파악하기
        if(pluginName.equals("all")) {
            for(File f : getAllScript()) {
                if(f.getName().equals(scriptName+".hyun")) {
                    targetFile = f;
                }
            }
        }

        else if(pluginName.equals("root")) {
            targetFile = new File(Script.main.getDataFolder()+"/"+scriptName+".hyun");

            if(!targetFile.exists()) {
                System.out.println("§c[HyunsolScript] 오류: "+scriptName+"라는 스크립트 파일을 찾을 수 없습니다.");
                return false;
            }

        }

        else {
            targetFile = new File(Script.main.getDataFolder()+"/"+pluginName+"/"+scriptName+".hyun");

            if(!targetFile.exists()) {
                System.out.println("§c[HyunsolScript] "+pluginName+" -> 오류: "+scriptName+"라는 스크립트 파일을 찾을 수 없습니다.");
                return false;
            }
        }

        // 스크립트 읽기
        ServerGlobal.scripts.put(scriptName,new ArrayList<>());

        try {
            FileReader r = new FileReader(targetFile);
            BufferedReader buf = new BufferedReader(r);

            String line = null;
            while((line = buf.readLine()) != null){
                ServerGlobal.scripts.get(scriptName).add(line);
            }

            r.close();
            buf.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
