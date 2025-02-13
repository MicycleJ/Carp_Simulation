package SyntSpec;
import java.util.ArrayList;
import java.util.Random;

/**
 * SSCarps represents the most basic form of fish. Fish that are
 * SS, or have the value "isSS" as <code>TRUE</code>, are sterile.
 */
public class SSCarps
{
  static final int NO_GENERATION = -2147483648;
  static final double WINTERKILL_MORTALITY = Math.random() * 0.04600000000000004D + 0.954D;
  
  static final double SEINING_MORTALITY = 0.25D;
  
  static final double RETURN_PROBABILITY = Math.random() * 0.508D + 0.38D;
  int age;
  double weight;
  double length;
  SSLake location;
  
  String name;
  
  boolean isAlive = true;
  
  SSLake dispersalLake;
  
  SSLake bornLake;
  
  boolean isSS;
  
  String gender;
  
  int generation;
  
  static final double lambda = 0.13D;
  
  /**
   * SSCarps constructor, takes in ss (sterility status). Generation
   * defaults to <code>Integer.MIN_VALUE</code>
   * @param newName			identifying ID tag for the fish
   * @param newAge			fish age, in integer years
   * @param newLocation		current lake and birth lake of the fish
   * @param ss				<code>TRUE</code> if fish is sterile
   * @param fgender			gender of the fish
   */
  public SSCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
    this.name = newName;
    this.age = newAge;
    this.location = newLocation;
    this.bornLake = newLocation;
    this.length = 120.0D;
    this.isSS = ss;
    this.gender = fgender;
    this.generation = Integer.MIN_VALUE;
  }
  
  /**
   * SSCarps constructor, takes in ss (sterility status) and generation.
   * Length defaults to 120
   * @param newName			identifying ID tag for the fish
   * @param newAge			fish age, in integer years
   * @param newLocation		current lake and birth lake of the fish
   * @param ss				<code>TRUE</code> if fish is sterile
   * @param fgender			gender of the fish
   * @param genome			DEPRECATED / UNUSED
   * @param gen				generation, used for GENE_GENERATION_DEATH.
   * 						-1 if there should be no countdown to death
   */
  public SSCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender, String genome, int gen) {
    this.name = newName;
    this.age = newAge;
    this.location = newLocation;
    this.bornLake = newLocation;
    this.length = 120.0D;
    this.isSS = ss;
    this.gender = fgender;
    this.generation = gen;
  }
  
  /**
   * SSCarps constructor, takes in ss and genome. However, genome is unused.
   * Length defaults to 120
   * @param newName			identifying ID tag for the fish
   * @param newAge			fish age, in integer years
   * @param newLocation		current lake and birth lake of the fish
   * @param ss				<code>TRUE</code> if fish is sterile
   * @param fgender			gender of the fish
   * @param genome			DEPRECATED / UNUSED
   */
  public SSCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender, String genome) {
    this.name = newName;
    this.age = newAge;
    this.location = newLocation;
    this.bornLake = newLocation;
    this.length = 120.0D;
    this.isSS = ss;
    this.gender = fgender;
  }
  
  /**
   * SSCarps constructor, takes in ss, length, and splits lakes into the 
   * current location, birth lake, and dispersal lake. Generation is set 
   * to <code>Integer.MIN_VALUE</code>
   * @param newName			identifying ID tag for the fish
   * @param newAge			fish age, in integer years
   * @param length			input length in cm
   * @param newLocation		SSLake, the current location of the fish
   * @param bornLake		SSLake, the lake the fish was born in
   * @param dispersal		SSLake, used for migration
   * @param ss				<code>TRUE</code> if fish is sterile
   * @param fgender			gender of the fish
   */
  public SSCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
    this.name = newName;
    this.age = newAge;
    this.location = newLocation;
    this.bornLake = bornLake;
    this.dispersalLake = dispersal;
    this.length = length;
    this.isSS = ss;
    this.gender = fgender;
    this.generation = Integer.MIN_VALUE;
  }
  
  /**
   * SSCarps constructor, takes in ss, length, and splits lakes into the 
   * current location, birth lake, and dispersal lake. Also takes in
   * genome, which is unused. Generation is set to <code>Integer.MIN_VALUE</code>
   * @param newName			identifying ID tag for the fish
   * @param newAge			fish age, in integer years
   * @param length			input length in cm
   * @param newLocation		SSLake, the current location of the fish
   * @param bornLake		SSLake, the lake the fish was born in
   * @param dispersal		SSLake, used for migration
   * @param ss				<code>TRUE</code> if fish is sterile
   * @param fgender			gender of the fish
   * @param genome			DEPRECATED/UNUSED
   */
  public SSCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String genome) {
    this.name = newName;
    this.age = newAge;
    this.location = newLocation;
    this.bornLake = bornLake;
    this.dispersalLake = dispersal;
    this.length = length;
    this.isSS = ss;
    this.gender = fgender;
    
    this.generation = Integer.MIN_VALUE;
  }
  
  /**
   * Public boolean for whether fish is alive
   * @return	this.isAlive
   */
  public boolean isAlive() {
    return this.isAlive; //originally "return isAlive()"
  }
  
  /**
   * Return whether the current fish is large enough to reproduce. Fish
   * longer than 360cm are guaranteed to be mature, fish under 330cm
   * are guaranteed to be immature, and in between these values there
   * is an intermediate probability of maturity.
   * @return	if a random double \< reproductive function
   */
  public boolean isMature() {
    double reproductive = 1.0D / (1.0D + Math.exp(-1.0D * (-199.517D + 0.5796D * this.length)));
    
    String x = this.name + Double.toString(this.length);
    Random rng = new Random(x.hashCode());
    
    return (rng.nextDouble() < reproductive);
  }
  
  /**
   * Across every lake in the simulation, find the number of fish
   * @return	adultCount, a count of fish older than 0
   */
  public static int countAdults() {
    int adultCount = 0;
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      
      adultCount = 0;
      
      for (SSCarps current : SSCarp.fishes) {
        
        if (current.location.equals(SSLakeData.lakes[lake]))
        {
          if (current.age > 0)
          {
            adultCount++;
          }
        }
      } 
    } 
    
    return adultCount;
  }
  
  /**
   * Count the number of ADULT ss (sterile) fish across all lakes in the simulation
   * @return	SSMaleCount, the number of SS male fish older than 2
   */
  public static int countSSMales() {
    int SSMaleCount = 0;
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      
      SSMaleCount = 0;
      
      for (SSCarps current : SSCarp.fishes) {
        
        if (current.location.equals(SSLakeData.lakes[lake]))
        {
          if (current.isSS)
          {
            if (current.gender == "male" && current.age > 2)
            {
              
              SSMaleCount++;
            }
          }
        }
      } 
    } 
    
    return SSMaleCount;
  }
  
  /**
   * Find the mass of a given fish
   * @return	6 * 10^{-8} * this.length^{2.763}
   */
  public double calcMass() {
    return 6.0D * Math.pow(10.0D, -5.0D) * Math.pow(this.length, 2.763D) / 1000.0D;
  }
  
  /**
   * Get a list of possible offspring a female can have. Offspring are
   * a mix of males and females, based on MALE_OFFSPRING_PROBABILITY
   * @return	ArrayList of SSCarps, the number of which is determined
   * 			by the function <code>juvenileSurvival()</code>
   */
  public ArrayList<SSCarps> tryReproduce() {
    ArrayList<SSCarps> babies = new ArrayList<>();
    
    int survivedBabies = juvenileSurvival();
    
    for (int i = 0; i < survivedBabies; i++) {
      
      if (Math.random() < SimConfigs.MALE_OFFSPRING_PROBABILITY) {
        
        babies.add(new FLCarps("Fish_" + SSCarp.ID++, 0, this.location, false, "male"));
      }
      else {
        
        babies.add(new FLCarps("Fish_" + SSCarp.ID++, 0, this.location, false, "female"));
      } 
    } 
    
    return babies;
  }
  
  /**
   * Get the overall density of the lake the fish resides in
   * @return	<code>SSLakeData.carpDensity</code>[current_lake]
   */
  public double getMyLakeDensity() {
    double stock = 0.0D;
    
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      if (this.location.equals(SSLakeData.lakes[lake])) {
        stock = SSLakeData.carpDensity[lake];
      }
    } 
    return stock;
  }
  
  /**
   * Get the adult density of the lake the fish resides in
   * @return	<code>SSLakeData.adultCarpDensity</code>[current_lake]
   */
  public double getMyAdultLakeDensity() {
    double stock = 0.0D;
    
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      if (this.location.equals(SSLakeData.lakes[lake])) {
        stock = SSLakeData.adultCarpDensity[lake];
      }
    } 
    
    return stock;
  }
  
  /**
   * Get the number of juveniles that would survive from a single mother's 
   * brood. Maximum value (for an empty lake) is 838, and if density is 
   * greater than 605, no offspring will survive.
   * @return	number of juveniles that will survive from a single brood
   */
  public int juvenileSurvival() {
    int survived = 0;
    double lethality = 1.0D;
    double stock = getMyAdultLakeDensity();
    
    survived = (int)(lethality * 106.6D * 3.9228D * Math.exp(-0.01D * stock));
    
    return 2 * survived;
  }
  
  /**
   * With some probability, move the fish to its dispersalLake
   */
  public void tryReturn() {
    double rand = Math.random();
    if (rand < RETURN_PROBABILITY && this.dispersalLake != null)
    {
      this.location = this.dispersalLake;
    }
  }
  
  /**
   * With some probability, move fish to any of the other lakes
   * present in the simulation
   */
  public void tryMove() {
    double move = Math.random();
    
    double prob = 0.0D;
    if (this.location != null && this.dispersalLake != null) {
      
      double[] movementProbabilites = SSLakeData.getMovementProbabilities(this.bornLake, this.dispersalLake, this.location);
      
      for (int i = 0; i < movementProbabilites.length; i++) {
        
        prob += movementProbabilites[i];
        if (move < prob) {
          
          this.location = SSLakeData.lakes[i];
          break;
        } 
      } 
    } 
  }
  
  /**
   * With some probability, more likely if age >=2, call <code>doDispersal</code>
   */
  public void tryDispersal() {
    double rand = Math.random();
    
    if (this.dispersalLake == null)
    {
      
      if (this.age == 0 && rand < 0.0016D) {
        
        doDispersal();
      }
      else if (this.age == 1 && rand < 0.0016D) {
        
        doDispersal();
      }
      else if (this.age >= 2 && rand < 0.5D) {
        
        doDispersal();
      } 
    }
  }
  
  /**
   * Fish may migrate between lakes, and afterwards the fish's current
   * location is set as its dispersalLake
   */
  public void doDispersal() {
    double move = Math.random();
    
    double prob = 0.0D;
    if (this.location != null) {
      
      double[] movementProbabilites = { 0.0D, 1.0D };
      
      for (int i = 0; i < movementProbabilites.length; i++) {
        
        prob += movementProbabilites[i];
        if (move < prob) {
          
          this.location = SSLakeData.lakes[i];
          
          break;
        } 
      } 
    } 
    
    this.dispersalLake = this.location;
  }
  
  /**
   * With probability WINTERKILL_MORTALITY, kill the fish
   */
  public void winterkillMortality() {
    double rand = Math.random();
    
    if (rand < WINTERKILL_MORTALITY)
    {
      this.isAlive = false;
    }
  }
  
  /**
   * With probability SeiningRemovePercent (an input parameter),
   * kill the current fish
   */
  public void seiningMortality() {
    double rand = Math.random();
    
    if (rand < SimConfigs.SeiningRemovePercent)
    {
      
      this.isAlive = false;
    }
  }
  
  /**
   * Kill the fish due to natural causes, dependent on length
   */
  public void tryDies() {
    double Linf = 440.0D;
    
    for (SSCarps current : SSCarp.fishes) {
      
      double c = current.length / Linf;
      double mort = Math.random();
      double check = mort;
      double instmortality = 0.06D * Math.pow(c, -1.5D);
      double percentmortality = 1.0D - Math.exp(-instmortality);
      double check2 = percentmortality;
      System.out.println("Mortality = " + check2 + " random number = " + check);
      if (mort < percentmortality) {
        
        this.isAlive = false;
        System.out.println(" alive = " + this.isAlive);
      } 
    } 
  }
  
  /**
   * return <code>TRUE</code>, as "SSCarps" are always wildtype.
   * @return	<code>TRUE</code>
   */
  public boolean isWild() {
    return true;
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */