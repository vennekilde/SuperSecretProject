package supersecretproject.TriggerBoxes;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;

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
