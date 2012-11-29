package supersecretproject.Entities;

import supersecretproject.Entities.Mobs.TestZombie;
import org.bukkit.Location;

public enum SSPEntityType {
    TestZombie;
    
    public BasicEntity createEntity(Location location){
        switch(this){
            case TestZombie:
                return new TestZombie(location);
        }
        return null;
    }
}
