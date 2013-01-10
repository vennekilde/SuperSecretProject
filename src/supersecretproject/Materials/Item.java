package supersecretproject.Materials;

import org.bukkit.inventory.ItemStack;

public class Item extends ItemStackWrapper{
    public Item(ItemStack item){
        super(item);
    }
    
    @Override
    public Item clone(){
        return new Item(this.getItemStack().clone());
    }
}
