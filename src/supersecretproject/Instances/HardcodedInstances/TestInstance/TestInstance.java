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
        NamedItemStack mightySword = new NamedItemStack(ChatColor.DARK_PURPLE+"The Mighty Sword",new ItemStack(Material .DIAMOND_SWORD));
        ItemStack itemStack = mightySword.getItemStack();
        itemStack.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        mightySword.setItemColor(255, 0, 0);
        mightySword.setLore("test This sword has been used by\nsome of the best warriors in Minecraft",ChatColor.GRAY);
        
        
        NamedItemStack helmet = new NamedItemStack(ChatColor.RED+"Vision of Hatred",new ItemStack(Material.LEATHER_HELMET));
        helmet.setItemColor(255, 0, 0);
        NamedItemStack armor = new NamedItemStack(ChatColor.RED+"Hells Flame",new ItemStack(Material.LEATHER_CHESTPLATE));
        armor.setItemColor(255, 0, 0);
        NamedItemStack leggings = new NamedItemStack(ChatColor.RED+"Nether Waders",new ItemStack(Material.LEATHER_LEGGINGS));
        leggings.setItemColor(255, 0, 0);
        NamedItemStack boots = new NamedItemStack(ChatColor.RED+"Walking Fire",new ItemStack(Material.LEATHER_BOOTS));
        boots.setItemColor(255, 0, 0);
        
        for(ZoneUser player : getPlayers()){
            player.getInventory().addItem(itemStack.clone());
            player.getInventory().addItem(helmet.getItemStack().clone());
            player.getInventory().addItem(armor.getItemStack().clone());
            player.getInventory().addItem(leggings.getItemStack().clone());
            player.getInventory().addItem(boots.getItemStack().clone());
        }
    }

    @Override
    public String getStringReward() {
        return "The Mighty Sword!";
    }
}
