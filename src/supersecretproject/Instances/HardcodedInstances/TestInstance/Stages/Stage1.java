package supersecretproject.Instances.HardcodedInstances.TestInstance.Stages;

import info.jeppes.ZoneCore.Users.ZoneUser;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import supersecretproject.Instances.Stage;

public class Stage1 extends Stage{
    private Entity spawnEntity = null;
    
    @Override
    public void start() {
        for(ZoneUser player : getInstance().getPlayers()){
            player.sendMessage("Zombie Incomming!");
        }
        ZoneUser player = getInstance().getPlayers().get(0);
        spawnEntity = player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
    }

    @Override
    public void finish() {
        for(ZoneUser player : getInstance().getPlayers()){
            player.sendMessage("You have beaten the zombie!");
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity().equals(spawnEntity)){
            nextStage();
        }
    }
}
