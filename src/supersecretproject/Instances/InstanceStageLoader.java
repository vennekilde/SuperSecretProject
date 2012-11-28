package supersecretproject.Instances;

import info.jeppes.ZoneCore.ZonePlugin;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supersecretproject.Loader;
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

public abstract class InstanceStageLoader extends InstanceStarter{
    private enum LoadStageProcess{
        FILE,
        PACKAGE
    }
    private ArrayList<Stage> stages = new ArrayList();
    private boolean stagesLoaded = false;
    
    //next 2 variables are used to reload without specifying which method to use
    private LoadStageProcess loadProcessUsed = null;
    private String directory = null;
    
    
    public ArrayList<Stage> getStages() {
        return stages;
    }
    public Stage getStage(int stage){
        return stages.get(stage);
    }
    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }
    
    public boolean isStagesLoaded() {
        return stagesLoaded;
    }
    
    public void reloadStages(){
        if(stagesLoaded){
            switch(loadProcessUsed){
                case FILE:
                    loadStagesFromClassFiles(directory);
                    break;
                case PACKAGE:
                    loadStagesFromPackage();
                    break;
            }
        }
    }
    
    public void loadStagesFromClassFiles(String directory){
        if(stagesLoaded){
            return;
        }
        stages.clear();
        ArrayList<Object> javaObjectsFromDirectory = Loader.getJavaObjectsFromDirectory(directory);
        
        for(Object obj : javaObjectsFromDirectory){
            if(obj instanceof Stage){
                stages.add((Stage)obj);
            }
        }
        this.directory = directory;
        stagesLoaded = true;
        loadProcessUsed = LoadStageProcess.FILE;
    }
    
    public void loadStagesFromPackage(){
        loadStagesFromPackage(getClass().getPackage().getName()+".Stages");
    }
    public void loadStagesFromPackage(String packageName){
        if(stagesLoaded){
            return;
        }
        stages.clear();
        Class[] classesInPackage = SSPAPI.getPlugin().getClassesInPackage(packageName,null);
        for(Class commandClass : classesInPackage){
            try {
                Object obj = commandClass.newInstance();
                if(obj instanceof Stage){
                    stages.add((Stage) obj);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ZonePlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        stagesLoaded = true;
        loadProcessUsed = LoadStageProcess.PACKAGE;
    }
}
