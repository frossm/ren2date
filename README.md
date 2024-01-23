<p align="center"> 
    <img width="70%" src ="https://github.com/frossm/ren2date/raw/master/graphics/ScreenShot.jpg">
</p> 

# Ren2Date

Ren2Date will rename a file in various ways, with the current date & time.  Useful for log files, statements, and other time based data.

There are two ways to choose the name of the renamed file.  The first is to simply choose one of the pre-defined patterns using the `-p #` switch.  I'm happy to add other pre-defined style if you like, just create an issue in [GitHub](https://github.com/frossm/ren2date/issues).

<img align="right" width="200" src="https://github.com/frossm/ren2date/raw/master/graphics/PostIt.jpg">The second, and more flexible way, it so create a custom template.  Ren2Date will replace the placeholders with the current year, month, day, hour, min, or seconds.  This is defined with the `-c PLACEHOLDER` format.  Below is the usage information for Ren2Date.

Please note that for SNAP installs, you just execute `ren2date` and do not need the java -jar syntax.  Actually, for SNAP, you don't even have to have Java installed on yoru system as the SNAP package contains everything needed to execute.  However, you may need to give the snap access if the renames occurs outside of the home direction.

For the normal usage, there is no installation needed.  You simply download the executable jar file and run it per the usage below.  To remove it just delete the ren2date.jar file.

If there are suggestions or bugs, please raise an issue per the GitHub link above.

HomePage:  https://github.com/frossm/ren2date

```
Usage:
  java -jar ren2date [-D] [-p STYLE] [-v] [-z] [-h] FileName1.ext [FileName2.ext ...]

Command Line Options:
 -p NUM      Select the style from the predefined templates listed below
 -c STYLE    Custom style. See below for details
 -D          Start in debug mode
 -v          Display the program version and latest GitHub Cal release
 -z          Disable colorized output
 -h or -?    Display this help information

Parameters:
FILENAME     Provide the filename to rename with the timestamp

Custom Style:
You may include a string with placeholders.  These will be replaced when renaming
Please note that the placeholders are case sensitive
  - YYYY    Current Year         - MM      Current Month Number
  - DD      Current Day Number   - HH      Current Hour
  - NN      Current Minute       - SS      Current Seconds

  Example:  -c FirewallLog3312_YYYY-MM-DD_HH:NN:SS

Predefined Styles:
0 - YYYY-MM-DD_filename.ext      1 - YYYY-MM-DD-filename.ext
2 - YYYY_MM_DD_filename.ext      3 - YYYYMMDD-filename.ext
4 - YYYYMMDD_filename.ext        5 - MM-DD-YYYY-filename.ext
6 - MM_DD_YYYY_filename.ext      7 - MMDDYYYY_filename.ext
8 - MMDDYYYY-filename.ext        9 - filename-YYYY-MM-DD.ext
10 - filename_YYYY_MM_DD.ext     11 - filename-YYYYMMDD.ext
12 - filename_YYYYMMDD.ext       13 - filename-MM-DD-YYYY.ext
14 - filename_MM_DD_YYYY.ext     15 - filename-MMDDYYYY.ext
16 - filename_MMDDYYYY.ext       17 - YYYY-MM-DD_HH-MM-SS_filename.ext
```
