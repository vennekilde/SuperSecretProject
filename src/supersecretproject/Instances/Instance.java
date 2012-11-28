package supersecretproject.Instances;

import SSP.Player;
import java.util.ArrayList;
import org.bukkit.Location;
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

public class Instance {
    private final String name;
    private boolean started = false;
    private boolean paused = false;
    private boolean ended = false;
    private ArrayList<Player> players = new ArrayList();
    private int currentStage;
    private ArrayList<Stage> stages = new ArrayList();
    private int time;
    private Reward reward = null;
    private Location startLocation;
    
    public Instance(String name){
        this.name = name;
    }
    public void start(){
        if(!started){
            if(stages.isEmpty()){
                MSG.outputErrorMessage("Instance "+getName()+" does not have any stages");
                return;
            }
            started = true;
        }
    }
    public void finish(){
        ended = true;
        reward.give();
    }
    public void nextStage(){
        if(!ended){
            getStage(currentStage).finish();
            currentStage++;
            if(getStages().size() > currentStage){
                getStage(currentStage).start();
            } else {
                finish();
            }
        }
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }
    public Stage getStage(int stage){
        return stages.get(stage);
    }
    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
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
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
        return reward;
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
}
