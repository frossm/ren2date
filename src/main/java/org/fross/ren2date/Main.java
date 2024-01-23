/*--------------------------------------------------------------------------------
 *  Ren2Date - Rename the provided file with a current date timestamp
 *
 *  Copyright (c) 2004-2024 Michael Fross
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
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *------------------------------------------------------------------------------*/
package org.fross.ren2date;

import org.fross.library.Output;
import org.fusesource.jansi.Ansi;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
   protected static String VERSION = "";
   protected static String COPYRIGHT = "";
   public static final String PROPERTIES_FILE = "app.properties";


   /**
    * Main(): Main program execution class
    *
    * @param args Command line arguments provided by the user
    */
   public static void main(String[] args) {
      // Process application level properties file
      // Update properties from Maven at build time:
      // https://stackoverflow.com/questions/3697449/retrieve-version-from-maven-pom-xml-in-code
      try {
         InputStream iStream = Main.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
         Properties prop = new Properties();
         prop.load(iStream);
         VERSION = prop.getProperty("Application.version");
         COPYRIGHT = "Copyright " + prop.getProperty("Application.inceptionYear") + "-" + org.fross.library.Date.getCurrentYear() + " by Michael Fross";
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
         String oldName = CommandLineArgs.cli.clFilename.get(i);
         String newName = NameProcessing.getNewName(oldName);

         Output.printColorln(Ansi.Color.YELLOW, "Directory:\t" + NameProcessing.getFilePath(oldName));
         Output.printColorln(Ansi.Color.WHITE, "Style:\t\t'" + StyleTemplates.queryPredefinedStyle() + "'");
         Output.printColorln(Ansi.Color.WHITE, "Renaming\t'" + oldName + "'  ->  '" + newName + "'");

         // Create the file objects from the string names
         File oldFile = new File(oldName).getAbsoluteFile();
         File newFile = new File(NameProcessing.getFilePath(oldName) + "/" + newName);

         // Ensure that the old file exists and the new name doesn't
         if (!oldFile.exists()) {
            Output.printColorln(Ansi.Color.RED, oldName + " does not exist. Skipping rename...\n");
            continue;

            // Ensure that the destination filename does not exist
         } else if (newFile.exists()) {
            Output.printColorln(Ansi.Color.RED, newName + "' name already exists. Skipping rename...\n");
            continue;
         }

         // Rename the file
         if (oldFile.renameTo(newFile)) {
            Output.printColorln(Ansi.Color.GREEN, "Rename successful");
         } else {
            Output.printColorln(Ansi.Color.RED, "Rename unsuccessful");
         }

         Output.println("");
      }

   }

}