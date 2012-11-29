package supersecretproject.Instances.HardcodedInstances.TestInstance;

import info.jeppes.ZoneCore.Users.ZoneUser;
import info.jeppes.ZoneCore.ZoneCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import supersecretproject.Instances.Instance;
import supersecretproject.Items.NamedItemStack;

public class TestInstance extends Instance{
    
    public TestInstance(){
        super("test instance");
        this.setPublicInstance(true);
    }
    
    @Override
    public void initInstanceStarter() {
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(event.getMessage().equalsIgnoreCase("testinstance")){
            this.addPlayer(ZoneCore.getUser(event.getPlayer()));
            Instance newInstance = newInstanceAndStart();
            this.removePlayer(ZoneCore.getUser(event.getPlayer()));
        }
    }

    @Override
    public void giveReward() {
        for(ZoneUser player : getPlayers()){
            player.sendMessage("Here is your reward!");
        }
        //.addLore("Used by some of the best warriors")
        NamedItemStack namedItemStack = new NamedItemStack(ChatColor.DARK_PURPLE+"The Mighty Sword",new ItemStack(Material .DIAMOND_SWORD));
        ItemStack itemStack = namedItemStack.getItemStack();
        itemStack.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        namedItemStack.setItemColor(255, 0, 0);
        namedItemStack.setLore("test This sword has been used by\nsome of the best warriors in Minecraft",ChatColor.GRAY);
        
        for(ZoneUser player : getPlayers()){
            player.getInventory().addItem(itemStack.clone());
        }
    }

    @Override
    public String getStringReward() {
        return "The Mighty Sword!";
    }
}
