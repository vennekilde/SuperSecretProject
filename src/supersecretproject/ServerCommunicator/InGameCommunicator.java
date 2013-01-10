package supersecretproject.ServerCommunicator;

import info.jeppes.ZoneCore.Commands.CommandData;
import info.jeppes.ZoneCore.ZonePlugin;
import java.util.HashMap;
import org.bukkit.Achievement;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.CraftServer;
import supersecretproject.SSPAPI;

/**
 * This is suppose to read commands issued by the server so command blocks
 * and such can communicate with the plugin
 */
public class InGameCommunicator extends CommandData{

    private HashMap<String,InGameMessageReciever> inGameCommunicators = new HashMap();
    
    public InGameCommunicator(){
        super("ccom",SSPAPI.getPlugin());
        CraftServer server = (CraftServer)SSPAPI.getPlugin().getServer();
        Bukkit.getPlayer("test").awardAchievement(Achievement.OVERKILL);
        this.setIsHelpCommand(true);
    }

    @Override
    public void run(ZonePlugin plugin, CommandSender cs, Command cmnd, String[] args) {
        if(args.length < 2){
            this.sendErrorMessage(cs, "Missing identifier");
            return;
        }
        String identifier = args[2].toLowerCase();
        if(inGameCommunicators.containsKey(identifier)){
            InGameMessageReciever IGMR = inGameCommunicators.get(identifier);
            IGMR.messageRecieved(args);
        }
    }

    @Override
    public String getDescrption() {
        return "Used to communicate to "+SSPAPI.getPlugin().getName()+" by use of server commands";
    }

    @Override
    public String getUsage() {
        return "/ccom identifier {arg1} {arg2} {arg3}...";
    }
}
