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

import org.apache.commons.io.FilenameUtils;
import org.fross.library.Date;
import org.fross.library.Output;

public class NameProcessing {
   public static String getFilePath(String fileName) {
      fileName = FilenameUtils.normalizeNoEndSeparator(fileName, false);

      String p = FilenameUtils.getFullPathNoEndSeparator(fileName);

      // If path is empty, add the current working directory
      if (p.isEmpty()) {
         return System.getProperty("user.dir");
      } else {
         return p;
      }
   }

   public static String getBaseName(String fileName) {
      return FilenameUtils.getBaseName(fileName);
   }

   public static String getExtension(String fileName) {
      return FilenameUtils.getExtension(fileName);
   }

   public static String getNewName(String fileName) {
      Output.debugPrintln(String.format("Provided: %s  | Path: %s  | Base: %s  | Ext: %s", fileName, getFilePath(fileName), getBaseName(fileName), getExtension(fileName)));

      // Create the templates
      StyleTemplates.CreateTemplates();

      // Replace the template placeholders with today's date
      String selectedStyle = StyleTemplates.queryPredefinedStyle();
      String newName = selectedStyle.replaceAll("YYYY", String.format("%d", Date.getCurrentYear()));
      newName = newName.replaceAll("MM", String.format("%02d", Date.getCurrentMonth()));
      newName = newName.replaceAll("DD", String.format("%02d", Date.getCurrentDay()));

      // Replace the name
      newName = newName.replaceAll("FILENAME", getBaseName(fileName));
      if (getExtension(fileName).isEmpty()) {
         // There is no extension so remove the dot
         newName = newName.replaceAll("\\.*?EXT", getExtension(fileName));
      } else {
         newName = newName.replaceAll("EXT", getExtension(fileName));
      }

      return newName;
   }
}
