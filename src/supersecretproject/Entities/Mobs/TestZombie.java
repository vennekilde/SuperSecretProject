package supersecretproject.Entities.Mobs;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import supersecretproject.Entities.BasicEntity;

public class TestZombie extends BasicEntity{

    public TestZombie(Location location){
        super("Test Zombie", "Zombie", (Zombie)location.getWorld().spawnEntity(location, EntityType.ZOMBIE));
        Zombie zombie = (Zombie)this.getBaseEntity();
        
    }
}
