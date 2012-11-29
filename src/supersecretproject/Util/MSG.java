package supersecretproject.Util;

import info.jeppes.ZoneCore.ZoneConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import supersecretproject.SSPAPI;

public class MSG {
    public static File errorFileLog = null;
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static Date date = new Date();
    
    public MSG(){
        errorFileLog = new File(SSPAPI.getPlugin().getZoneCore().getPluginDirectory()+"/Logs/errors.log");
        if(!errorFileLog.exists()){
            try {
                errorFileLog.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ZoneConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void sendMessage(String message){
        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendMessage(message);
        }
    }
    public static void sendMessage(Player player, String message){
        player.sendMessage(message);
    }
    public static void sendMessage(Location origin, double radius, String message){
        for(Player player : Bukkit.getOnlinePlayers()){
            if(origin.getWorld().equals(player.getWorld()) && origin.distance(player.getLocation()) <= radius){
                player.sendMessage(message);
            }
        }
    }
    
    
    public static void outputErrorMessage(String message){
        Bukkit.getLogger().log(Level.WARNING, message);
        writeToErrorLog("[WARNING]["+dateFormat.format(date)+"] "+message);
    }
    public static void outputFatalErrorMessage(String message){
        Bukkit.getLogger().log(Level.SEVERE, message);
        writeToErrorLog("[SEVERE ERROR]["+dateFormat.format(date)+"] "+message);
    }
    
    public static void writeToErrorLog(String message){
        message = "\n"+message;
        FileWriter fw = null;
        try {
            fw = new FileWriter(errorFileLog.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(message);
            }
        } catch (IOException ex) {
            Logger.getLogger(MSG.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MSG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
