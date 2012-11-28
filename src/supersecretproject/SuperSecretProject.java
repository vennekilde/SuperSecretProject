package supersecretproject;

import info.jeppes.ZoneCore.ZoneConfig;
import info.jeppes.ZoneCore.ZonePlugin;
import supersecretproject.Util.MSG;

public class SuperSecretProject extends ZonePlugin{
    private Loader loader;

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
        MSG msg = new MSG(); //init
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
        loader = new Loader();
        loader.load();
    }
}
