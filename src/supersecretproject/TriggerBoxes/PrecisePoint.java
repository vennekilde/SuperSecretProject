package supersecretproject.TriggerBoxes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

public class PrecisePoint extends Point2D{
    public double x;
    public double y;
    
    public PrecisePoint(){
        this.setLocation(0,0);
    }
    public PrecisePoint(double x, double y){
        this.setLocation(x, y);
    }
    
    public final void setX(double x) {
        this.x = x;
    }

    public final void setY(double y) {
        this.y = y;
    }
    @Override
    public final double getX() {
        return x;
    }

    @Override
    public final double getY() {
        return y;
    }

    @Override
    public final void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double angleFrom(PrecisePoint location){
        return Math.atan2(y - location.getY(), location.getX() - x);
    }
    public double angleFrom(Point location){
        return Math.atan2(y - location.getY(), location.getX() - x);
    }
    
    public final Dimension toDimension(){
        return new Dimension((int)x,(int)y);
    }
    
    public final Point toPoint(){
        Point simpleLocation = new Point((int)x,(int)y);
        return simpleLocation;
    }

    @Override
    public String toString(){
        return this.getClass().getName()+"[x="+x+",y="+y+"]";
    }
}
