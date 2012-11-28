package supersecretproject;

public class SSPAPI {
    public static SuperSecretProject plugin;
    public SSPAPI(SuperSecretProject plugin){
        SSPAPI.plugin = plugin;
    }
    
    public static SuperSecretProject getPlugin(){
        return plugin;
    }
}
