package supersecretproject;

import info.jeppes.ZoneCore.ZonePlugin;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supersecretproject.Instances.Instance;
import supersecretproject.Instances.InstanceManager;
import supersecretproject.Quests.Quest;
import supersecretproject.Skills.Magic.Skill;

public class Loader {
    public static String instancesClassDirectory = Instance.class.getPackage().getName()+".HardcodedInstances";
    public static String questsClassDirectory = Quest.class.getPackage().getName()+".HardcodedQuests";
    public static String skillsClassDirectory = Skill.class.getPackage().getName()+".HardcodeSkills";
    
    public void load(){
        loadSkills();
        loadInstances();
        loadQuests();
    }
    public void loadInstances(){
        //Load isntances from directory
        String instancesDirectory = SSPAPI.getPlugin().getZoneCore().getPluginDirectory()+File.separator+"instances";
        ArrayList<Object> javaObjectsFromDirectory = getJavaObjectsFromDirectory(instancesDirectory);
        
        ArrayList<Instance> instances = new ArrayList();
        for(Object obj : javaObjectsFromDirectory){
            if(obj instanceof Instance){
                Instance instance = (Instance)obj;
                instance.loadStagesFromClassFiles(instancesDirectory);
                instances.add(instance);
            }
        }
        
        //Load hardcoded instances
        Class[] classesInPackage = null;
        classesInPackage = SSPAPI.getPlugin().getClassesInPackage(instancesClassDirectory,null);
        for(Class commandClass : classesInPackage){
            try {
                Object obj = commandClass.newInstance();
                if(obj instanceof Instance){
                    Instance instance = (Instance)obj;
                    instance.loadStagesFromPackage();
                    instances.add(instance);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ZonePlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        InstanceManager.setInstances(instances);
    }
    
    
    ////////////////////////////////////
    //NEED MORE CODE FOR QUEST MANAGER//
    ////////////////////////////////////
    public void loadQuests(){
        //Load quests from directory
        String instancesDirectory = SSPAPI.getPlugin().getZoneCore().getPluginDirectory()+File.separator+"quests";
        ArrayList<Object> javaObjectsFromDirectory = getJavaObjectsFromDirectory(instancesDirectory);
        
        ArrayList<Instance> quests = new ArrayList();
        for(Object obj : javaObjectsFromDirectory){
            if(obj instanceof Quest){
                quests.add((Instance)obj);
            }
        }
        
        //Load hardcoded quests
        Class[] classesInPackage = null;
        classesInPackage = SSPAPI.getPlugin().getClassesInPackage(questsClassDirectory,null);
        for(Class commandClass : classesInPackage){
            try {
                Object obj = commandClass.newInstance();
                if(obj instanceof Quest){
                    quests.add((Instance) obj);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ZonePlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    /////////////////////////////////////
    //NEED MORE CODE FOR SKILLS MANAGER//
    /////////////////////////////////////
    public void loadSkills(){
        //Load quests from directory
        String instancesDirectory = SSPAPI.getPlugin().getZoneCore().getPluginDirectory()+File.separator+"skills";
        ArrayList<Object> javaObjectsFromDirectory = getJavaObjectsFromDirectory(instancesDirectory);
        
        ArrayList<Instance> skills = new ArrayList();
        for(Object obj : javaObjectsFromDirectory){
            if(obj instanceof Quest){
                skills.add((Instance)obj);
            }
        }
        
        //Load hardcoded quests
        Class[] classesInPackage = null;
        classesInPackage = SSPAPI.getPlugin().getClassesInPackage(skillsClassDirectory,null);
        for(Class commandClass : classesInPackage){
            try {
                Object obj = commandClass.newInstance();
                if(obj instanceof Quest){
                    skills.add((Instance) obj);
                }
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(ZonePlugin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static ArrayList<Object> getJavaObjectsFromDirectory(String directoryPath){
        ArrayList<Object> objectsInDirectory = new ArrayList();
        File directory = new File(directoryPath);
        if(directory != null){
            if(directory.isDirectory()){
                ArrayList<String> commandClassFiles = getFilesFromDirectoryAndSubDirectories(directory,new ArrayList<String>(),"");
        
                ClassLoader loader = null;
                try {
                    URL url = directory.toURI().toURL(); 
                    URL[] urls = new URL[]{url};

                    loader = new URLClassLoader(urls);

                } catch (MalformedURLException ex) {
                    Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                }
                for(String classPackageAndName : commandClassFiles){
                    try {
                        Class commandClass = loader.loadClass(classPackageAndName);
                        objectsInDirectory.add(commandClass.newInstance());
                    } catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                        Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        return objectsInDirectory;
    }
    public static ArrayList<String> getFilesFromDirectoryAndSubDirectories(File aFile, ArrayList<String> files, String startPath) {
        if(aFile.isFile()){
            String fileName = aFile.getName();
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
            files.add(startPath + "." + fileName);
        } else if (aFile.isDirectory()) {
            File[] listOfFiles = aFile.listFiles();
            if(listOfFiles!=null) {
                for (File file : listOfFiles) {
                    files = getFilesFromDirectoryAndSubDirectories(file,files,startPath + "." + file.getName());
                }
            } else {
                Logger.getLogger(ZonePlugin.class.getName()).log(Level.SEVERE, null, "FILE ACCESS DENIED");
            }
        }
        return files;
    }
}
