/*    */ package SyntSpec;
/*    */ public class SSFLCarps
/*    */   extends SSCarps
/*    */ {
/*    */   String genotypeP;
/*    */   String genotypeT;
/*    */   String genotypeL;
/*    */   
/*    */   public SSFLCarps(String newName, int newAge, SSLake newLocation, boolean ss, String fgender) {
/* 17 */     super(newName, newAge, newLocation, ss, fgender);
/* 18 */     this.genotypeP = "pp";
/* 19 */     this.genotypeT = "tt";
/* 20 */     this.genotypeL = "ll";
/*    */   }
/*    */   
/*    */   public SSFLCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender) {
/* 26 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 27 */     this.genotypeP = "pp";
/* 28 */     this.genotypeT = "tt";
/* 29 */     this.genotypeL = "ll";
/*    */   }
/*    */   
/*    */   public SSFLCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt) {
/* 34 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 35 */     this.genotypeP = gt.substring(0, 2);
/* 36 */     this.genotypeT = gt.substring(2, 4);
/* 37 */     this.genotypeL = gt.substring(4, 6);
/*    */   }
/*    */   
/*    */   public SSFLCarps(String newName, int newAge, double length, SSLake newLocation, SSLake bornLake, SSLake dispersal, boolean ss, String fgender, String gt, int gen) {
/* 42 */     super(newName, newAge, length, newLocation, bornLake, dispersal, ss, fgender);
/* 43 */     this.genotypeP = gt.substring(0, 2);
/* 44 */     this.genotypeT = gt.substring(2, 4);
/* 45 */     this.genotypeL = gt.substring(4, 6);
/* 46 */     this.generation = gen;
/*    */   }
/*    */   
/*    */   public SSFLCarps(SSCarps o) {
/* 51 */     super(o.name, o.age, o.length, o.location, o.bornLake, o.dispersalLake, o.isSS, o.gender);
/* 52 */     this.genotypeP = "pp";
/* 53 */     this.genotypeT = "tt";
/* 54 */     this.genotypeL = "ll";
/*    */   }
/*    */   
/*    */   public boolean isWild() {
/* 60 */     return (this.genotypeP.equals("pp") && this.genotypeT.equals("tt") && this.genotypeL.equals("ll"));
/*    */   }
/*    */ }


/* Location:              C:\Users\mljoh\Downloads\Default Model (Smanski Carp)\SyntSpec.jar!\SyntSpec\SSFLCarps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */