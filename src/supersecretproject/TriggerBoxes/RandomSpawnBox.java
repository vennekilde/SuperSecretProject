package supersecretproject.TriggerBoxes;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;
import supersecretproject.Entities.BasicEntity;
import supersecretproject.Entities.EntitySpawner;
import supersecretproject.SSPAPI;

public abstract class RandomSpawnBox extends PolygonTriggerBox{
    private ArrayList<Integer> schedualTaskIds = new ArrayList();
    private HashMap<EntitySpawner, Double> entitySpawners = new HashMap();
    private HashMap<Entity, Location> spawnedEntitiesLocations = new HashMap();
    private HashMap<Entity, EntitySpawner> spawnedEntitiesSpawner = new HashMap();
    private HashMap<EntitySpawner, Long> deathEntities = new HashMap();
    
    public RandomSpawnBox(ArrayList<Location> polygon) throws Exception {
        super(polygon);
        addTimer();
    }

    public RandomSpawnBox(ArrayList<Location> polygon, double minY, double maxY) throws Exception {
        super(polygon, minY, maxY);
        addTimer();
    }

    public RandomSpawnBox(ArrayList<Point2D> polygon, World world, double minY, double maxY) throws Exception {
        super(polygon, world, minY, maxY);
        addTimer();
    }
    
    private void addTimer(){
        schedualTaskIds.add(Bukkit.getScheduler().scheduleAsyncRepeatingTask(SSPAPI.getPlugin(), new Runnable(){
            @Override
            public void run() {
                for(Entity entity : spawnedEntitiesLocations.keySet()){
                    if(!isInside(entity.getLocation())){
                        entity.teleport(spawnedEntitiesLocations.get(entity));
                    }
                }
            }
        }, 5000, 5000));
    }
    
    @Override
    public void delete() {
        super.delete();
        for(int id : schedualTaskIds){
            Bukkit.getScheduler().cancelTask(id);
        }
    }
    
    private void spawnEntities(){
        for(EntitySpawner entitySpawner : entitySpawners.keySet()){
            spawnEntities(entitySpawner,entitySpawners.get(entitySpawner));
        }
    }
    private void spawnEntities(EntitySpawner entitySpawner, double fillRate){
        int n = (int) (getArea() * fillRate);
        for(int i = 0; i < n; i++){
            Location randomLocation = getRandomLocationInsideBoxOnGround();
            entitySpawner.spawnEntity(randomLocation);
        }
    }
    
    public void checkSpawn(){
        long currentTimeMillis = System.currentTimeMillis();
        for(EntitySpawner entitySpawner : deathEntities.keySet()){
            Long timeSinceDeath = deathEntities.get(entitySpawner);
            if(currentTimeMillis - timeSinceDeath >= entitySpawner.getRespawnTime()){
                Location randomLocationInsideBoxOnGround = getRandomLocationInsideBoxOnGround();
                BasicEntity spawnedEntity = entitySpawner.spawnEntity(getRandomLocationInsideBoxOnGround());
                spawnedEntitiesLocations.put(spawnedEntity.getBaseEntity(), randomLocationInsideBoxOnGround);
                spawnedEntitiesSpawner.put(spawnedEntity.getBaseEntity(), entitySpawner);
                deathEntities.remove(entitySpawner);
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event){
        for(Entity entity : getSpawnedEntities()){
            if(event.getEntity().getEntityId() == entity.getEntityId()){
                deathEntities.put(spawnedEntitiesSpawner.get(entity), System.currentTimeMillis());
                spawnedEntitiesLocations.remove(entity);
                spawnedEntitiesSpawner.remove(entity);
            }
        }
    }
    
    public void addEntitySpawner(EntitySpawner entitySpawner, double fillRate){
        entitySpawners.put(entitySpawner,fillRate);
        spawnEntities(entitySpawner,fillRate);
    }
    public void removeEntitySpawner(EntitySpawner entitySpawner){
        entitySpawners.remove(entitySpawner);
    }
    public HashMap<EntitySpawner,Double> getEntitySpawners() {
        return entitySpawners;
    }

    public Set<Entity> getSpawnedEntities() {
        return spawnedEntitiesLocations.keySet();
    }
}
