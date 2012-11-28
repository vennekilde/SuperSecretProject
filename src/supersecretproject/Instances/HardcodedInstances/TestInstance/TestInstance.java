package supersecretproject.Instances.HardcodedInstances.TestInstance;

import info.jeppes.ZoneCore.Users.ZoneUser;
import info.jeppes.ZoneCore.ZoneCore;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import supersecretproject.Instances.Instance;
import supersecretproject.Instances.InstanceManager;
import supersecretproject.Instances.Reward;
import supersecretproject.SSPAPI;
import supersecretproject.Util.NamedItemStack;

/*
 * Author: Jeppe Boysen Vennekilde
 *
 * This document is Copyright ©() and is the intellectual property of the author.
 *
 * TERMS AND CONDITIONS
 * 0. USED TERMS
 * OWNER - The original author(s) of the program
 * USER - End user of the program, person installing/using the program.
 *
 * 1. LIABILITY
 * THIS PROGRAM IS PROVIDED 'AS IS' WITH NO WARRANTIES, IMPLIED OR OTHERWISE.
 * THE OWNER OF THIS PROGRAM TAKES NO RESPONSIBILITY FOR ANY DAMAGES INCURRED
 * FROM THE USE OF THIS PROGRAM.
 *
 * 2. REDISTRIBUTION
 * This program may only be distributed where uploaded, mirrored, or otherwise
 * linked to by the OWNER solely. All mirrors of this program must have advance
 * written permission from the OWNER. ANY attempts to make money off of this
 * program (selling, selling modified versions, adfly, sharecash, etc.) are
 * STRICTLY FORBIDDEN, and the OWNER may claim damages or take other action to
 * rectify the situation.
 *
 * 3. DERIVATIVE WORKS/MODIFICATION
 * This program is provided freely and may be decompiled and modified for
 * private use, either with a decompiler or a bytecode editor. Public
 * distribution of modified versions of this program require advance written
 * permission of the OWNER and may be subject to certain terms.
 */

public class TestInstance extends Instance{
    
    public TestInstance(){
        super("test instance");
        this.setPublicInstance(true);
    }
    
    @Override
    public void initInstanceStarter() {
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if(event.getMessage().equalsIgnoreCase("testinstance")){
            this.addPlayer(ZoneCore.getUser(event.getPlayer()));
            Instance newInstance = newInstanceAndStart();
            this.removePlayer(ZoneCore.getUser(event.getPlayer()));
        }
    }

    @Override
    public void giveReward() {
        for(ZoneUser player : getPlayers()){
            player.sendMessage("Here is your reward!");
        }
        //.addLore("Used by some of the best warriors")
        NamedItemStack namedItemStack = new NamedItemStack(ChatColor.DARK_PURPLE+"The Mighty Sword",new ItemStack(Material .DIAMOND_SWORD));
        ItemStack itemStack = namedItemStack.getItemStack();
        itemStack.addEnchantment(Enchantment.DAMAGE_ALL, 5);
        namedItemStack.setLore("This sword has been used by\nsome of the best warriors in Minecraft",ChatColor.GRAY);
        
        for(ZoneUser player : getPlayers()){
            player.getInventory().addItem(itemStack.clone());
        }
    }

    @Override
    public String getStringReward() {
        return "The Mighty Sword!";
    }
}
