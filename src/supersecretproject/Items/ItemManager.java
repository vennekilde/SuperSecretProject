package supersecretproject.Items;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.entity.CraftArrow;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import supersecretproject.Exceptions.StatNotfoundExcpetion;
import supersecretproject.SSPAPI;
import supersecretproject.Util.NBTTagUtil;

public class ItemManager implements Listener{
    
    private Random random = new Random();
    
    public ItemManager(){
        Bukkit.getPluginManager().registerEvents(this, SSPAPI.getPlugin());
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamage(EntityDamageEvent event) {
        if(event instanceof EntityDamageByEntityEvent){
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent)event;
            System.out.println("Damage: "+event.getDamage());
            org.bukkit.entity.Entity damager = e.getDamager();
            if(damager instanceof CraftArrow){
                return;
            }
            switch(e.getCause()){
                default:
                    if(damager instanceof HumanEntity){
                        HumanEntity human = (HumanEntity)damager;
                        ItemStack weaponUse = human.getInventory().getItemInHand();
                        double damage = 0;
                        double minDamage;
                        try {
                            minDamage = NBTTagUtil.getStat(weaponUse, "minDamage");
                            try {
                                double maxDamge = NBTTagUtil.getStat(weaponUse, "maxDamage");
                                damage = minDamage + (maxDamge - minDamage) * random.nextDouble();
                            } catch (StatNotfoundExcpetion ex) {
                                damage = minDamage;
                            }
                            event.setDamage((int)damage);
                            break;
                        } catch (StatNotfoundExcpetion ex) {}
                    }
                    net.minecraft.server.ItemStack[] equipment = ((CraftEntity)damager).getHandle().getEquipment();
                    System.out.println(equipment.length);
//                    ItemStack weapon = ((CraftEntity)damager).getHandle().getEquipment();
                    
                    if(true){
                        break;
                    }
                    
                    ItemStack weapon = null;
                    double damage = 0;
                    double minDamage;
                    try {
                        minDamage = NBTTagUtil.getStat(weapon, "minDamage");
                    
                        try {
                            double maxDamge = NBTTagUtil.getStat(weapon, "maxDamage");
                            damage = minDamage + (maxDamge - minDamage) * random.nextDouble();
                        } catch (StatNotfoundExcpetion ex) {
                            damage = minDamage;
                        }
                    } catch (StatNotfoundExcpetion ex) {
                    }
                    event.setDamage((int)damage);
                    break;
            }
            System.out.println(e.getCause());
        }
    }
    
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityShootBow(EntityShootBowEvent event){
        ItemStack bow = event.getBow();
        try {
            double damage = 0;
            double minDamage = NBTTagUtil.getStat(bow, "minDamage");
            try {
                double maxDamge = NBTTagUtil.getStat(bow, "maxDamage");
                damage = minDamage + (maxDamge - minDamage) * random.nextDouble();
            } catch (StatNotfoundExcpetion ex) {
                damage = minDamage;
            }
            //read https://github.com/Bukkit/CraftBukkit/blob/master/src/main/java/net/minecraft/server/ItemBow.java
            //to get the newest method names
            ((CraftArrow)event.getProjectile()).getHandle().b(damage);
        } catch (StatNotfoundExcpetion ex) {}
    }
}
