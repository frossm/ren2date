/* ------------------------------------------------------------------------------
 *  Ren2Date - Rename the provided file with a current date timestamp
 *
 *  Copyright (c) 2004-2026 Michael Fross
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 * ------------------------------------------------------------------------------*/
package org.fross.ren2date;

import org.fross.library.Date;
import org.fross.library.Output;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
   protected static String VERSION = "";
   protected static String COPYRIGHT = "";
   public static final String PROPERTIES_FILE = "app.properties";
   public static Terminal terminal = null;


   /**
    * Main(): Main program execution class
    *
    * @param args Command line arguments provided by the user
    */
   public static void main(String[] args) {
      // Silence the following JLine warning line when you start
      // WARNING: Unable to create a system terminal, creating a dumb terminal
      java.util.logging.LogManager.getLogManager().reset();
      java.util.logging.Logger.getLogger("org.jline").setLevel(java.util.logging.Level.OFF);

      // Force JLine to assume the terminal supports ANSI color and movement
      System.setProperty("org.jline.terminal.type", "xterm-256color");

      // Create a terminal used for input and output with JLine
      try {
         // This will print the actual reason (like "Missing library" or "Access Denied") to the console
         System.setProperty("org.jline.terminal.debug", "true");
         terminal = TerminalBuilder.builder().system(true).build();

         // Let Output and Input classes know which terminal to use
         Output.setTerminal(terminal);

      } catch (IOException ex) {
         // Note: Since terminal failed, we use System.out as a fallback
         Output.printColorln(Output.RED, "Unable to create a terminal. Visuals will be impacted.");
      }

      // Process application level properties file
      // Update properties from the build system when running:
      // https://stackoverflow.com/questions/3697449/retrieve-version-from-maven-pom-xml-in-code
      try {
         InputStream iStream = Main.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
         Properties prop = new Properties();
         prop.load(iStream);
         VERSION = prop.getProperty("Application.version");
         COPYRIGHT = "Copyright " + prop.getProperty("Application.inceptionYear") + "-" + Date.getCurrentYear() + " by Michael Fross";
      } catch (IOException ex) {
         Output.fatalError("Unable to read property file '" + PROPERTIES_FILE + "'", 3);
      }

      // Process the command line arguments and switches
      CommandLineArgs.ProcessCommandLine(args);

      // Ensure at least one filename has been entered
      if (CommandLineArgs.cli.clFilename.isEmpty()) {
         Output.fatalError("ERROR: A filename to rename is required", 3);
      }

      // Loop through each filename provided on the command line and rename it
      for (int i = 0; i < CommandLineArgs.cli.clFilename.size(); i++) {
         String oldNameStr = CommandLineArgs.cli.clFilename.get(i);
         String newNameStr = NameProcessing.getNewName(oldNameStr);

         if (!CommandLineArgs.cli.clQuiet) {
            Output.printColorln(Output.YELLOW, "Directory:\t" + NameProcessing.getFilePath(oldNameStr));
            Output.printColorln(Output.WHITE, "Style:\t\t'" + StyleTemplates.queryPredefinedStyle() + "'");
            Output.printColorln(Output.WHITE, "Renaming\t'" + oldNameStr + "'  ->  '" + newNameStr + "'");
         }

         // Create the file objects from the string names
         File oldFile = new File(oldNameStr).getAbsoluteFile();
         File newFile = new File(NameProcessing.getFilePath(oldNameStr) + "/" + newNameStr);

         // Ensure that the old file exists and the new name doesn't
         if (!oldFile.exists()) {
            Output.printColorln(Output.RED, "FAIL: " + oldNameStr + " does not exist. Skipping rename...\n");
            continue;

            // Ensure that the destination filename does not exist
         } else if (newFile.exists()) {
            Output.printColorln(Output.RED, "FAIL: " + newNameStr + "' name already exists. Skipping rename...\n");
            continue;
         }

         // Rename the file
         try {
            if (oldFile.renameTo(newFile) && !CommandLineArgs.cli.clQuiet) {
               Output.printColorln(Output.GREEN, "Rename successful");
            }

         } catch (SecurityException ex) {
            Output.printColorln(Output.RED, String.format("FAIL: Not authorized to rename '%s'\n", oldNameStr));
         } catch (Exception ex) {
            Output.printColorln(Output.RED, "FAIL: Rename unsuccessful\n");
         }

      }

   }

}