package supersecretproject.Instances.HardcodedInstances.TestInstance;

import info.jeppes.ZoneCore.Users.ZoneUser;
import info.jeppes.ZoneCore.ZoneCore;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import supersecretproject.Instances.Instance;
import supersecretproject.Materials.ItemStackWrapper;
import supersecretproject.Materials.Items.Weapon;

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
        Weapon mightySword = new Weapon(new ItemStack(Material .DIAMOND_SWORD));
        mightySword.setName(ChatColor.DARK_PURPLE+"The Mighty Sword");
        mightySword.setDamagePerHit(15, 22);
//        mightySword.setItemColor(255, 0, 0);
        mightySword.setLore("This sword has been used by\nsome of the best warriors in Minecraft",ChatColor.GRAY);
        
        
        ItemStackWrapper helmet = new ItemStackWrapper(ChatColor.RED+"Vision of Hatred",new ItemStack(Material.LEATHER_HELMET));
        helmet.setItemColor(255, 0, 0);
        ItemStackWrapper armor = new ItemStackWrapper(ChatColor.RED+"Hells Flame",new ItemStack(Material.LEATHER_CHESTPLATE));
        armor.setItemColor(255, 0, 0);
        ItemStackWrapper leggings = new ItemStackWrapper(ChatColor.RED+"Nether Waders",new ItemStack(Material.LEATHER_LEGGINGS));
        leggings.setItemColor(255, 0, 0);
        ItemStackWrapper boots = new ItemStackWrapper(ChatColor.RED+"Walking Fire",new ItemStack(Material.LEATHER_BOOTS));
        boots.setItemColor(255, 0, 0);
        
        Weapon bestBowEver = new Weapon(new ItemStack(Material.BOW));
        bestBowEver.setName("Herobrines Siege Bow");
        bestBowEver.setLore("One hit kill...");
        bestBowEver.setDamagePerHit(100,200);
        
        for(ZoneUser player : getPlayers()){
            player.getInventory().addItem(mightySword.getItemStack().clone());
            player.getInventory().addItem(helmet.getItemStack().clone());
            player.getInventory().addItem(armor.getItemStack().clone());
            player.getInventory().addItem(leggings.getItemStack().clone());
            player.getInventory().addItem(boots.getItemStack().clone());
            player.getInventory().addItem(bestBowEver.getItemStack().clone());
        }
    }

    @Override
    public String getStringReward() {
        return "The Mighty Sword!";
    }
}
