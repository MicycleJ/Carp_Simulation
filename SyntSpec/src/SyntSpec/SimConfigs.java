/*     */ package SyntSpec;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ public class SimConfigs
/*     */ {
/*   7 */   public static int WORKER_THREAD_COUNT = 24;
/*     */   
/*     */   public static final boolean useWrapper = true;
/*     */   
/*     */   public static final boolean multipleLogs = true;
/*     */   public static final boolean outputPostSimulationData = false;
/*     */   public static final int filterSeining = 1;
/* mike*/   public static String FILE_INFO = "";
/*  14 */   public static int INITIAL_FISH_GENE_GENERATION = -1;
/*  15 */   public static int INITIAL_FISH = 10;
/*  16 */   public static String INITIAL_GENOTYPE = "";
/*  17 */   public static double INITIAL_MALE_PERCENT = 0.5D;
/*  18 */   public static int INITIAL_FISH_MIN_AGE = 5;
/*  19 */   public static int INITIAL_FISH_MAX_AGE = 15;
/*     */   
/*  21 */   public static int MAX_FISH_AGE = 70;
/*     */   
/*  23 */   public static int RESTOCK_FREQUENCY = 1;
/*  24 */   public static int RESTOCK_TIME = Integer.MAX_VALUE;
/*     */   
/*  26 */   public static int RESTOCK_TIME_ONE = -1;
/*  27 */   public static int RESTOCK_TIME_TWO = -1;
/*  28 */   public static int RESTOCK_TIME_THREE = -1;
/*  29 */   public static int RESTOCK_TIME_FOUR = -1;
/*     */   
/*  31 */   public static int RESTOCK_GENE_GENERATION = -1;
/*  32 */   public static int RESTOCK_AMOUNT = 100;
/*  33 */   public static int RESTOCK_FISH_AGE = 3;
/*  34 */   public static double RESTOCK_MALE_PERCENT = 0.5D;
/*  35 */   public static String RESTOCK_GENOTYPE = "";
/*     */   
/*  37 */   public static int RESTOCK_GENE_GENERATION_GROUP2 = 0;
/*  38 */   public static int RESTOCK_AMOUNT_GROUP2 = 0;
/*  39 */   public static int RESTOCK_FISH_AGE_GROUP2 = 3;
/*  40 */   public static double RESTOCK_MALE_PERCENT_GROUP2 = 0.5D;
/*  41 */   public static String RESTOCK_GENOTYPE_GROUP2 = "";
/*     */   
/*  43 */   public static int RESTOCK_GENE_GENERATION_GROUP3 = 0;
/*  44 */   public static int RESTOCK_AMOUNT_GROUP3 = 0;
/*  45 */   public static int RESTOCK_FISH_AGE_GROUP3 = 3;
/*  46 */   public static double RESTOCK_MALE_PERCENT_GROUP3 = 0.5D;
/*  47 */   public static String RESTOCK_GENOTYPE_GROUP3 = "";
/*     */   
/*  49 */   public static double RELATIVE_FITNESS = 1.0D;
/*     */   
/*  51 */   public static int GENE_GENERATION_DEATH = 5;
/*     */ 
/*     */   
/*  54 */   public static int MAX_ALLELE = 8;
/*     */ 
/*     */   
/*  57 */   public static int SIMULATION_DURATION = 2;
/*  58 */   public static int SIMULATION_RUNS = 1;
/*  59 */   public static double MALE_OFFSPRING_PROBABILITY = 0.5D;
/*     */   
/*  61 */   public static double PromoterMutationFrequency = 0.05D;
/*  62 */   public static double PTAMutationFrequency = 0.05D;
/*  63 */   public static double FLMutationFrequency = 0.05D;
/*     */   
/*  65 */   public static double homingFrequency = 0.05D;
/*  66 */   public static double NHEJfrequency = 0.05D;
/* me  */   public static double editingFrequency = 1.0D;
/*     */   
/*  68 */   public static double SeiningOccurProbability = 0.0D;
/*  69 */   public static double SeiningRemovePercent = 0.25D;
/*     */ 
/*     */   
/*  72 */   public static String fishAgeLengthFile = "50yfishDataSeining25.csv";
/*     */   
/*     */   public void changeValue(String varName, String value) {
/*     */     try {
/*  88 */       if (value.length() > 0 && !Character.isDigit(value.charAt(0))) {
/*     */         
/*  90 */         getClass().getField(varName).set(this, value);
/*     */       }
/*  92 */       else if (value.equals("0")) {
/*     */         
/*  94 */         getClass().getField(varName).set(this, Integer.valueOf(0));
/*     */       }
/*  96 */       else if (-1.0D < Double.parseDouble(value) && Double.parseDouble(value) < 1.0D) {
/*     */         
/*  98 */         getClass().getField(varName).set(this, Double.valueOf(Double.parseDouble(value)));
/*     */       }
/*     */       else {
/*     */         
/* 102 */         getClass().getField(varName).set(this, Integer.valueOf((int)Double.parseDouble(value)));
/*     */       } 
/* 104 */     } catch (Throwable throwable) {}
/*     */   }
/*     */   
/*     */   public String toString() {
/* 108 */     StringBuilder result = new StringBuilder();
/* 109 */     String newLine = System.getProperty("line.separator");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     Field[] fields = getClass().getDeclaredFields();
/*     */ 
/*     */     
/* 119 */     for (Field field : fields) {
/*     */       
/*     */       try {
/* 122 */         result.append(field.getName());
/* 123 */         result.append(": ");
/*     */         
/* 125 */         result.append(field.get(this));
/* 126 */       } catch (IllegalAccessException ex) {
/* 127 */         System.out.println(ex);
/*     */       } 
/* 129 */       result.append(newLine);
/*     */     } 
/*     */ 
/*     */     
/* 133 */     return result.toString();
/*     */   }
/*     */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SimConfigs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */