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
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineArgsTest {
   @Test
   void testShortCommandLineArgs() {
      // Test Short Options
      String[] argv = {"-h", "-c", "FileName-YYYY", "-z", "-v", "-D", "-p", "3"};

      CommandLineArgs cli = new CommandLineArgs();
      JCommander jc = new JCommander();
      jc.setProgramName("cal");

      jc = JCommander.newBuilder().addObject(cli).build();
      jc.parse(argv);

      assertTrue(cli.clHelp);
      assertEquals("FileName-YYYY", CommandLineArgs.queryCustomStyle());
      assertTrue(cli.clNoColor);
      assertTrue(cli.clVersion);
      assertTrue(cli.clDebug);
      assertEquals(3, CommandLineArgs.queryPredefinedStyle());

   }

   @Test
   void testLongCommandLineArgs() {
      // Test Long Options
      String[] argv = {"--predefined-style", "17", "--custom-style", "YYYY-MM-DD_FILENAME.EXT", "--debug", "--version", "--no-color", "--help"};

      CommandLineArgs cli = new CommandLineArgs();
      JCommander jc = new JCommander();
      jc.setProgramName("cal");

      jc = JCommander.newBuilder().addObject(cli).build();
      jc.parse(argv);

      assertTrue(cli.clHelp);
      assertEquals("YYYY-MM-DD_FILENAME.EXT", CommandLineArgs.queryCustomStyle());
      assertTrue(cli.clNoColor);
      assertTrue(cli.clVersion);
      assertTrue(cli.clDebug);
      assertEquals(17, CommandLineArgs.queryPredefinedStyle());

   }

   @Test
   void testMixedCommandLineArgs1() {
      // Test Mix of Options
      String[] argv3 = {"--no-color", "-?", "--predefined-style", "13", "-c", "file-FILENAME"};

      CommandLineArgs cli = new CommandLineArgs();
      JCommander jc = new JCommander();
      jc.setProgramName("Ren2Date");

      jc = JCommander.newBuilder().addObject(cli).build();
      jc.parse(argv3);

      assertFalse(cli.clDebug);
      assertTrue(cli.clNoColor);
      assertEquals(13, CommandLineArgs.queryPredefinedStyle());
      assertFalse(cli.clVersion);
      assertTrue(cli.clHelp);
      assertEquals("file-FILENAME", CommandLineArgs.queryCustomStyle());
   }

   @Test
   void testMixedCommandLineArgs2() {
      // Test Mix of Options
      String[] argv4 = {"--debug", "-h", "--version", "-c", "MixedLoadFile2.txt"};

      CommandLineArgs cli = new CommandLineArgs();
      JCommander jc = new JCommander();
      jc.setProgramName("Ren2Date");

      jc = JCommander.newBuilder().addObject(cli).build();
      jc.parse(argv4);

      assertTrue(cli.clDebug);
      assertFalse(cli.clNoColor);
      assertTrue(cli.clVersion);
      assertTrue(cli.clHelp);
      assertEquals("MixedLoadFile2.txt", CommandLineArgs.queryCustomStyle());
   }
}