package supersecretproject.Entities;

import org.bukkit.entity.Entity;

public abstract class BasicEntity {
    private String name;
    private Entity baseEntity;
    
    public BasicEntity(String name, Entity baseEntity){
        this.name = name;
        this.baseEntity = baseEntity;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity getBaseEntity() {
        return baseEntity;
    }
}
