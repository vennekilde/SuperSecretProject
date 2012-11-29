package supersecretproject.TriggerBoxes;

import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import supersecretproject.Events.TriggerBoxEnterEvent;
import supersecretproject.Events.TriggerBoxLeaveEvent;
import supersecretproject.SSPAPI;

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

public abstract class TriggerBox implements Listener{
    private boolean triggerByEveryone = true;
    private boolean useEvents = false;
    private ArrayList<Entity> triggerEntities = new ArrayList();
    private ArrayList<Entity> isInside = new ArrayList();
    
    public TriggerBox(){
        Bukkit.getPluginManager().registerEvents(this, SSPAPI.getPlugin());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(triggerByEveryone || triggerEntities.contains(player)){
            if(isInside(player.getLocation())){
                //An entity entered the trigger box
                //make sure the event is only called once
                if(!isInside.contains(player)){
                    isInside.add(player);
                    entered(player);
                    if(useEvents){
                        TriggerBoxEnterEvent triggerBoxEnterEvent = new TriggerBoxEnterEvent(player);
                        Bukkit.getPluginManager().callEvent(triggerBoxEnterEvent);
                    }
                }
            } else {
                //An entity left the trigger box
                //make sure the event is only called once
                if(isInside.contains(player)){
                    isInside.remove(player);
                    left(player);
                    if(useEvents){
                        TriggerBoxLeaveEvent triggerBoxEnterEvent = new TriggerBoxLeaveEvent(player);
                        Bukkit.getPluginManager().callEvent(triggerBoxEnterEvent);
                    }
                }
            }
        }
    }
    public abstract boolean isInside(Location location);
    public abstract void entered(Entity entity);
    public abstract void left(Entity entity);

    public boolean isTriggerByEveryone() {
        return triggerByEveryone;
    }
    public void setTriggerByEveryone(boolean triggerByEveryone) {
        this.triggerByEveryone = triggerByEveryone;
    }

    public ArrayList<Entity> getTriggerEntities() {
        return triggerEntities;
    }
    /**
     * set which entities that will trigger the box
     * NOTE that only players are supported at this time
     * @param entities
     */
    public void setTriggerEntities(ArrayList<Entity> entities) {
        this.triggerEntities = entities;
    }
    /**
     * Add an entity to that will trigger the box
     * NOTE that only players are supported at this time
     * @param entity
     */
    public void addTriggerEntity(Entity entity){
        triggerEntities.add(entity);
    }
    
    public void removeTriggerEntity(Entity entity){
        triggerEntities.remove(entity);
    }
}
