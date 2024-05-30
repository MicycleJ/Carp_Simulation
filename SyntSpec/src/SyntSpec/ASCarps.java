/*    */ package SyntSpec;
/*    */ 
/*    */ 
/*    */ public class ASCarps
/*    */   extends SSCarps
/*    */ {
/*    */   String genotypeAS;
/*    */   /* Genotype ANNFF = type (allele sail), no sail, no sail, wt, wt
 *            Genotype ASSMM = type (allele sail), sail, sail, male-causing, male-causing
 */
/*    */
/*    */   public ASCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
/* 14 */     super(newName, newAge, newLocation, ss, fgender);
/* 15 */     this.genotypeAS = "ANNFF";
/*    */   }
/*    */ 
/*    */   
/*    */   public ASCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
/* 20 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 21 */     this.genotypeAS = "ANNFF";
/*    */   }
/*    */ 
/*    */   
/*    */   public ASCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
/* 26 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 27 */     this.genotypeAS = gt;
/* 28 */     this.generation = gen;
/*    */   }
/*    */ 
/*    */   
/*    */   public ASCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
/* 34 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 35 */     this.genotypeAS = gt;
/*    */   }
/*    */ 
/*    */   
/*    */   public ASCarps(SSCarps o) {
/* 41 */     super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
/* 42 */     this.genotypeAS = "ANNFF";
/*    */   }
/*    */ 
/*    */   public boolean isWild() {
/* 48 */     return this.genotypeAS.equals("ANNFF");
/*    */   }
/*    */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\GDCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */