package SyntSpec;

/**
 * SSLakeData contains an array of lakes in the simulation, and values
 * for carpDensity and adultCarpDensity of each lake. Defaults to having 
 * two lakes, lake "Marsh" and lake "Susan"
 */
public class SSLakeData
{
  static double[] carpDensity = new double[2];
  static double[] adultCarpDensity = new double[2];
			// TODO take in winterprob from simconfigs, don't default to 0.0
			// TODO make lake area also configurable
  static SSLake[] lakes = new SSLake[] { new SSLake("Marsh", 0.0D, 100.0D), new SSLake("Susan", 0.0D, 100.0D) };
  
  /**
   * Get density of all carp in each lake, in number of fish / hectare
   */
  public static void countDensity() {
    int[] carpCount = new int[lakes.length];
    for (SSCarps current : SSCarp.fishes) {
      
      for (int i = 0; i < lakes.length; i++) {
        
        if (lakes[i].equals(current.location) && current.age > 1) {
          carpCount[i] = carpCount[i] + 1;
        }
      } 
    } 
    
    for (int lake = 0; lake < lakes.length; lake++) {
      carpDensity[lake] = carpCount[lake] / (lakes[lake]).lakeArea;
    }
  }
  
  /**
   * Get density of all adult carp in each lake, as number of adult
   * fish per hectare
   */
  public static void countAdultDensity() {
    int[] adultCarpCount = new int[lakes.length];
    
    for (SSCarps current : SSCarp.fishes) {
      
      for (int i = 0; i < lakes.length; i++) {
        
        if (lakes[i].equals(current.location) && current.age > 2) {
          adultCarpCount[i] = adultCarpCount[i] + 1;
        }
      } 
    } 
    
    for (int lake = 0; lake < lakes.length; lake++) {
      adultCarpDensity[lake] = adultCarpCount[lake] / (lakes[lake]).lakeArea;
    }
  }
  
  /**
   * Get probability that an individual will move to a lake. We assume
   * there are only two lakes. An individual in lake 0 (Marsh) will always 
   * stay in lake 0. An individual in lake 1 (Susan) has 30% chance it will
   * move to lake 0.
   * @param born		lake the fish was born in
   * @param dispersal	a lake for dispersal
   * @param location	lake the fish is currently in
   * @return			an array of two double, for probability of movement
   * 					to the two lakes in the simulation
   * @deprecated 		used for "simulateFishMovement" which is not
   * 					currently used in the simulation
   */
  public static double[] getMovementProbabilities(SSLake born, SSLake dispersal, SSLake location) {
    if (born.equals(lakes[0]) && dispersal.equals(lakes[1]) && location.equals(lakes[0]))
    {
      return new double[] { 1.0D, 0.0D };
    }
    if (born.equals(lakes[0]) && dispersal.equals(lakes[1]) && location.equals(lakes[1]))
    {
      return new double[] { 0.3D, 0.7D };
    }
    
    if (born.equals(lakes[0]) && dispersal.equals(lakes[0]) && location.equals(lakes[0]))
    {
      return new double[] { 1.0D, 0.0D };
    }
    if (born.equals(lakes[0]) && dispersal.equals(lakes[0]) && location.equals(lakes[1]))
    {
      
      return new double[] { 0.3D, 0.7D };
    }
    
    if (born.equals(lakes[1]) && dispersal.equals(lakes[0]) && location.equals(lakes[0]))
    {
      return new double[] { 1.0D, 0.0D };
    }
    if (born.equals(lakes[1]) && dispersal.equals(lakes[0]) && location.equals(lakes[1]))
    {
      
      return new double[] { 0.3D, 0.7D };
    }
    
    if (born.equals(lakes[1]) && dispersal.equals(lakes[1]) && location.equals(lakes[0]))
    {
      return new double[] { 1.0D, 0.0D };
    }
    if (born.equals(lakes[1]) && dispersal.equals(lakes[1]) && location.equals(lakes[1]))
    {
      
      return new double[] { 0.3D, 0.7D };
    }
    
    System.out.println("Warning! Invalid movment probability");
    return null;
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSLakeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */