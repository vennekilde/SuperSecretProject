/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supersecretproject.Items;

import org.bukkit.inventory.ItemStack;
import supersecretproject.Util.NamedItemStack;

/**
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
