package supersecretproject.Instances;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import supersecretproject.SSPAPI;

public abstract class Stage implements Listener{
    private Instance instance;
    private boolean started = false;
    private boolean ended = false;

    public void start(Instance instance){
        Bukkit.getPluginManager().registerEvents(this, SSPAPI.getPlugin());
        this.instance = instance;
        this.started = true;
        start();
    }
    
    public void nextStage(){
        instance.nextStage();
    }
    
    /**
     * SHOULD NOT BE RAN DIRECTLY, start(Instance instance) should be ran instead
     * 
     * Called when the stage should start
     */
    public abstract void start();
    
    public void internalFinish(){
        HandlerList.unregisterAll(this);
        ended = true;
        finish();
    }
    /**
     * SHOULD NOT BE RAN DIRECTLY, internalFinish() should be ran instead
     * 
     * Called when the stage should end
     */
    public abstract void finish();

    public Instance getInstance() {
        return instance;
    }

    public boolean isStarted() {
        return started;
    }
    public boolean isEnded() {
        return ended;
    }
}
