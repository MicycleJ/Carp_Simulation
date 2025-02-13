package SyntSpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Wrapper is the main class for running. It is used to convert the 
 * parameters in config.txt into variables, and runs the simulation
 * in SSCarp.java
 */
public class Wrapper {
  public static Map<String, String> configs;
  public static final String valueDelim = " ";
  public static String timestamp = ""; public static final String pathToFile = "./src/SyntSpec/"; public static final String JavaConfigFileName = "SimConfigs.java";
  public static int runNumber = 1;
  
  /**
   * Call functions to convert config.txt to variables, and to run
   * the simulation. If no config file exists, a sample one will be
   * created
   * @param args
   */
  public static void main(String[] args) {
    configs = new HashMap<>();
    File configFile = new File("config.txt");
    
    if (configFile.exists()) {
      
      fillMap(configFile);
      
      runConfigs(configs);
    }
    else {
      
      System.out.println("Creating sample \"config.txt\" file.  Please modify then run again");
      
      try {
        PrintWriter out = new PrintWriter(configFile);
        out.printf("INITIAL_FISH: 50 to 201 step 50\n", new Object[0]);
        out.printf("RESTOCK_AMOUNT: [10 100]\n", new Object[0]);
        out.printf("RESTOCK_GENOTYPE: [GR ppttll oldsettings]\n", new Object[0]);
        
        out.close();
      } catch (FileNotFoundException e) {
        System.out.printf("Error creating config files (and nonexistant)\n", new Object[0]);
        System.exit(1);
      } 
    } 
  }
  
  /**
   * recursively call mainOld, to handle parameters being varied
   * @param configs		HashMap of varied configuration values (?)
   */
  public static void runConfigs(Map<String, String> configs) {
    if (configs.isEmpty()) {
      
      String[] input = new String[0];
      
      DateFormat df = new SimpleDateFormat("dd-MM-yy_HH~mm~ss");
      Date dateobj = new Date();
      timestamp = df.format(dateobj) + "_";
      writeCurrentConfigs();
      SSCarp.mainOld(input);
      runNumber++;
      
      return;
    } 
    String currentConfig = (String)configs.keySet().toArray()[0];
    String values = configs.get(currentConfig);
    String[] sp = values.split(" ");
    
    Map<String, String> nc = new HashMap<>(configs);
    nc.remove(currentConfig);
    for (String cv : sp) {
      
      SimConfigs x = new SimConfigs();
      x.changeValue(currentConfig, cv);
      
      runConfigs(nc);
    } 
  }
  
  /**
   * Change the original SimConfigs.java file to have the given
   * value for the given variable
   * @param variable	name of a parameter in SimConfigs.java
   * @param value		new parameter value
   * @deprecated 		unused
   */
  public static void changeFile(String variable, String value) {
    String wholeFile = "";
    try {
      File jcf = new File("./src/SyntSpec/SimConfigs.java");
      Scanner in = new Scanner(jcf);
      while (in.hasNextLine()) {
        
        String line = in.nextLine();
        if (line.contains(variable)) {
          
          String start = line.substring(0, line.indexOf("=") + 1);
          
          if (-1.0D < Double.parseDouble(value) && Double.parseDouble(value) < 1.0D) {
            
            wholeFile = wholeFile + start + value + ";\n";
            
            continue;
          } 
          wholeFile = wholeFile + start + Integer.toString((int)Double.parseDouble(value)) + ";\n";
          
          continue;
        } 
        
        wholeFile = wholeFile + line + "\n";
      } 
      
      in.close();
      
      PrintWriter out = new PrintWriter(jcf);
      out.print(wholeFile);
      out.close();
    
    }
    catch (Exception e) {
      
      System.out.printf("Error finding: SimConfigs.java", new Object[0]);
      e.printStackTrace();
      System.exit(1);
    } 
  }
  
  /**
   * Given the name of the config file, read through each
   * line and add the variables to the configs HashMap. 
   * Each line in the config file must have either "step"
   * or a single parameter in brackets [ ]
   * @param input		name of file, always "config.txt"
   */
  public static void fillMap(File input) {
    try {
      Scanner in = new Scanner(input);
      while (in.hasNextLine()) {
        
        String line = in.nextLine();
        if (line.contains("step")) {
          
          String configWord = line.substring(0, line.indexOf(":"));
          String rest = line.substring(line.indexOf(":") + 1);
          String[] sp = rest.trim().split(" ");
          double start = Double.parseDouble(sp[0]);
          double end = Double.parseDouble(sp[2]);
          double step = Double.parseDouble(sp[4]);
          
          String values = ""; double i;
          for (i = start; i < end; i += step)
          {
            values = values + " " + Double.toString(i);
          }
          
          if (values != "")
          {
            configs.put(configWord, values.trim()); } 
          continue;
        } 
        if (line.contains("[")) {
          
          String configWord = line.substring(0, line.indexOf(":"));
          String rest = line.substring(line.indexOf(":") + 1);
          String values = rest.substring(rest.indexOf("[") + 1, rest.indexOf("]"));
          
          if (values != "")
          {
            configs.put(configWord, values.trim());
          }
        } 
      } 
      
      in.close();
    } catch (FileNotFoundException e) {
      
      System.out.println("Config file not created?!");
      System.exit(1);
    } 
  }
  
  /**
   * Write out all variables in SimConfigs to a file named
   * <code>FILE_INFO</code>_configs.txt
   */
  public static void writeCurrentConfigs() {
    try {
      PrintWriter cf = new PrintWriter(new FileOutputStream(stamp() + SimConfigs.FILE_INFO + "_configs.txt"));
      SimConfigs sc = new SimConfigs();
      cf.print(sc);
      cf.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("Error logging config settings");
      System.out.println(e);
    } 
  }
  
  /**
   * Output timestamp, run number, and an underscore
   * @return	<code>timestamp + runNumber + "_"</code>
   */
  public static String stamp() {
    return timestamp + runNumber + "_";
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\Wrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */