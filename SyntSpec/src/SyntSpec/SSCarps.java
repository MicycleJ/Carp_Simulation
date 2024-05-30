/*     */ package SyntSpec;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ public class SSCarps
/*     */ {
/*     */   static final int NO_GENERATION = -2147483648;
/*  20 */   static final double WINTERKILL_MORTALITY = Math.random() * 0.04600000000000004D + 0.954D;
/*     */   
/*     */   static final double SEINING_MORTALITY = 0.25D;
/*     */   
/*  25 */   static final double RETURN_PROBABILITY = Math.random() * 0.508D + 0.38D;
/*     */   int age;
/*     */   double weight;
/*     */   double length;
/*     */   SSLake location;
/*     */   
/*     */   String name;
/*     */   
/*     */   boolean isAlive = true;
/*     */   
/*     */   SSLake dispersalLake;
/*     */   
/*     */   SSLake bornLake;
/*     */   
/*     */   boolean isSS;
/*     */   
/*     */   String gender;
/*     */   
/*     */   int generation;
/*     */   
/*     */   static final double lambda = 0.13D;
/*     */   
/*     */   public SSCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
/* 101 */     this.name = newName;
/* 102 */     this.age = newAge;
/* 103 */     this.location = newLocation;
/* 104 */     this.bornLake = newLocation;
/* 105 */     this.length = 120.0D;
/* 106 */     this.isSS = ss;
/* 107 */     this.gender = fgender;
/* 108 */     this.generation = Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public SSCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender, String genome, int gen) {
/* 113 */     this.name = newName;
/* 114 */     this.age = newAge;
/* 115 */     this.location = newLocation;
/* 116 */     this.bornLake = newLocation;
/* 117 */     this.length = 120.0D;
/* 118 */     this.isSS = ss;
/* 119 */     this.gender = fgender;
/* 120 */     this.generation = gen;
/*     */   }
/*     */   
/*     */   public SSCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender, String genome) {
/* 127 */     this.name = newName;
/* 128 */     this.age = newAge;
/* 129 */     this.location = newLocation;
/* 130 */     this.bornLake = newLocation;
/* 131 */     this.length = 120.0D;
/* 132 */     this.isSS = ss;
/* 133 */     this.gender = fgender;
/*     */   }
/*     */   
/*     */   public SSCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
/* 158 */     this.name = newName;
/* 159 */     this.age = newAge;
/* 160 */     this.location = newLocation;
/* 161 */     this.bornLake = bornLake;
/* 162 */     this.dispersalLake = dispersal;
/* 163 */     this.length = length;
/* 164 */     this.isSS = ss;
/* 165 */     this.gender = fgender;
/* 166 */     this.generation = Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public SSCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String genome) {
/* 171 */     this.name = newName;
/* 172 */     this.age = newAge;
/* 173 */     this.location = newLocation;
/* 174 */     this.bornLake = bornLake;
/* 175 */     this.dispersalLake = dispersal;
/* 176 */     this.length = length;
/* 177 */     this.isSS = ss;
/* 178 */     this.gender = fgender;
/*     */     
/* 180 */     this.generation = Integer.MIN_VALUE;
/*     */   }
/*     */   
/*     */   public boolean isAlive() {
/* 186 */     return isAlive();
/*     */   }
/*     */   
/*     */   public boolean isMature() {
/* 192 */     double reproductive = 1.0D / (1.0D + Math.exp(-1.0D * (-199.517D + 0.5796D * this.length)));
/*     */     
/* 196 */     String x = this.name + Double.toString(this.length);
/* 197 */     Random rng = new Random(x.hashCode());
/*     */     
/* 199 */     return (rng.nextDouble() < reproductive);
/*     */   }
/*     */   
/*     */   public static int countAdults() {
/* 212 */     int adultCount = 0;
/* 213 */     for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
/*     */       
/* 215 */       adultCount = 0;
/*     */       
/* 219 */       for (SSCarps current : SSCarp.fishes) {
/*     */         
/* 221 */         if (current.location.equals(SSLakeData.lakes[lake]))
/*     */         {
/* 223 */           if (current.age > 0)
/*     */           {
/* 225 */             adultCount++;
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 232 */     return adultCount;
/*     */   }
/*     */   
/*     */   public static int countSSMales() {
/* 238 */     int SSMaleCount = 0;
/* 239 */     for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
/*     */       
/* 241 */       SSMaleCount = 0;
/*     */       
/* 243 */       for (SSCarps current : SSCarp.fishes) {
/*     */         
/* 245 */         if (current.location.equals(SSLakeData.lakes[lake]))
/*     */         {
/* 247 */           if (current.isSS)
/*     */           {
/* 249 */             if (current.gender == "male" && current.age > 2)
/*     */             {
/*     */               
/* 252 */               SSMaleCount++;
/*     */             }
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 260 */     return SSMaleCount;
/*     */   }
/*     */   
/*     */   public double calcMass() {
/* 271 */     return 6.0D * Math.pow(10.0D, -5.0D) * Math.pow(this.length, 2.763D) / 1000.0D;
/*     */   }
/*     */   
/*     */   public ArrayList<SSCarps> tryReproduce() {
/* 278 */     ArrayList<SSCarps> babies = new ArrayList<>();
/*     */     
/* 281 */     int survivedBabies = juvenileSurvival();
/*     */     
/* 285 */     for (int i = 0; i < survivedBabies; i++) {
/*     */       
/* 287 */       if (Math.random() < SimConfigs.MALE_OFFSPRING_PROBABILITY) {
/*     */         
/* 289 */         babies.add(new FLCarps("Fish_" + SSCarp.ID++, 0, this.location, false, "male"));
/*     */       }
/*     */       else {
/*     */         
/* 293 */         babies.add(new FLCarps("Fish_" + SSCarp.ID++, 0, this.location, false, "female"));
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     return babies;
/*     */   }
/*     */   
/*     */   public double getMyLakeDensity() {
/* 307 */     double stock = 0.0D;
/*     */     
/* 309 */     for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
/* 310 */       if (this.location.equals(SSLakeData.lakes[lake])) {
/* 311 */         stock = SSLakeData.carpDensity[lake];
/*     */       }
/*     */     } 
/* 314 */     return stock;
/*     */   }
/*     */   
/*     */   public double getMyAdultLakeDensity() {
/* 319 */     double stock = 0.0D;
/*     */     
/* 321 */     for (int lake = 0; lake < SSLakeData.lakes.length; lake++) {
/* 322 */       if (this.location.equals(SSLakeData.lakes[lake])) {
/* 323 */         stock = SSLakeData.adultCarpDensity[lake];
/*     */       }
/*     */     } 
/*     */     
/* 328 */     return stock;
/*     */   }
/*     */   
/*     */   public int juvenileSurvival() {
/* 333 */     int survived = 0;
/* 334 */     double lethality = 1.0D;
/* 335 */     double stock = getMyAdultLakeDensity();
/*     */     
/* 339 */     survived = (int)(lethality * 106.6D * 3.9228D * Math.exp(-0.01D * stock));
/*     */     
/* 358 */     return 2 * survived;
/*     */   }
/*     */   
/*     */   public void tryReturn() {
/* 367 */     double rand = Math.random();
/* 368 */     if (rand < RETURN_PROBABILITY && this.dispersalLake != null)
/*     */     {
/* 370 */       this.location = this.dispersalLake;
/*     */     }
/*     */   }
/*     */   
/*     */   public void tryMove() {
/* 376 */     double move = Math.random();
/*     */     
/* 378 */     double prob = 0.0D;
/* 379 */     if (this.location != null && this.dispersalLake != null) {
/*     */       
/* 381 */       double[] movementProbabilites = SSLakeData.getMovementProbabilities(this.bornLake, this.dispersalLake, this.location);
/*     */       
/* 383 */       for (int i = 0; i < movementProbabilites.length; i++) {
/*     */         
/* 385 */         prob += movementProbabilites[i];
/* 386 */         if (move < prob) {
/*     */           
/* 388 */           this.location = SSLakeData.lakes[i];
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tryDispersal() {
/* 398 */     double rand = Math.random();
/*     */     
/* 400 */     if (this.dispersalLake == null)
/*     */     {
/*     */       
/* 403 */       if (this.age == 0 && rand < 0.0016D) {
/*     */         
/* 405 */         doDispersal();
/*     */       }
/* 407 */       else if (this.age == 1 && rand < 0.0016D) {
/*     */         
/* 409 */         doDispersal();
/*     */       }
/* 411 */       else if (this.age >= 2 && rand < 0.5D) {
/*     */         
/* 413 */         doDispersal();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void doDispersal() {
/* 424 */     double move = Math.random();
/*     */     
/* 426 */     double prob = 0.0D;
/* 427 */     if (this.location != null) {
/*     */       
/* 437 */       double[] movementProbabilites = { 0.0D, 1.0D };
/*     */       
/* 440 */       for (int i = 0; i < movementProbabilites.length; i++) {
/*     */         
/* 442 */         prob += movementProbabilites[i];
/* 443 */         if (move < prob) {
/*     */           
/* 445 */           this.location = SSLakeData.lakes[i];
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 452 */     this.dispersalLake = this.location;
/*     */   }
/*     */   
/*     */   public void winterkillMortality() {
/* 475 */     double rand = Math.random();
/*     */     
/* 477 */     if (rand < WINTERKILL_MORTALITY)
/*     */     {
/* 479 */       this.isAlive = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void seiningMortality() {
/* 487 */     double rand = Math.random();
/*     */     
/* 490 */     if (rand < SimConfigs.SeiningRemovePercent)
/*     */     {
/*     */       
/* 493 */       this.isAlive = false;
/*     */     }
/*     */   }
/*     */   
/*     */   public void tryDies() {
/* 505 */     double Linf = 440.0D;
/*     */     
/* 507 */     for (SSCarps current : SSCarp.fishes) {
/*     */       
/* 512 */       double c = current.length / Linf;
/* 513 */       double mort = Math.random();
/* 514 */       double check = mort;
/* 515 */       double instmortality = 0.06D * Math.pow(c, -1.5D);
/* 516 */       double percentmortality = 1.0D - Math.exp(-instmortality);
/* 517 */       double check2 = percentmortality;
/* 518 */       System.out.println("Mortality = " + check2 + " random number = " + check);
/* 519 */       if (mort < percentmortality) {
/*     */         
/* 521 */         this.isAlive = false;
/* 522 */         System.out.println(" alive = " + this.isAlive);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isWild() {
/* 530 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */