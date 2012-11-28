package supersecretproject.Instances;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import supersecretproject.SSPAPI;

public abstract class InstanceStarter implements Listener{
    
    public void initInstanceStarterInternal(){
        Bukkit.getPluginManager().registerEvents(this, SSPAPI.getPlugin());
    } 
    public abstract void initInstanceStarter();
    public abstract Instance newInstance();
    public abstract Instance newInstanceAndStart(); 
    
}
