package supersecretproject.Entities;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Location;
import org.bukkit.entity.Creature;

public abstract class BasicEntity implements Cloneable{
    private String name;
    private String type;
    private Creature baseEntity;
    
    public BasicEntity(String name, String type, Creature baseEntity){
        this.name = name;
        this.type = type;
        this.baseEntity = baseEntity;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEntityType(){
        return type;
    }
    public void setEntityType(String type){
        this.type = type;
    }
    
    public Creature getBaseEntity() {
        return baseEntity;
    }
    
    @Override
    public BasicEntity clone(){
        return clone(getBaseEntity().getLocation());
    }
    public BasicEntity clone(Location location){
        try {
            return this.getClass().getConstructor(Location.class).newInstance(location);
        } catch (Exception ex) {
            return null;
        }
    }
}
