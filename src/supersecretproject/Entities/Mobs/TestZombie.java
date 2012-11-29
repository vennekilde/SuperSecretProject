package supersecretproject.Entities.Mobs;

import supersecretproject.Entities.BasicEntity;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftZombie;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

public class TestZombie extends BasicEntity{

    public TestZombie(Location location){
        super("Test Zombie", location.getWorld().spawnEntity(location, EntityType.ZOMBIE));
        Zombie zombie = (Zombie)this.getBaseEntity();
        
    }
}
