package supersecretproject;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import supersecretproject.Instances.Instance;

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

public class Loader {
    
    
    
    public static void load(){
        
    }
    
    private ArrayList<Instance> instances = new ArrayList();
    public void loadInstances(){
        instances.clear();
    }
    /**
    * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
    * Adapted from http://snippets.dzone.com/posts/show/4831 and extended to support use of JAR files
    *
    * @param packageName The base package
    * @param regexFilter an optional class name pattern.
    * @return The classes
    */
   public Class[] getClassesInPackage(String packageName, String regexFilter) {
           Pattern regex = null;
           if (regexFilter != null)
                   regex = Pattern.compile(regexFilter);

           try {
                   ClassLoader classLoader = this.getClassLoader();
                   assert classLoader != null;
                   String path = packageName.replace('.', '/');
                   Enumeration<URL> resources = classLoader.getResources(path);
                   List<String> dirs = new ArrayList<>();
                   while (resources.hasMoreElements()) {
                           URL resource = resources.nextElement();
                           dirs.add(resource.getFile());
                   }
                   TreeSet<String> classes = new TreeSet<>();
                   for (String directory : dirs) {
                           classes.addAll(findClasses(directory, packageName, regex));
                   }
                   ArrayList<Class> classList = new ArrayList<>();
                   for (String clazz : classes) {
                           classList.add(Class.forName(clazz));
                   }
                   return classList.toArray(new Class[classes.size()]);
           } catch (Exception e) {
                   e.printStackTrace();
                   return null;
           }
   }

   /**
    * Recursive method used to find all classes in a given path (directory or zip file url).  Directories
    * are searched recursively.  (zip files are
    * Adapted from http://snippets.dzone.com/posts/show/4831 and extended to support use of JAR files
    *
    * @param path   The base directory or url from which to search.
    * @param packageName The package name for classes found inside the base directory
    * @param regex       an optional class name pattern.  e.g. .*Test
    * @return The classes
    */
   private TreeSet<String> findClasses(String path, String packageName, Pattern regex) throws Exception {
       TreeSet<String> classes = new TreeSet<>();
       if (path.startsWith("file:") && path.contains("!")) {
           String[] split = path.split("!");
           URL jar = new URL(split[0]);
           ZipInputStream zip = new ZipInputStream(jar.openStream());
           ZipEntry entry;
           while ((entry = zip.getNextEntry()) != null) {
               if (entry.getName().endsWith(".class")) {
                   String className = entry.getName().replaceAll("[$].*", "").replaceAll("[.]class", "").replace('/', '.');
                   if (className.startsWith(packageName) && (regex == null || regex.matcher(className).matches()))
                       classes.add(className);
               }
           }
       }
       File dir = new File(path);
       if (!dir.exists()) {
           return classes;
       }
       File[] files = dir.listFiles();
       for (File file : files) {
           if (file.isDirectory()) {
               assert !file.getName().contains(".");
               classes.addAll(findClasses(file.getAbsolutePath(), packageName + "." + file.getName(), regex));
           } else if (file.getName().endsWith(".class")) {
               String className = packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
               if (regex == null || regex.matcher(className).matches())
                       classes.add(className);
           }
       }
       return classes;
    }
}
