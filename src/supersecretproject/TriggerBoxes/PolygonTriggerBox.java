package supersecretproject.TriggerBoxes;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.World;

public abstract class PolygonTriggerBox extends TriggerBox{

    private ArrayList<Point2D> polygon;
    private double minY;
    private double maxY;
    
    public PolygonTriggerBox(ArrayList<Location> polygon) throws Exception{
        super(polygon.get(0).getWorld());
        for(Location location : polygon){
            if(!location.getWorld().equals(getWorld())){
                throw new Exception("Some locations are not in the same world");
            } else{
                this.polygon.add(new PrecisePoint(location.getX(), location.getBlockZ()));
                minY = Math.min(minY, location.getY());
                maxY = Math.max(maxY, location.getY());
            }
        }
    }
    public PolygonTriggerBox(ArrayList<Location> polygon, double minY, double maxY) throws Exception{
        super(polygon.get(0).getWorld());
        if(minY > maxY){
            double tempMinY = minY;
            this.minY = maxY;
            this.maxY = tempMinY;
        } else {
            this.minY = minY;
            this.maxY = maxY;
        }
        for(Location location : polygon){
            if(!location.getWorld().equals(getWorld())){
                throw new Exception("Some locations are not in the same world");
            } else{
                this.polygon.add(new PrecisePoint(location.getX(), location.getBlockZ()));
            }
        }
    }
    public PolygonTriggerBox(ArrayList<Point2D> polygon, World world, double minY, double maxY) throws Exception{
        super(world);
        this.polygon = polygon;
        if(minY > maxY){
            double tempMinY = minY;
            this.minY = maxY;
            this.maxY = tempMinY;
        } else {
            this.minY = minY;
            this.maxY = maxY;
        }
    }
    
    @Override
    public boolean isInside(Location location) {
        //fast and easy check to see if the location is inside the box
        if(location.getY() < minY || maxY < location.getY() || location.getWorld().equals(getWorld())){
            return false;
        }
        int i;
        int j;
        boolean result = false;
        for (i = 0, j = polygon.size() - 1; i < polygon.size(); j = i++) {
            if ((polygon.get(i).getY() > location.getBlockZ()) != (polygon.get(j).getY() > location.getBlockZ()) &&
                (location.getBlockX() < (polygon.get(j).getX() - polygon.get(i).getX()) * (location.getBlockZ() - polygon.get(i).getY()) / (polygon.get(j).getY()-polygon.get(i).getY()) + polygon.get(i).getX())) {
                result = !result;
            }
        }
        return result;
    }

}
