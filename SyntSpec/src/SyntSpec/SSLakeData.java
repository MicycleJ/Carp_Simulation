/*     */ package SyntSpec;
/*     */ public class SSLakeData
/*     */ {
/*  13 */   static double[] carpDensity = new double[2];
/*  14 */   static double[] adultCarpDensity = new double[2];
			// TODO take in winterprob from simconfigs, don't default to 0.0
			// TODO make lake area also configurable
/*  15 */   static SSLake[] lakes = new SSLake[] { new SSLake("Marsh", 0.0D, 100.0D), new SSLake("Susan", 0.0D, 100.0D) };
/*     */   
/*     */   public static void countDensity() {
/*  28 */     int[] carpCount = new int[lakes.length];
/*  29 */     for (SSCarps current : SSCarp.fishes) {
/*     */       
/*  36 */       for (int i = 0; i < lakes.length; i++) {
/*     */         
/*  38 */         if (lakes[i].equals(current.location) && current.age > 1) {
/*  39 */           carpCount[i] = carpCount[i] + 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  48 */     for (int lake = 0; lake < lakes.length; lake++) {
/*  49 */       carpDensity[lake] = carpCount[lake] / (lakes[lake]).lakeArea;
/*     */     }
/*     */   }
/*     */   
/*     */   public static void countAdultDensity() {
/*  55 */     int[] adultCarpCount = new int[lakes.length];
/*     */     
/*  57 */     for (SSCarps current : SSCarp.fishes) {
/*     */       
/*  59 */       for (int i = 0; i < lakes.length; i++) {
/*     */         
/*  61 */         if (lakes[i].equals(current.location) && current.age > 2) {
/*  62 */           adultCarpCount[i] = adultCarpCount[i] + 1;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  68 */     for (int lake = 0; lake < lakes.length; lake++) {
/*  69 */       adultCarpDensity[lake] = adultCarpCount[lake] / (lakes[lake]).lakeArea;
/*     */     }
/*     */   }
/*     */   
/*     */   public static double[] getMovementProbabilities(SSLake born, SSLake dispersal, SSLake location) {
/* 112 */     if (born.equals(lakes[0]) && dispersal.equals(lakes[1]) && location.equals(lakes[0]))
/*     */     {
/* 114 */       return new double[] { 1.0D, 0.0D };
/*     */     }
/* 116 */     if (born.equals(lakes[0]) && dispersal.equals(lakes[1]) && location.equals(lakes[1]))
/*     */     {
/* 118 */       return new double[] { 0.3D, 0.7D };
/*     */     }
/*     */     
/* 142 */     if (born.equals(lakes[0]) && dispersal.equals(lakes[0]) && location.equals(lakes[0]))
/*     */     {
/* 144 */       return new double[] { 1.0D, 0.0D };
/*     */     }
/* 146 */     if (born.equals(lakes[0]) && dispersal.equals(lakes[0]) && location.equals(lakes[1]))
/*     */     {
/*     */       
/* 149 */       return new double[] { 0.3D, 0.7D };
/*     */     }
/*     */     
/* 158 */     if (born.equals(lakes[1]) && dispersal.equals(lakes[0]) && location.equals(lakes[0]))
/*     */     {
/* 160 */       return new double[] { 1.0D, 0.0D };
/*     */     }
/* 162 */     if (born.equals(lakes[1]) && dispersal.equals(lakes[0]) && location.equals(lakes[1]))
/*     */     {
/*     */       
/* 165 */       return new double[] { 0.3D, 0.7D };
/*     */     }
/*     */     
/* 171 */     if (born.equals(lakes[1]) && dispersal.equals(lakes[1]) && location.equals(lakes[0]))
/*     */     {
/* 173 */       return new double[] { 1.0D, 0.0D };
/*     */     }
/* 175 */     if (born.equals(lakes[1]) && dispersal.equals(lakes[1]) && location.equals(lakes[1]))
/*     */     {
/*     */       
/* 178 */       return new double[] { 0.3D, 0.7D };
/*     */     }
/*     */     
/* 364 */     System.out.println("Warning! Invalid movment probability");
/* 365 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSLakeData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */