/*    */ package SyntSpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GDCarps
/*    */   extends SSCarps
/*    */ {
/*    */   String genotypeGD;
/*    */   
/*    */   public GDCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
/* 14 */     super(newName, newAge, newLocation, ss, fgender);
/* 15 */     this.genotypeGD = "WW";
/*    */   }
/*    */ 
/*    */   
/*    */   public GDCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
/* 20 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 21 */     this.genotypeGD = "WW";
/*    */   }
/*    */ 
/*    */   
/*    */   public GDCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
/* 26 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 27 */     this.genotypeGD = gt;
/* 28 */     this.generation = gen;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GDCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
/* 34 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 35 */     this.genotypeGD = gt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GDCarps(SSCarps o) {
/* 41 */     super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
/* 42 */     this.genotypeGD = "WW";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isWild() {
/* 48 */     return this.genotypeGD.equals("WW");
/*    */   }
/*    */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\GDCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */