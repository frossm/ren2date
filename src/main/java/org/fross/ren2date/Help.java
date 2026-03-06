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

import org.fross.library.Format;
import org.fross.library.Output;

/**
 * Help(): Display the help page when users enters 'h' or '?' command.
 *
 * @author michael.d.fross
 */
public class Help {
   static final int HELPWIDTH = 80;

   /**
    * display(): Prints help in color using the jAnsi library in the output module.
    */
   public static void display() {
      Output.printColorln(Output.CYAN, "\n+" + "-".repeat(HELPWIDTH) + "+");
      Output.printColorln(Output.WHITE, "+" + Format.CenterText(HELPWIDTH, "Ren2Date - Add current timestamp to provided file name") + "+");
      Output.printColorln(Output.WHITE, "+" + Format.CenterText(HELPWIDTH, "Version v" + Main.VERSION) + "+");
      Output.printColorln(Output.WHITE, "+" + Format.CenterText(HELPWIDTH, Main.COPYRIGHT) + "+");
      Output.printColorln(Output.CYAN, "+" + "-".repeat(HELPWIDTH) + "+");
      Output.printColorln(Output.CYAN, Format.CenterText(HELPWIDTH, "https://github.com/frossm/ren2date"));

      Output.printColorln(Output.YELLOW, "\nUsage:");
      Output.printColorln(Output.WHITE, "  java -jar ren2date [-p NUM] [-c STYLE] [-D] [-v] [-q] [-z] [-h] FileName1.ext [FileName2.ext ...]");

      Output.printColorln(Output.YELLOW, "\nCommand Line Options:");
      Output.printColorln(Output.WHITE, " -p NUM      Select the style from the predefined templates listed below");
      Output.printColorln(Output.WHITE, " -c STYLE    Custom style. See below for details");
      Output.printColorln(Output.WHITE, " -D          Start in debug mode");
      Output.printColorln(Output.WHITE, " -v          Display the program version and latest GitHub Cal release");
      Output.printColorln(Output.WHITE, " -q          Quiet mode: Only errors / failures will be displayed");
      Output.printColorln(Output.WHITE, " -z          Disable colorized output");
      Output.printColorln(Output.WHITE, " -h or -?    Display this help information");

      Output.printColorln(Output.YELLOW, "\nParameters:");
      Output.printColorln(Output.WHITE, "FILENAME     Provide the filename to rename with the timestamp");

      Output.printColorln(Output.YELLOW, "\nCustom Style:");
      Output.printColorln(Output.WHITE, "You may include a string with placeholders.  These will be replaced when renaming");
      Output.printColorln(Output.WHITE, "Please note that the placeholders are case sensitive");
      Output.printColorln(Output.WHITE, " - YYYY  Current Year \t\t - MM  Current Month Number");
      Output.printColorln(Output.WHITE, " - DD    Current Day Number \t - HH  Current Hour");
      Output.printColorln(Output.WHITE, " - NN    Current Minute \t - SS  Current Seconds");

      Output.printColorln(Output.WHITE, "\nExample:  java -jar ren2date.jar -c FirewallLog3312_YYYY-MM-DD_HH:NN:SS");

      Output.printColorln(Output.YELLOW, "\nPredefined Styles:");
      Output.printColorln(Output.WHITE, "00 - YYYY-MM-DD_filename.ext \t 01 - YYYY-MM-DD-filename.ext");
      Output.printColorln(Output.WHITE, "02 - YYYY_MM_DD_filename.ext \t 03 - YYYYMMDD-filename.ext");
      Output.printColorln(Output.WHITE, "04 - YYYYMMDD_filename.ext \t 05 - MM-DD-YYYY-filename.ext");
      Output.printColorln(Output.WHITE, "06 - MM_DD_YYYY_filename.ext \t 07 - MMDDYYYY_filename.ext");
      Output.printColorln(Output.WHITE, "08 - MMDDYYYY-filename.ext \t 09 - filename-YYYY-MM-DD.ext");
      Output.printColorln(Output.WHITE, "10 - filename_YYYY_MM_DD.ext \t 11 - filename-YYYYMMDD.ext");
      Output.printColorln(Output.WHITE, "12 - filename_YYYYMMDD.ext \t 13 - filename-MM-DD-YYYY.ext");
      Output.printColorln(Output.WHITE, "14 - filename_MM_DD_YYYY.ext \t 15 - filename-MMDDYYYY.ext");
      Output.printColorln(Output.WHITE, "16 - filename_MMDDYYYY.ext \t 17 - YYYY-MM-DD_HH-MM-SS_filename.ext");
   }
}