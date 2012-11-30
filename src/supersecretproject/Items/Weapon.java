package supersecretproject.Items;

import org.bukkit.inventory.ItemStack;
import supersecretproject.Exceptions.NoDamageSetException;

public class Weapon extends Item implements Cloneable{
    private int minDamage;
    private int maxDamage;
    public Weapon(ItemStack weapon){
        super(weapon);
        minDamage = -1;
        maxDamage = -1;
    }
    public Weapon(ItemStack weapon, int minDamage, int maxDamage){
        super(weapon);
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
    }
    public void setDamagePerHit(int damage){
        setDamagePerHit(damage,damage);
    }
    public void setDamagePerHit(int minDamage, int maxDamage){
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.setStat("minDamage", minDamage);
        this.setDisplayStat("Damage", "" + (minDamage == maxDamage ? minDamage : (minDamage + " - " + maxDamage)));
        if(minDamage != maxDamage){
            this.setStat("maxDamage", maxDamage);
        } else {
            this.removeStat("maxDamage");
        }
    }
    public int[] getDamagePerHit() throws NoDamageSetException{
        if(!isDamageSet()){
            throw new NoDamageSetException();
        }
        return new int[]{minDamage,maxDamage};
    }
    public boolean isDamageSet(){
        return(minDamage >= 0 && maxDamage >= 0);
    }
    
    @Override
    public Weapon clone(){
        Weapon weapon = new Weapon(this.getItemStack().clone());
        if(isDamageSet()){
            weapon.setDamagePerHit(minDamage,maxDamage);
        }
        return weapon;
    }
}
