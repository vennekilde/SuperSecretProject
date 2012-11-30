package supersecretproject.Items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import net.minecraft.server.NBTBase;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagDouble;
import net.minecraft.server.NBTTagInt;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import supersecretproject.Exceptions.StatNotfoundExcpetion;
import supersecretproject.Util.NBTTagUtil;
 
public class NamedItemStack implements Cloneable{
 
    private CraftItemStack                      craftStack;
    private net.minecraft.server.ItemStack      itemStack;
    
    private HashMap<String,String>              itemDisplayStats;
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
        
        itemDisplayStats = new HashMap();
        itemLore = new ArrayList();
        itemStatsStartColor = ChatColor.GOLD;
        itemLoreStartColor = ChatColor.GRAY;
        //load already added lore
        NBTTagList savedLore = getTag().getList("Lore");
        for(int i = 0; i < savedLore.size(); i++){
            itemLore.add(((NBTTagString)savedLore.get(i)).data);
        }
        //load already added stats
        NBTTagList savedStats = getTag().getList("Stats");
        for(int i = 0; i < savedStats.size(); i++){
            itemDisplayStats.put(savedStats.get(i).getName(), ((NBTTagString)savedStats.get(i)).data);
        }
    }
    public NamedItemStack(String name,ItemStack item) {
        this(item);
        setName(name);
    }
    
    private NBTTagCompound getTag() {
        return NBTTagUtil.getTag(craftStack);
    }
 
    private boolean hasDisplay() {
        return NBTTagUtil.hasDisplay(craftStack);
    }
    private NBTTagCompound getDisplay() {
        return NBTTagUtil.getDisplay(craftStack);
    }
    private void addDisplay() {
        NBTTagUtil.addDisplay(craftStack);
    }
 
    private boolean hasStats() {
        return itemStack.getTag().hasKey("Stats");
    }
    private NBTTagCompound getStats() {
        return getTag().getCompound("Stats");
    }
    private void addStats() {
        getTag().set("Stats", new NBTTagList());
    }
 
    public String getName() {
        return NBTTagUtil.getName(craftStack);
    }
 
    public NamedItemStack setName(String name) {
        NBTTagUtil.setName(craftStack, name);
        return this;
    }
    public void setStat(String stat, double statValue){
        if(!hasStats()){
            addStats();
        }
        NBTTagDouble nbtStat;
        try {
            nbtStat = getNBTStat(stat);
            nbtStat.data = statValue;
        } catch (StatNotfoundExcpetion ex) {
            NBTTagList list = itemStack.getTag().getList("Stats");
            nbtStat = new NBTTagDouble(stat,statValue);
            list.add(nbtStat);
        }
        
    }
    public void removeStat(String stat){
        if(!hasStats()){
            return;
        }
        NBTTagList list = itemStack.getTag().getList("Stats");
        NBTTagList newList = new NBTTagList();
        for(int i = 0; i < list.size(); i++){
            if(!newList.get(i).getName().equals(stat)){
                newList.add(list.get(i));
            }
        }
        itemStack.getTag().set("stat", newList);
    }
    public double getStat(String name) throws StatNotfoundExcpetion{
        return getNBTStat(name).data;
    }
    public NBTTagDouble getNBTStat(String name) throws StatNotfoundExcpetion{
        if(!hasStats()){
            throw new StatNotfoundExcpetion(name);
        }
        NBTTagList list = itemStack.getTag().getList("Stats");
        for(int i = 0; i < list.size(); i++){
            NBTBase base = list.get(i);
            if(base instanceof NBTTagDouble){
                return ((NBTTagDouble)base);
            }
        }
        throw new StatNotfoundExcpetion(name);
    }
    public void setDisplayStat(String stat, String statValue){
        itemDisplayStats.put(stat, statValue);
        updateLoreAndStats();
    }
    public void removeDisplayStat(String stat){
        itemDisplayStats.remove(stat);
        updateLoreAndStats();
    }
    public HashMap<String,String> getDisplayStats(){
        return itemDisplayStats;
    }
    public String getDisplayStat(String statName){
        return itemDisplayStats.get(statName);
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
        itemLore.clear();
        itemLore.addAll(Arrays.asList(loreLines));
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
        
        int red = itemColor >> 16;
        int green = (itemColor - (red << 16) >> 8);
        int blue = itemColor - (red << 16) - (green << 8);
        
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
        NBTTagList NBTLorelist = new NBTTagList();
        NBTTagList lore = new NBTTagList();
        // Add each stat line
        for(String statName : itemDisplayStats.keySet())
        {
             lore.add(new NBTTagString("", itemStatsStartColor + statName + ": " + itemDisplayStats.get(statName)));
        }
        // Add each lore line
        for(String loreLine : itemLore)
        {
            NBTTagString nbtTagString = new NBTTagString("", ChatColor.ITALIC.toString() + itemLoreStartColor.toString() + loreLine);
            lore.add(nbtTagString);
            NBTLorelist.add(nbtTagString);
        }
        // Set the stats & lore
        display.set("Lore", lore);
        getTag().set("Lore", NBTLorelist);
    }
    
    public ItemStack getItemStack(){
        return craftStack;
    }
    
    @Override
    public NamedItemStack clone(){
        return new NamedItemStack(getItemStack().clone());
    }
}

