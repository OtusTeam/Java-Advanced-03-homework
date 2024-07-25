package com.ozn.jlink;

import java.util.logging.Logger;

//compile and run
// cd jlink
// javac -d jlink-target ./src/main/java/module-info.java           - compile module
// javac -d jlink-target ./src/main/java/com/ozn/jlink/Main.java    - add all needed classes into module
// java --module-path jlink-target --module <module name>/<main module class>    -run module, check it works
// java --module-path jlink-target --module java.custom.jre.jlink/com.ozn.jlink.Main

// use jdeps to check dependencies
//  jdeps --module-path jlink-target -s --module java.custom.jre.jlink

//create own lightweight jre
//jlink [options] -module-path modulepath --add-modules module [, module] --output <target directory>
//jlink --module-path "/Users/kseniya/IdeaProjects/Java-Advanced-homework/oznobishina/task5/jlink/jlink-target:/Users/kseniya/Library/Java/JavaVirtualMachines/liberica-17.0.9/jmods" --add-modules java.custom.jre.jlink --output jlink-customjre

//run jre
// jlink-customjre/bin/customjrelauncher.bat - for Windows
// '/Users/kseniya/IdeaProjects/Java-Advanced-homework/oznobishina/task5/jlink/jlink-customjre/bin/java' -classpath '$/Users/kseniya/IdeaProjects/Java-Advanced-homework/oznobishina/task5/jlink/jlink-customjre' --module java.custom.jre.jlink/com.ozn.jlink.Main

public class Main {
    private static final Logger logger =  Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info("I am JLink example");
    }
}