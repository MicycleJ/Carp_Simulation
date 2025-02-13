package SyntSpec;

import java.lang.reflect.Field;

/**
 * SimConfigs objects hold all of the parameters for a simulation. While
 * currently set to defaults, they can be changed within the program.
 */
public class SimConfigs
{
  public static int WORKER_THREAD_COUNT = 24;
  
  public static String FILE_INFO = "";
  public static int INITIAL_FISH_GENE_GENERATION = -1;
  public static int INITIAL_FISH = 10;
  public static String INITIAL_GENOTYPE = "WW";
  public static double INITIAL_MALE_PERCENT = 0.5D;
  public static int INITIAL_FISH_MIN_AGE = 5;
  public static int INITIAL_FISH_MAX_AGE = 15;
  
  public static int MAX_FISH_AGE = 70;
  
  public static int RESTOCK_FREQUENCY = 1;
  public static int RESTOCK_TIME = Integer.MAX_VALUE;
  
  public static int RESTOCK_TIME_ONE = -1;
  public static int RESTOCK_TIME_TWO = -1;
  public static int RESTOCK_TIME_THREE = -1;
  public static int RESTOCK_TIME_FOUR = -1;
  
  public static int RESTOCK_GENE_GENERATION = -1;
  public static int RESTOCK_AMOUNT = 100;
  public static int RESTOCK_FISH_AGE = 3;
  public static double RESTOCK_MALE_PERCENT = 0.5D;
  public static String RESTOCK_GENOTYPE = "";
  
  public static int RESTOCK_GENE_GENERATION_GROUP2 = 0;
  public static int RESTOCK_AMOUNT_GROUP2 = 0;
  public static int RESTOCK_FISH_AGE_GROUP2 = 3;
  public static double RESTOCK_MALE_PERCENT_GROUP2 = 0.5D;
  public static String RESTOCK_GENOTYPE_GROUP2 = "";
  
  public static int RESTOCK_GENE_GENERATION_GROUP3 = 0;
  public static int RESTOCK_AMOUNT_GROUP3 = 0;
  public static int RESTOCK_FISH_AGE_GROUP3 = 3;
  public static double RESTOCK_MALE_PERCENT_GROUP3 = 0.5D;
  public static String RESTOCK_GENOTYPE_GROUP3 = "";
  
  public static double RELATIVE_FITNESS = 1.0D;
  
  public static int GENE_GENERATION_DEATH = 5;

  public static int SIMULATION_DURATION = 4;
  public static int SIMULATION_RUNS = 1;
  public static double MALE_OFFSPRING_PROBABILITY = 0.5D;
  
  public static double PromoterMutationFrequency = 0.05D;
  public static double PTAMutationFrequency = 0.05D;
  public static double FLMutationFrequency = 0.05D;
  
  public static double homingFrequency = 0.05D;
  public static double NHEJfrequency = 0.05D;
  public static double editingFrequency = 1.0D;
  public static double maternalCarryoverFrequency = 1.0D;
  
  public static double SeiningOccurProbability = 0.0D;
  public static double SeiningRemovePercent = 0.25D;

  
  public static String fishAgeLengthFile = "50yfishDataSeining25.csv";
  
  /**
   * change the value of variable
   * @param varName		variable to be changed
   * @param value		new value, as a string
   */
  public void changeValue(String varName, String value) {
    try {
      if (value.length() > 0 && !Character.isDigit(value.charAt(0))) {
        
        getClass().getField(varName).set(this, value);
      }
      else if (value.equals("0")) {
        
        getClass().getField(varName).set(this, Integer.valueOf(0));
      }
      else if (-1.0D < Double.parseDouble(value) && Double.parseDouble(value) < 1.0D) {
        
        getClass().getField(varName).set(this, Double.valueOf(Double.parseDouble(value)));
      }
      else {
        
        getClass().getField(varName).set(this, Integer.valueOf((int)Double.parseDouble(value)));
      } 
    } catch (Throwable throwable) {}
  }
  
  /**
   * convert any value to a string
   * TODO understand this function better
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    String newLine = System.getProperty("line.separator");
    
    Field[] fields = getClass().getDeclaredFields();

    for (Field field : fields) {
      
      try {
        result.append(field.getName());
        result.append(": ");
        
        result.append(field.get(this));
      } catch (IllegalAccessException ex) {
        System.out.println(ex);
      } 
      result.append(newLine);
    } 
    
    return result.toString();
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SimConfigs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */