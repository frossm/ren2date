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

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class StyleTemplates {
   static String predefinedStyle = "";

   /**
    * CreateTemplates(): Build the template library with the predefined styles
    */
   protected static void CreateTemplates() {
      try {
         // Build map of number (key) and the style (value)
         Map<String, String> templateLibrary = new HashMap<>();

         // Populate the templates with the predefined styles
         templateLibrary.put("0", "YYYY-MM-DD_FILENAME.EXT");
         templateLibrary.put("1", "YYYY-MM-DD-FILENAME.EXT");
         templateLibrary.put("2", "YYYY_MM_DD_FILENAME.EXT");
         templateLibrary.put("3", "YYYYMMDD-FILENAME.EXT");
         templateLibrary.put("4", "YYYYMMDD_FILENAME.EXT");
         templateLibrary.put("5", "MM-DD-YYYY-FILENAME.EXT");
         templateLibrary.put("6", "MM_DD_YYYY_FILENAME.EXT");
         templateLibrary.put("7", "MMDDYYYY_FILENAME.EXT");
         templateLibrary.put("8", "MMDDYYYY-FILENAME.EXT");
         templateLibrary.put("9", "FILENAME-YYYY-MM-DD.EXT");
         templateLibrary.put("10", "FILENAME_YYYY_MM_DD.EXT");
         templateLibrary.put("11", "FILENAME-YYYYMMDD.EXT");
         templateLibrary.put("12", "FILENAME_YYYYMMDD.EXT");
         templateLibrary.put("13", "FILENAME-MM-DD-YYYY.EXT");
         templateLibrary.put("14", "FILENAME_MM_DD_YYYY.EXT");
         templateLibrary.put("15", "FILENAME-MMDDYYYY.EXT");
         templateLibrary.put("16", "FILENAME_MMDDYYYY.EXT");

         predefinedStyle = templateLibrary.get(String.valueOf(CommandLineArgs.clPredefinedStyle));

         Output.debugPrintln("Predefined style selected: " + CommandLineArgs.clPredefinedStyle + "        template: '" + predefinedStyle + "'");

         // If we don't understand the argument to -p, throw a fatal error
         if (predefinedStyle.isEmpty()) throw new Exception();

      } catch (Exception ex) {
         Output.fatalError("Unknown pre-defined style: '" + CommandLineArgs.clPredefinedStyle + "'. Please see the help (-h)", 1);
      }

   }

   /**
    * queryPredefinedStyle(): Return the selected pre-defined template
    *
    * @return The template to use for renaming
    */
   public static String queryPredefinedStyle() {
      return predefinedStyle;
   }

}
