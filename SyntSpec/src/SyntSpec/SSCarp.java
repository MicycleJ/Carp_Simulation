package SyntSpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class SSCarp {
  static String config = "None";
  static String fileInfo = SimConfigs.FILE_INFO;
  static int currentRun = -1;
  
  static PrintWriter thisRun;
  static PrintWriter thisRun1;
  static PrintWriter thisRun2;
  static PrintWriter thisRun3;
  public static Set<SSCarps> fishes = new HashSet<>();
  public static int ID = 1;
  
  public static long lastTime;
  
  public static boolean debugTime = false;
  public static final String MALE = "male";
  public static final String FEMALE = "female";
  public static Map<Integer, ArrayList<Double>> age2lengthMap;
  
  public static void mainOld(String[] args) {
    System.out.println(Runtime.getRuntime().maxMemory());
    
    if (args.length > 0) {
      
      System.out.println(args[0]);
      SimConfigs.SIMULATION_RUNS = Integer.parseInt(args[0]);
    } 
    if (args.length > 1)
    {
      SimConfigs.SIMULATION_DURATION = Integer.parseInt(args[1]);
    }
    if (args.length > 2)
    {
      fileInfo = args[2];
    }
    
    try {
      thisRun = new PrintWriter(new FileOutputStream(Wrapper.stamp() + fileInfo + ".txt"));
      thisRun1 = new PrintWriter(new FileOutputStream(Wrapper.stamp() + fileInfo + "_AdultAbundance" + ".txt"));
      thisRun2 = new PrintWriter(new FileOutputStream(Wrapper.stamp() + fileInfo + "_Biomass" + ".txt"));
      thisRun3 = new PrintWriter(new FileOutputStream(Wrapper.stamp() + fileInfo + "_Gene_Generation" + ".txt"));
      System.out.println("File open!\n");
    
    }
    catch (IOException e) {
      
      System.out.println("Oops");
    } 
    initilizeAge2LengthMapFromFile(SimConfigs.fishAgeLengthFile);
    
    lastTime = System.currentTimeMillis();
    for (int i = 0; i < SimConfigs.SIMULATION_RUNS; i++) {
      
      currentRun = i + 1;
      thisRun.println("Simulation run #" + (i + 1));
      runSimulation();
    } 
    
    thisRun.close();
    thisRun1.close();
    thisRun2.close();
    thisRun3.close();
  }
  
  public static void runSimulation() {
    fishes.clear();
    ArrayList<SSCarps> babyFish = new ArrayList<>();
    ArrayList<SSCarps> deadFish = new ArrayList<>();
    int i;
    
    System.out.println("Adding " + SimConfigs.INITIAL_FISH + " " + SimConfigs.INITIAL_GENOTYPE);
    for (i = 0; i < SimConfigs.INITIAL_FISH; i++) {
      
      int age = (int)(Math.random() * (SimConfigs.INITIAL_FISH_MAX_AGE - SimConfigs.INITIAL_FISH_MIN_AGE + 1) + SimConfigs.INITIAL_FISH_MIN_AGE);
      
      restock(1, SimConfigs.INITIAL_MALE_PERCENT, age, age2length(age), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.INITIAL_GENOTYPE, SimConfigs.INITIAL_FISH_GENE_GENERATION);
    } 
    
    for (i = 0; i < SimConfigs.SIMULATION_DURATION; i++) {
      
      System.out.println("On year " + (i + 1));
      System.out.println("Current # of fish = " + fishes.size());
      
      SSLakeData.countDensity();
      SSLakeData.countAdultDensity();
      simulateDeath(deadFish);
      
      simulateWinterSeining(deadFish);
      
      if (i < SimConfigs.RESTOCK_TIME && i % SimConfigs.RESTOCK_FREQUENCY == 0) {
        
        restock(SimConfigs.RESTOCK_AMOUNT, SimConfigs.RESTOCK_MALE_PERCENT, SimConfigs.RESTOCK_FISH_AGE, age2length(SimConfigs.RESTOCK_FISH_AGE), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.RESTOCK_GENOTYPE, SimConfigs.RESTOCK_GENE_GENERATION);
        restock(SimConfigs.RESTOCK_AMOUNT_GROUP2, SimConfigs.RESTOCK_MALE_PERCENT_GROUP2, SimConfigs.RESTOCK_FISH_AGE_GROUP2, age2length(SimConfigs.RESTOCK_FISH_AGE_GROUP2), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.RESTOCK_GENOTYPE_GROUP2, SimConfigs.RESTOCK_GENE_GENERATION_GROUP2);
        restock(SimConfigs.RESTOCK_AMOUNT_GROUP3, SimConfigs.RESTOCK_MALE_PERCENT_GROUP3, SimConfigs.RESTOCK_FISH_AGE_GROUP3, age2length(SimConfigs.RESTOCK_FISH_AGE_GROUP3), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.RESTOCK_GENOTYPE_GROUP3, SimConfigs.RESTOCK_GENE_GENERATION_GROUP3);
      } 
      
      int year = i + 1;
      if (year == SimConfigs.RESTOCK_TIME_ONE || year == SimConfigs.RESTOCK_TIME_TWO || year == SimConfigs.RESTOCK_TIME_THREE || year == SimConfigs.RESTOCK_TIME_FOUR) {
        
        restock(SimConfigs.RESTOCK_AMOUNT, SimConfigs.RESTOCK_MALE_PERCENT, SimConfigs.RESTOCK_FISH_AGE, age2length(SimConfigs.RESTOCK_FISH_AGE), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.RESTOCK_GENOTYPE, SimConfigs.RESTOCK_GENE_GENERATION);
        restock(SimConfigs.RESTOCK_AMOUNT_GROUP2, SimConfigs.RESTOCK_MALE_PERCENT_GROUP2, SimConfigs.RESTOCK_FISH_AGE_GROUP2, age2length(SimConfigs.RESTOCK_FISH_AGE_GROUP2), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.RESTOCK_GENOTYPE_GROUP2, SimConfigs.RESTOCK_GENE_GENERATION_GROUP2);
        restock(SimConfigs.RESTOCK_AMOUNT_GROUP3, SimConfigs.RESTOCK_MALE_PERCENT_GROUP3, SimConfigs.RESTOCK_FISH_AGE_GROUP3, age2length(SimConfigs.RESTOCK_FISH_AGE_GROUP3), SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], SimConfigs.RESTOCK_GENOTYPE_GROUP3, SimConfigs.RESTOCK_GENE_GENERATION_GROUP3);
      } 
      stopWatch("restock");
      SSLakeData.countDensity();
      SSLakeData.countAdultDensity();
      simulateFishReproduction(babyFish);
      System.out.println("Final babies: " + babyFish.size());
      
      stopWatch("summer");
      
      SSLakeData.countDensity();
      SSLakeData.countAdultDensity();
      simulateFishGrowth();
      simulateFishAging();
      
      stopWatch("growth");
      
      fishes.addAll(babyFish);
      babyFish.clear();
      
      fishes.removeAll(deadFish);
      deadFish.clear();
      
      stopWatch("adding new fish");
      
      System.out.println();
      SSLakeData.countDensity();
      SSLakeData.countAdultDensity();
      writeData(i + 1);
      writeAbundanceData(i + 1);
      writeBiomassData(i + 1);
      writeGeneGeneration(i + 1);
    } 
  }
  
  public static void simulateFishReturn() {
    for (SSCarps current : fishes)
    {
      current.tryReturn();
    }
  }
  
  public static double age2length(int age) {
    if (age2lengthMap != null && age2lengthMap.get(Integer.valueOf(age)) != null) {
      
      ArrayList<Double> lens = age2lengthMap.get(Integer.valueOf(age));
      return ((Double)lens.get((int)(Math.random() * lens.size()))).doubleValue();
    } 
    
    if (age <= 1)
    {
      return 108.0D;
    }
    if (age <= 2)
    {
      return 250.0D;
    }
    if (age <= 3)
    {
      return 354.66080750000003D;
    }
    if (age <= 4)
    {
      return 395.2004183542328D;
    }
    if (age <= 5)
    {
      return 415.3820395439626D;
    }
    if (age <= 6)
    {
      return 443.7164724540761D;
    }
    if (age <= 7)
    {
      return 475.007864818167D;
    }
    if (age <= 8)
    {
      return 506.34807657992184D;
    }
    if (age <= 9)
    {
      return 525.8954021587807D;
    }
    if (age <= 10)
    {
      return 539.6706964366016D;
    }
    
    return 550.0D;
  }
  
  public static void restock(int amount, double probMale, int age, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, String genome, int gen) {
    boolean ss = false;
    String cname = "SyntSpec.";
    if (genome.length() == 6) {
      
      cname = cname + "SSFLCarps";
    }
    else if (genome.length() == 2) {
      
      cname = cname + "GDCarps";
    }
    else if (genome.length() == 5) {
    	cname = cname + "ASCarps";
    }
    else if (genome.length() > 0) {
      
      cname = cname + "SSGenomeCarps";
    
    }
    else if (config == "SS") {
      
      cname = cname + "SSCarps";
      ss = true;
    }
    else if (config == "FL") {
      
      cname = cname + "FLCarps";
    }
    else {
      
      cname = cname + "SSCarps";
    } 
    
//    System.out.println("Adding " + amount + " " + genome + " " + cname);
    if (amount != 1) {
    	System.out.println("Adding " + amount + " " + genome + " " + cname);
    }
    
    for (int i = 0; i < amount; i++) {
      
      String gender = "female";
      if (Math.random() < probMale)
      {
        gender = "male";
      }
      
      makeFish(cname, "Fish_" + ID++, age, length, newLocation, bornLake, dispersal, ss, gender, genome, gen);
    } 
  }
  
  public static void makeFish(String type, String name, int age, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String gender, String genome, int gen) {
    try {
      Class<?> clazz = Class.forName(type);
      Constructor<?> ctor = clazz.getConstructor(new Class[] { String.class, int.class, double.class, SSLake.class, SSLake.class, SSLake.class, boolean.class, String.class, String.class, int.class });
      Object object = ctor.newInstance(new Object[] { name, Integer.valueOf(age), Double.valueOf(length), newLocation, bornLake, dispersal, Boolean.valueOf(ss), gender, genome, Integer.valueOf(gen) });
      fishes.add((SSCarps)object);
    } catch (Exception e) {
      System.out.println("Error making fish");
      e.printStackTrace();
      System.exit(0);
    } 
  }
  
  public static void addSSFish(int amount) {
    for (int i = 0; i < amount; i++)
    {
      
      fishes.add(new SSCarps("Fish_" + ID++, 3, 400.0D, SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], true, "male"));
    }
  }
  
  public static void addSSFLFish(int amount) {
    for (int i = 0; i < amount; i++) {
      
      String gender = "";
      if (Math.random() < SimConfigs.RESTOCK_MALE_PERCENT) {
        
        gender = "male";
      }
      else {
        
        gender = "female";
      } 
      fishes.add(new SSFLCarps("Fish_" + ID++, 3, 400.0D, SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], false, gender, SimConfigs.RESTOCK_GENOTYPE));
    } 
  }
  
  public static void addGDFish(int amount) {
    for (int i = 0; i < amount; i++) {
      
      String gender = "";
      if (Math.random() < SimConfigs.RESTOCK_MALE_PERCENT) {
        
        gender = "male";
      }
      else {
        
        gender = "female";
      } 
      fishes.add(new GDCarps("Fish_" + ID++, 3, 400.0D, SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], false, gender, SimConfigs.RESTOCK_GENOTYPE));
    } 
  }
  
  public static void addFLFish(int amount) {
    for (int i = 0; i < amount; i++)
    {
      
      fishes.add(new FLCarps("Fish_" + ID++, 3, 400, SSLakeData.lakes[1], SSLakeData.lakes[1], SSLakeData.lakes[1], true, "male", 8));
    }
  }
  
  public static void simulateDispersal() {
    for (SSCarps current : fishes)
    {
      current.tryDispersal();
    }
  }
  
  public static void simulateDeath(ArrayList<SSCarps> deadFish) {
    double Linf = 712.0D;
    
    boolean isAlive = true;
    
    for (SSCarps current : fishes) {
      
      double c = current.length / Linf;
      double mort = Math.random();
      double instmortality = 0.06D * Math.pow(c, -1.5D);
      double percentmortality = 1.0D - Math.exp(-instmortality) + 1.0E-4D * current.getMyLakeDensity();
      
      if (mort < percentmortality || current.age > SimConfigs.MAX_FISH_AGE) {
        
        isAlive = false;
        
        deadFish.add(current);
      } 
    } 
  }
  
  public static void simulateWinterSeining(ArrayList<SSCarps> deadFish) {
    for (SSCarps current : fishes) {
      
      if (!seiningFishToStayInLake(current))
      {
        if (current.location.equals(SSLakeData.lakes[1])) {
          
          current.location.trySeining();
          
          if (current.location.isSeining && current.length > 0.0D) {
            
            current.seiningMortality();
            
            if (!current.isAlive)
            {
              
              deadFish.add(current); } 
          } 
        } 
      }
    } 
  }
  
  public static boolean seiningFishToStayInLake(SSCarps f) {
    boolean toKeep = false;
    
    return toKeep;
  }
  
  public static void simulateWinterkill(ArrayList<SSCarps> deadFish) {
    for (int z = 0; z < SSLakeData.lakes.length; z++) {
      
      SSLake currentLake = SSLakeData.lakes[z];
      currentLake.tryWinterkill();
      if (currentLake.isWinterKilled)
      {
        for (SSCarps currentFish : fishes) {
          
          if (currentFish.isAlive && currentFish.location.equals(currentLake)) {
            
            currentFish.winterkillMortality();
            
            if (!currentFish.isAlive)
            {
              deadFish.add(currentFish);
            }
          } 
        } 
      }
      System.out.println("Lake " + currentLake.lakeName + ", winterkill " + currentLake.isWinterKilled);
    } 
  }
  
  public static void ACBarrier() {
    double rand = Math.random();
    double ACON = 0.99D;
    
    if (rand < ACON) {
      
      boolean AC = true;
    }
    else {
      
      boolean AC = false;
    } 
  }
  
  public static void simulateFishMovement() {
    for (SSCarps current : fishes) {
      
      if (current.isAlive)
      {
        current.tryMove();
      }
    } 
  }
  
  public static void simulateFishReproduction(ArrayList<SSCarps> babyFish) {
    stopWatch("start reproduction");
    ArrayList<SSCarps> maleAdultsWild = new ArrayList<>(fishes.size());
    ArrayList<SSCarps> maleAdultsSynthetic = new ArrayList<>(fishes.size());
    ArrayList<SSCarps> femaleAdults = new ArrayList<>();
    ArrayList<SSCarps> potentialBabyFish = new ArrayList<>(babyFish.size());
    
    for (SSCarps current : fishes) {
      
      if (current.gender == "male" && current.isMature()) {
        
        if (current.isWild()) {
          
          maleAdultsWild.add(current);
          
          continue;
        } 
        maleAdultsSynthetic.add(current);
        continue;
      } 
      if (current.gender == "female" && current.isMature())
      {
        femaleAdults.add(current);
      }
    } 
    
    ArrayList<SSCarps> mothers = new ArrayList<>();
    
    for (SSCarps current : femaleAdults) {
      
      if (current.isAlive) {
        
        ArrayList<SSCarps> newFish = current.tryReproduce();
        
        if (newFish != null) {
          
          potentialBabyFish.addAll(newFish);
          for (int j = 0; j < newFish.size(); j++)
          {
            mothers.add(current);
          }
        } 
      } 
    } 
    
    babyFish.clear();
    
    stopWatch("classifying m/f");
    
    System.out.println("#males=" + (maleAdultsWild.size() + maleAdultsSynthetic.size()) + ", #females=" + femaleAdults.size());
    if (maleAdultsWild.size() + SimConfigs.RELATIVE_FITNESS * maleAdultsSynthetic.size() == 0.0D) {
      return;
    }
    
    ArrayList<SSCarps> fathers = new ArrayList<>();
    
    int babyCount = potentialBabyFish.size();
    int w = maleAdultsWild.size();
    int s = maleAdultsSynthetic.size();
    int wm = 0;
    int sm = 0;
    for (int i = 0; i < babyCount; i++) {
      
      if (Math.random() < w / (w + s * SimConfigs.RELATIVE_FITNESS)) {
        
        int index = (int)(Math.random() * w);
        fathers.add(maleAdultsWild.get(index));
        wm++;
      }
      else {
        
        int index = (int)(Math.random() * s);
        fathers.add(maleAdultsSynthetic.get(index));
        sm++;
      } 
    } 
    System.out.println("percentage of males wild=" + (1.0D * maleAdultsWild.size() / (maleAdultsWild.size() + maleAdultsSynthetic.size())) + ", percentage of wild males reproducing=" + (1.0D * wm / (wm + sm)));
    
    int maximumPossibleBabies = potentialBabyFish.size();
    stopWatch("find partners");
    System.out.println("Potential babies: " + maximumPossibleBabies);
    
    if (SimConfigs.WORKER_THREAD_COUNT <= 1) {
      
      for (int j = 0; j < maximumPossibleBabies; j++)
      {
        SSCarps b = makeBaby(j, potentialBabyFish, mothers, fathers);
        if (b != null)
        {
          babyFish.add(b);      
        }
      }
    }
    else {
      int step = maximumPossibleBabies / SimConfigs.WORKER_THREAD_COUNT;
      int start = 0;
      ExecutorService executor = Executors.newFixedThreadPool(SimConfigs.WORKER_THREAD_COUNT);
      while (start < maximumPossibleBabies) {
        
        Runnable worker = new WorkerThread(start, Math.min(start + step, maximumPossibleBabies), fathers, mothers, potentialBabyFish, babyFish);
        executor.execute(worker);
        start += step;
      } 
      executor.shutdown();
      while (!executor.isTerminated());
    } 
  }
/*      */
  public static SSCarps makeBaby(int i, ArrayList<SSCarps> potentialBabyFish, ArrayList<SSCarps> mothers, ArrayList<SSCarps> fathers) {
    SSCarps currentBaby = potentialBabyFish.get(i);
    SSCarps currentMom = mothers.get(i);
    SSCarps currentDad = fathers.get(i);
    
    if (currentDad.isSS)
    {
      
      return null;
    }
    if (currentDad instanceof FLCarps)
    {
      
      return currentBaby;
    }
    
    if (currentMom instanceof SSFLCarps || currentDad instanceof SSFLCarps) {
      
      if (!(currentMom instanceof SSFLCarps))
      {
        
        currentMom = new SSFLCarps(currentMom);
      }
      if (!(currentDad instanceof SSFLCarps))
      {
        
        currentDad = new SSFLCarps(currentDad);
      }
      SSFLCarps potentialBaby = reproduceSSFL((SSFLCarps)currentMom, (SSFLCarps)currentDad, new SSFLCarps(currentBaby));
      
      return potentialBaby;
    } 
    if (currentMom instanceof GDCarps || currentDad instanceof GDCarps) {
      
      if (!(currentMom instanceof GDCarps))
      {
        currentMom = new GDCarps(currentMom);
      }
      if (!(currentDad instanceof GDCarps))
      {
        currentDad = new GDCarps(currentDad);
      }
      
      GDCarps potentialBaby = reproduceGD((GDCarps)currentMom, (GDCarps)currentDad, new GDCarps(currentBaby));
      
      return potentialBaby;
    } 
    /* Michelle starts adding things here */
    if (currentMom instanceof ASCarps || currentDad instanceof ASCarps) {
    	// convert both parents to ASCarps
    	if (!(currentMom instanceof ASCarps)) {
    		currentMom = new ASCarps(currentMom);
    	}
    	if (!(currentDad instanceof ASCarps)) {
    		currentDad = new ASCarps(currentDad);
    	}
    	
    	ASCarps potentialBaby = reproduceAS((ASCarps)currentMom, (ASCarps)currentDad, new ASCarps(currentBaby));
    	
    	return potentialBaby;
    	
	}
    /* Michelle stops adding things here */
    if (currentMom instanceof SSGenomeCarps || currentDad instanceof SSGenomeCarps) {
      
      if (!(currentMom instanceof SSGenomeCarps)) {
        
        SSGenomeCarps temp = new SSGenomeCarps(currentDad);
        temp.makeWild();
        currentMom = temp;
      } 
      if (!(currentDad instanceof SSGenomeCarps)) {
        
        SSGenomeCarps temp = new SSGenomeCarps(currentMom);
        temp.makeWild();
        currentDad = temp;
      } 
      
      String reproduceType = getReproduceType((SSGenomeCarps)currentMom, (SSGenomeCarps)currentDad);
      
      SSGenomeCarps potentialBaby = null;
      if (reproduceType == "Trojan") {
        
        potentialBaby = reproduceTrojan((SSGenomeCarps)currentMom, (SSGenomeCarps)currentDad, new SSGenomeCarps(currentBaby));
      }
      else if (reproduceType == "FLExtended") {
        
        potentialBaby = reproduceGeneral((SSGenomeCarps)currentMom, (SSGenomeCarps)currentDad, new SSGenomeCarps(currentBaby));
      }
      else if (reproduceType == "AlleleSailMC") {
    	  potentialBaby = reproduceMaternalCarryoverAS((SSGenomeCarps)currentMom, (SSGenomeCarps)currentDad, new SSGenomeCarps(currentBaby));
      }
      else if (reproduceType == "GeneDriveSexSkew") {
    	  potentialBaby = reproduceGDMCSS((SSGenomeCarps)currentMom, (SSGenomeCarps)currentDad, new SSGenomeCarps(currentBaby));
      }
      else {
        
        System.out.println("ERROR! invalid/unknown reproduction type");
        System.exit(0);
      } 
      
      return potentialBaby;
    } 
    
    return potentialBabyFish.get(i);
  }
  
  public static String getReproduceType(SSGenomeCarps mom, SSGenomeCarps dad) {
    if (mom.getGenome().toUpperCase().contains("TROJANS") || dad.getGenome().toUpperCase().contains("TROJANS"))
    {
      return "Trojan";
    }
    else if (mom.getGenome().contains("Amc") || dad.getGenome().contains("Amc"))
    {
    	return "AlleleSailMC";
    }
    else if (mom.getGenome().contains("gdmcss") || dad.getGenome().contains("gdmcss"))
    {
    	return "GeneDriveSexSkew";
    }
    return "FLExtended";
  }
  
  public static SSGenomeCarps reproduceMaternalCarryoverAS(SSGenomeCarps mom, SSGenomeCarps dad, SSGenomeCarps baby) {
	  int nextgen = Math.max(mom.generation, dad.generation);
	  // generation information
	  if (nextgen >= 0) {
	    baby.generation = nextgen + 1;
	  }
	  else {
	    baby.generation = nextgen;
	  } 
	  if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
	  {
	    return null;
	  }
	  
	  // get the number of sails in mom
	  int momSailCount = 0;
	  for (int i = 0; i < mom.getGenome().length(); i++) {
	  	char temp = mom.getGenome().charAt(i);
	  	if (temp == 'S') {
	  		momSailCount++;
	  	}
	  }
	  
	  // get the number of sails in dad
	  int dadSailCount = 0;
	  for (int i = 0; i < dad.getGenome().length(); i++) {
		  char temp = dad.getGenome().charAt(i);
	  	  if (temp == 'S') {
	  		  dadSailCount++;
	  	  }
	  }
	  
	  // initialize child genotype
	  String childGenotypeAS = "Amc";
	  
	  // for a genotype AmcNNFF, we need to get the N loci (3 & 4), and then the F loci (5 & 6)
	  for (int i = 3; i < mom.getGenome().length(); i += 2) {
		  char maternalAllele = 'x';
		  double rand = Math.random();
		  if (rand < 0.5D) {
	    	maternalAllele = mom.getGenome().charAt(0 + i);
		  }
		  else {
	    	maternalAllele = mom.getGenome().charAt(1 + i);
		  }
		  if (maternalAllele == 'F' && momSailCount != 0) {
	    	// get probability F remains an F
	    	double escapeProbability = Math.pow((1-SimConfigs.editingFrequency), momSailCount);
	    	rand = Math.random();
	   		if (rand > escapeProbability) {
	   			maternalAllele = 'M';
	    	}
		  }
		  // maternal carryover may occur
		  if (maternalAllele == 'F' && momSailCount != 0) {
			  // get probability F remains an F
			  double escapeProbability = Math.pow((1-SimConfigs.maternalCarryoverFrequency), momSailCount);
		      rand = Math.random();
		   	  if (rand > escapeProbability) {
		   		  maternalAllele = 'M';
		      }
		  }
	    	
		  char paternalAllele = 'x';
		  rand = Math.random();
		  if (rand < 0.5D) {
	    	paternalAllele = dad.getGenome().charAt(0 + i);
		  }
		  else {
	    	paternalAllele = dad.getGenome().charAt(1 + i);
		  }
		  if (paternalAllele == 'F' && dadSailCount != 0) {
	    	// get probability F remains an F
	   		double escapeProbability = Math.pow((1-SimConfigs.editingFrequency), dadSailCount);
	   		rand = Math.random();
	   		if (rand > escapeProbability) {
	   			paternalAllele = 'M';
    		}
	   	  }
		  
		  // maternal carryover may occur
		  if (paternalAllele == 'F' && momSailCount != 0) {
			  // get probability F remains an F
			  double escapeProbability = Math.pow((1-SimConfigs.maternalCarryoverFrequency), momSailCount);
		      rand = Math.random();
		   	  if (rand > escapeProbability) {
		   		paternalAllele = 'M';
		      }
		  }
		  
	    	
		  childGenotypeAS = childGenotypeAS + maternalAllele + paternalAllele;
	    	
	  }
	    
	  baby.setGenome(childGenotypeAS);
	  // set wild ??
	    
	  if (childGenotypeAS.contains("F")) {
	    double rand = Math.random();
	    if (rand < 0.5D) {
	      baby.gender = "male";
	    } else {
		  baby.gender = "female";
	    } 
	  }
	  else {
		  baby.gender = "male";
	  }
	    
	  return baby;
  }
  
  public static SSGenomeCarps reproduceGDMCSS(SSGenomeCarps mom, SSGenomeCarps dad, SSGenomeCarps baby) {
	  int nextgen = Math.max(mom.generation, dad.generation);
	  // generation information
	  if (nextgen >= 0) {
	    baby.generation = nextgen + 1;
	  }
	  else {
	    baby.generation = nextgen;
	  } 
	  if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
	  {
	    return null;
	  }
	  
	  char maternalGD = 'x';
	  if (mom.getGenome().contains("G")) {
		  if (mom.getGenome().contains("W")) {
			  double d = Math.random();
			  if (d <= SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D) {
				  maternalGD = 'G';
			  }
			  else if (SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D < d && d < SimConfigs.homingFrequency * 0.5D + 0.5D) {
				  double randB = Math.random();
				  if (randB < 0.33D) {
					  maternalGD = 'R';
				  }
				  else {
					  maternalGD = 'L';
				  }
			  } else {
				  maternalGD = 'W';
			  }
		  } else if (mom.getGenome().contains("R")) {
			  double d = Math.random();
			  if (d < 0.5D) {
				  maternalGD = 'R';
			  } else {
	          maternalGD = 'G';
	          }
	      
	      } else {
	        /* this used to catch the case where mom was either GG or GL, which should be lethal
	         * for sex skew though, no one cares  
	         */
	    	  double d = Math.random();
		      if (d < 0.5D) {
		        
		        maternalGD = mom.getGenome().charAt(6);
		      }
		      else {
		        
		        maternalGD = mom.getGenome().charAt(7);
		      } 
	      }
		}
	    else {
	      
	      double d = Math.random();
	      if (d < 0.5D) {
	        maternalGD = mom.getGenome().charAt(6);
	      }
	      else {
	        maternalGD = mom.getGenome().charAt(7);
	      } 
	    } 
	    
	    char paternalGD = 'x';
	    if (dad.getGenome().contains("G")) {
	      
	      if (dad.getGenome().contains("W")) {
	        
	        double d = Math.random();
	        if (d <= SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D) {
	          
	          paternalGD = 'G';
	        }
	        else if (SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D < d && d < SimConfigs.homingFrequency * 0.5D + 0.5D) {
	          
	          double randB = Math.random();
	          if (randB < 0.33D)
	          {
	            paternalGD = 'R';
	          }
	          else
	          {
	            paternalGD = 'L';
	          }
	        
	        } else {
	          
	          paternalGD = 'W';
	        }
	      
	      } else if (dad.getGenome().contains("R")) {
	        
	        double d = Math.random();
	        if (d < 0.5D)
	        {
	          paternalGD = 'R';
	        }
	        else
	        {
	          paternalGD = 'G';
	        }
	      
	      } else {
	    	  /* this used to catch the case where mom was either GG or GL, which should be lethal
		       * for sex skew though, no one cares  
		       */
		  	  double d = Math.random();
		      if (d < 0.5D) {	        
			      paternalGD = dad.getGenome().charAt(6);
		      }
		      else {	        
			      paternalGD = dad.getGenome().charAt(7);
			  } 
	      }
	    
	    }
	    else {
	      
	      double d = Math.random();
	      if (d < 0.5D) {
	        
	        paternalGD = dad.getGenome().charAt(6);
	      }
	      else {
	        
	        paternalGD = dad.getGenome().charAt(7);
	      } 
	    } 
	    
	    /* check for maternal carryover. NOTE!! this is incorrect for homing frequencies < 1 and does not consider
	     * resistance alleles or lof alleles
	    */ 
	    if (maternalGD == 'W' && mom.getGenome().contains("G")) {
	    	// get probability W remains an W
	    	double rand = Math.random();
	    	if (rand < SimConfigs.maternalCarryoverFrequency) {
	    		maternalGD = 'G';
		    }
	    }
	    if (paternalGD == 'W' && mom.getGenome().contains("G")) {
	    	// get probability W remains an W
	    	double rand = Math.random();
	    	if (rand < SimConfigs.maternalCarryoverFrequency) {
	    		paternalGD = 'G';
		    }
	    }
	    
	    String childGenotypeGD = "gdmcss" + maternalGD + paternalGD;
	    baby.setGenome(childGenotypeGD);
	    
	    // add in a sex skew ! GG = male
	    if (childGenotypeGD.contains("W")) {
		    double rand = Math.random();
		    if (rand < 0.5D) {
		      baby.gender = "male";
		    } else {
			  baby.gender = "female";
		    } 
		  }
		  else {
			  baby.gender = "male";
		  }
		    
		  return baby;
  }
  
  public static SSGenomeCarps reproduceTrojan(SSGenomeCarps mom, SSGenomeCarps dad, SSGenomeCarps baby) {
    String moms = "N";
    String dads = "N";
    if (mom.getGenome().contains("TROJANS"))
    {
      moms = "T";
    }
    if (dad.getGenome().contains("TROJANS"))
    {
      dads = "T";
    }
    
    int nextgen = Math.max(mom.generation, dad.generation);
    if (nextgen >= 0) {
      
      baby.generation = nextgen + 1;
    }
    else {
      
      baby.generation = nextgen;
    } 
    
    if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
    {
      return null;
    }
    
    if (moms == "N" && dads == "N") {
      
      if (Math.random() < 0.5D) {
        baby.gender = "male";
      } else {
        baby.gender = "female";
      }  
      baby.setGenome(mom.getGenome());
      baby.makeWild();
    
    }
    else if (moms == "T" && dads == "N") {
      
      baby.gender = "male";
      if (Math.random() < 0.5D) {
        baby.setGenome(mom.getGenome());
      } else {
        
        baby.setGenome(mom.getGenome());
        baby.makeWild();
      
      }
    
    }
    else if (moms == "N" && dads == "T") {
      
      baby.gender = "male";
      baby.setGenome(mom.getGenome());
      baby.makeWild();
    
    }
    else if (moms == "T" && dads == "T") {
      
      baby.gender = "male";
      baby.setGenome(mom.getGenome());
    }
    else {
      
      System.out.println("ERROR! Bad TROJAN genotypes");
      System.exit(0);
    } 
    
    return baby;
  }
  
  public static SSGenomeCarps reproduceGeneral(SSGenomeCarps mom, SSGenomeCarps dad, SSGenomeCarps baby) {
    String babyGenome = "";
    String momGenome = mom.getGenome();
    String dadGenome = dad.getGenome();
    if (momGenome.length() != dadGenome.length()) {
      
      System.out.println("ERROR! mom and dad genotype different lengths... no babies being produced");
      return null;
    } 
    
    int nextgen = Math.max(mom.generation, dad.generation);
    if (nextgen >= 0) {
      
      baby.generation = nextgen + 1;
    }
    else {
      
      baby.generation = nextgen;
    } 
    
    if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
    {
      return null;
    }
    
    for (int i = 0; i < momGenome.length(); i += 2) {
      
      if (Math.random() < 0.5D) {
        
        babyGenome = babyGenome + momGenome.charAt(i);
      }
      else {
        
        babyGenome = babyGenome + momGenome.charAt(i + 1);
      } 
      
      if (Math.random() < 0.5D) {
        
        babyGenome = babyGenome + dadGenome.charAt(i);
      }
      else {
        
        babyGenome = babyGenome + dadGenome.charAt(i + 1);
      } 
    } 
    
    String babyGenomeMutated = "";
    for (int j = 0; j < momGenome.length(); j++) {
      
      if (babyGenome.charAt(j) == 'p' && Math.random() < SimConfigs.PromoterMutationFrequency) {
        
        babyGenomeMutated = babyGenomeMutated + 'P';
      }
      else if (babyGenome.charAt(j) == 'T' && Math.random() < SimConfigs.PTAMutationFrequency) {
        
        babyGenomeMutated = babyGenomeMutated + 't';
      }
      else if (babyGenome.charAt(j) == 'L' && Math.random() < SimConfigs.FLMutationFrequency) {
        
        babyGenomeMutated = babyGenomeMutated + 'l';
      }
      else {
        
        babyGenomeMutated = babyGenomeMutated + babyGenome.charAt(j);
      } 
    } 
    
    baby.setGenome(babyGenomeMutated);
    if (Math.random() < 0.5D) {
      baby.gender = "male";
    } else {
      baby.gender = "female";
    } 
    
    boolean viability = true;
    
    if (babyGenomeMutated.contains("p") && babyGenomeMutated.contains("T"))
    {
      viability = false;
    }
    if (babyGenomeMutated.contains("L") && baby.gender == "female")
    {
      viability = false;
    }
    
    if (viability)
    {
      return baby;
    }
    
    return null;
  }
  
  public static SSFLCarps reproduceSSFL(SSFLCarps mom, SSFLCarps dad, SSFLCarps baby) {
    char paternalT;
    int nextgen = Math.max(mom.generation, dad.generation);
    if (nextgen >= 0) {
      
      baby.generation = nextgen + 1;
    }
    else {
      
      baby.generation = nextgen;
    } 
    
    if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
    {
      return null;
    }
    
    char maternalP = 'x';
    double rand = Math.random();
    if (rand < 0.5D) {
      maternalP = mom.genotypeP.charAt(0);
    } else {
      maternalP = mom.genotypeP.charAt(1);
    } 
    if (maternalP == 'p') {
      
      rand = Math.random();
      if (rand < SimConfigs.PromoterMutationFrequency) {
        maternalP = 'P';
      } else {
        maternalP = 'p';
      } 
    } else {
      
      maternalP = 'P';
    } 
    
    char paternalP = 'x';
    rand = Math.random();
    if (rand < 0.5D) {
      paternalP = dad.genotypeP.charAt(0);
    } else {
      paternalP = dad.genotypeP.charAt(1);
    } 
    if (paternalP == 'p') {
      
      rand = Math.random();
      if (rand < SimConfigs.PromoterMutationFrequency) {
        paternalP = 'P';
      } else {
        paternalP = 'p';
      } 
    } else {
      paternalP = 'P';
    } 
    
    String childGenotypeP = "" + maternalP + paternalP;
    baby.genotypeP = childGenotypeP;
    
    char maternalT = 'x';
    rand = Math.random();
    if (rand < 0.5D) {
      maternalT = mom.genotypeT.charAt(0);
    } else {
      maternalT = mom.genotypeT.charAt(1);
    } 
    if (maternalT == 'T') {
      
      rand = Math.random();
      if (rand < SimConfigs.PTAMutationFrequency) {
        maternalT = 't';
      } else {
        maternalT = 'T';
      } 
    } else {
      maternalT = 't';
    } 
    
    rand = Math.random();
    if (rand < 0.5D) {
      paternalT = dad.genotypeT.charAt(0);
    } else {
      paternalT = dad.genotypeT.charAt(1);
    } 
    if (paternalT == 'T') {
      
      rand = Math.random();
      if (rand < SimConfigs.PTAMutationFrequency) {
        paternalT = 't';
      } else {
        paternalT = 'T';
      } 
    } else {
      paternalT = 't';
    } 
    String childGenotypeT = "" + maternalT + paternalT;
    baby.genotypeT = childGenotypeT;
    
    char maternalL = 'x';
    rand = Math.random();
    if (rand < 0.5D) {
      maternalL = mom.genotypeL.charAt(0);
    } else {
      maternalL = mom.genotypeL.charAt(1);
    } 
    if (maternalL == 'L') {
      
      rand = Math.random();
      if (rand < SimConfigs.FLMutationFrequency) {
        maternalL = 'l';
      } else {
        maternalL = 'L';
      } 
    } else {
      maternalL = 'l';
    } 
    char paternalL = 'x';
    rand = Math.random();
    if (rand < 0.5D) {
      paternalL = dad.genotypeL.charAt(0);
    } else {
      paternalL = dad.genotypeL.charAt(1);
    } 
    if (paternalL == 'L') {
      
      rand = Math.random();
      if (rand < SimConfigs.FLMutationFrequency) {
        paternalL = 'l';
      } else {
        paternalL = 'L';
      } 
    } else {
      paternalL = 'l';
    } 
    String childGenotypeL = "" + maternalL + paternalL;
    baby.genotypeL = childGenotypeL;
    
    rand = Math.random();
    if (rand < 0.5D) {
      baby.gender = "male";
    } else {
      baby.gender = "female";
    } 
    
    int viabilitySS = -1;
    int viabilityFL = -1;
    if (baby.genotypeP.contains("p") && baby.genotypeT.contains("T")) {
      viabilitySS = 0;
    } else {
      viabilitySS = 1;
    } 
    if (baby.genotypeL.contains("L") && baby.gender == "female") {
      viabilityFL = 0;
    } else {
      viabilityFL = 1;
    } 
    if (viabilitySS == 1 && viabilityFL == 1)
    {
      return baby;
    }
    
    return null;
  }
  
  public static GDCarps reproduceGD(GDCarps mom, GDCarps dad, GDCarps baby) {
    int nextgen = Math.max(mom.generation, dad.generation);
    if (nextgen >= 0) {
      
      baby.generation = nextgen + 1;
    }
    else {
      
      baby.generation = nextgen;
    } 
    
    if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
    {
      return null;
    }
    
    char maternalGD = 'x';
    if (mom.genotypeGD.contains("G")) {
      
      if (mom.genotypeGD.contains("W")) {
        
        double d = Math.random();
        
        if (d <= SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D) {
          
          maternalGD = 'G';
        }
        else if (SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D < d && d < SimConfigs.homingFrequency * 0.5D + 0.5D) {
          
          double randB = Math.random();
          if (randB < 0.33D)
          {
            maternalGD = 'R';
          }
          else
          {
            maternalGD = 'L';
          }
        
        } else {
          
          maternalGD = 'W';
        }
      
      } else if (mom.genotypeGD.contains("R")) {
        
        double d = Math.random();
        if (d < 0.5D)
        {
          maternalGD = 'R';
        }
        else
        {
          maternalGD = 'G';
        }
      
      } else {
        
        System.out.println("Fatal error: Mom has an inviable genotype");
        System.exit(0);
      }
    
    }
    else {
      
      double d = Math.random();
      if (d < 0.5D) {
        
        maternalGD = mom.genotypeGD.charAt(0);
      }
      else {
        
        maternalGD = mom.genotypeGD.charAt(1);
      } 
    } 
    
    char paternalGD = 'x';
    if (dad.genotypeGD.contains("G")) {
      
      if (dad.genotypeGD.contains("W")) {
        
        double d = Math.random();
        if (d <= SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D) {
          
          paternalGD = 'G';
        }
        else if (SimConfigs.homingFrequency * (1.0D - SimConfigs.NHEJfrequency) * 0.5D + 0.5D < d && d < SimConfigs.homingFrequency * 0.5D + 0.5D) {
          
          double randB = Math.random();
          if (randB < 0.33D)
          {
            paternalGD = 'R';
          }
          else
          {
            paternalGD = 'L';
          }
        
        } else {
          
          paternalGD = 'W';
        }
      
      } else if (dad.genotypeGD.contains("R")) {
        
        double d = Math.random();
        if (d < 0.5D)
        {
          paternalGD = 'R';
        }
        else
        {
          paternalGD = 'G';
        }
      
      } else {
        
        System.out.println("Fatal error: Dad has an inviable genotype");
        System.exit(0);
      }
    
    }
    else {
      
      double d = Math.random();
      if (d < 0.5D) {
        
        paternalGD = dad.genotypeGD.charAt(0);
      }
      else {
        
        paternalGD = dad.genotypeGD.charAt(1);
      } 
    } 
    
    String childGenotypeGD = "" + maternalGD + paternalGD;
    baby.genotypeGD = childGenotypeGD;
    
    double rand = Math.random();
    if (rand < 0.5D) {
      baby.gender = "male";
    } else {
      baby.gender = "female";
    } 
    
    int viabilityGD = -1;
    if (baby.genotypeGD.contains("W") || baby.genotypeGD.contains("R")) {
      viabilityGD = 1;
    } else {
      viabilityGD = 0;
    } 
    if (viabilityGD == 1)
    {
      return baby;
    }
    
    return null;
  }
  // Michelle starts editing here
  public static ASCarps reproduceAS(ASCarps mom, ASCarps dad, ASCarps baby) {
	  int nextgen = Math.max(mom.generation, dad.generation);
	  if (nextgen >= 0) {
	    
	    baby.generation = nextgen + 1;
	  }
	  else {
	      
	    baby.generation = nextgen;
	  } 
	    
	  if (baby.generation >= SimConfigs.GENE_GENERATION_DEATH)
	  {
	    return null;
	  }
	  
	  // get the number of sails in mom
	  int momSailCount = 0;
	  for (int i = 0; i < mom.genotypeAS.length(); i++) {
	  	char temp = mom.genotypeAS.charAt(i);
	  	if (temp == 'S') {
	  		momSailCount++;
	  	}
	  }
	  
	  // get the number of sails in dad
	  int dadSailCount = 0;
	  for (int i = 0; i < dad.genotypeAS.length(); i++) {
		  char temp = dad.genotypeAS.charAt(i);
	  	  if (temp == 'S') {
	  		  dadSailCount++;
	  	  }
	  }
	  
	  // initialize child genotype
	  String childGenotypeAS = "A";
	  
	  // for a genotype ANNFF, we need to get the N loci (1 & 2), and then the F loci (3 & 4)
	  for (int i = 1; i < mom.genotypeAS.length(); i += 2) {
		  char maternalAllele = 'x';
		  double rand = Math.random();
		  if (rand < 0.5D) {
	    	maternalAllele = mom.genotypeAS.charAt(0 + i);
		  }
		  else {
	    	maternalAllele = mom.genotypeAS.charAt(1 + i);
		  }
		  if (maternalAllele == 'F' && momSailCount != 0) {
	    	// get probability F remains an F
	    	double escapeProbability = Math.pow((1-SimConfigs.editingFrequency), momSailCount);
	    	rand = Math.random();
	   		if (rand > escapeProbability) {
	   			maternalAllele = 'M';
	    	}
		  }
	    	
		  char paternalAllele = 'x';
		  rand = Math.random();
		  if (rand < 0.5D) {
	    	paternalAllele = dad.genotypeAS.charAt(0 + i);
		  }
		  else {
	    	paternalAllele = dad.genotypeAS.charAt(1 + i);
		  }
		  if (paternalAllele == 'F' && dadSailCount != 0) {
	    	// get probability F remains an F
	   		double escapeProbability = Math.pow((1-SimConfigs.editingFrequency), dadSailCount);
	   		rand = Math.random();
	   		if (rand > escapeProbability) {
	   			paternalAllele = 'M';
    		}
	   	  }
	    	
		  childGenotypeAS = childGenotypeAS + maternalAllele + paternalAllele;
	    	
	  }
	    
	  baby.genotypeAS = childGenotypeAS;
	    
	  if (childGenotypeAS.contains("F")) {
	    double rand = Math.random();
	    if (rand < 0.5D) {
	      baby.gender = "male";
	    } else {
		  baby.gender = "female";
	    } 
	  }
	  else {
		  baby.gender = "male";
	  }
	    
	  return baby;

  }    
  // Michelle stops editing here
  
  public static void simulateFishGrowth() {
    for (SSCarps current : fishes) {
      
      if ((current.isAlive) & (current.age == 0))
      {
        
        current.length = 108.0D;
      }
      
      if ((current.isAlive) & (current.age == 1)) {
        
        current.length = 250.0D;
        continue;
      } 
      if ((current.isAlive) & (current.age > 1)) {
        
        double m = -0.21D - 4.13E-4D * current.getMyLakeDensity();
        double b = 167.39D + 0.093D * current.getMyLakeDensity();
        double increment = m * current.length + b;
        
        if (increment > 0.0D) {
          
          current.length += increment;
          
          continue;
        } 
        current.length += 0.0D;
      } 
    } 
  }
  
  public static void simulateFishAging() {
    for (SSCarps current : fishes) {
      
      if (current.isAlive)
      {
        current.age++;
      }
    } 
  }
  
  public static void initilizeAge2LengthMapFromFile(String fname) {
    age2lengthMap = new HashMap<>();
    try {
      Scanner file = new Scanner(new File(fname));
      while (file.hasNextLine()) {
        
        String[] lines = file.nextLine().split(",");
        int age = Integer.parseInt(lines[3]);
        double len = Double.parseDouble(lines[5]);
        if (!age2lengthMap.containsKey(Integer.valueOf(age)))
        {
          age2lengthMap.put(Integer.valueOf(age), new ArrayList<>());
        }
        ((ArrayList<Double>)age2lengthMap.get(Integer.valueOf(age))).add(Double.valueOf(len));
      } 
    } catch (FileNotFoundException e) {
      
      e.printStackTrace();
    } 
  }
  
  public static void writeBiomassData(int year) {
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      double FishMass = 0.0D;
      for (SSCarps current : fishes) {
        
        if (current.location.equals(SSLakeData.lakes[lake])) {
          FishMass += current.calcMass();
        }
      } 
      
      thisRun2.println("Year," + year + ",Lake," + (SSLakeData.lakes[lake]).lakeName + ",BioMass," + (FishMass / (SSLakeData.lakes[lake]).lakeArea) + ",winterkill, " + (SSLakeData.lakes[lake]).isWinterKilled);
    } 
  }
  
  public static void writeAbundanceData(int year) {
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      
      int FishCount = 0;
      
      for (SSCarps current : fishes) {
        
        if (current.location.equals(SSLakeData.lakes[lake]))
        {
          if (current.age > 0)
          {
            FishCount++;
          }
        }
      } 
      thisRun1.println("Year," + year + ",Lake," + (SSLakeData.lakes[lake]).lakeName + ",Number," + FishCount + " ,density, " + SSLakeData.carpDensity[lake]);
    } 
  }
  
  public static void writeGeneGeneration(int year) {
    HashMap[] arrayOfHashMap1 = new HashMap[300];
    HashMap[] arrayOfHashMap2 = new HashMap[300];
    HashMap[] arrayOfHashMap3 = new HashMap[300];
    int[] ages = new int[300];
    int FishCount = 0;
    
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      int i;
      
      for (i = 0; i < ages.length; i++) {
        
        ages[i] = 0;
        arrayOfHashMap1[i] = new HashMap<>();
        arrayOfHashMap2[i] = new HashMap<>();
        arrayOfHashMap3[i] = new HashMap<>();
      } 
      
      for (SSCarps current : fishes) {
        
        if (current.location.equals(SSLakeData.lakes[lake])) {
          
          ages[current.age] = ages[current.age] + 1;
          
          String type = Integer.toString(current.generation);
          
          if (!arrayOfHashMap1[current.age].containsKey(type)) {
            
            arrayOfHashMap1[current.age].put(type, Integer.valueOf(1));
            arrayOfHashMap2[current.age].put(type, Integer.valueOf(0));
            arrayOfHashMap3[current.age].put(type, Integer.valueOf(0));
          }
          else {
            
            arrayOfHashMap1[current.age].put(type, Integer.valueOf(((Integer)arrayOfHashMap1[current.age].get(type)).intValue() + 1));
          } 
          
          if (current.gender == "male") {
            
            arrayOfHashMap2[current.age].put(type, Integer.valueOf(((Integer)arrayOfHashMap2[current.age].get(type)).intValue() + 1));
            
            continue;
          } 
          arrayOfHashMap3[current.age].put(type, Integer.valueOf(((Integer)arrayOfHashMap3[current.age].get(type)).intValue() + 1));
        } 
      } 
      
      for (i = 0; i < ages.length; i++) {
        
        FishCount += ages[i];
        if (ages[i] > 0) {
          
          thisRun3.print("Run," + currentRun + ",Year," + year + ",Lake," + (SSLakeData.lakes[lake]).lakeName + ",Age," + i + ",Number," + ages[i]);
          thisRun3.print("---generation:");
          for (Object s : arrayOfHashMap1[i].keySet())
        	  // changed from "String s" to "Object s"
          {
            thisRun3.print(s + "=" + arrayOfHashMap1[i].get(s) + " (males=" + arrayOfHashMap2[i].get(s) + "/females=" + arrayOfHashMap3[i].get(s) + ")__");
          }
          thisRun3.println();
        } 
      } 
    } 
  }
  
  public static void writeData(int year) {
    HashMap[] arrayOfHashMap1 = new HashMap[300];
    HashMap[] arrayOfHashMap2 = new HashMap[300];
    HashMap[] arrayOfHashMap3 = new HashMap[300];
    int[] ages = new int[300];
    int FishCount = 0;
    
    for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
      int i;
      
      for (i = 0; i < ages.length; i++) {
        
        ages[i] = 0;
        arrayOfHashMap1[i] = new HashMap<>();
        arrayOfHashMap2[i] = new HashMap<>();
        arrayOfHashMap3[i] = new HashMap<>();
      } 
      
      for (SSCarps current : fishes) {
        
        if (current.location.equals(SSLakeData.lakes[lake])) {
          
          ages[current.age] = ages[current.age] + 1;
          
          String type = "wild";
          if (current instanceof SSFLCarps)
          {
            type = ((SSFLCarps)current).genotypeP + ((SSFLCarps)current).genotypeT + ((SSFLCarps)current).genotypeL;
          }
          if (current instanceof GDCarps)
          {
            type = ((GDCarps)current).genotypeGD;
          }
          if (current instanceof SSGenomeCarps)
          {
            type = ((SSGenomeCarps)current).getGenome();
          }
          if (current instanceof ASCarps)
          {
            type = ((ASCarps)current).genotypeAS;
          }
          
          if (!arrayOfHashMap1[current.age].containsKey(type)) {
            
            arrayOfHashMap1[current.age].put(type, Integer.valueOf(1));
            arrayOfHashMap2[current.age].put(type, Integer.valueOf(0));
            arrayOfHashMap3[current.age].put(type, Integer.valueOf(0));
          }
          else {
            
            arrayOfHashMap1[current.age].put(type, Integer.valueOf(((Integer)arrayOfHashMap1[current.age].get(type)).intValue() + 1));
          } 
          
          if (current.gender == "male") {
            
            arrayOfHashMap2[current.age].put(type, Integer.valueOf(((Integer)arrayOfHashMap2[current.age].get(type)).intValue() + 1));
            
            continue;
          } 
          arrayOfHashMap3[current.age].put(type, Integer.valueOf(((Integer)arrayOfHashMap3[current.age].get(type)).intValue() + 1));
        } 
      } 
      
      for (i = 0; i < ages.length; i++) {
        
        FishCount += ages[i];
        if (ages[i] > 0) {
          
          thisRun.print("Run," + currentRun + ",Year," + year + ",Lake," + (SSLakeData.lakes[lake]).lakeName + ",Age," + i + ",Number," + ages[i]);
          thisRun.print("---types:");
          for (Object s : arrayOfHashMap1[i].keySet())
        	  // changed above from "String s" to "Object s"
          {
            thisRun.print(s + "=" + arrayOfHashMap1[i].get(s) + " (males=" + arrayOfHashMap2[i].get(s) + "/females=" + arrayOfHashMap3[i].get(s) + ")__");
          }
          thisRun.println();
        } 
      } 
    } 
  }
  
  public static int factorial(int n) {
    int prod = 1;
    for (int i = n; i > 0; i--)
    {
      prod *= i;
    }
    
    return prod;
  }
  
  public static int nChooseK(int n, int k) {
    return factorial(n) / factorial(n - k) * factorial(k);
  }
  
  public static void stopWatch(String description) {
    if (debugTime) {
      
      System.out.println(description + " took " + ((System.currentTimeMillis() - lastTime) / 1000.0D) + "s");
      lastTime = System.currentTimeMillis();
    } 
  }
}


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSCarp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */