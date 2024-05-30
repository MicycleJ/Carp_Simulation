/*     */ package SyntSpec;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Scanner;


/*     */ public class Wrapper {
/*     */   public static Map<String, String> configs;
/*     */   public static final String valueDelim = " ";
/*  15 */   public static String timestamp = ""; public static final String pathToFile = "./src/SyntSpec/"; public static final String JavaConfigFileName = "SimConfigs.java";
/*  16 */   public static int runNumber = 1;
/*     */   
/*     */   public static void main(String[] args) {
/*  20 */     configs = new HashMap<>();
/*  21 */     File configFile = new File("config.txt");
/*     */     
/*  27 */     if (configFile.exists()) {
/*     */       
/*  29 */       fillMap(configFile);
/*     */       
/*  31 */       runConfigs(configs);
/*     */     }
/*     */     else {
/*     */       
/*  35 */       System.out.println("Creating sample \"config.txt\" file.  Please modify then run again");
/*     */       
/*     */       try {
/*  38 */         PrintWriter out = new PrintWriter(configFile);
/*  39 */         out.printf("INITIAL_FISH: 50 to 201 step 50\n", new Object[0]);
/*  40 */         out.printf("RESTOCK_AMOUNT: [10 100]\n", new Object[0]);
/*  41 */         out.printf("RESTOCK_GENOTYPE: [GR ppttll oldsettings]\n", new Object[0]);
/*     */         
/*  43 */         out.close();
/*  44 */       } catch (FileNotFoundException e) {
/*  45 */         System.out.printf("Error creating config files (and nonexistant)\n", new Object[0]);
/*  46 */         System.exit(1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void runConfigs(Map<String, String> configs) {
/*  54 */     if (configs.isEmpty()) {
/*     */       
/*  56 */       String[] input = new String[0];
/*     */       
/*  58 */       DateFormat df = new SimpleDateFormat("dd-MM-yy_HH~mm~ss");
/*  59 */       Date dateobj = new Date();
/*  60 */       timestamp = df.format(dateobj) + "_";
/*  61 */       writeCurrentConfigs();
/*  62 */       SSCarp.mainOld(input);
/*  63 */       runNumber++;
/*     */       
/*     */       return;
/*     */     } 
/*  67 */     String currentConfig = (String)configs.keySet().toArray()[0];
/*  68 */     String values = configs.get(currentConfig);
/*  69 */     String[] sp = values.split(" ");
/*     */     
/*  74 */     Map<String, String> nc = new HashMap<>(configs);
/*  75 */     nc.remove(currentConfig);
/*  76 */     for (String cv : sp) {
/*     */       
/*  80 */       SimConfigs x = new SimConfigs();
/*  81 */       x.changeValue(currentConfig, cv);
/*     */       
/*  84 */       runConfigs(nc);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void changeFile(String variable, String value) {
/*  91 */     String wholeFile = "";
/*     */     try {
/*  93 */       File jcf = new File("./src/SyntSpec/SimConfigs.java");
/*  94 */       Scanner in = new Scanner(jcf);
/*  95 */       while (in.hasNextLine()) {
/*     */         
/*  97 */         String line = in.nextLine();
/*  98 */         if (line.contains(variable)) {
/*     */           
/* 100 */           String start = line.substring(0, line.indexOf("=") + 1);
/*     */           
/* 102 */           if (-1.0D < Double.parseDouble(value) && Double.parseDouble(value) < 1.0D) {
/*     */             
/* 104 */             wholeFile = wholeFile + start + value + ";\n";
/*     */             
/*     */             continue;
/*     */           } 
/* 108 */           wholeFile = wholeFile + start + Integer.toString((int)Double.parseDouble(value)) + ";\n";
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 113 */         wholeFile = wholeFile + line + "\n";
/*     */       } 
/*     */       
/* 117 */       in.close();
/*     */       
/* 119 */       PrintWriter out = new PrintWriter(jcf);
/* 120 */       out.print(wholeFile);
/* 121 */       out.close();
/*     */     
/*     */     }
/* 124 */     catch (Exception e) {
/*     */       
/* 126 */       System.out.printf("Error finding: SimConfigs.java", new Object[0]);
/* 127 */       e.printStackTrace();
/* 128 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void fillMap(File input) {
/*     */     try {
/* 135 */       Scanner in = new Scanner(input);
/* 136 */       while (in.hasNextLine()) {
/*     */         
/* 138 */         String line = in.nextLine();
/* 139 */         if (line.contains("step")) {
/*     */           
/* 141 */           String configWord = line.substring(0, line.indexOf(":"));
/* 142 */           String rest = line.substring(line.indexOf(":") + 1);
/* 143 */           String[] sp = rest.trim().split(" ");
/* 144 */           double start = Double.parseDouble(sp[0]);
/* 145 */           double end = Double.parseDouble(sp[2]);
/* 146 */           double step = Double.parseDouble(sp[4]);
/*     */           
/* 149 */           String values = ""; double i;
/* 150 */           for (i = start; i < end; i += step)
/*     */           {
/* 152 */             values = values + " " + Double.toString(i);
/*     */           }
/*     */           
/* 156 */           if (values != "")
/*     */           {
/* 158 */             configs.put(configWord, values.trim()); } 
/*     */           continue;
/*     */         } 
/* 161 */         if (line.contains("[")) {
/*     */           
/* 163 */           String configWord = line.substring(0, line.indexOf(":"));
/* 164 */           String rest = line.substring(line.indexOf(":") + 1);
/* 165 */           String values = rest.substring(rest.indexOf("[") + 1, rest.indexOf("]"));
/*     */           
/* 168 */           if (values != "")
/*     */           {
/* 170 */             configs.put(configWord, values.trim());
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 178 */       in.close();
/* 179 */     } catch (FileNotFoundException e) {
/*     */       
/* 181 */       System.out.println("Config file not created?!");
/* 182 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void writeCurrentConfigs() {
/*     */     try {
/* 191 */       PrintWriter cf = new PrintWriter(new FileOutputStream(stamp() + SimConfigs.FILE_INFO + "_configs.txt"));
/* 192 */       SimConfigs sc = new SimConfigs();
/* 193 */       cf.print(sc);
/* 194 */       cf.close();
/*     */     }
/* 196 */     catch (FileNotFoundException e) {
/* 197 */       System.out.println("Error logging config settings");
/* 198 */       System.out.println(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String stamp() {
/* 207 */     return timestamp + runNumber + "_";
/*     */   }
/*     */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\Wrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */