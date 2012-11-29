package supersecretproject.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagInt;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
 
public class NamedItemStack implements Cloneable{
 
    private CraftItemStack                      craftStack;
    private net.minecraft.server.ItemStack      itemStack;
    
    private HashMap<String,String>              itemStats;
    private ArrayList<String>                   itemLore;
    private ChatColor                           itemStatsStartColor;
    private ChatColor                           itemLoreStartColor;
    
    
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
        
        itemStats = new HashMap();
        itemLore = new ArrayList();
        itemStatsStartColor = ChatColor.GOLD;
        itemLoreStartColor = ChatColor.GRAY;
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
    public void setStat(String stat, String statValue){
        itemStats.put(stat, statValue);
        updateLoreAndStats();
    }
    public void removeStat(String stat){
        itemStats.remove(stat);
        updateLoreAndStats();
    }
    public HashMap<String,String> getStats(){
        return itemStats;
    }
    public String getStat(String statName){
        return itemStats.get(statName);
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
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        itemLore = (ArrayList<String>) Arrays.asList(loreLines);
        updateLoreAndStats();
        return this;
    }
    public NamedItemStack setLore(String lore,ChatColor startColor)
    {
        StringBuilder newLore = new StringBuilder();
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        // Add each line
        for(String loreLine : loreLines)
        {
             newLore.append("\n").append(startColor).append(loreLine);
        }
        
        return setLore(newLore.toString());
    }
    
    public NamedItemStack addLore(String lore){
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        itemLore.addAll(Arrays.asList(loreLines));
        updateLoreAndStats();
        return this;
    }
    public NamedItemStack addLore(String lore,ChatColor startColor){
        StringBuilder newLore = new StringBuilder();
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        // Add each line
        for(String loreLine : loreLines)
        {
             newLore.append("\n").append(startColor).append(loreLine);
        }
        
        return addLore(newLore.toString());
    }
    public NamedItemStack addLoreAtLine(String lore, int line){
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        // Add each line
        for(String loreLine : loreLines)
        {
             itemLore.add(line, loreLine);
             line++;
        }
        updateLoreAndStats();
        return this;
    }
    public NamedItemStack addLoreAtLine(String lore, int line, ChatColor startColor){
        StringBuilder newLore = new StringBuilder();
        // Added multiline lore support
        // Splits the lines
        String[] loreLines = lore.split("\n");
        // Add each line
        for(String loreLine : loreLines)
        {
             newLore.append("\n").append(startColor).append(loreLine);
        }
        
        return addLoreAtLine(newLore.toString(),line);
    }
    
    public int getItemColor(){
        if (!hasDisplay())
        {
            return -1;
        }
        if (!getDisplay().hasKey("color")) 
        {
            // returns empty list
            return -1;
        }
        int colorID = getDisplay().getInt("color");
        return colorID;
    }
    public int[] getItemColorRGB(){
        int itemColor = getItemColor();
        if (itemColor < 0) 
        {
            // returns empty list
            return new int[]{-1,-1,-1};
        }
        int colorID = getDisplay().getInt("color");
        
        int red = colorID >> 16;
        int green = (colorID - (red << 16) >> 8);
        int blue = colorID - (red << 16) - (green << 8);
        
        return new int[]{red,green,blue};
    }
    
    public NamedItemStack setItemColor(int red, int green, int blue){    
        if (!hasDisplay())
        {
            this.addDisplay();
        }
        
        NBTTagCompound display = this.getDisplay();
        if(red < 0 || green < 0 || blue < 0){
            display.remove("color");
            return this;
        }
        
        red = red > 255 ? 255 : red;
        green = green > 255 ? 255 : green;
        blue = blue > 255 ? 255 : blue;
        
        int colorId = (red << 16) + (green << 8) + blue;
        NBTTagInt colorTag = new NBTTagInt("",colorId);
        display.set("color", colorTag);
        return this;
    }
    
    private void updateLoreAndStats(){
        if(!hasDisplay())
        {
            this.addDisplay();
        }
        NBTTagCompound display = this.getDisplay();
        NBTTagList l = new NBTTagList();
        // Add each stat line
        for(String statName : itemStats.keySet())
        {
             l.add(new NBTTagString("", itemStatsStartColor + statName + ": " + itemStats.get(statName)));
        }
        // Add each lore line
        for(String loreLine : itemLore)
        {
             l.add(new NBTTagString("", ChatColor.ITALIC.toString() + itemLoreStartColor.toString() + loreLine));
        }
        // Set the stats & lore
        display.set("Lore", l);
    }
    
    public ItemStack getItemStack(){
        return craftStack;
    }
    
    @Override
    public NamedItemStack clone(){
        return new NamedItemStack(getItemStack().clone());
    }
}

