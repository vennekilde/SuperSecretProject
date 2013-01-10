package supersecretproject.Util;

import net.minecraft.server.v1_4_6.NBTBase;
import net.minecraft.server.v1_4_6.NBTTagCompound;
import net.minecraft.server.v1_4_6.NBTTagDouble;
import net.minecraft.server.v1_4_6.NBTTagInt;
import net.minecraft.server.v1_4_6.NBTTagList;
import org.bukkit.craftbukkit.v1_4_6.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import supersecretproject.Exceptions.StatNotfoundExcpetion;

public class NBTTagUtil {
    
    public static NBTTagCompound getTag(ItemStack item) {
        return CraftItemStack.asNMSCopy(item).getTag();
    }
    
    public static boolean hasDisplay(ItemStack item) {
        return getTag(item).hasKey("display");
    }
    public static NBTTagCompound getDisplay(ItemStack item) {
        return getTag(item).getCompound("display");
    }
    public static void addDisplay(ItemStack item) {
        getTag(item).setCompound("display", new NBTTagCompound());
    }
 
    public static String getName(ItemStack item) {
        if (hasDisplay(item) == false) {
            return null;
        }
        String name = getDisplay(item).getString("Name");
        if (name.equals("")) {
            return null;
        }
        return name;
    }
 
    public static ItemStack setName(ItemStack item, String name) {
        if (hasDisplay(item) == false) {
            addDisplay(item);
        }
        NBTTagCompound display = getDisplay(item);
        if (name == null) {
            display.set("Name", null);
        }
        display.setString("Name", name);
        return item;
    }
    
    public static ItemStack setStat(ItemStack item, String stat, double statValue){
        NBTTagDouble nbtStat;
        try {
            nbtStat = getNBTStat(item,stat);
            nbtStat.data = statValue;
        } catch (StatNotfoundExcpetion ex) {
            NBTTagList list = getTag(item).getList("Stats");
            nbtStat = new NBTTagDouble(stat,statValue);
            list.add(nbtStat);
        }
        return item;
    }
    public static ItemStack removeStat(ItemStack item, String stat){
        NBTTagList list = getTag(item).getList("Stats");
        NBTTagList newList = new NBTTagList();
        for(int i = 0; i < list.size(); i++){
            if(!newList.get(i).getName().equals(stat)){
                newList.add(list.get(i));
            }
        }
        getTag(item).set("stat", newList);
        return item;
    }
    public static double getStat(ItemStack item,String name) throws StatNotfoundExcpetion{
        return getNBTStat(item,name).data;
    }
    public static NBTTagDouble getNBTStat(ItemStack item, String name) throws StatNotfoundExcpetion{
        NBTTagList list = getTag(item).getList("Stats");
        for(int i = 0; i < list.size(); i++){
            NBTBase base = list.get(i);
            if(base instanceof NBTTagDouble){
                return ((NBTTagDouble)base);
            }
        }
        throw new StatNotfoundExcpetion(name);
    }

//    public void setDisplayStat(String stat, String statValue){
//        itemDisplayStats.put(stat, statValue);
//        updateLoreAndStats();
//    }
//    public void removeDisplayStat(String stat){
//        itemDisplayStats.remove(stat);
//        updateLoreAndStats();
//    }
//    public HashMap<String,String> getDisplayStats(){
//        return itemDisplayStats;
//    }
//    public String getDisplayStat(String statName){
//        return itemDisplayStats.get(statName);
//    }
 
//    public String[] getLoreStrings(){
//        NBTTagList list = getLore();
//        ArrayList<String> strings = new ArrayList();
//        String[] lores = new String[] {};
//        for (int i = 0; i < strings.size(); i++) {
//            strings.add(((NBTTagString) list.get(i)).data);
//        }
//        strings.toArray(lores);
//        return lores;
//    }
        
//    public NBTTagList getLore()
//    {
//        if (!hasDisplay())
//        {
//            return new NBTTagList();
//        }
//        NBTTagList lore = getDisplay().getList("Lore");
//        if (lore == null) 
//        {
//            // returns empty list
//            return new NBTTagList();
//        }
//        return lore;
//    }
// 
//    public supersecretproject.Items.NamedItemStack setLore(String lore){
//        // Added multiline lore support
//        // Splits the lines
//        String[] loreLines = lore.split("\n");
//        itemLore.clear();
//        itemLore.addAll(Arrays.asList(loreLines));
//        updateLoreAndStats();
//        return this;
//    }
//    public supersecretproject.Items.NamedItemStack setLore(String lore,ChatColor startColor)
//    {
//        StringBuilder newLore = new StringBuilder();
//        // Added multiline lore support
//        // Splits the lines
//        String[] loreLines = lore.split("\n");
//        // Add each line
//        for(String loreLine : loreLines)
//        {
//             newLore.append("\n").append(startColor).append(loreLine);
//        }
//        
//        return setLore(newLore.toString());
//    }
//    
//    public supersecretproject.Items.NamedItemStack addLore(String lore){
//        // Added multiline lore support
//        // Splits the lines
//        String[] loreLines = lore.split("\n");
//        itemLore.addAll(Arrays.asList(loreLines));
//        updateLoreAndStats();
//        return this;
//    }
//    public supersecretproject.Items.NamedItemStack addLore(String lore,ChatColor startColor){
//        StringBuilder newLore = new StringBuilder();
//        // Added multiline lore support
//        // Splits the lines
//        String[] loreLines = lore.split("\n");
//        // Add each line
//        for(String loreLine : loreLines)
//        {
//             newLore.append("\n").append(startColor).append(loreLine);
//        }
//        
//        return addLore(newLore.toString());
//    }
//    public supersecretproject.Items.NamedItemStack addLoreAtLine(String lore, int line){
//        // Added multiline lore support
//        // Splits the lines
//        String[] loreLines = lore.split("\n");
//        // Add each line
//        for(String loreLine : loreLines)
//        {
//             itemLore.add(line, loreLine);
//             line++;
//        }
//        updateLoreAndStats();
//        return this;
//    }
//    public supersecretproject.Items.NamedItemStack addLoreAtLine(String lore, int line, ChatColor startColor){
//        StringBuilder newLore = new StringBuilder();
//        // Added multiline lore support
//        // Splits the lines
//        String[] loreLines = lore.split("\n");
//        // Add each line
//        for(String loreLine : loreLines)
//        {
//             newLore.append("\n").append(startColor).append(loreLine);
//        }
//        
//        return addLoreAtLine(newLore.toString(),line);
//    }
//    
    public int getItemColor(ItemStack item){
        if (!hasDisplay(item))
        {
            return -1;
        }
        if (!getDisplay(item).hasKey("color")) 
        {
            // returns empty list
            return -1;
        }
        int colorID = getDisplay(item).getInt("color");
        return colorID;
    }
    public int[] getItemColorRGB(ItemStack item){
        int itemColor = getItemColor(item);
        if (itemColor < 0) 
        {
            // returns empty list
            return new int[]{-1,-1,-1};
        }
        
        int red = itemColor >> 16;
        int green = (itemColor - (red << 16) >> 8);
        int blue = itemColor - (red << 16) - (green << 8);
        
        return new int[]{red,green,blue};
    }
    
    public ItemStack setItemColor(ItemStack item, int red, int green, int blue){    
        if (!hasDisplay(item))
        {
            this.addDisplay(item);
        }
        
        NBTTagCompound display = this.getDisplay(item);
        if(red < 0 || green < 0 || blue < 0){
            display.set("color", null);
            return item;
        }
        
        red = red > 255 ? 255 : red;
        green = green > 255 ? 255 : green;
        blue = blue > 255 ? 255 : blue;
        
        int colorId = (red << 16) + (green << 8) + blue;
        NBTTagInt colorTag = new NBTTagInt("",colorId);
        display.set("color", colorTag);
        return item;
    }
}
