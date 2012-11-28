/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supersecretproject.Items;

import org.bukkit.inventory.ItemStack;
import supersecretproject.Util.NamedItemStack;

public class Item implements Cloneable{
    private NamedItemStack namedItemStack;
    public Item(ItemStack item){
        namedItemStack = new NamedItemStack(item);
    }
    public Item(NamedItemStack namedItem){
        this.namedItemStack = namedItem;
    }
    
    public NamedItemStack getNamedItemStack(){
        return namedItemStack;
    }
    public ItemStack getItemStack(){
        return namedItemStack.getItemStack();
    }
    
    @Override
    public Item clone(){
        Item newItem = new Item(namedItemStack.clone());
        return newItem;
    }
}
