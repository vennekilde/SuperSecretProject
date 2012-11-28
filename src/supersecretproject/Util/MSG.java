package supersecretproject.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import SSP.SuperSecretProject;

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

public class MSG {
    public static File errorFileLog = new File(SuperSecretProject.directory+"Logs/errors.log");
    public static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static Date date = new Date();
    
    public static void outputErrorMessage(String message){
        Bukkit.getLogger().log(Level.WARNING, message);
        writeToErrorLog("[WARNING]["+dateFormat.format(date)+"] "+message);
    }
    public static void outputFatalErrorMessage(String message){
        Bukkit.getLogger().log(Level.SEVERE, message);
        writeToErrorLog("[SEVERE ERROR]["+dateFormat.format(date)+"] "+message);
    }
    
    public static void writeToErrorLog(String message){
        message = "\n"+message;
        FileWriter fw = null;
        try {
            fw = new FileWriter(errorFileLog.getAbsoluteFile());
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(message);
            }
        } catch (IOException ex) {
            Logger.getLogger(MSG.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(MSG.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
