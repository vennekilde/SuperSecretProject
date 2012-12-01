package supersecretproject.Entities;

import org.bukkit.Location;

public abstract class EntitySpawner {
    private final BasicEntity entity;
    public long respawnTime;
    public EntitySpawner(BasicEntity entity, long respawnTime){
        this.entity = entity;
        this.respawnTime = respawnTime;
    }
    
    public BasicEntity spawnEntity(Location location){
        return entity.clone(location);
    }
    
    public String getEntityName(){
        return entity.getName();
    }
    public String getEntityType(){
        return entity.getEntityType();
    }

    public long getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(long respawnTime) {
        this.respawnTime = respawnTime;
    }
}
