package supersecretproject.Util;

import java.util.ArrayList;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
 
//Thanks to nisovin for this

public class NamedItemStack{
    private CraftItemStack                    craftStack;
    private net.minecraft.server.ItemStack    itemStack;
 
    public NamedItemStack(ItemStack item) {
        if (item instanceof CraftItemStack) {
            craftStack = (CraftItemStack) item;
            itemStack = craftStack.getHandle();
        }
        else if (item instanceof ItemStack) {
            craftStack = new CraftItemStack(item);
            itemStack = craftStack.getHandle();
        }
        NBTTagCompound tag = itemStack.tag;
        if (tag == null) {
            tag = new NBTTagCompound();
            tag.setCompound("display", new NBTTagCompound());
            itemStack.tag = tag;
        }
    }
 
    public NamedItemStack setName(String name) {
        NBTTagCompound tag = itemStack.tag.getCompound("display");
        tag.setString("Name", name);
        itemStack.tag.setCompound("display", tag);
        return this;
    }
 
    public String getName() {
        NBTTagCompound tag = itemStack.tag.getCompound("display");
        return tag.getString("Name");
    }
 
    public NamedItemStack addLore(String lore) {
        NBTTagCompound tag = itemStack.tag.getCompound("display");
        NBTTagList list = tag.getList("Lore");
        if (list == null) {
            list = new NBTTagList();
        }
        list.add(new NBTTagString(lore));
        tag.set("Lore", list);
        itemStack.tag.setCompound("display", tag);
        return this;
    }
 
    public NamedItemStack setLore(String... lore) {
        NBTTagCompound tag = itemStack.tag.getCompound("display");
        NBTTagList list = new NBTTagList();
        for (String l : lore) {
            list.add(new NBTTagString(l));
        }
        tag.set("Lore", list);
        itemStack.tag.setCompound("display", tag);
        return this;
    }
 
    public String[] getLore() {
        NBTTagCompound tag = itemStack.tag;
        NBTTagList list = tag.getCompound("display").getList("Lore");
        ArrayList<String> strings = new ArrayList();
        String[] lores = new String[] {};
        for (int i = 0; i < strings.size(); i++) {
            strings.add(((NBTTagString) list.get(i)).data);
        }
        strings.toArray(lores);
        return lores;
    }
 
    public ItemStack getItemStack() {
        return craftStack;
    }
}
