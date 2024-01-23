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

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.fross.library.Debug;
import org.fross.library.GitHub;
import org.fross.library.Output;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.List;

public class CommandLineArgs {
   static CommandLineArgs cli = new CommandLineArgs();
   static JCommander jc = new JCommander();

   // ---------------------------------------------------------------------------------------------
   // Define command line options that can be used
   // ---------------------------------------------------------------------------------------------
   @Parameter(names = {"-p", "--predefined-style"}, description = "Select a pre-defined style - see help for details")
   protected static int clPredefinedStyle = 0;

   @Parameter(names = {"-c", "--custom-style"}, description = "Use a custom style.  See help for details")
   protected static String clCustomStyle = "";

   @Parameter(names = {"-D", "--debug"}, description = "Turn on Debug mode to display extra program information")
   protected boolean clDebug = false;

   @Parameter(names = {"-v", "--version"}, description = "Show current program version and latest release on GitHub")
   protected boolean clVersion = false;

   @Parameter(names = {"-z", "--no-color"}, description = "Disable colorized output")
   protected boolean clNoColor = false;

   @Parameter(names = {"-h", "-?", "--help"}, help = true, description = "Display Ren2Date help and exit")
   protected boolean clHelp = false;

   @Parameter(description = "File to rename")
   protected List<String> clFilename = new ArrayList<>();

   // ---------------------------------------------------------------------------------------------
   // Process command line parameters with the following methods
   // ---------------------------------------------------------------------------------------------
   public static void ProcessCommandLine(String[] argv) {
      // JCommander parses the command line
      try {
         jc.setProgramName("Ren2Date");
         jc = JCommander.newBuilder().addObject(cli).build();
         jc.parse(argv);
      } catch (ParameterException ex) {
         System.out.println(ex.getMessage());
         jc.usage();
         System.exit(0);
      }

      // ---------------------------------------------------------------------------------------------
      // Process the parsed command line options
      // ---------------------------------------------------------------------------------------------
      // Debug Switch
      if (cli.clDebug) {
         Debug.enable();
      }

      // Version Switch
      if (cli.clVersion) {
         Output.printColorln(Ansi.Color.WHITE, "Ren2Date Version: v" + Main.VERSION);
         Output.printColorln(Ansi.Color.CYAN, Main.COPYRIGHT);
         Output.printColorln(Ansi.Color.WHITE, "\nLatest Release on GitHub: " + GitHub.updateCheck("ren2date"));
         Output.printColorln(Ansi.Color.CYAN, "HomePage: https://github.com/frossm/ren2date");
         System.exit(0);
      }

      // Disable Colorized Output Switch
      if (cli.clNoColor) {
         Output.enableColor(false);
      }

      // Show Help and Exit
      if (cli.clHelp) {
         Help.display();
         System.exit(0);
      }

   }

   /**
    * queryCustomStyle():  Return the custom style string entered by the user
    *
    * @return Custom style string
    */
   protected static String queryCustomStyle() {
      return clCustomStyle;
   }

   /**
    * queryPredefinedStyle():  Return the predefined style number selected
    *
    * @return predefined style selection number
    */
   protected static int queryPredefinedStyle() {
      return clPredefinedStyle;
   }

}
