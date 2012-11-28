package supersecretproject.Instances;

import java.util.ArrayList;

public class InstanceManager {
    private static ArrayList<Instance> instances = new ArrayList();
    private static ArrayList<Instance> activeInstances = new ArrayList();

    public static ArrayList<Instance> getInstances() {
        return instances;
    }
    public static void setInstances(ArrayList<Instance> instances) {
        InstanceManager.instances = instances;
        for(Instance instance : instances){
            instance.initInstanceStarterInternal();
        }
    }
    public static void addInstance(Instance instance){
        instances.add(instance);
        instance.initInstanceStarterInternal();
    }
    public static void removeInstance(Instance instance){
        instances.remove(instance);
    }
    
    public static ArrayList<Instance> getActiveInstances() {
        return activeInstances;
    }
    public static void addActiveInstance(Instance instance){
        activeInstances.add(instance);
    }
    public static void removeActiveInstance(Instance instance){
        activeInstances.remove(instance);
    }
}
