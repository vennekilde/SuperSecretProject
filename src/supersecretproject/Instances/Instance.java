package supersecretproject.Instances;

import info.jeppes.ZoneCore.Users.ZoneUser;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import supersecretproject.Instances.HardcodedInstances.TestInstance.TestInstance;
import supersecretproject.SSPAPI;
import supersecretproject.Util.MSG;

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

public abstract class Instance extends InstanceStageLoader implements Reward{
    private String name;
    private boolean started = false;
    private boolean paused = false;
    private boolean ended = false;
    private ArrayList<ZoneUser> players = new ArrayList();
    private int currentStage;
    private int time;
    private Reward reward = null;
    private Location startLocation;
    private boolean publicInstance = false;
    
    public Instance(String name){
        this.name = name;
    }
    public Instance(Instance superInstance){
        copyInstance(superInstance);
    }
    
    private void copyInstance(Instance superInstance){
        this.name = superInstance.name;
        this.started = superInstance.started;
        this.paused = superInstance.paused;
        this.ended = superInstance.ended;
        this.players = (ArrayList<ZoneUser>) superInstance.players.clone();
        this.currentStage = superInstance.currentStage;
        this.setStages((ArrayList<Stage>) superInstance.getStages().clone());
        this.time = superInstance.time;
        this.reward = superInstance.reward;
        if(startLocation != null){
            this.startLocation = superInstance.startLocation.clone();
        }
        this.publicInstance = superInstance.publicInstance;
    }
    
    @Override
    public Instance newInstance(){
        Instance newInstance = null;
        try {
            newInstance = this.getClass().newInstance();
            newInstance.copyInstance(this);
            newInstance.currentStage = 0;
            newInstance.paused = false;
            newInstance.started = false;
            newInstance.ended = false;
            
        } catch (Exception ex) {
            Logger.getLogger(Instance.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newInstance;
    }
    @Override
    public Instance newInstanceAndStart(){
        Instance newInstance = newInstance();
        InstanceManager.addActiveInstance(newInstance);
        newInstance.start();
        return newInstance;
    }
    public void start(){
        if(!started){
            if(getStages().isEmpty()){
                MSG.outputErrorMessage("Instance "+getName()+" does not have any stages");
                return;
            }
            started = true;
            InstanceManager.addActiveInstance(this);
            getStages().get(getCurrentStage()).start(this);
        }
    }
    public void finish(){
        ended = true;
        getReward().giveReward();
        InstanceManager.removeActiveInstance(this);
        HandlerList.unregisterAll(this);
    }
    public void nextStage(){
        if(!ended){
            Stage lastStage = getStage(currentStage);
            if(!lastStage.isEnded()){
                lastStage.internalFinish();
            }
            currentStage++;
            if(getStages().size() > currentStage){
                getStage(currentStage).start();
            } else {
                finish();
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean isStarted() {
        return started;
    }
    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isPaused() {
        return paused;
    }
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isEnded() {
        return ended;
    }

    public ArrayList<ZoneUser> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<ZoneUser> players) {
        this.players = players;
    }
    public void addPlayer(ZoneUser player){
        players.add(player);
    }
    public void removePlayer(ZoneUser player){
        players.remove(player);
    }

    public int getCurrentStage() {
        return currentStage;
    }
    public void setCurrentStage(int currentStage) {
        this.currentStage = currentStage;
    }

    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }

    public Reward getReward() {
        return reward == null ? this : reward;
    }
    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public Location getStartLocation() {
        return startLocation;
    }
    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public boolean isPublicInstance() {
        return publicInstance;
    }
    public void setPublicInstance(boolean publicInstance) {
        this.publicInstance = publicInstance;
    }
}
