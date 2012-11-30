package supersecretproject.Items;

import org.bukkit.inventory.ItemStack;
import supersecretproject.Exceptions.NoDefenceSetException;

public class Armor extends Item implements Cloneable{
    private int defence;
    public Armor(ItemStack armor){
        super(armor);
        defence = -1;
    }
    public Armor(ItemStack armor, int defence){
        super(armor);
        this.defence = defence;
    }
    public void setDefence(int defence){
        this.defence = defence;
        this.setStat("Defence", defence);
        this.setDisplayStat("Defence", ""+defence);
    }
    public int getDefence() throws NoDefenceSetException{
        if(isDefenceSet()){
            throw new NoDefenceSetException();
        }
        return defence;
    }
    public boolean isDefenceSet(){
        return defence > 0;
    }
    
    @Override
    public Armor clone(){
        Armor armor = new Armor(this.getItemStack());
        if(isDefenceSet()){
            armor.setDefence(defence);
        }
        return armor;
    }
}
