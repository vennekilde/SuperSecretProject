package supersecretproject;

import info.jeppes.ZoneCore.ZoneConfig;
import info.jeppes.ZoneCore.ZonePlugin;
import supersecretproject.Items.ItemManager;
import supersecretproject.Util.MSG;

public class SuperSecretProject extends ZonePlugin{
    private Loader loader;
    private ItemManager itemManager = null;
    private MSG msg = null;

    @Override
    public String[] preLoadConfig() {
        return null;
    }

    @Override
    public void loadDefaultConfig(ZoneConfig config) {
    }

    @Override
    public void loadConfig(ZoneConfig config) {
    }

    @Override
    public SSPAPI initAPI() {
        SSPAPI API = new SSPAPI(this);
        return API;
    }

    @Override
    public SSPAPI getAPI() {
        return (SSPAPI)this.getObjectAPI();
    }

    @Override
    public String[] initCommandAliases() {
        return null;
    }

    @Override
    public String initCommandPackageDirectory() {
        return null;
    }
    
    @Override
    public void onEnable(){
        super.onEnable();
        msg = new MSG();
        itemManager = new ItemManager();
        
        loader = new Loader();
        loader.load();
    }
}
