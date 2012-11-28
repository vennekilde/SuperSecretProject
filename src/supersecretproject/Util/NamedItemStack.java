package supersecretproject.Util;

import java.util.ArrayList;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
 
public class NamedItemStack implements Cloneable{
 
    private CraftItemStack                    craftStack;
    private net.minecraft.server.ItemStack    itemStack;
 
    public NamedItemStack(ItemStack item) {
        if (item instanceof CraftItemStack) {
            craftStack = (CraftItemStack) item;
            itemStack = craftStack.getHandle();
        } else if (item instanceof ItemStack) {
            craftStack = new CraftItemStack(item);
            itemStack = craftStack.getHandle();
        }
        NBTTagCompound tag = craftStack.getHandle().getTag();
        if (tag == null) {
            craftStack.getHandle().setTag(new NBTTagCompound());
        }
    }
    public NamedItemStack(String name,ItemStack item) {
        this(item);
        setName(name);
    }
 
    private boolean hasDisplay() {
        return itemStack.getTag().hasKey("display");
    }
 
    private NBTTagCompound getDisplay() {
        return itemStack.getTag().getCompound("display");
    }
 
    private void addDisplay() {
        itemStack.getTag().setCompound("display", new NBTTagCompound());
    }
 
    public String getName() {
        if (hasDisplay() == false) {
            return null;
        }
        String name = getDisplay().getString("Name");
        if (name.equals("")) {
            return null;
        }
        return name;
    }
 
    public NamedItemStack setName(String name) {
        if (hasDisplay() == false) {
            this.addDisplay();
        }
        NBTTagCompound display = this.getDisplay();
        if (name == null) {
            display.remove("Name");
        }
        display.setString("Name", name);
        return this;
    }
 
    public String[] getLoreStrings(){
        NBTTagList list = getLore();
        ArrayList<String> strings = new ArrayList();
        String[] lores = new String[] {};
        for (int i = 0; i < strings.size(); i++) {
            strings.add(((NBTTagString) list.get(i)).data);
        }
        strings.toArray(lores);
        return lores;
    }
        
    public NBTTagList getLore()
    {
        if (!hasDisplay())
        {
            return new NBTTagList();
        }
        NBTTagList lore = getDisplay().getList("Lore");
        if (lore == null) 
        {
            // returns empty list
            return new NBTTagList();
        }
        return lore;
    }
 
    public NamedItemStack setLore(String lore){
        return setLore(lore,ChatColor.GRAY);
    }
    public NamedItemStack setLore(String lore,ChatColor startColor)
    {
        if(!hasDisplay())
        {
            this.addDisplay();
        }
        NBTTagCompound display = this.getDisplay();
        NBTTagList l = new NBTTagList();
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        // Add each line
        for(String line : loreLines)
        {
             l.add(new NBTTagString("", startColor + line));
        }
        // Set the lore
        display.set("Lore", l);
        return this;
    }
    
    public NamedItemStack addLore(String lore){
        return addLore(lore,ChatColor.GRAY);
    }
    public NamedItemStack addLore(String lore,ChatColor startColor){
        if(hasDisplay() == false){
            this.addDisplay();
        }
        NBTTagCompound display = new NBTTagCompound();
        NBTTagList l = display.getList("lore");
        
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        // Add each line
        for(String line : loreLines)
        {
             l.add(new NBTTagString("", startColor + line));
        }
        // Set the lore
        display.set("Lore", l);
        return this;
    }
    
    public ItemStack getItemStack(){
        return craftStack;
    }
    
    @Override
    public NamedItemStack clone(){
        return new NamedItemStack(getItemStack().clone());
    }
}

