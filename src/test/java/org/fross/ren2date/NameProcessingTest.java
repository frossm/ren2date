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

import org.fross.library.Date;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameProcessingTest {
   @Test
   void getBaseName() {
      assertEquals("", NameProcessing.getBaseName(".vimrc"));
      assertEquals("filename", NameProcessing.getBaseName("filename"));
      assertEquals("x", NameProcessing.getBaseName("/home/frosty/x.vimrc"));
      assertEquals("TestFile", NameProcessing.getBaseName("C:\\Utils\\TestFile.txt"));
      assertEquals("HiThere.123", NameProcessing.getBaseName("./HiThere.123.456"));
      assertEquals("filename", NameProcessing.getBaseName("Dir.Path.With.Dot\\filename"));
      assertEquals("FILEname", NameProcessing.getBaseName("/FILEname.eXtension"));
   }

   @Test
   void getExtension() {
      assertEquals("vimrc", NameProcessing.getExtension(".vimrc"));
      assertEquals("", NameProcessing.getExtension("filename"));
      assertEquals("abc", NameProcessing.getExtension(".xyz.abc"));
      assertEquals("vimrc", NameProcessing.getExtension("/home/frosty/x.vimrc"));
      assertEquals("txt", NameProcessing.getExtension("C:\\Utils\\TestFile.txt"));
      assertEquals("456", NameProcessing.getExtension("./HiThere.123.456"));
      assertEquals("", NameProcessing.getExtension("Dir.Path.With.Dot\\filename"));
      assertEquals("eXtension", NameProcessing.getExtension("/FILEname.eXtension"));
   }

   @Test
   void currentDateTests() {
      java.util.Calendar jc = java.util.Calendar.getInstance();

      assertEquals(jc.get(java.util.Calendar.MONTH) + 1, Date.getCurrentMonth());
      assertEquals(jc.get(java.util.Calendar.YEAR), Date.getCurrentYear());
      assertEquals(jc.get(Calendar.DAY_OF_MONTH), Date.getCurrentDay());
      assertEquals(jc.get(java.util.Calendar.HOUR), Date.getCurrentHour());
      assertEquals(jc.get(java.util.Calendar.MINUTE), Date.getCurrentMinute());
      assertEquals(jc.get(java.util.Calendar.SECOND), Date.getCurrentSecond());
   }

   @Test
   void getNewName() {
      java.util.Calendar jc = java.util.Calendar.getInstance();
      int year = Date.getCurrentYear();
      int month = Date.getCurrentMonth();
      int day = Date.getCurrentDay();

      String fname = "FilenameHere.ext";
      assertEquals(String.format("%04d-%02d-%02d_%s", year, month, day, fname), NameProcessing.getNewName(fname));

      fname = ".vimrc";
      assertEquals(String.format("%04d-%02d-%02d_%s", year, month, day, fname), NameProcessing.getNewName(fname));

      fname = "name.123.456.789";
      assertEquals(String.format("%04d-%02d-%02d_%s", year, month, day, fname), NameProcessing.getNewName(fname));

      fname = "FilenameHere.ext";
      assertEquals(String.format("%04d-%02d-%02d_%s", year, month, day, fname), NameProcessing.getNewName(fname));

      CommandLineArgs.clCustomStyle = "FILENAME-DD-MM-YYYY_HH.EXT.end";
      fname = "FILEname.eXtension";
      assertEquals(String.format("FILEname-%02d-%02d-%04d_%02d.eXtension.end", jc.get(Calendar.DAY_OF_MONTH), jc.get(Calendar.MONTH) + 1, jc.get(Calendar.YEAR), jc.get(Calendar.HOUR)), NameProcessing.getNewName(fname));
   }
}