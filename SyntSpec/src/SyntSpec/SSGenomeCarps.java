/*    */ package SyntSpec;
/*    */ 
/*    */ public class SSGenomeCarps
/*    */   extends SSCarps
/*    */ {
/*    */   private String genome;
/*    */   
/*    */   public SSGenomeCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
/*  9 */     super(newName, newAge, newLocation, ss, fgender);
/* 10 */     this.genome = "";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public SSGenomeCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
/* 16 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 17 */     this.genome = "";
/*    */   }
/*    */ 
/*    */   
/*    */   public SSGenomeCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
/* 22 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 23 */     this.genome = gt;
/* 24 */     this.generation = gen;
/*    */   }
/*    */ 
/*    */   
/*    */   public SSGenomeCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
/* 29 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 30 */     this.genome = gt;
/*    */   }
/*    */ 
/*    */   
/*    */   public SSGenomeCarps(SSCarps o) {
/* 35 */     super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
/* 36 */     this.genome = "";
/*    */   }
/*    */ 
/*    */   
/*    */   public SSGenomeCarps(SSGenomeCarps o) {
/* 41 */     super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender, o.genome);
/*    */   }
/*    */ 
/*    */   
/*    */   public void makeWild() {
/* 46 */     this.genome = wildGenome(this.genome);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGenome() {
/* 51 */     return this.genome;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGenome(String newG) {
/* 56 */     this.genome = newG;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGenome(String letters) {
/* 61 */     return this.genome.replaceAll("[^" + letters + "]", "");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGenomeWithout(String letters) {
/* 66 */     return this.genome.replaceAll("[" + letters + "]", "");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isWild() {
/* 72 */     return this.genome.equals(wildGenome(this.genome));
/*    */   }
/*    */ 
/*    */   
/*    */   public String wildGenome(String in) {
/* 77 */     return in.toLowerCase();
/*    */   }
/*    */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSGenomeCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */