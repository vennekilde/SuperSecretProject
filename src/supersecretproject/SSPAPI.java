package supersecretproject;

public class SSPAPI {
    private static SuperSecretProject plugin;
    public SSPAPI(SuperSecretProject plugin){
        SSPAPI.plugin = plugin;
    }
    
    public static SuperSecretProject getPlugin(){
        return plugin;
    }
}
