package supersecretproject.TriggerBoxes;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import org.bukkit.Location;
import org.bukkit.World;

/*
 * Author: Jeppe Boysen Vennekilde
 *
 * This document is Copyright ©() and is the intellectual property of the author.
 *
 * TERMS AND CONDITIONS
 * 0. USED TERMS
 * OWNER - The original author(s) of the program
 * USER - End user of the program, person installing/using the program.
 *
 * 1. LIABILITY
 * THIS PROGRAM IS PROVIDED 'AS IS' WITH NO WARRANTIES, IMPLIED OR OTHERWISE.
 * THE OWNER OF THIS PROGRAM TAKES NO RESPONSIBILITY FOR ANY DAMAGES INCURRED
 * FROM THE USE OF THIS PROGRAM.
 *
 * 2. REDISTRIBUTION
 * This program may only be distributed where uploaded, mirrored, or otherwise
 * linked to by the OWNER solely. All mirrors of this program must have advance
 * written permission from the OWNER. ANY attempts to make money off of this
 * program (selling, selling modified versions, adfly, sharecash, etc.) are
 * STRICTLY FORBIDDEN, and the OWNER may claim damages or take other action to
 * rectify the situation.
 *
 * 3. DERIVATIVE WORKS/MODIFICATION
 * This program is provided freely and may be decompiled and modified for
 * private use, either with a decompiler or a bytecode editor. Public
 * distribution of modified versions of this program require advance written
 * permission of the OWNER and may be subject to certain terms.
 */

public abstract class PolygonTriggerBox extends TriggerBox{

    private ArrayList<Point2D> polygon;
    private World world;
    private double minY;
    private double maxY;
    
    public PolygonTriggerBox(ArrayList<Location> polygon) throws Exception{
        world = polygon.get(0).getWorld();
        for(Location location : polygon){
            if(!location.getWorld().equals(world)){
                throw new Exception("Some locations are not in the same world");
            } else{
                this.polygon.add(new PrecisePoint(location.getX(), location.getBlockZ()));
                minY = Math.min(minY, location.getY());
                maxY = Math.max(maxY, location.getY());
            }
        }
    }
    public PolygonTriggerBox(ArrayList<Location> polygon, double minY, double maxY) throws Exception{
        if(minY > maxY){
            double tempMinY = minY;
            this.minY = maxY;
            this.maxY = tempMinY;
        } else {
            this.minY = minY;
            this.maxY = maxY;
        }
        world = polygon.get(0).getWorld();
        for(Location location : polygon){
            if(!location.getWorld().equals(world)){
                throw new Exception("Some locations are not in the same world");
            } else{
                this.polygon.add(new PrecisePoint(location.getX(), location.getBlockZ()));
            }
        }
    }
    public PolygonTriggerBox(ArrayList<Point2D> polygon, World world, double minY, double maxY) throws Exception{
        this.polygon = polygon;
        this.world = world;
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
        if(location.getY() < minY || maxY < location.getY()){
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
